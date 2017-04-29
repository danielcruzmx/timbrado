package danielcruzmx.com.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DatosXMLTimbrado {

	// comprobante
	private String version;
	private String serie;
	private int folio;
	private int difFolio;
	private Date fecha;
	private String sello;
	private String formaPago;
	private String noCertificado;
	private String certificado;
	private BigDecimal subTotal;
	private BigDecimal descuento;
	private String moneda;
	private BigDecimal total;
	private String tipoDeComprobante;
	private String metodoDePago;
	private String lugarExpedicion;
	private String cadenaOriginal;
	
	// emisor
	private String rfcEmisor;
	private String nombreEmisor;
	private String regimen;
		
	// receptor
	private String rfcReceptor;
	private String nombreReceptor;
	private String usoCfdi;
	
	// conceptos
	private int    cantidad;
	private String cveUnidad;
	private String descripcionConcepto;
	private BigDecimal valorUnitario;
	private BigDecimal importe;
	private String claveProdServ;
	
	// nomina
	private String versionNomina;
	private String tipoNomina;
	private Date   fechaPago;
	private Date   fechaInicial;
	private Date   fechaFinal;
	private int    numDiasPagados;
	private BigDecimal totalPercepciones;
	private BigDecimal totalDeducciones;
	private BigDecimal totalOtrosPagos;
	
	// emisor nomina
	private String registroPatronal;
	
	// receptor nomina
	private String curp;
	private String nss;
	private Date   fechaInicioRelLab;
	private String antiguedad;
	private String tipoContrato;
	private String tipoJornada;
	private String tipoRegimen;
	private String numEmpleado;
	private String departamento;
	private String puesto;
	private String riesgoPuesto;
	private String periodicidadPago;
	private String banco;
	private String cuentaBancaria;
	private BigDecimal sdoBaseCotApor;
	private BigDecimal sdoDiarioIntegrado;
	private String cveEntFed;
	
	// percepciones nomina
	private BigDecimal totalSueldos;
	private BigDecimal totalSeparacionInd;
	private BigDecimal totalJubPensRet;
	private BigDecimal totalGravado;
	private BigDecimal totalExento;
	
	// deducciones nomina
	private BigDecimal totalOtrasDeducciones;
	private BigDecimal totalImpuestosRetenidos;
	
	//List <ConceptoPagoTimbrado> cptos;
	
	List <ConceptoPagoTimbrado> percepciones;
	List <ConceptoPagoTimbrado> deducciones;
	
	public DatosXMLTimbrado()  {
        super();
    }
	
	public List <ConceptoPagoTimbrado> getPercepciones() {
		return percepciones;
	}

	public void setPercepciones(List <ConceptoPagoTimbrado> percepciones) {
		this.percepciones = percepciones;
	}

	public List <ConceptoPagoTimbrado> getDeducciones() {
		return deducciones;
	}

	public void setDeducciones(List <ConceptoPagoTimbrado> deducciones) {
		this.deducciones = deducciones;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public BigDecimal getTotalPercepciones() {
		return totalPercepciones;
	}

	public void setTotalPercepciones(BigDecimal totalPercepciones) {
		this.totalPercepciones = totalPercepciones;
	}

	public BigDecimal getTotalDeducciones() {
		return totalDeducciones;
	}

	public void setTotalDeducciones(BigDecimal totalDeducciones) {
		this.totalDeducciones = totalDeducciones;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String numSerie) {
		this.serie = numSerie;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNoCertificado() {
		return noCertificado;
	}

	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getTipoDeComprobante() {
		return tipoDeComprobante;
	}

	public void setTipoDeComprobante(String tipoDeComprobante) {
		this.tipoDeComprobante = tipoDeComprobante;
	}

	public String getMetodoDePago() {
		return metodoDePago;
	}

	public void setMetodoDePago(String metodoDePago) {
		this.metodoDePago = metodoDePago;
	}

	public String getLugarExpedicion() {
		return lugarExpedicion;
	}

	public void setLugarExpedicion(String lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}

	public String getRfcEmisor() {
		return rfcEmisor;
	}

	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public String getRfcReceptor() {
		return rfcReceptor;
	}

	public void setRfcReceptor(String rfcReceptor) {
		this.rfcReceptor = rfcReceptor;
	}

	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
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

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getRegistroPatronal() {
		return registroPatronal;
	}

	public void setRegistroPatronal(String registroPatronal) {
		this.registroPatronal = registroPatronal;
	}

	public String getVersionNomina() {
		return versionNomina;
	}

	public void setVersionNomina(String versionNomina) {
		this.versionNomina = versionNomina;
	}

	public String getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public int getNumDiasPagados() {
		return numDiasPagados;
	}

	public void setNumDiasPagados(int numDiasPagados) {
		this.numDiasPagados = numDiasPagados;
	}

	public BigDecimal getTotalOtrosPagos() {
		return totalOtrosPagos;
	}

	public void setTotalOtrosPagos(BigDecimal totalOtrosPagos) {
		this.totalOtrosPagos = totalOtrosPagos;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public Date getFechaInicioRelLab() {
		return fechaInicioRelLab;
	}

	public void setFechaInicioRelLab(Date fechaInicioRelLab) {
		this.fechaInicioRelLab = fechaInicioRelLab;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public String getTipoRegimen() {
		return tipoRegimen;
	}

	public void setTipoRegimen(String tipoRegimen) {
		this.tipoRegimen = tipoRegimen;
	}

	public String getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto.replace(".","");
	}

	public String getRiesgoPuesto() {
		return riesgoPuesto;
	}

	public void setRiesgoPuesto(String riesgoPuesto) {
		this.riesgoPuesto = riesgoPuesto;
	}

	public String getPeriodicidadPago() {
		return periodicidadPago;
	}

	public void setPeriodicidadPago(String periodicidadPago) {
		this.periodicidadPago = periodicidadPago;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public BigDecimal getSdoBaseCotApor() {
		return sdoBaseCotApor;
	}

	public void setSdoBaseCotApor(BigDecimal sdoBaseCotApor) {
		this.sdoBaseCotApor = sdoBaseCotApor;
	}

	public String getCveEntFed() {
		return cveEntFed;
	}

	public void setCveEntFed(String cveEntFed) {
		this.cveEntFed = cveEntFed;
	}

	public BigDecimal getSdoDiarioIntegrado() {
		return sdoDiarioIntegrado;
	}

	public void setSdoDiarioIntegrado(BigDecimal sdoDiarioIntegrado) {
		this.sdoDiarioIntegrado = sdoDiarioIntegrado;
	}

	public BigDecimal getTotalSueldos() {
		return totalSueldos;
	}

	public void setTotalSueldos(BigDecimal totalSueldos) {
		this.totalSueldos = totalSueldos;
	}

	public BigDecimal getTotalSeparacionInd() {
		return totalSeparacionInd;
	}

	public void setTotalSeparacionInd(BigDecimal totalSeparacionInd) {
		this.totalSeparacionInd = totalSeparacionInd;
	}

	public BigDecimal getTotalJubPensRet() {
		return totalJubPensRet;
	}

	public void setTotalJubPensRet(BigDecimal totalJubPensRet) {
		this.totalJubPensRet = totalJubPensRet;
	}

	public BigDecimal getTotalExento() {
		return totalExento;
	}

	public void setTotalExento(BigDecimal totalExento) {
		this.totalExento = totalExento;
	}

	public BigDecimal getTotalGravado() {
		return totalGravado;
	}

	public void setTotalGravado(BigDecimal totalGravado) {
		this.totalGravado = totalGravado;
	}


	public BigDecimal getTotalImpuestosRetenidos() {
		return totalImpuestosRetenidos;
	}


	public void setTotalImpuestosRetenidos(BigDecimal totalImpuestosRetenidos) {
		this.totalImpuestosRetenidos = totalImpuestosRetenidos;
	}


	public BigDecimal getTotalOtrasDeducciones() {
		return totalOtrasDeducciones;
	}


	public void setTotalOtrasDeducciones(BigDecimal totalOtrasDeducciones) {
		this.totalOtrasDeducciones = totalOtrasDeducciones;
	}

	public int getDifFolio() {
		return difFolio;
	}

	public void setDifFolio(int difFolio) {
		this.difFolio = difFolio;
	}

	public String getCadenaOriginal() {
		return cadenaOriginal;
	}

	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}

	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
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

	public String getClaveProdServ() {
		return claveProdServ;
	}

	public void setClaveProdServ(String claveProdServ) {
		this.claveProdServ = claveProdServ;
	}
	
}
