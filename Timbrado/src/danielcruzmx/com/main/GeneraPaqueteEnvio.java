package danielcruzmx.com.main;

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

import danielcruzmx.com.model.PaqueteNomina;
import danielcruzmx.com.service.EnvioTimbradoService;

public class GeneraPaqueteEnvio {

	private Integer idNomina;

    public GeneraPaqueteEnvio(Integer idNomina) {
    	this.setIdNomina(idNomina);
    	EnvioTimbradoService datos = new EnvioTimbradoService();
    	datos.recuperaDatos(idNomina);
    	List <PaqueteNomina> comp = datos.getListaXML();

    	Integer total = comp.get(1).getTotalRegs();
    	String id = comp.get(1).getId();
    	
    	if(validaXMLs(comp)){
    		System.out.println("PROCESO DE VALIDACION CORRECTO");
    		
    		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			try {
				
				docBuilder = docFactory.newDocumentBuilder();
				Document doc= docBuilder.newDocument();
				Element rootElement = doc.createElement("PaqueteNomina");
				rootElement.setAttribute("xmlns", "http://www.sat.gob.mx/Nomina/Paquete");
				rootElement.setAttribute("id", id);
				rootElement.setAttribute("totalregs", String.valueOf(total));
				doc.appendChild(rootElement);
				
				int a=1;
				for(PaqueteNomina c: comp){

					//InputStream is = new ByteArrayInputStream( c.getXmlComp().getBytes("UTF-8") );
					//StringReader reader = new StringReader(xml.toString());

					Element paquete = doc.createElement("ControlComprobante");
					paquete.setAttribute("Num", String.valueOf(a));
					paquete.setAttribute("id", c.getIdComprobante());
					rootElement.appendChild(paquete);
					
					Element xml = doc.createElement("xmlComp");
					CDATASection cdata =  doc.createCDATASection(c.getXmlComp());
			        xml.appendChild(cdata);
			        paquete.appendChild(xml);
			        
					a = a + 1;
				}
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer;
				DOMSource source = new DOMSource(doc);
				transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			    
				StreamResult result = new StreamResult(new File("D:\\RESULTADO_TIMBRADO\\envio_id_"+ id +".xml"));
			    transformer.transform(source, result);
			    
			    System.out.println("ARCHIVO DE ENVIO "+ id + " GENERADO ....");
				
				
    		} catch (ParserConfigurationException e) {
				e.printStackTrace();
    		} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

	public Integer getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(Integer idNomina) {
		this.idNomina = idNomina;
	}
	
	public boolean validaXML(InputStream xml){
		
		boolean correcto = false;
		
		File fxsd1 = new File("C:/Users/daniel_cruz/workspace2/Timbrado/src/META-INF/cfdv33.xsd");
		File fxsd2 = new File("C:/Users/daniel_cruz/workspace2/Timbrado/src/META-INF/nomina12.xsd");
        //StreamSource xsd1 = new StreamSource(fxsd1);
        //SStreamSource xsd2 = new StreamSource(fxsd2);
		
		try
	    {
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new Source[] { new StreamSource(fxsd1), new StreamSource(fxsd2) } );
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(xml));
	        correcto = true;
	    }
	    catch(Exception ex)
	    {
	    	ex.printStackTrace();
	        correcto = false;
	    }

		return correcto;
	}
	
	public boolean validaXMLs(List <PaqueteNomina> comp){
		
		boolean correcto = false;
		Integer num = 1;
		
		File fxsd1 = new File("C:/Users/daniel_cruz/workspace2/Timbrado/src/META-INF/cfdv33.xsd");
		File fxsd2 = new File("C:/Users/daniel_cruz/workspace2/Timbrado/src/META-INF/nomina12.xsd");
		
		try
	    {
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new Source[] { new StreamSource(fxsd1), new StreamSource(fxsd2) } );
	        
	        for(PaqueteNomina c: comp){

	        	InputStream is = new ByteArrayInputStream( c.getXmlComp().getBytes("UTF-8") );
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
	
}
