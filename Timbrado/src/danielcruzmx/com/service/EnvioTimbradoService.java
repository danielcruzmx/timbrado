package danielcruzmx.com.service;

import java.util.ArrayList;
import java.util.List;

import danielcruzmx.com.data.TnComprobanteDig;
import danielcruzmx.com.model.PaqueteNomina;

public class EnvioTimbradoService {
	
	private List <TnComprobanteDig> comprobantes;	
	private List <PaqueteNomina>    listaXML;
	
	public EnvioTimbradoService()  {
		setListaXML(new ArrayList<PaqueteNomina>());
    }
	
	public void recuperaDatos(Integer idNomina){
	    setComprobantes(new TnComprobanteDigitalService().NOMINAfind(idNomina));
	}


	public void setComprobantes(List <TnComprobanteDig> comprobantes) {
		this.comprobantes = comprobantes;
	}

	public List <PaqueteNomina> getListaXML() {
		
		PaqueteNomina c;
		Integer num = 1;
		Integer total = comprobantes.size();
    	
    	for(TnComprobanteDig comp: comprobantes){
		
    		c = new PaqueteNomina();
    		c.setDifFolio(comp.getId().getCnDifFolio());
    		c.setFolio(comp.getId().getCnFolio());
    		c.setIdNomina(comp.getCnConsecNomina());
    		c.setIdPaquete(comp.getCnPaquete());
    		c.setNum(num);
    		c.setTotalRegs(total);
    		c.setXmlComp(comp.getCnXmlGenerado());
    		num = num + 1;
    		
    		listaXML.add(c);
    	}	
		
		return listaXML;
	}

	public void setListaXML(List <PaqueteNomina> listaXML) {
		this.listaXML = listaXML;
	}

}
