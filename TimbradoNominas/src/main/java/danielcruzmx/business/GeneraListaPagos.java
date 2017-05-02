package danielcruzmx.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import danielcruzmx.data.ConceptoPagado;
import danielcruzmx.data.HistoricoPago;
import danielcruzmx.domain.ConceptoDeduccion;
import danielcruzmx.domain.ConceptoOtrosPagos;
import danielcruzmx.domain.ConceptoPercepcion;
import danielcruzmx.domain.Constantes;
import danielcruzmx.domain.DatosXML;
import danielcruzmx.service.ConceptoPagadoService;
import danielcruzmx.service.ConstantesService;
import danielcruzmx.service.HistoricoPagoService;

public class GeneraListaPagos {
	
	private List <HistoricoPago>    listaPagos;
	private List <ConceptoPagado>   listaConceptosPago;
	private List <DatosXML> 		listaDatos;
	private Constantes              constantes;

	public GeneraListaPagos(Integer idNomina){
	    setListaPagos(new HistoricoPagoService().NOMINAfind(idNomina));
		setListaConceptosPago(new ConceptoPagadoService().NOMINAfolio(idNomina));
		setConstantes(new ConstantesService().getConstantes());
		listaDatos = new ArrayList<DatosXML>();
	}
	
	public List <DatosXML> getListaDatos() {
		//
		//   Genera la lista de datos que llenan el comprobante
		//
		DatosXML dato;
		
    	for(HistoricoPago pago: listaPagos){
    		dato = new DatosXML();
    		dato.setVersion(constantes.getVersionComprobante());
    		dato.setSerie(pago.getIdNomina().toString());
    		dato.setFolio(pago.getFolio().toString() + "_" + pago.getHpDifFolio());
    		dato.setFecha(new Date());
    		
    		if(pago.getBanco().equals("000")){
    			dato.setFormaPago("02");
    		} else {
    		    dato.setFormaPago("03");
    		}
    		
    		dato.setNoCertificado(constantes.getNumCertificado());
    		dato.setMoneda("MXN");
    		//dato.setTipoDeComprobante(constantes.getTipoComprobante());
    		dato.setMetodoDePago(constantes.getMetodoPago());
    		dato.setLugarExpedicion(constantes.getLugarExpedicion());
    		dato.setRfcEmisor(constantes.getRfcEmisor());
    		dato.setNombreEmisor(constantes.getNombreEmisor());
    		dato.setRegimen(constantes.getRegimenFiscal());
    		dato.setRfcReceptor(pago.getRfcEmpleado());
    		dato.setNombreReceptor(this.nombreCompleto(pago));
    		dato.setUsoCfdi(constantes.getUsoCfdi());
    		dato.setCantidad(new BigDecimal(constantes.getCantidad()));
    		dato.setClaveProdServ(constantes.getCveProdServ());
    		dato.setCveUnidad(constantes.getCveUnidad());
    		dato.setCertificado(constantes.getCertificado());
    		dato.setDescripcionConcepto(constantes.getDescripcionConcepto());
    		dato.setRegistroPatronal(constantes.getRegistroPatronal());
    		dato.setVersionNomina(constantes.getVersionNomina());
    		dato.setFechaPago(pago.getFecPago());
    		dato.setFechaInicial(pago.getFecIniPago());
    		dato.setFechaFinal(pago.getFecFinPago());
    		dato.setNumDiasPagados(new BigDecimal(pago.getDiasPagados()));
    		dato.setCurp(pago.getCurpEmpleado());
    		dato.setNss(pago.getNssEmpleado());
    		dato.setFechaInicioRelLab(pago.getFecIngreso());
    		dato.setAntiguedad(pago.getAntiguedad().toString());
    		dato.setTipoContrato(pago.getTipoContrata());
    		dato.setTipoJornada(pago.getJornada());
    		dato.setTipoRegimen(pago.getTipoReg().toString());
    		dato.setNumEmpleado(pago.getRfcEmpleado());
    		dato.setDepartamento(pago.getDepto());
    		dato.setPuesto(pago.getPuesto());
    		dato.setRiesgoPuesto(pago.getRiesgo().toString());
    		dato.setTipoNomina(pago.getTipoNomina());
    		dato.setPeriodicidadPago(pago.getPeriodicidad());
    		dato.setBanco(pago.getBanco());
    		
    		List <ConceptoPagado> cptos = this.obtCptos(pago.getFolio(), pago.getHpDifFolio());
    		
    		dato.setPercepciones(this.obtPercepciones(cptos, dato));
    		dato.setDeducciones(this.obtDeducciones(cptos, dato));
    		dato.setOtrosPagos(this.obtOtrosPagos(cptos, dato));
    		
    		dato.setTotalPercepciones(dato.getTotalSueldos());
    		dato.setTotalDeducciones(dato.getTotalOtrasDeducciones().add(dato.getTotalImpuestosRetenidos()));
    		dato.setSubTotal(dato.getTotalPercepciones().add(dato.getTotalOtrosPagos()));
    		dato.setDescuento(dato.getTotalOtrasDeducciones());
    		dato.setTotal(dato.getSubTotal().subtract(dato.getDescuento().subtract(dato.getTotalImpuestosRetenidos())));
    		dato.setValorUnitario(dato.getTotalPercepciones().add(dato.getTotalOtrosPagos()));
    		dato.setImporte(dato.getTotalPercepciones().add(dato.getTotalOtrosPagos()));
    		    		    	
    		listaDatos.add(dato);
    	}
		return listaDatos;
	}

	private String nombreCompleto(HistoricoPago emp)
	{
    	return emp.getPrimerApellido().trim().replace(".", " ") + " " + 
               emp.getSegundoApellido().trim().replace(".", " ") + " " + 
    		   emp.getNombreEmpleado().trim().replace(".", " ");  
    }
	
	private List<ConceptoPercepcion> obtPercepciones(List <ConceptoPagado> cptos, DatosXML dato)
	{
		List<ConceptoPercepcion> resultados = new ArrayList<ConceptoPercepcion>();
		
		BigDecimal totPer = new BigDecimal(0);
		BigDecimal totGra = new BigDecimal(0);
		BigDecimal totExe = new BigDecimal(0);
		
		for(ConceptoPagado cpto: cptos){
			if(cpto.getTipoCpto().equals("P") && (
					cpto.getTieneISr().equals("E") || cpto.getTieneISr().equals("S"))){		
				totPer = totPer.add(cpto.getMonto());
				ConceptoPercepcion per = new ConceptoPercepcion();
				per.setClaveCptoMiNomina(cpto.getCptoPago());
				per.setCptoDescMiNomina(cpto.getDescripcion());
				per.setTipoSAT(cpto.getCveSat());
				per.setImporteGravado(new BigDecimal(0));
				per.setImporteExcento(new BigDecimal(0));
				if(cpto.getTieneISr().equals("S")){
					per.setImporteGravado(cpto.getMonto());
					totGra = totGra.add(cpto.getMonto());
				} else {
					per.setImporteExcento(cpto.getMonto());
					totExe = totExe.add(cpto.getMonto());
				}
				resultados.add(per);
			}	
		}
		dato.setTotalSueldos(totGra.add(totExe));
		dato.setTotalGravado(totGra);
		dato.setTotalExento(totExe);
		return resultados;
	}
	
	private List<ConceptoDeduccion> obtDeducciones(List <ConceptoPagado> cptos, DatosXML dato)
	{
		List<ConceptoDeduccion> resultados = new ArrayList<ConceptoDeduccion>();
		
		BigDecimal totDed = new BigDecimal(0);
		BigDecimal totImp = new BigDecimal(0);
		
		for(ConceptoPagado cpto: cptos){
			if(cpto.getTipoCpto().equals("D")){		
				ConceptoDeduccion ded = new ConceptoDeduccion();
				ded.setClaveCptoMiNomina(cpto.getCptoPago());
				ded.setCptoDescMiNomina(cpto.getDescripcion());
				ded.setTipoSAT(cpto.getCveSat());
				ded.setImporte(cpto.getMonto());
				if(cpto.getTieneISr().equals("I")){
					totImp = totImp.add(cpto.getMonto());
				} else {
					totDed = totDed.add(cpto.getMonto());
				}
				resultados.add(ded);
			}	
		}
		dato.setTotalOtrasDeducciones(totDed);
		dato.setTotalImpuestosRetenidos(totImp);
		return resultados;
	}
	
	private List<ConceptoOtrosPagos> obtOtrosPagos(List <ConceptoPagado> cptos, DatosXML dato)
	{
		List<ConceptoOtrosPagos> resultados = new ArrayList<ConceptoOtrosPagos>();
		
		BigDecimal totOtros = new BigDecimal(0);
		
		for(ConceptoPagado cpto: cptos){
			if(cpto.getTipoCpto().equals("P") && 
					(cpto.getTieneISr().equals("N"))){		
				ConceptoOtrosPagos otros = new ConceptoOtrosPagos();
				otros.setClaveCptoMiNomina(cpto.getCptoPago());
				otros.setCptoDescMiNomina(cpto.getDescripcion());
				otros.setTipoSAT(cpto.getCveSat());
				otros.setImporte(cpto.getMonto());
				totOtros = totOtros.add(cpto.getMonto());
				resultados.add(otros);
			}	
		}
		dato.setTotalOtrosPagos(totOtros);
		return resultados;
	}
	
	private List <ConceptoPagado> obtCptos(int folio, int difFolio)
	{
    	List <ConceptoPagado> cptos = new ArrayList<ConceptoPagado>();
    	for (ConceptoPagado cptosHistorico: listaConceptosPago){
    		if(cptosHistorico.getFolio() == folio && cptosHistorico.getDifFolio() == difFolio){
    			ConceptoPagado cpto = new ConceptoPagado();
    	        cpto.setCptoPago(cptosHistorico.getCptoPago().trim());
    	        cpto.setCveSat(cptosHistorico.getCveSat());
    	        cpto.setDescripcion(cptosHistorico.getDescripcion());
    	        cpto.setMonto(cptosHistorico.getMonto().setScale(2, RoundingMode.CEILING));
    	        cpto.setNumConcepto(cptosHistorico.getNumConcepto());
    	        cpto.setTieneISr(cptosHistorico.getTieneISr());
    	        cpto.setTipoCpto(cptosHistorico.getTipoCpto().trim());
				cptos.add(cpto);
    		} 
    	}
    	return cptos;
    }
	
	public List <HistoricoPago> getListaPagos() {
		return listaPagos;
	}

	public void setListaPagos(List <HistoricoPago> listaPagos) {
		this.listaPagos = listaPagos;
	}

	public List <ConceptoPagado> getListaConceptosPago() {
		return listaConceptosPago;
	}

	public void setListaConceptosPago(List <ConceptoPagado> listaConceptosPago) {
		this.listaConceptosPago = listaConceptosPago;
	}

	public void setListaDatos(List <DatosXML> listaDatos) {
		this.listaDatos = listaDatos;
	}

	public Constantes getConstantes() {
		return constantes;
	}

	public void setConstantes(Constantes constantes) {
		this.constantes = constantes;
	}

}
