package danielcruzmx.com.model;

import java.io.Serializable;

public class Dependencia implements Serializable {
 	private static final long serialVersionUID = 1L;
 	
	private String  rfc;
    private String  nombre;
    private String  regimenFiscal;
    private String  lugar;
    private String  numCertificado;
 	private String  registroPatronal;
 	private String  version;
 	private String  versionNomina;
 	private Integer cantidad;
 	private String  cveUnidad;
 	private String  descripcionConcepto;
 	private String  formaPago;
 	private String  tipoComprobante;
 	private String  metodoPago;
 	private String  certificado;
 	private String  usoCfdi;
 	private String  cveProdServ;
 	 	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRegimenFiscal() {
		return regimenFiscal;
	}
	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getRegistroPatronal() {
		return registroPatronal;
	}
	public void setRegistroPatronal(String registroPatronal) {
		this.registroPatronal = registroPatronal;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVersionNomina() {
		return versionNomina;
	}
	public void setVersionNomina(String versionNomina) {
		this.versionNomina = versionNomina;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getCveUnidad() {
		return cveUnidad;
	}
	public void setCveUnidad(String unidad) {
		this.cveUnidad = unidad;
	}
	public String getDescripcionConcepto() {
		return descripcionConcepto;
	}
	public void setDescripcionConcepto(String descripcionConcepto) {
		this.descripcionConcepto = descripcionConcepto;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public String getCertificado() {
		return certificado;
	}
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	public String getUsoCfdi() {
		return usoCfdi;
	}
	public void setUsoCfdi(String usoCfdi) {
		this.usoCfdi = usoCfdi;
	}
	public String getCveProdServ() {
		return cveProdServ;
	}
	public void setCveProdServ(String cveProdServ) {
		this.cveProdServ = cveProdServ;
	}
 	
}
