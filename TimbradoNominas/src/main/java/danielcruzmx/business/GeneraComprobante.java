package danielcruzmx.business;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;
import mx.gob.sat.cfd._3.Comprobante;
import mx.gob.sat.cfd._3.ObjectFactory;
import mx.gob.sat.nomina12.Nomina;
import mx.gob.sat.sitio_internet.cfd.catalogos.CEstado;
import mx.gob.sat.sitio_internet.cfd.catalogos.CMetodoPago;
import mx.gob.sat.sitio_internet.cfd.catalogos.CMoneda;
import mx.gob.sat.sitio_internet.cfd.catalogos.CTipoDeComprobante;
import mx.gob.sat.sitio_internet.cfd.catalogos.CUsoCFDI;
import mx.gob.sat.sitio_internet.cfd.catalogos.nomina.CTipoNomina;
import danielcruzmx.domain.ConceptoDeduccion;
import danielcruzmx.domain.ConceptoOtrosPagos;
import danielcruzmx.domain.ConceptoPercepcion;
import danielcruzmx.domain.DatosXML;

@SuppressWarnings("restriction")
public class GeneraComprobante 
{
	public String getXML(DatosXML dato, String fileCSD, String password, String fileTrasf)
	{
		ObjectFactory o = new ObjectFactory();
        
        // DEFINE COMPROBANTE
        Comprobante                    comprobante  = o.createComprobante();
        Comprobante.Emisor             cEmisor      = o.createComprobanteEmisor();
        Comprobante.Receptor           cReceptor    = o.createComprobanteReceptor();
        Comprobante.Complemento        cComplemento = o.createComprobanteComplemento();
        Comprobante.Conceptos          cConceptos   = o.createComprobanteConceptos();
        Comprobante.Conceptos.Concepto cConcepto    = o.createComprobanteConceptosConcepto();
        Comprobante.Impuestos          cImpuestos   = o.createComprobanteImpuestos();
        
        /*** ATRIBUTOS DE COMPROBANTE ***/
        comprobante.setVersion(dato.getVersion());
        comprobante.setSerie(dato.getSerie());              
        comprobante.setFolio(dato.getFolio());
        comprobante.setFecha(getXMLGregorianCalendarNow());
        comprobante.setFormaPago(dato.getFormaPago());
        //comprobante.setCertificado(dato.getCertificado());
        //comprobante.setNoCertificado(dato.getNoCertificado());
        comprobante.setNoCertificado(this.getNoCertificado(fileCSD, password));
        comprobante.setCertificado(this.getCertificado(fileCSD, password));
        comprobante.setSubTotal(dato.getSubTotal().setScale(2, RoundingMode.CEILING));   
        comprobante.setDescuento(dato.getDescuento().setScale(2, RoundingMode.CEILING)); 
        comprobante.setMoneda(CMoneda.MXN);
        comprobante.setTotal(dato.getTotal().setScale(2, RoundingMode.CEILING));        
        comprobante.setTipoDeComprobante(CTipoDeComprobante.N);
        comprobante.setMetodoPago(CMetodoPago.PUE);
        comprobante.setLugarExpedicion(dato.getLugarExpedicion());
        
        /*** NODO EMISOR ***/
        cEmisor.setRfc(dato.getRfcEmisor());
        cEmisor.setNombre(dato.getNombreEmisor());
        cEmisor.setRegimenFiscal(dato.getRegimen()); //?
        
        /*** NODO RECEPTOR ***/
        cReceptor.setNombre(dato.getNombreReceptor());
        cReceptor.setRfc(dato.getRfcReceptor());
        cReceptor.setUsoCFDI(CUsoCFDI.P_01);
        
        /*** NODO CONCEPTO ***/
        cConcepto.setClaveProdServ(dato.getClaveProdServ());
        cConcepto.setCantidad(dato.getCantidad().setScale(0, RoundingMode.CEILING));
        cConcepto.setClaveUnidad(dato.getCveUnidad());
        cConcepto.setDescripcion(dato.getDescripcionConcepto());
        cConcepto.setValorUnitario(dato.getValorUnitario().setScale(2, RoundingMode.CEILING)); 
        cConcepto.setImporte(dato.getImporte().setScale(2, RoundingMode.CEILING));
        cConceptos.getConcepto().add(cConcepto);
        
        /** DEFINE COMPLEMENTO NOMINA **/
        Nomina nomina = new Nomina();
        nomina.setVersion(dato.getVersionNomina());
        if (dato.getTipoNomina().equals("O")) {
        	nomina.setTipoNomina(CTipoNomina.O);	 
        } else {
        	nomina.setTipoNomina(CTipoNomina.E);
        }
        nomina.setFechaPago(getXMLGregorianCalendarFecha(dato.getFechaPago()));
        nomina.setFechaInicialPago(getXMLGregorianCalendarFecha(dato.getFechaInicial()));
        nomina.setFechaFinalPago(getXMLGregorianCalendarFecha(dato.getFechaFinal()));
        nomina.setNumDiasPagados(dato.getNumDiasPagados().setScale(3, RoundingMode.CEILING));
        
        // Totales de los grupos Percepciones, Deducciones y otros pagos 
        nomina.setTotalPercepciones(dato.getTotalPercepciones().setScale(2, RoundingMode.CEILING));
        nomina.setTotalDeducciones(dato.getTotalDeducciones().setScale(2, RoundingMode.CEILING));
        nomina.setTotalOtrosPagos(dato.getTotalOtrosPagos().setScale(2, RoundingMode.CEILING));
        
        /*** EMISOR NOMINA ***/
        Nomina.Emisor emisornomina = new Nomina.Emisor();
        emisornomina.setRegistroPatronal(dato.getRegistroPatronal());
            
        /*** RECEPTOR NOMINA ***/
        Nomina.Receptor receptornomina = new Nomina.Receptor();
        receptornomina.setCurp(dato.getCurp());
        receptornomina.setNumSeguridadSocial(dato.getNss());
        receptornomina.setFechaInicioRelLaboral(getXMLGregorianCalendarFecha(dato.getFechaInicioRelLab()));
        receptornomina.setCurp(dato.getCurp());
        receptornomina.setNumSeguridadSocial(dato.getNss());
        receptornomina.setAntiguedad("P"+dato.getAntiguedad()+"W");
        receptornomina.setTipoContrato(dato.getTipoContrato());
        receptornomina.setTipoJornada(dato.getTipoJornada());
        receptornomina.setTipoRegimen(dato.getTipoRegimen());
        receptornomina.setNumEmpleado(dato.getNumEmpleado());
        receptornomina.setDepartamento(dato.getDepartamento());
        receptornomina.setPuesto(dato.getPuesto());
        receptornomina.setPeriodicidadPago(dato.getPeriodicidadPago());
        
        // Si no es deposito no hay cuenta
        if(!dato.getBanco().equals("000")){
        	receptornomina.setCuentaBancaria(dato.getCuentaBancaria());
        }
        receptornomina.setSalarioDiarioIntegrado(dato.getSdoDiarioIntegrado());
        receptornomina.setClaveEntFed(CEstado.DIF);
        
        /** PERCEPCIONES NOMINA **/
        Nomina.Percepciones percnomina = new Nomina.Percepciones();
        percnomina.setTotalSueldos(dato.getTotalSueldos().setScale(2, RoundingMode.CEILING));
        percnomina.setTotalGravado(dato.getTotalGravado().setScale(2, RoundingMode.CEILING));
        percnomina.setTotalExento(dato.getTotalExento().setScale(2, RoundingMode.CEILING));
        
        for(ConceptoPercepcion pag:  dato.getPercepciones()){
        	
      		if(pag.getTipoSAT().equals("022") || pag.getTipoSAT().equals("023") || pag.getTipoSAT().equals("025") ){
      			// meter pago por separacion
      			
      			// primero el total en percepciones
      			percnomina.setTotalSeparacionIndemnizacion(dato.getTotalSeparacionInd());
      			
      			// construye nodo
      			Nomina.Percepciones.SeparacionIndemnizacion ps = new Nomina.Percepciones.SeparacionIndemnizacion();
      			ps.setTotalPagado(pag.getTotalPagado());
      			ps.setNumAniosServicio(pag.getNumAniosServ());
      			ps.setUltimoSueldoMensOrd(pag.getUltimoSdoMenOrd());
      			ps.setIngresoAcumulable(pag.getIngresoAcumulable());
      			ps.setIngresoNoAcumulable(pag.getIngresoNoAcumulable());
      			
      			// agrega nodo a percepciones
      			percnomina.setSeparacionIndemnizacion(ps);
      			
      		} else if(pag.getTipoSAT().equals("039") || pag.getTipoSAT().equals("044")) {
      			// meter pago por jubilacion
      		
      			// primero total en percepciones
      			percnomina.setTotalJubilacionPensionRetiro(dato.getTotalJubPensRet());
      			
      			// construye nodo
      			Nomina.Percepciones.JubilacionPensionRetiro pj = new Nomina.Percepciones.JubilacionPensionRetiro();
      			pj.setTotalUnaExhibicion(pag.getTotalUnaExhibicion());
      			
      			if(!pj.getTotalUnaExhibicion().equals(0)){ 
      				pj.setTotalParcialidad(pag.getTotalParcialidad());
      				pj.setMontoDiario(pag.getMontoDiario());
      			}
      			pj.setIngresoAcumulable(pag.getIngresoAcumulable());
      			pj.setIngresoNoAcumulable(pag.getIngresoNoAcumulable());
      			
      			// agrega nodo a percepciones
      			percnomina.setJubilacionPensionRetiro(pj);
      		
      		} else {
      			
      			Nomina.Percepciones.Percepcion p = new Nomina.Percepciones.Percepcion();
            	p.setTipoPercepcion(pag.getTipoSAT());
            	p.setClave(pag.getClaveCptoMiNomina());
            	p.setConcepto(pag.getCptoDescMiNomina());
          		p.setImporteGravado(pag.getImporteGravado().setScale(2, RoundingMode.CEILING));
          		p.setImporteExento(pag.getImporteExcento().setScale(2, RoundingMode.CEILING));

          		if(pag.getTipoSAT().equals("019")){ 
          			Nomina.Percepciones.Percepcion.HorasExtra he = new Nomina.Percepciones.Percepcion.HorasExtra();
          			he.setDias(pag.getDiasHoras());
          			he.setHorasExtra(pag.getHorasExtra());
          			he.setImportePagado(pag.getImportePagado());
          			he.setTipoHoras(pag.getTipoHoras());
          			p.getHorasExtra().add(he);
          		}
          		percnomina.getPercepcion().add(p);
      		}
        }

        /** DEDUCCIONES NOMINA **/
        Nomina.Deducciones deducnomina = new Nomina.Deducciones();
        deducnomina.setTotalOtrasDeducciones(dato.getTotalOtrasDeducciones().setScale(2, RoundingMode.CEILING));
        deducnomina.setTotalImpuestosRetenidos(dato.getTotalImpuestosRetenidos().setScale(2, RoundingMode.CEILING));

        for(ConceptoDeduccion pag:  dato.getDeducciones()){
        	Nomina.Deducciones.Deduccion d = new Nomina.Deducciones.Deduccion();
        	d.setTipoDeduccion(pag.getTipoSAT());
        	d.setClave(pag.getClaveCptoMiNomina());
        	d.setConcepto(pag.getCptoDescMiNomina());
        	d.setImporte(pag.getImporte().setScale(2, RoundingMode.CEILING));
        	deducnomina.getDeduccion().add(d);
        }
        
        /** OTROS PAGOS NOMINA COMO DEVOLUCIONES **/
        Nomina.OtrosPagos otrosnomina = new Nomina.OtrosPagos();
        
        for(ConceptoOtrosPagos pag:  dato.getOtrosPagos()){
        	Nomina.OtrosPagos.OtroPago op = new Nomina.OtrosPagos.OtroPago();
        	op.setTipoOtroPago(pag.getTipoSAT());
  			op.setClave(pag.getClaveCptoMiNomina());
  			op.setConcepto(pag.getCptoDescMiNomina());
  			op.setImporte(pag.getImporte());
        	otrosnomina.getOtroPago().add(op);
        }
        
        /** ARMADO DEL COMPLEMENTO NOMINA **/
        nomina.setEmisor(emisornomina);
        nomina.setReceptor(receptornomina);
        nomina.setPercepciones(percnomina);
        nomina.setDeducciones(deducnomina);
        nomina.setOtrosPagos(otrosnomina);

        /** AGREGA NOMINA A COMPLEMENTO **/
        cComplemento.getAny().add(nomina);
        
        /** ARMADO DEL COMPROBANTE **/
        comprobante.setEmisor(cEmisor);
        comprobante.setReceptor(cReceptor);
        comprobante.setConceptos(cConceptos);
        comprobante.setImpuestos(cImpuestos);
        comprobante.setComplemento(cComplemento);
        
        StringWriter xml = getComprobanteXML(comprobante);
        
        dato.setCadenaOriginal(this.getCadenaOriginal(xml, fileTrasf));
        
        // genera sello digital 
        comprobante.setSello(getSelloDigital12(dato.getCadenaOriginal(), fileCSD, password));
        
        // generar archivo XML con sello digital
        StringWriter archivoXML = getComprobanteXML(comprobante); 
        
        return archivoXML.toString();
	}
	
	
	private XMLGregorianCalendar getXMLGregorianCalendarNow() 
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
	
	private static XMLGregorianCalendar getXMLGregorianCalendarFecha(Date fecha) 
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

	private String getNoCertificado(String fileName, String password)
	{
		X509Certificate val;

		try{
			
			KeyStore keyStore = getKeyStore(fileName, password);	
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
	
	private KeyStore getKeyStore(String fileName, String password)
	{
		KeyStore keyStore = null;
		
		try {
			
			File p12File = new File(fileName);
			keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream fis;
			fis = new FileInputStream(p12File);
			BufferedInputStream bis = new BufferedInputStream(fis);
			keyStore.load(bis, password.toCharArray());
			
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
	
	private String getCertificado(String fileName, String password)
	{
		X509Certificate val;
		String certificado = null;
		
		try {

			KeyStore keyStore = getKeyStore(fileName, password);
			val = (X509Certificate)keyStore.getCertificate("1");
			certificado =new String(Base64.encodeBase64(val.getEncoded()));
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		}
		
		return certificado;
	}
	
	/*private String getSelloDummy(String cadena)  {

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
	}*/
	
	/*public String getSelloDigitalPKCS8(String cadenaOriginal, String fileName, String pass)
	{ 
		String selloDigital = null; 
		
		try {	
			
			File pkeyFile = new File(fileName);
			byte[] decodedBytes = Base64.decodeBase64(pass.getBytes());
			String password = new String(decodedBytes);
			
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
	}*/
	
	private String getSelloDigital12(String cadenaOriginal, String fileName, String password) { 
		
		String selloDigital = null; 
		
		try {	

			KeyStore keyStore = getKeyStore(fileName, password);
			PrivateKey pk = (PrivateKey) keyStore.getKey("1", password.toCharArray()); 
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
	
	private String quitarSaltos(String cadena) {
		  return cadena.replaceAll("[\n\r]", ""); 
	}
	
	private StringWriter getComprobanteXML(Comprobante comprobante){
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
	
	private String getCadenaOriginal(StringWriter xml, String fileTrasf) {
        // cargar el archivo XSLT
        File xslt = new File(fileTrasf);
        StreamSource sourceXSL = new StreamSource(xslt);
        StringWriter outWriter = new StringWriter();
        StreamResult resulCadenaOriginal = new StreamResult(outWriter);
 
        StringReader reader = new StringReader(xml.toString());
        StreamSource sourceXML = new StreamSource(reader);
 
        // crear el procesador XSLT que nos ayudaria generar la cadena original
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

	
}
