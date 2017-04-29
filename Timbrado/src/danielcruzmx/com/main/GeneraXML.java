package danielcruzmx.com.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.ssl.Base64;
import org.apache.commons.ssl.PKCS8Key; 

import java.security.KeyStore;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import sun.misc.BASE64Encoder;
import mx.gob.sat.cfd._3.Comprobante;
import mx.gob.sat.nomina12.Nomina;
import mx.gob.sat.cfd._3.ObjectFactory;
import mx.gob.sat.sitio_internet.cfd.catalogos.CEstado;
import mx.gob.sat.sitio_internet.cfd.catalogos.CMetodoPago;
import mx.gob.sat.sitio_internet.cfd.catalogos.CMoneda;
import mx.gob.sat.sitio_internet.cfd.catalogos.CTipoDeComprobante;
import mx.gob.sat.sitio_internet.cfd.catalogos.CUsoCFDI;
import mx.gob.sat.sitio_internet.cfd.catalogos.nomina.CTipoNomina;
import danielcruzmx.com.model.ConceptoPagoTimbrado;
import danielcruzmx.com.model.DatosXMLTimbrado;

public class GeneraXML {
	
	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	//private static final String pathResultados = "D:\\RESULTADO_TIMBRADO\\";
	
	public String getFile(DatosXMLTimbrado pago, PrintWriter wcadenas){

		ObjectFactory c = new ObjectFactory();
        
        // DEFINE COMPROBANTE
        Comprobante                    comprobante  = c.createComprobante();
        Comprobante.Emisor             cEmisor      = c.createComprobanteEmisor();
        Comprobante.Receptor           cReceptor    = c.createComprobanteReceptor();
        Comprobante.Complemento        cComplemento = c.createComprobanteComplemento();
        Comprobante.Conceptos          cConceptos   = c.createComprobanteConceptos();
        Comprobante.Conceptos.Concepto cConcepto    = c.createComprobanteConceptosConcepto();
        Comprobante.Impuestos          cImpuestos   = c.createComprobanteImpuestos();
        
        /*** ATRIBUTOS DE COMPROBANTE ***/
        comprobante.setVersion(pago.getVersion());
        comprobante.setSerie(pago.getSerie());              
        comprobante.setFolio(Integer.toString(pago.getFolio()));
        comprobante.setFecha(getXMLGregorianCalendarNow());
        //comprobante.setFormaPago(pago.getFormaPago());
        comprobante.setNoCertificado(this.generaNoCertificado());
        comprobante.setCertificado(this.generaCertificado());
        comprobante.setSubTotal(pago.getSubTotal().setScale(2, RoundingMode.CEILING));   
        comprobante.setDescuento(pago.getDescuento().setScale(2, RoundingMode.CEILING)); 
        comprobante.setMoneda(CMoneda.MXN);
        comprobante.setTotal(pago.getTotal().setScale(2, RoundingMode.CEILING));        
        comprobante.setTipoDeComprobante(CTipoDeComprobante.N);
        comprobante.setMetodoPago(CMetodoPago.PUE);
        comprobante.setLugarExpedicion(pago.getLugarExpedicion());
        
        /*** NODO EMISOR ***/
        cEmisor.setRfc(pago.getRfcEmisor());
        cEmisor.setNombre(pago.getNombreEmisor());
        cEmisor.setRegimenFiscal(pago.getRegimen()); //?
        
        /*** NODO RECEPTOR ***/
        cReceptor.setNombre(pago.getNombreReceptor());
        cReceptor.setRfc(pago.getRfcReceptor());
        cReceptor.setUsoCFDI(CUsoCFDI.P_01);
        
        /*** NODO CONCEPTO ***/
        cConcepto.setClaveProdServ(pago.getClaveProdServ());
        cConcepto.setCantidad(new BigDecimal(pago.getCantidad()).setScale(0, RoundingMode.CEILING));
        cConcepto.setClaveUnidad(pago.getCveUnidad());
        cConcepto.setDescripcion(pago.getDescripcionConcepto());
        cConcepto.setValorUnitario(pago.getValorUnitario().setScale(2, RoundingMode.CEILING)); 
        cConcepto.setImporte(pago.getImporte().setScale(2, RoundingMode.CEILING));
        cConceptos.getConcepto().add(cConcepto);
        
        /*** NODO IMPUESTOS ***/
        // No lleva nada 
        
        /** DEFINE COMPLEMENTO NOMINA **/
        Nomina nomina = new Nomina();
        nomina.setVersion(pago.getVersionNomina());
        if (pago.getTipoNomina().equals("O")) {
        	nomina.setTipoNomina(CTipoNomina.O);	 
        } else {
        	nomina.setTipoNomina(CTipoNomina.E);
        }
        nomina.setFechaPago(getXMLGregorianCalendarFecha(pago.getFechaPago()));
        nomina.setFechaInicialPago(getXMLGregorianCalendarFecha(pago.getFechaInicial()));
        nomina.setFechaFinalPago(getXMLGregorianCalendarFecha(pago.getFechaFinal()));
        nomina.setNumDiasPagados(new BigDecimal(pago.getNumDiasPagados()).setScale(3, RoundingMode.CEILING));
        nomina.setTotalPercepciones(pago.getTotalPercepciones().setScale(2, RoundingMode.CEILING));
        nomina.setTotalDeducciones(pago.getTotalDeducciones().setScale(2, RoundingMode.CEILING));
        nomina.setTotalOtrosPagos(pago.getTotalOtrosPagos().setScale(2, RoundingMode.CEILING));
        
        /*** EMISOR NOMINA ***/
        Nomina.Emisor emisornomina = new Nomina.Emisor();
        emisornomina.setRegistroPatronal(pago.getRegistroPatronal());
            
        /*** RECEPTOR NOMINA ***/
        Nomina.Receptor receptornomina = new Nomina.Receptor();
        receptornomina.setCurp(pago.getCurp());
        receptornomina.setNumSeguridadSocial(pago.getNss());
        receptornomina.setFechaInicioRelLaboral(getXMLGregorianCalendarFecha(pago.getFechaInicioRelLab()));
        receptornomina.setCurp(pago.getCurp());
        receptornomina.setNumSeguridadSocial(pago.getNss());
        receptornomina.setAntiguedad("P"+pago.getAntiguedad()+"W");
        receptornomina.setTipoContrato(pago.getTipoContrato());
        receptornomina.setTipoJornada(pago.getTipoJornada());
        receptornomina.setTipoRegimen(pago.getTipoRegimen());
        receptornomina.setNumEmpleado(pago.getNumEmpleado());
        receptornomina.setDepartamento(pago.getDepartamento());
        receptornomina.setPuesto(pago.getPuesto());
        //receptornomina.setRiesgoPuesto(pago.getRiesgoPuesto());
        receptornomina.setPeriodicidadPago(pago.getPeriodicidadPago());
        if(!pago.getBanco().equals("000")){
        	//receptornomina.setBanco(pago.getBanco());
        	receptornomina.setCuentaBancaria(pago.getCuentaBancaria());
        }
        receptornomina.setSalarioDiarioIntegrado(pago.getSdoDiarioIntegrado());
        receptornomina.setClaveEntFed(CEstado.DIF);
        
        /** PERCEPCIONES NOMINA **/
        Nomina.Percepciones percnomina = new Nomina.Percepciones();
        percnomina.setTotalSueldos(pago.getTotalSueldos().setScale(2, RoundingMode.CEILING));
        percnomina.setTotalGravado(pago.getTotalGravado().setScale(2, RoundingMode.CEILING));
        percnomina.setTotalExento(pago.getTotalExento().setScale(2, RoundingMode.CEILING));
        
        for(ConceptoPagoTimbrado pag:  pago.getPercepciones()){
        	Nomina.Percepciones.Percepcion p = new Nomina.Percepciones.Percepcion();
        	p.setTipoPercepcion(pag.getCveCptoTimbrado());
        	p.setClave(pag.getCveCptoNom());
        	p.setConcepto(pag.getDescripcionCpto());
      		p.setImporteGravado(pag.getMontoGravado().setScale(2, RoundingMode.CEILING));
      		p.setImporteExento(pag.getMontoExento().setScale(2, RoundingMode.CEILING));
        	percnomina.getPercepcion().add(p);
        }

        /** DEDUCCIONES NOMINA **/
        Nomina.Deducciones deducnomina = new Nomina.Deducciones();
        deducnomina.setTotalOtrasDeducciones(pago.getTotalOtrasDeducciones().setScale(2, RoundingMode.CEILING));
        deducnomina.setTotalImpuestosRetenidos(pago.getTotalImpuestosRetenidos().setScale(2, RoundingMode.CEILING));

        for(ConceptoPagoTimbrado pag:  pago.getDeducciones()){
        	Nomina.Deducciones.Deduccion d = new Nomina.Deducciones.Deduccion();
        	d.setTipoDeduccion(pag.getCveCptoTimbrado());
        	d.setClave(pag.getCveCptoNom());
        	d.setConcepto(pag.getDescripcionCpto());
        	d.setImporte(pag.getMontoCpto().setScale(2, RoundingMode.CEILING));
        	deducnomina.getDeduccion().add(d);
        }
        
        /** ARMADO DEL COMPLEMENTO NOMINA **/
        nomina.setEmisor(emisornomina);
        nomina.setReceptor(receptornomina);
        nomina.setPercepciones(percnomina);
        nomina.setDeducciones(deducnomina);

        /** AGREGA NOMINA A COMPLEMENTO **/
        cComplemento.getAny().add(nomina);
        
        /** ARMADO DEL COMPROBANTE **/
        comprobante.setEmisor(cEmisor);
        comprobante.setReceptor(cReceptor);
        comprobante.setConceptos(cConceptos);
        comprobante.setImpuestos(cImpuestos);
        comprobante.setComplemento(cComplemento);
        
        // String nombre = pathResultados + pago.getSerie() + "_" + pago.getRfcReceptor().trim() + "_" +  pago.getFolio(); 

        // genera archivo XML por primera vez para obtener cadena original
        // String archivoXML = generaArchivo(nombre, comprobante);
        
        StringWriter xml = generaXML(comprobante);
        
        // guarda atributo de cadena original 
        // pago.setCadenaOriginal(this.generaCadenaOriginal(archivoXML));
        
        pago.setCadenaOriginal(this.generaCadenaOriginalB(xml));
        
        // guarda cadena original en archivos de texto
        wcadenas.println(pago.getCadenaOriginal());
        
        // genera sello digital 
        comprobante.setSello(generaSelloDigital12(pago.getCadenaOriginal()));
        
        // generar archivo XML con sello
        // archivoXML = generaArchivo(nombre, comprobante);
        
        StringWriter archivoXML = generaXML(comprobante); 
        
        // Guarda XML en base de datos 
        if(archivoXML != null){
        	//guardaXML(pago.getFolio(), pago.getRfcReceptor(), pago.getSerie(), archivoXML);
        }
        
        // genera PDF
        if(archivoXML != null){
        	// generaPDF(nombre);
        }
        
        return archivoXML.toString();
	}
	
	public String fileXMLToString(String archivoXML){
		
		File cfdi = new File(archivoXML);
        StreamSource sourceXML = new StreamSource(cfdi);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        String strResult = null;
        
		try {

	        TransformerFactory tFactory = TransformerFactory.newInstance();
	        Transformer transformer;
			transformer = tFactory.newTransformer();
	        transformer.transform(sourceXML,result);
	        strResult = writer.toString();
	        
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strResult;
	}
	
	public String generaArchivo(String nombre, Comprobante comprobante){
		try {
            
        	String nombreArchivo = nombre + ".xml" ;
            File file =  new File(nombreArchivo);
            JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante.class, Nomina.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
					"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd "
			  	  + "http://www.sat.gob.mx/nomina12 http://www.sat.gob.mx/sitio_internet/cfd/nomina/nomina12.xsd"
			);
			
            jaxbMarshaller.marshal(comprobante, file);
            //jaxbMarshaller.marshal(comprobante, System.out);

            return nombreArchivo;
            
        } catch (JAXBException e){
            e.printStackTrace();
            return null;
        }
	}
	
	public StringWriter generaXML(Comprobante comprobante){
		try {
            
			StringWriter outputString = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante.class, Nomina.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
					"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd "
						  	  + "http://www.sat.gob.mx/nomina12 http://www.sat.gob.mx/sitio_internet/cfd/nomina/nomina12.xsd"
			);
			
            jaxbMarshaller.marshal(comprobante, outputString);

            return outputString;
            
        } catch (JAXBException e){
            e.printStackTrace();
            return null;
        }
	}
	
	public String generaCadenaOriginal(String archivoXML) {
        // cargar el archivo XSLT
        File xslt = new File("C:/Users/daniel_cruz/workspace2/Timbrado/src/META-INF/cadenaoriginal_3_3.xslt");
        StreamSource sourceXSL = new StreamSource(xslt);
        StringWriter outWriter = new StringWriter();
        StreamResult resulCadenaOriginal = new StreamResult(outWriter);
 
        // cargar el CFDI
        File cfdi = new File(archivoXML);
        StreamSource sourceXML = new StreamSource(cfdi);
 
        // crear el procesador XSLT que nos ayudará a generar la cadena original
        // con base en las reglas del archivo XSLT
        try {
        	TransformerFactory tFactory = TransformerFactory.newInstance();
        	Transformer transformer = tFactory.newTransformer(sourceXSL);

        	//aplicar las reglas del XSLT con los datos del CFDI 
        	transformer.transform(sourceXML, resulCadenaOriginal);
        	StringBuffer sb = outWriter.getBuffer(); 
        	
        	return sb.toString();
        	
        } catch (TransformerException e)	{
        	e.printStackTrace();
        	return null;
        }
    }
	
	public String generaCadenaOriginalB(StringWriter xml) {
        // cargar el archivo XSLT
        File xslt = new File("C:/Users/daniel_cruz/workspace2/Timbrado/src/META-INF/cadenaoriginal_3_3.xslt");
        StreamSource sourceXSL = new StreamSource(xslt);
        StringWriter outWriter = new StringWriter();
        StreamResult resulCadenaOriginal = new StreamResult(outWriter);
 
        StringReader reader = new StringReader(xml.toString());
        StreamSource sourceXML = new StreamSource(reader);
 
        // crear el procesador XSLT que nos ayudará a generar la cadena original
        // con base en las reglas del archivo XSLT
        try {
        	TransformerFactory tFactory = TransformerFactory.newInstance();
        	Transformer transformer = tFactory.newTransformer(sourceXSL);

        	//aplicar las reglas del XSLT con los datos del CFDI 
        	transformer.transform(sourceXML, resulCadenaOriginal);
        	StringBuffer sb = outWriter.getBuffer(); 
        	
        	return sb.toString();
        	
        } catch (TransformerException e)	{
        	e.printStackTrace();
        	return null;
        }
    }

	public String generaSello(String cadena)  {

			try {
				
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(cadena.getBytes());
	
				byte byteData[] = md.digest();
	
				//convert the byte to hex format method 1
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
	
				return sb.toString();

			} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
			}
    }
	
	public String generaSelloDigital(String cadenaOriginal) { 
	
		String selloDigital = null; 
		try {	
			File pkeyFile = new File("d:/Claveprivada.key");
			
			// EJEMPLO RECUPERACION DE PASSWORD CODIFICADO EN BASE 64
			// ORIGINAL
			String password = "valeria1";
			// CODIFICADO
			byte[] encodedBytes = Base64.encodeBase64(password.getBytes());
			System.out.println("password base 64, Asi hay que guardarlo -> " + new String(encodedBytes));
			// DECODIFICADO
			byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
			System.out.println("password original, Y con decode hay que volver al original -> " + new String(decodedBytes));
			// USAR EL ORIGINAL
			password = new String(decodedBytes);
			
			byte[] clavePrivada = FileUtils.readFileToByteArray(pkeyFile);
			PKCS8Key pkcs8 = new PKCS8Key(clavePrivada, password.toCharArray());
			PrivateKey pk = pkcs8.getPrivateKey(); 
			Signature firma = Signature.getInstance("SHA256withRSA"); 
			firma.initSign(pk); 
			firma.update(cadenaOriginal.getBytes("UTF-8")); 
			BASE64Encoder b64 = new BASE64Encoder(); 
			selloDigital = b64.encode(firma.sign()); 
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(quitarSaltos(selloDigital)); 
		return quitarSaltos(selloDigital); 
	}
	
	public static KeyStore getKeyStore(){

		KeyStore keyStore = null;
		
		try {
			
			File p12File = new File("D:\\CSD_SSHC850101U37.p12");
			String pass = "DICF760910XE";
			keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream fis;
			fis = new FileInputStream(p12File);
			BufferedInputStream bis = new BufferedInputStream(fis);
			keyStore.load(bis, pass.toCharArray());
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keyStore;
	}
	
	public String generaCertificado(){
		
		X509Certificate val;
		String certificado = null;
		
		try {

			KeyStore keyStore = getKeyStore();
			val = (X509Certificate)keyStore.getCertificate("1");
			certificado =new String(Base64.encodeBase64(val.getEncoded()));
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		}
		
		return certificado;
	}
	
	public String generaSelloDigital12(String cadenaOriginal) { 
		
		String selloDigital = null; 
		
		try {	

			String pass = "DICF760910XE";
			KeyStore keyStore = getKeyStore();
			PrivateKey pk = (PrivateKey) keyStore.getKey("1", pass.toCharArray()); 
			Signature firma = Signature.getInstance("SHA256withRSA"); 
			firma.initSign(pk); 
			firma.update(cadenaOriginal.getBytes("UTF-8")); 
			BASE64Encoder b64 = new BASE64Encoder(); 
			selloDigital = b64.encode(firma.sign());
			
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace(); 
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//System.out.println(quitarSaltos(selloDigital)); 
		return quitarSaltos(selloDigital); 
	} 
	
	public String generaNoCertificado(){
		
		X509Certificate val;
		
		try{
			
			KeyStore keyStore = getKeyStore();	
			val = (X509Certificate)keyStore.getCertificate("1");
			BigInteger serial = val.getSerialNumber();
			byte[] serialArr = serial.toByteArray();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < serialArr.length; i++) {
		      buffer.append((char)serialArr[i]);
		    }
		    
		    return buffer.toString();
		    
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
    }

	public String generaUUID()
	{
	    String result = java.util.UUID.randomUUID().toString();

	    //result.replaceAll("-", "");
	    //result.substring(0, 32);

	    return result;
	}

	public String quitarSaltos(String cadena) {

		  return cadena.replaceAll("[\n\r]", ""); 

	}

	public static XMLGregorianCalendar getXMLGregorianCalendarNow() 
    {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        XMLGregorianCalendar now = null;
        Date date = new Date();
        try {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            now = datatypeFactory.newXMLGregorianCalendar(sdf.format(date));
        } catch (DatatypeConfigurationException e) {
               e.printStackTrace();
        }    
        return now;
    }

	public static XMLGregorianCalendar getXMLGregorianCalendarFecha(Date fecha) 
    {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        XMLGregorianCalendar now = null;
        Date date = fecha;
        try {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            now = datatypeFactory.newXMLGregorianCalendar(sdf.format(date));
        } catch (DatatypeConfigurationException e) {
               e.printStackTrace();
        }    
        return now;
    }
	
	public static void generaPDF(String nombre) {
	    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

        try {
        
        	DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        	Document doc = builder.parse(nombre);
        
        	String sourceFileName = "C:/Users/daniel_cruz/workspace2/Timbrado/src/META-INF/comprobante.jasper";
        	String destFileName =   nombre +  ".pdf";
        
        	Map<String, Object> params = new HashMap<String, Object>();
        	Locale locale = new Locale("es", "MX");
        	params.put(JRParameter.REPORT_LOCALE, locale);
        	//params.put("IMAGES_URI", imagesPath);
        	//params.put("SUBREPORT_DIR", reportBasePath);
        
        	JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, params, new JRXmlDataSource(doc));
        	JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
        	
        } catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
}
