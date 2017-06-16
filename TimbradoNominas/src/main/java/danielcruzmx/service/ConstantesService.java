package danielcruzmx.service;

import danielcruzmx.domain.Constantes;

public class ConstantesService {

	private Constantes constantes;

	public ConstantesService(){
		constantes = new Constantes();
		this.setConstantes();
	}
	
	public Constantes getConstantes() {
		return constantes;
	}

	public void setConstantes() {
		
		String path = "E:\\PROYECTOS\\timbrado\\TimbradoNominas\\src\\main\\resources\\META-INF\\";
		
		this.constantes.setVersionComprobante("3.3");
		this.constantes.setVersionNomina("1.2");
		this.constantes.setLugarExpedicion("06060");
		this.constantes.setRfcEmisor("SHC850101U37");
		this.constantes.setNombreEmisor("Secretaría de Hacienda y Crédito Público");
		this.constantes.setRegimenFiscal("603");
		this.constantes.setRegistroPatronal("0000699900");
		this.constantes.setCantidad(1);
		this.constantes.setCveUnidad("ACT");
		this.constantes.setDescripcionConcepto("Pago de nómina");
		this.constantes.setMetodoPago("PUE");
		this.constantes.setUsoCfdi("P01");
		this.constantes.setCveProdServ("01010101");
		this.constantes.setContrasenia("12345678a");
		//this.constantes.setContrasenia("XJZwnDLL");
		this.constantes.setFileCSDp12(path + "CVA0506151X5.pfx");
		this.constantes.setFileEsquemaCFDIV33(path + "cfdv33.xsd");
		this.constantes.setFileEsquemaNominaV12(path + "nomina12.xsd");
		this.constantes.setFileTransformCadOrig(path + "cadenaoriginal_3_3_sat.xslt");
		this.constantes.setFileKey("millave.key");
		this.constantes.setNumCertificado("00001000000302376743");
		this.constantes.setCertificado("MIIErjCCA5agAwIBAgIUMDAwMDEwMDAwMDAzMDIzNzY3NDMwDQYJ"
				+ "KoZIhvcNAQEFBQAwggGKMTgwNgYDVQQDDC9BLkMuIGRlbCBTZXJ2aWNpbyBkZSBBZG1pbmlzdH"
				+ "JhY2nDs24gVHJpYnV0YXJpYTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7Nu"
				+ "IFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbG"
				+ "EgSW5mb3JtYWNpw7NuMR8wHQYJKoZIhvcNAQkBFhBhY29kc0BzYXQuZ29iLm14MSYwJAYDVQQJ"
				+ "DB1Bdi4gSGlkYWxnbyA3NywgQ29sLiBHdWVycmVybzEOMAwGA1UEEQwFMDYzMDAxCzAJBgNVBA"
				+ "YTAk1YMRkwFwYDVQQIDBBEaXN0cml0byBGZWRlcmFsMRQwEgYDVQQHDAtDdWF1aHTDqW1vYzEV"
				+ "MBMGA1UELRMMU0FUOTcwNzAxTk4zMTUwMwYJKoZIhvcNAQkCDCZSZXNwb25zYWJsZTogQ2xhdW"
				+ "RpYSBDb3ZhcnJ1YmlhcyBPY2hvYTAeFw0xNDAxMTQwMDE0NTFaFw0xODAxMTQwMDE0NTFaMIH6"
				+ "MTEwLwYDVQQDEyhTRUNSRVRBUklBIERFIEhBQ0lFTkRBIFkgQ1JFRElUTyBQVUJMSUNPMTEwLw"
				+ "YDVQQpEyhTRUNSRVRBUklBIERFIEhBQ0lFTkRBIFkgQ1JFRElUTyBQVUJMSUNPMTEwLwYDVQQK"
				+ "EyhTRUNSRVRBUklBIERFIEhBQ0lFTkRBIFkgQ1JFRElUTyBQVUJMSUNPMSUwIwYDVQQtExxTSE"
				+ "M4NTAxMDFVMzcgLyBDQURGNjEwODA0QVA0MR4wHAYDVQQFExUgLyBDQURGNjEwODA0TURGU0dS"
				+ "MDAxGDAWBgNVBAsTDzcxMSBER1JIIE5PTUlOQTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgY"
				+ "EA73pebYYNQElQxfqBvHGlzy67UxEkefnICfpXglvlCfXPh7kcsAgRFCC7189SE62mLqcpFMOr"
				+ "cOXnyruucX+ysgxSgt0W3CmY0EQH8IuOmGS0ynjrCvibpfiIJJNPRa/XDHfOQMARHH5jfpghZn"
				+ "zE377YgUM1NGkjA6+lTlZiCdsCAwEAAaMdMBswDAYDVR0TAQH/BAIwADALBgNVHQ8EBAMCBsAw"
				+ "DQYJKoZIhvcNAQEFBQADggEBAC3y+koCpqCzhIwlPALeYtf/fhhbCsG55vtQnhXENJ9fhgyvAm"
				+ "7e/VpslHl2VWdzANFPEfNAjQOG47h6LaG4AguBGk4dAR9vdBbFWQbQcI0X5aoAbNGp4Rli1X+c"
				+ "ie8p/mjAhCULDshcCHhNAjyqByuOyFZx8ovJ/gVWemSLP5ZGYwZgzvP3lN0S/TSuDoKtdcegX5"
				+ "6t46ktJsCwb0DC0LNAW4tCqjYrQ2RY55GroIR4+a8tnmk5K7u3145DkwaA84g45I8D+xyAaIuA"
				+ "DKt8V8JmSDQJM+PCA4D2pmTpVK3Wbw1bN7IpO7DE4ts+YCj6K3Up/0LtpJx7D3sHrcf5IKI=");
	}
	
}
