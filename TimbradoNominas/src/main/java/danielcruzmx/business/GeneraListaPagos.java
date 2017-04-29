package danielcruzmx.business;

import java.util.ArrayList;
import java.util.List;

import danielcruzmx.data.ConceptoPagado;
import danielcruzmx.data.HistoricoPago;
import danielcruzmx.domain.DatosXML;
import danielcruzmx.service.ConceptoPagadoService;
import danielcruzmx.service.HistoricoPagoService;

public class GeneraListaPagos {
	
	private List <HistoricoPago>    listaPagos;
	private List <ConceptoPagado>   listaConceptosPago;
	private List <DatosXML> 		listaDatos;

	public GeneraListaPagos(Integer idNomina){
	    setListaPagos(new HistoricoPagoService().NOMINAfind(idNomina));
		setListaConceptosPago(new ConceptoPagadoService().NOMINAfolio(idNomina));
		listaDatos = new ArrayList<DatosXML>();
	}
	
	public List <DatosXML> getListaDatos() {
		//
		//   Genera la lista de datos que llenan el comprobante
		//
		DatosXML dato;
		
    	for(HistoricoPago pago: listaPagos){
    		dato = new DatosXML();
    		dato.setFolio(pago.getFolio().toString() + "_" + pago.getHpDifFolio());
    		dato.setRfcReceptor(pago.getRfcEmpleado());
    		dato.setNombreReceptor(this.nombreCompleto(pago));
    		listaDatos.add(dato);
    	}
		return listaDatos;
	}

	private String nombreCompleto(HistoricoPago emp){
    	return emp.getPrimerApellido().trim().replace(".", " ") + " " + 
               emp.getSegundoApellido().trim().replace(".", " ") + " " + 
    		   emp.getNombreEmpleado().trim().replace(".", " ");  
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

}
