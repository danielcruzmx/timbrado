package danielcruzmx.business;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import danielcruzmx.data.ComprobanteDigital;
import danielcruzmx.service.ComprobanteDigitalService;

public class GeneraPaquete {

	private List <ComprobanteDigital> comprobantes;	
	
	public GeneraPaquete(Integer idNomina)
	{
		this.recuperaDatos(idNomina);
	}
	
	public void recuperaDatos(Integer idNomina){
	    setComprobantes(new ComprobanteDigitalService().NOMINAfind(idNomina));
	}
		
	public List <ComprobanteDigital> getComprobantes() {
		return comprobantes;
	}

	public void setComprobantes(List <ComprobanteDigital> comprobantes) {
		this.comprobantes = comprobantes;
	}

	public void  getPaquete(String pathr) {
		
		Integer total = comprobantes.size();
    	String id = comprobantes.get(1).getCnConsecNomina().toString();
		
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
			for(ComprobanteDigital c: comprobantes){
				
				Element paquete = doc.createElement("ControlComprobante");
				paquete.setAttribute("Num", String.valueOf(a));
				paquete.setAttribute("id", String.valueOf(c.getId().getCnFolio()));
				rootElement.appendChild(paquete);
				
				Element xml = doc.createElement("xmlComp");
				CDATASection cdata =  doc.createCDATASection(c.getCnXmlGenerado());
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
		    
			StreamResult result = new StreamResult(new File(pathr));
		    transformer.transform(source, result);
		    
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
