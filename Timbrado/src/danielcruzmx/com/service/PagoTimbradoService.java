package danielcruzmx.com.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import danielcruzmx.com.data.ConceptoPagado;
import danielcruzmx.com.model.ConceptoPagoTimbrado;
import danielcruzmx.com.model.Dependencia;
import danielcruzmx.com.data.HistoricoPago;
import danielcruzmx.com.model.DatosXMLTimbrado;

public class PagoTimbradoService {
	
	private List <HistoricoPago>    historicoPago;
	
	private List <ConceptoPagado>   conceptosPagados;
	private List <DatosXMLTimbrado> listaXML;
	
	private Dependencia patron;
	
	public PagoTimbradoService()  {
		
		listaXML = new ArrayList<DatosXMLTimbrado>();
		
		patron = new Dependencia();
		patron.setVersion("3.3");
		patron.setVersionNomina("1.2");
		patron.setLugar("06060");
		patron.setRfc("SHC850101U37");
        patron.setNombre("Secretaría de Hacienda y Crédito Público");
        patron.setRegimenFiscal("603"); //?
        patron.setRegistroPatronal("0000699900");
        patron.setCantidad(1);
        patron.setCveUnidad("ACT");
        patron.setDescripcionConcepto("Pago de nómina");
        patron.setTipoComprobante("Nómina");
        patron.setMetodoPago("PUE");
        patron.setUsoCfdi("P01");
        patron.setCveProdServ("01010101");
    }
	
	public void recuperaDatos(Integer idNomina){
	    setHistoricoPago(new HistoricoPagoService().NOMINAfind(idNomina));
		setConceptosPagados(new ConceptoPagadoService().NOMINAfolio(idNomina));
	}

    public List <DatosXMLTimbrado> getPagos(){
    	 
    	DatosXMLTimbrado pago;
    	
    	for(HistoricoPago dato: historicoPago){
    		
    		pago = new DatosXMLTimbrado();
    		
    		pago.setVersion(patron.getVersion());
    		pago.setSerie(dato.getIdNomina().toString());
    		pago.setFolio(dato.getFolio());
    		pago.setDifFolio(dato.getHpDifFolio());
    		pago.setFecha(new Date());

    		if(dato.getBanco().equals("000")){
    			pago.setFormaPago("02");
    		} else {
    		    pago.setFormaPago("03");
    		}
	
    		pago.setNoCertificado(patron.getNumCertificado());
    		pago.setMoneda("MXN");
    		pago.setTipoDeComprobante(patron.getTipoComprobante());
    		pago.setMetodoDePago(patron.getMetodoPago());
    		pago.setLugarExpedicion(patron.getLugar());
    		pago.setRfcEmisor(patron.getRfc());
    		pago.setNombreEmisor(patron.getNombre());
    		pago.setRegimen(patron.getRegimenFiscal());
    		pago.setRfcReceptor(dato.getRfcEmpleado());
    		pago.setNombreReceptor(this.nombre(dato));
    		pago.setUsoCfdi(patron.getUsoCfdi());
    		pago.setCantidad(patron.getCantidad());
    		pago.setClaveProdServ(patron.getCveProdServ());
    		pago.setCveUnidad(patron.getCveUnidad());
    		pago.setCertificado(patron.getCertificado());
    		pago.setDescripcionConcepto(patron.getDescripcionConcepto());
    		pago.setRegistroPatronal(patron.getRegistroPatronal());
    		
    		pago.setVersionNomina(patron.getVersionNomina());
    		pago.setFechaPago(dato.getFecPago());
    		pago.setFechaInicial(dato.getFecIniPago());
    		pago.setFechaFinal(dato.getFecFinPago());
    		pago.setNumDiasPagados(dato.getDiasPagados());
    		pago.setCurp(dato.getCurpEmpleado());
    		pago.setNss(dato.getNssEmpleado());
    		pago.setFechaInicioRelLab(dato.getFecIngreso());
    		pago.setAntiguedad(dato.getAntiguedad().toString());
    		pago.setTipoContrato(dato.getTipoContrata());
    		pago.setTipoJornada(dato.getJornada());
    		pago.setTipoRegimen(dato.getTipoReg().toString());
    		pago.setNumEmpleado(dato.getRfcEmpleado());
    		pago.setDepartamento(dato.getDepto());
    		pago.setPuesto(dato.getPuesto());
    		pago.setRiesgoPuesto(dato.getRiesgo().toString());
    		pago.setTipoNomina(dato.getTipoNomina());
    		pago.setPeriodicidadPago(dato.getPeriodicidad());
    		pago.setBanco(dato.getBanco());
    		pago.setCuentaBancaria(dato.getClabe());
    		
    		List <ConceptoPagoTimbrado> cptos = this.obtCptos(dato.getFolio(),dato.getHpDifFolio());

    		// calcula salario diario integrado
    		pago.setSdoDiarioIntegrado(this.salarioDiario(cptos, pago.getNumDiasPagados()));

    		// subTotal, descuento, total, velorUnitario, importe, totalPercepciones
    		// totalDeducciones, totalSueldos, totalGravado, totalExento, totalSueldos
    		calculaTotales(pago, cptos);
    		
    	    listaXML.add(pago);
    	}
    	
        return listaXML;
    }
    
    public BigDecimal salarioDiario(List <ConceptoPagoTimbrado> cptos, Integer diasPagados ){
    	BigDecimal valor  = new BigDecimal(0);
    	BigDecimal totPer = new BigDecimal(0);
    	if(!cptos.isEmpty()){
			for(ConceptoPagoTimbrado c :  cptos){
				if(c.getTipoCptoNom().equals("P")) {
					if(c.getCveCptoNom().equals("007") || c.getCveCptoNom().equals("002") || c.getCveCptoNom().equals("005") || c.getCveCptoNom().equals("077")){
						totPer = totPer.add(c.getMontoCpto());
					}
				}
			}
			valor = totPer.divide(new BigDecimal(diasPagados),2,RoundingMode.CEILING);
    	}	
    	return valor;
    }
    
    public void calculaTotales(DatosXMLTimbrado pago, List <ConceptoPagoTimbrado> cptos){
		BigDecimal totPer = new BigDecimal(0);
		BigDecimal totDed = new BigDecimal(0);
		BigDecimal totImp = new BigDecimal(0);
		BigDecimal totGra = new BigDecimal(0);
		BigDecimal totExe = new BigDecimal(0);
		
		List <ConceptoPagoTimbrado> per = new ArrayList<ConceptoPagoTimbrado>();
		List <ConceptoPagoTimbrado> ded = new ArrayList<ConceptoPagoTimbrado>();
		
		if(!cptos.isEmpty()){
			for(ConceptoPagoTimbrado c :  cptos){
				if(c.getTipoCptoNom().equals("P")) {

					totPer = totPer.add(c.getMontoCpto());
					
					// impuesto
					if(c.getTieneIsr().equals("I")) {
						totImp = totImp.subtract(c.getMontoCpto());
					}
					// gravado
					if(c.getTieneIsr().equals("S")) {
						totGra = totGra.add(c.getMontoCpto());
						c.setMontoGravado(c.getMontoCpto());
					}
					// exento
					if(c.getTieneIsr().equals("E")) {
						totExe = totExe.add(c.getMontoCpto());
						c.setMontoExento(c.getMontoCpto());
					}
					
					per.add(c);
					
				} else {
					
					// impuesto
					if(c.getTieneIsr().equals("I")) {
						totImp = totImp.add(c.getMontoCpto());
					} else {
						totDed = totDed.add(c.getMontoCpto());
					}
					
					ded.add(c);
				}
			}
		}
		pago.setSubTotal(totPer);
		pago.setDescuento(totDed);
		pago.setTotal(totPer.subtract(totDed).subtract(totImp));
		pago.setValorUnitario(totPer);
		pago.setImporte(totPer);
		pago.setTotalPercepciones(totPer);
		pago.setTotalDeducciones(totDed.add(totImp));
		pago.setTotalSueldos(totPer);
		pago.setTotalGravado(totGra);
		pago.setTotalExento(totExe);
		pago.setTotalSueldos(totExe.add(totGra));
		pago.setTotalOtrasDeducciones(totDed);
		pago.setTotalImpuestosRetenidos(totImp);
		pago.setDeducciones(ded);
		pago.setPercepciones(per);
		pago.setTotalOtrosPagos(new BigDecimal(0));
	}
    	
    private List <ConceptoPagoTimbrado> obtCptos(int folio, int difFolio){
    	List <ConceptoPagoTimbrado> cptos = new ArrayList<ConceptoPagoTimbrado>();
    	for (ConceptoPagado cptosHistorico: conceptosPagados){
    		if(cptosHistorico.getFolio() == folio && cptosHistorico.getDifFolio() == difFolio){
    			ConceptoPagoTimbrado cpto = new ConceptoPagoTimbrado();
    	        cpto.setCveCptoNom(cptosHistorico.getCptoPago().trim());
    	        cpto.setCveCptoTimbrado(cptosHistorico.getCveSat());
    	        cpto.setDescripcionCpto(cptosHistorico.getDescripcion());
    	        cpto.setMontoCpto(cptosHistorico.getMonto().setScale(2, RoundingMode.CEILING));
    	        cpto.setNumCpto(cptosHistorico.getNumConcepto());
    	        cpto.setTieneIsr(cptosHistorico.getTieneISr());
    	        cpto.setTipoCptoNom(cptosHistorico.getTipoCpto().trim());
    	        cpto.setMontoGravado(new BigDecimal(0));
    	        cpto.setMontoExento(new BigDecimal(0));
				cptos.add(cpto);
    		} 
    	}
    	return cptos;
    }
    	
    private String nombre(HistoricoPago emp){
    	return emp.getPrimerApellido().trim().replace(".", " ") + " " + 
               emp.getSegundoApellido().trim().replace(".", " ") + " " + 
    		   emp.getNombreEmpleado().trim().replace(".", " ");  
    }
    
	public List <ConceptoPagado> getConceptosPagados() {
		return conceptosPagados;
	}

	public void setConceptosPagados(List <ConceptoPagado> conceptosPagados) {
		this.conceptosPagados = conceptosPagados;
	}
    
	public List <HistoricoPago> getHistoricoPago() {
		return historicoPago;
	}

	public void setHistoricoPago(List <HistoricoPago> historicoPago) {
		this.historicoPago = historicoPago;
	}
    
}
