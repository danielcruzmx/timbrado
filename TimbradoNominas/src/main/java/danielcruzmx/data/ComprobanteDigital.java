package danielcruzmx.data;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TN_COMPROBANTE_DIG database table.
 * 
 */
@Entity
@Table(name="TN_COMPROBANTE_DIG")
@NamedQueries( {@NamedQuery(name="ComprobanteDigital.findAll", query="SELECT t FROM ComprobanteDigital t ") })
@NamedNativeQueries( { @NamedNativeQuery(name="ComprobanteDigital.findByNomina", query="" + ""
								+ "	SELECT t.cn_folio, t.cn_dif_folio, t.cn_consec_nomina, t.cn_paquete, t.cn_xml_generado "  
		                        + " FROM tn_comprobante_dig t WHERE t.CN_CONSEC_NOMINA = ?1 ",
		                        resultClass=ComprobanteDigital.class
		           )})
public class ComprobanteDigital implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String FIND_NOMINA = "ComprobanteDigital.findByNomina";
	
	@EmbeddedId
	private ComprobanteDigitalPK id;

	@Column(name="CN_CONSEC_NOMINA")
	private BigDecimal cnConsecNomina;

	@Column(name="CN_EJERCICIO")
	private BigDecimal cnEjercicio;

	@Temporal(TemporalType.DATE)
	@Column(name="CN_FEC_CANC")
	private Date cnFecCanc;

	@Temporal(TemporalType.DATE)
	@Column(name="CN_FEC_ENV_CANC")
	private Date cnFecEnvCanc;

	@Temporal(TemporalType.DATE)
	@Column(name="CN_FEC_ENVIO")
	private Date cnFecEnvio;

	@Temporal(TemporalType.DATE)
	@Column(name="CN_FEC_FIRMA_REC")
	private Date cnFecFirmaRec;

	@Temporal(TemporalType.DATE)
	@Column(name="CN_FEC_RESPUESTA")
	private Date cnFecRespuesta;

	@Temporal(TemporalType.DATE)
	@Column(name="CN_FECHA_GENERADO")
	private Date cnFechaGenerado;

	@Column(name="CN_FOLIO_FISCAL")
	private String cnFolioFiscal;

	@Column(name="CN_FOLIO_RECIBO")
	private String cnFolioRecibo;

	@Column(name="CN_PAQUETE")
	private BigDecimal cnPaquete;

	@Column(name="CN_RFC")
	private String cnRfc;

	@Column(name="CN_SITUACION")
	private String cnSituacion;

	@Lob
	@Column(name="CN_XML_CANCELADO")
	private String cnXmlCancelado;

	@Lob
	@Column(name="CN_XML_GENERADO")
	private String cnXmlGenerado;

	@Lob
	@Column(name="CN_XML_TIMBRADO")
	private String cnXmlTimbrado;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_MODIFICO")
	private Date fecModifico;

	private String usuario;

	public ComprobanteDigital() {
	}

	public ComprobanteDigitalPK getId() {
		return this.id;
	}

	public void setId(ComprobanteDigitalPK id) {
		this.id = id;
	}

	public BigDecimal getCnConsecNomina() {
		return this.cnConsecNomina;
	}

	public void setCnConsecNomina(BigDecimal cnConsecNomina) {
		this.cnConsecNomina = cnConsecNomina;
	}

	public BigDecimal getCnEjercicio() {
		return this.cnEjercicio;
	}

	public void setCnEjercicio(BigDecimal cnEjercicio) {
		this.cnEjercicio = cnEjercicio;
	}

	public Date getCnFecCanc() {
		return this.cnFecCanc;
	}

	public void setCnFecCanc(Date cnFecCanc) {
		this.cnFecCanc = cnFecCanc;
	}

	public Date getCnFecEnvCanc() {
		return this.cnFecEnvCanc;
	}

	public void setCnFecEnvCanc(Date cnFecEnvCanc) {
		this.cnFecEnvCanc = cnFecEnvCanc;
	}

	public Date getCnFecEnvio() {
		return this.cnFecEnvio;
	}

	public void setCnFecEnvio(Date cnFecEnvio) {
		this.cnFecEnvio = cnFecEnvio;
	}

	public Date getCnFecFirmaRec() {
		return this.cnFecFirmaRec;
	}

	public void setCnFecFirmaRec(Date cnFecFirmaRec) {
		this.cnFecFirmaRec = cnFecFirmaRec;
	}

	public Date getCnFecRespuesta() {
		return this.cnFecRespuesta;
	}

	public void setCnFecRespuesta(Date cnFecRespuesta) {
		this.cnFecRespuesta = cnFecRespuesta;
	}

	public Date getCnFechaGenerado() {
		return this.cnFechaGenerado;
	}

	public void setCnFechaGenerado(Date cnFechaGenerado) {
		this.cnFechaGenerado = cnFechaGenerado;
	}

	public String getCnFolioFiscal() {
		return this.cnFolioFiscal;
	}

	public void setCnFolioFiscal(String cnFolioFiscal) {
		this.cnFolioFiscal = cnFolioFiscal;
	}

	public String getCnFolioRecibo() {
		return this.cnFolioRecibo;
	}

	public void setCnFolioRecibo(String cnFolioRecibo) {
		this.cnFolioRecibo = cnFolioRecibo;
	}

	public BigDecimal getCnPaquete() {
		return this.cnPaquete;
	}

	public void setCnPaquete(BigDecimal cnPaquete) {
		this.cnPaquete = cnPaquete;
	}

	public String getCnRfc() {
		return this.cnRfc;
	}

	public void setCnRfc(String cnRfc) {
		this.cnRfc = cnRfc;
	}

	public String getCnSituacion() {
		return this.cnSituacion;
	}

	public void setCnSituacion(String cnSituacion) {
		this.cnSituacion = cnSituacion;
	}

	public String getCnXmlCancelado() {
		return this.cnXmlCancelado;
	}

	public void setCnXmlCancelado(String cnXmlCancelado) {
		this.cnXmlCancelado = cnXmlCancelado;
	}

	public String getCnXmlGenerado() {
		return this.cnXmlGenerado;
	}

	public void setCnXmlGenerado(String cnXmlGenerado) {
		this.cnXmlGenerado = cnXmlGenerado;
	}

	public String getCnXmlTimbrado() {
		return this.cnXmlTimbrado;
	}

	public void setCnXmlTimbrado(String cnXmlTimbrado) {
		this.cnXmlTimbrado = cnXmlTimbrado;
	}

	public Date getFecModifico() {
		return this.fecModifico;
	}

	public void setFecModifico(Date fecModifico) {
		this.fecModifico = fecModifico;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}