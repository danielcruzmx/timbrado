package danielcruzmx.business;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import danielcruzmx.data.ComprobanteDigital;
import danielcruzmx.domain.*;
import danielcruzmx.service.ComprobanteDigitalService;

public class GeneraPaquete {

	private List <ComprobanteDigital> comprobantes;	
	private List <Paquete>            paquetes;
	
	public GeneraPaquete(Integer idNomina)
	{
		this.recuperaDatos(idNomina);
	}
	
	public void recuperaDatos(Integer idNomina){
	    setComprobantes(new ComprobanteDigitalService().NOMINAfind(idNomina));
	}
	
	public boolean validaXMLs(String xsdV33, String xsdV12){
		
		boolean correcto = false;
		Integer num = 1;
		
		File fxsd1 = new File(xsdV33);
		File fxsd2 = new File(xsdV12);
		
		try
	    {
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new Source[] { new StreamSource(fxsd1), new StreamSource(fxsd2) } );
	        
	        for(ComprobanteDigital c: comprobantes){
	        	System.out.println(" va en XML -> " + c.getCnRfc());
	        	InputStream is = new ByteArrayInputStream( c.getCnXmlGenerado().getBytes("UTF-8") );
    	        Validator validator = schema.newValidator();
    	        validator.validate(new StreamSource(is));
    	        num = num + 1;
    	        if((num % 10) == 0) System.out.println("."+num+".");
    		}
    		
    		correcto = true;
	        
	    }
	    catch(Exception ex)
	    {
	    	ex.printStackTrace();
	        correcto = false;
	    }

		return correcto;
	}

	public List <ComprobanteDigital> getComprobantes() {
		return comprobantes;
	}

	public void setComprobantes(List <ComprobanteDigital> comprobantes) {
		this.comprobantes = comprobantes;
	}

	public void  generaPaquete() {
		
		Paquete c;
		Integer num = 1;
		Integer total = comprobantes.size();
    	
    	for(ComprobanteDigital comp: comprobantes){		
    		c = new Paquete();
    		//c.setIdComprobante(comp.getId().getCnFolio());
    		c.setIdPaquete(comp.getCnPaquete());
    		c.setNum(num);
    		c.setTotalRegs(total);
    		c.setXmlComp(comp.getCnXmlGenerado());
    		num = num + 1;
    		
    		paquetes.add(c);
    	}	
		
	}

	
}
