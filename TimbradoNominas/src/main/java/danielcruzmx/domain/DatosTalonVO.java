package danielcruzmx.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DatosTalonVO {

	private BigDecimal idXmlCfdi;
	private String folio;
	private String serietalon;
	private Date fechaCreacion;
	private String periodoFecha;
	private Long nomina;
	private String nomimadescripcion;
	private Date finicio;
	private Date ffin;
	private String clabe;
	private String rfc;
	private String curp;
	private String nivel;
	private long idcatnomina;
	private double diasPagados;
	private long numempleado;
	private String puesto;
	private String nombreEmpleado;
	private String tipoContratacion;
	private Date fechaIngreso;
	private String ubicacion;
	private String codigoubicacion;
	private Long numeroSeguridadSocial;
	private Long plaza;
	private String cvepuesto;
	private String entidadlabora;
	private String contadores;
	private Date fechapago;
	private String numerocertificado;
	private String sellosre;
	private String cadenaoriginal;
	private String sellosat;
	private String fechacertificacion;
	private String metodopago;
	private long ejercicio;
	private long qnapago;
	private String contenidoQR;
	private String nivelmando;
	private String tipoNomina;
	
	private List<DatosTalonDetalleVO> listaPercepciones;
	private List<DatosTalonDetalleVO> listaDeducciones;

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getPeriodoFecha() {
		return periodoFecha;
	}

	public void setPeriodoFecha(String periodoFecha) {
		this.periodoFecha = periodoFecha;
	}

	public Long getNomina() {
		return nomina;
	}

	public void setNomina(Long nomina) {
		this.nomina = nomina;
	}

	public Date getFinicio() {
		return finicio;
	}

	public void setFinicio(Date finicio) {
		this.finicio = finicio;
	}

	public Date getFfin() {
		return ffin;
	}

	public void setFfin(Date ffin) {
		this.ffin = ffin;
	}

	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public double getDiasPagados() {
		return diasPagados;
	}

	public void setDiasPagados(double diasPagados) {
		this.diasPagados = diasPagados;
	}

	public long getNumempleado() {
		return numempleado;
	}

	public void setNumempleado(long numempleado) {
		this.numempleado = numempleado;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(String tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getCodigoubicacion() {
		return codigoubicacion;
	}

	public void setCodigoubicacion(String codigoubicacion) {
		this.codigoubicacion = codigoubicacion;
	}

	public Long getNumeroSeguridadSocial() {
		return numeroSeguridadSocial;
	}

	public void setNumeroSeguridadSocial(Long numeroSeguridadSocial) {
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}

	public Long getPlaza() {
		return plaza;
	}

	public void setPlaza(Long plaza) {
		this.plaza = plaza;
	}

	public String getCvepuesto() {
		return cvepuesto;
	}

	public void setCvepuesto(String cvepuesto) {
		this.cvepuesto = cvepuesto;
	}

	public List<DatosTalonDetalleVO> getListaPercepciones() {
		return listaPercepciones;
	}

	public void setListaPercepciones(List<DatosTalonDetalleVO> listaPercepciones) {
		this.listaPercepciones = listaPercepciones;
	}

	public List<DatosTalonDetalleVO> getListaDeducciones() {
		return listaDeducciones;
	}

	public void setListaDeducciones(List<DatosTalonDetalleVO> listaDeducciones) {
		this.listaDeducciones = listaDeducciones;
	}

	public String getContadores() {
		return contadores;
	}

	public void setContadores(String contadores) {
		this.contadores = contadores;
	}

	public Date getFechapago() {
		return fechapago;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public String getNumerocertificado() {
		return numerocertificado;
	}

	public void setNumerocertificado(String numerocertificado) {
		this.numerocertificado = numerocertificado;
	}

	public String getSellosre() {
		return sellosre;
	}

	public void setSellosre(String sellosre) {
		this.sellosre = sellosre;
	}

	public String getCadenaoriginal() {
		return cadenaoriginal;
	}

	public void setCadenaoriginal(String cadenaoriginal) {
		this.cadenaoriginal = cadenaoriginal;
	}

	public String getSellosat() {
		return sellosat;
	}

	public void setSellosat(String sellosat) {
		this.sellosat = sellosat;
	}

	public String getFechacertificacion() {
		return fechacertificacion;
	}

	public void setFechacertificacion(String fechacertificacion) {
		this.fechacertificacion = fechacertificacion;
	}

	public String getMetodopago() {
		return metodopago;
	}

	public void setMetodopago(String metodopago) {
		this.metodopago = metodopago;
	}

	public String getNomimadescripcion() {
		return nomimadescripcion;
	}

	public void setNomimadescripcion(String nomimadescripcion) {
		this.nomimadescripcion = nomimadescripcion;
	}

	public long getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(long ejercicio) {
		this.ejercicio = ejercicio;
	}

	public long getQnapago() {
		return qnapago;
	}

	public void setQnapago(long qnapago) {
		this.qnapago = qnapago;
	}

	public String getContenidoQR() {
		return contenidoQR;
	}

	public void setContenidoQR(String contenidoQR) {
		this.contenidoQR = contenidoQR;
	}

	public BigDecimal getIdXmlCfdi() {
		return idXmlCfdi;
	}

	public void setIdXmlCfdi(BigDecimal idXmlCfdi) {
		this.idXmlCfdi = idXmlCfdi;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNivelmando() {
		return nivelmando;
	}

	public void setNivelmando(String nivelmando) {
		this.nivelmando = nivelmando;
	}

	public long getIdcatnomina() {
		return idcatnomina;
	}

	public void setIdcatnomina(long idcatnomina) {
		this.idcatnomina = idcatnomina;
	}

	public String getSerietalon() {
		return serietalon;
	}

	public void setSerietalon(String serietalon) {
		this.serietalon = serietalon;
	}

	public String getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public String getEntidadlabora() {
		return entidadlabora;
	}

	public void setEntidadlabora(String entidadlabora) {
		this.entidadlabora = entidadlabora;
	}
}
