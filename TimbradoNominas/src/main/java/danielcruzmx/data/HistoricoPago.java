package danielcruzmx.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Temporal;

@NamedNativeQuery( name="Historico.NOMINAfind",query = "" +
		"           SELECT HP_RFC, PRIMER_APELLIDO, SEGUNDO_APELLIDO, NOMBRE_EMPLEADO, HP_FOLIO, SERIE_RECIBO, CODICION_PAGO, TIPO_CAMBIO,  "+  
		"                    MONEDA, METODO_PAGO, LUGAR, NUM_CTA, REG_PATRONAL, RFC, CURP_EMPLEADO, TIPO_REG, NSS, FECHA_PAGO, INICIO_PAGO,  "+
		"                    FINAL_PAGO, DIAS_PAGADOS, DEPTO, CLABE, BANCO, FECHA_ING, ANTIGUEDAD, PUESTO, TIPO_CONTRATA, JORNADA, TIPO_NOMINA, PERIODICIDAD, "+  
		"                    SDO_BASE, RIESGO, SAL_DIA, DIAS_INCAPACIDAD, TIPO_INCAPACIDAD,   "+
		"                    DESCUENTO, DIAS_HORAS_EXTRA, TIPO_HORAS_EXT, HORAS_EXTRA, IMPORTE_PAG, HP_DIF_FOLIO, HP_CONSEC_NOMINA "+  
		"           FROM (SELECT H.HP_RFC, NVL(E.PRIMER_APELLIDO,' ') AS PRIMER_APELLIDO, NVL(E.SEGUNDO_APELLIDO,' ') AS SEGUNDO_APELLIDO, NVL(E.NOMBRE_EMPLEADO,' ') AS NOMBRE_EMPLEADO, H.HP_FOLIO, '' SERIE_RECIBO, "+  
		"           'Contado' CODICION_PAGO,  "+
		"           '1.00' TIPO_CAMBIO,'MXN' MONEDA,"+  
		"           CASE WHEN SUBSTR(H.HP_CLABE,1,3) = '000' THEN 'Cheque' ELSE 'Transferencia' END METODO_PAGO,"+  
		"           'México D.F.' LUGAR,  "+
		"           CASE WHEN SUBSTR(H.HP_CLABE,1,3) <> '000' THEN '6719' ELSE '6784' END NUM_CTA,"+  
		"           '0000699900' REG_PATRONAL, H.HP_RFC RFC, E.CURP_EMPLEADO,  "+
		"           CASE WHEN H.ID_GRUPOPAGO_NOM IN ('E','V','J') THEN '02'  "+
		"                WHEN H.ID_GRUPOPAGO_NOM = 'H' THEN '09' END TIPO_REG, "+ 
		"           TRIM(nvl(E.IMSS_ISSSTE,'00000000000')) NSS, TO_CHAR(R.NOM_FEC_DEPOSITO, 'YYYY-MM-DD') FECHA_PAGO,"+  
		"           TO_CHAR(H.HP_INI_PAGO, 'YYYY-MM-DD') INICIO_PAGO,  "+
		"           TO_CHAR(H.HP_FIN_PAGO, 'YYYY-MM-DD') FINAL_PAGO,  "+
		"          (H.HP_FIN_PAGO - H.HP_INI_PAGO) + 1 DIAS_PAGADOS, H.ID_UNIDAD_NOM||' '||U.DESC_UNIDAD DEPTO,"+  
		"           CASE WHEN SUBSTR(H.HP_CLABE,1,3) = '000' THEN '0' ELSE LPAD(H.HP_CLABE,18,'0') END CLABE,  "+
		"           CASE WHEN SUBSTR(H.HP_CLABE,1,3) = '000' THEN '000' ELSE SUBSTR(H.HP_CLABE,1,3) END BANCO,"+  
		"           TO_CHAR(E.INGRESO_DEPENDENCIA, 'YYYY-MM-DD') FECHA_ING,  "+
		"           LTRIM((TO_CHAR(ROUND(ABS((((H.HP_FIN_PAGO - E.INGRESO_DEPENDENCIA) + 1)/365)*52)),'99999'))) ANTIGUEDAD,"+  
		"           H.ID_TIPO_NOMINA||'-'||SUBSTR(H.HP_QNA_PAGO,1,4)||'-'||SUBSTR(H.HP_QNA_PAGO,5,2)||'-'||LPAD(TRIM(H.HP_NUM_COMPLEM),2,'0')|| "+  
		"           LPAD(H.ID_PLAZA, 6,'0')||RPAD(TRIM(A.ID_NIVEL_PTO),4,' ')||H.ID_UNIDAD_NOM||'-'||  "+
		"           SUBSTR(LPAD(H.ID_ZONA_DIST_NOM ,5,'0'),1,2)||'-'||SUBSTR(LPAD(H.ID_ZONA_DIST_NOM,5,'0'),3,3)|| LPAD(H.HP_NUM_PENSION,2,'0')|| "+  
		"           LPAD(H.HP_PCP_PAGADOS,3,'0')||' de '||LPAD(H.HP_PCP_POR_PAGAR,3,'0')||H.ID_PUESTO_NOM||SUBSTR(TRIM(P.DESC_PUESTO),1,45) PUESTO,  "+
		"           CASE WHEN H.ID_GRUPOPAGO_NOM = 'E' THEN '01' " +
		"                WHEN H.ID_GRUPOPAGO_NOM = 'J' THEN '01' ELSE '03' END TIPO_CONTRATA,  " +
		"           CASE WHEN A.ID_JERARQUIA >= '7' THEN '01' ELSE '03' END JORNADA,  "+
		"           CASE WHEN H.ID_TIPO_NOMINA = 'AC' THEN 'E' "+
		"                WHEN H.ID_TIPO_NOMINA = 'AG' THEN 'E'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'CJ' THEN 'O'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'DB' THEN 'E'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'DE' THEN 'E'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'DJ' THEN 'E'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'EX' THEN 'E' "+
		"                WHEN H.ID_TIPO_NOMINA = 'IN' THEN 'O'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'LA' THEN 'E'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'OB' THEN 'E'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'OD' THEN 'E'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'OR' THEN 'O'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'VE' THEN 'O' ELSE 'E' END TIPO_NOMINA, "+  
		"           CASE WHEN H.ID_TIPO_NOMINA = 'AC' THEN '99'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'AG' THEN '99'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'CJ' THEN '05'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'DB' THEN '99'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'DE' THEN '99'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'DJ' THEN '99'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'EX' THEN '99' "+
		"                WHEN H.ID_TIPO_NOMINA = 'IN' THEN '05'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'LA' THEN '99'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'OB' THEN '99'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'OD' THEN '09'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'OR' THEN '04'  "+
		"                WHEN H.ID_TIPO_NOMINA = 'VE' THEN '05' ELSE '99' END PERIODICIDAD, "+  
		"           0 SDO_BASE, 1 RIESGO, 0 SAL_DIA,  "+
		"           0 DIAS_INCAPACIDAD, '' TIPO_INCAPACIDAD, "+  
		"           '' DESCUENTO, '' DIAS_HORAS_EXTRA, '' TIPO_HORAS_EXT, '' HORAS_EXTRA, '' IMPORTE_PAG, "+  
		"           H.HP_DIF_FOLIO, H.HP_CONSEC_NOMINA   " +
		"      FROM SIREH.TN_HISTORICO_PAGO@PSIREH_CRA H, SIREH.TD_EMPLEADO@PSIREH_CRA E, SIREH.TC_UNIDAD@PSIREH_CRA U, " + 
		"           SIREH.TC_PUESTO@PSIREH_CRA P, SIREH.TC_ATRIBUTO_PUESTO@PSIREH_CRA A, "+  
		"           SIREH.TC_NOMBRAMIENTO@PSIREH_CRA N,  SIREH.TN_NOMINA@PSIREH_CRA R   "+
		"     WHERE H.HP_CONSEC_NOMINA = ?1 AND  "+
		//           --H.ID_PLAZA IN (6667,6495,6391,6464,5462,6385,12194,12810,9460,1015) AND  
		"           H.HP_NUM_PENSION = 0 AND "+
		"           H.HP_RFC = E.RFC_EMPLEADO AND "+  
		"           H.ID_UNIDAD_NOM = U.ID_UNIDAD AND "+  
		"           U.UNI_CICLO = H.HP_CICLO_UNIDAD AND "+  
		"           H.ID_PUESTO_NOM = P.ID_PUESTO AND  "+
		"           H.ID_PUESTO_NOM = A.ID_ATRIBUTO_PUESTO AND "+  
		"           TO_CHAR(A.AP_FIN, 'DD/MM/YYYY') = '01/01/2099' AND "+  
		"           A.ID_NOMBRAMIENTO = N.ID_NOMBRAMIENTO(+) AND  "+
		"           H.HP_QNA_PAGO = R.NOM_QNA_CAPTURA(+) AND  "+
		"           H.ID_TIPO_NOMINA = R.ID_TIPO_NOMINA(+) AND  "+
		"           H.HP_NUM_COMPLEM = R.NOM_NUM_COMPLEM(+)  "+
		"  GROUP BY E.RFC_UNICO, E.PRIMER_APELLIDO, E.SEGUNDO_APELLIDO, E.NOMBRE_EMPLEADO, H.HP_FOLIO, "+  
		"           H.HP_DIF_FOLIO, H.HP_CLABE, H.HP_RFC, E.CURP_EMPLEADO, H.ID_GRUPOPAGO_NOM, E.IMSS_ISSSTE, "+  
		"           R.NOM_FEC_DEPOSITO, H.HP_INI_PAGO, H.HP_FIN_PAGO, H.ID_UNIDAD_NOM, U.DESC_UNIDAD,  "+
		"           E.INGRESO_DEPENDENCIA, H.ID_TIPO_NOMINA, H.HP_QNA_PAGO, H.HP_NUM_COMPLEM, H.ID_PLAZA, A.ID_NIVEL_PTO, "+  
		"           H.ID_ZONA_DIST_NOM, H.HP_NUM_PENSION, H.HP_PCP_PAGADOS, H.HP_PCP_POR_PAGAR, H.ID_PUESTO_NOM,  "+
		"           P.DESC_PUESTO, N.DESC_NOMBRAMIENTO, A.ID_JERARQUIA, HP_CONSEC_NOMINA   "+
		"  ORDER BY H.HP_FOLIO, E.RFC_UNICO DESC)  " +
		"  GROUP BY HP_RFC, PRIMER_APELLIDO, SEGUNDO_APELLIDO, NOMBRE_EMPLEADO, HP_FOLIO, SERIE_RECIBO, CODICION_PAGO, TIPO_CAMBIO, "+  
		"           MONEDA, METODO_PAGO, LUGAR, NUM_CTA, REG_PATRONAL, RFC, CURP_EMPLEADO, TIPO_REG, NSS, FECHA_PAGO, INICIO_PAGO,  "+
		"           FINAL_PAGO, DIAS_PAGADOS, DEPTO, CLABE, BANCO, FECHA_ING, ANTIGUEDAD, PUESTO, TIPO_CONTRATA, JORNADA, TIPO_NOMINA, PERIODICIDAD, "+  
		"           SDO_BASE, RIESGO, SAL_DIA,  DIAS_INCAPACIDAD, TIPO_INCAPACIDAD,  "+
		"           DESCUENTO, DIAS_HORAS_EXTRA, TIPO_HORAS_EXT, HORAS_EXTRA, IMPORTE_PAG, HP_DIF_FOLIO, HP_CONSEC_NOMINA "+  
		"  ORDER BY HP_FOLIO, RFC  "+
"  ", resultClass=HistoricoPago.class)

@Entity
public class HistoricoPago implements Serializable{
	private static final long serialVersionUID = 1L;
	public final static String FIND_NOMINA = "Historico.NOMINAfind";

	/***
	HP_RFC, PRIMER_APELLIDO, SEGUNDO_APELLIDO, NOMBRE_EMPLEADO, HP_FOLIO, SERIE_RECIBO, CODICION_PAGO, TIPO_CAMBIO,
    MONEDA, METODO_PAGO, LUGAR, NUM_CTA, REG_PATRONAL, RFC, CURP_EMPLEADO, TIPO_REG, NSS, FECHA_PAGO, INICIO_PAGO,
    FINAL_PAGO, DIAS_PAGADOS, DEPTO, CLABE, BANCO, FECHA_ING, ANTIGUEDAD, PUESTO, TIPO_CONTRATA, JORNADA, PERIODICIDAD,
    SDO_BASE, RIESGO, SAL_DIA, TIPO_MOVTO, TIPO_PERCEP, TIPO_DEDUCC, CVE_CPTO, DESC_CON_PAG, DIAS_INCAPACIDAD, TIPO_INCAPACIDAD,
    DESCUENTO, DIAS_HORAS_EXTRA, TIPO_HORAS_EXT, HORAS_EXTRA, IMPORTE_PAG, HP_DIF_FOLIO, TIPO_CPTO, CPTO_PAGO, NUM_CPTO, MONTO_CPTO
	***/
	
    @Column(name="HP_RFC")
    private String rfcEmpleado;
    
    @Column(name="PRIMER_APELLIDO")
    private String primerApellido;
    
    @Column(name="SEGUNDO_APELLIDO")
    private String segundoApellido;
    
    @Column(name="NOMBRE_EMPLEADO")
    private String nombreEmpleado;
    
    @Column(name="HP_FOLIO")
    @Id
    private Integer folio;
    
    @Column(name="SERIE_RECIBO")
    private String serieRecibo;
    
    @Column(name="CONDICION_PAGO")
    private String condicionPago;  
    
    @Column(name="TIPO_CAMBIO")
    private Double tipoCambio;
    
    @Column(name="MONEDA")
    private String moneda;
    
    @Column(name="METODO_PAGO")
    private String metodoPago;
    
    @Column(name="LUGAR")
    private String lugar;
    
    @Column(name="NUM_CTA")
    private String numCta; 
    
    @Column(name="REG_PATRONAL")
    private String regPatronal;
    
    @Column(name="CURP_EMPLEADO")
    private String curpEmpleado;
    
    @Column(name="TIPO_REG")
    private String tipoReg;
    
    @Column(name="NSS")
    private String nssEmpleado;
 
    @Column(name="FECHA_PAGO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date fecPago;
    
    @Column(name="INICIO_PAGO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date fecIniPago;
    
    @Column(name="FINAL_PAGO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date fecFinPago;

    @Column(name="DIAS_PAGADOS")
    private Integer diasPagados;
    
    @Column(name="DEPTO")
    private String depto;
    
    @Column(name="CLABE")
    private String clabe;
    
    @Column(name="BANCO")
    private String banco;
    
    @Column(name="FECHA_ING")
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date fecIngreso;	

    @Column(name="ANTIGUEDAD")
    private Integer antiguedad;

    @Column(name="PUESTO")
    private String puesto;
    
    @Column(name="TIPO_CONTRATA")
    private String tipoContrata;
    
    @Column(name="JORNADA")
    private String jornada;

    @Column(name="TIPO_NOMINA")
    private String tipoNomina;
    
    @Column(name="PERIODICIDAD")
    private String periodicidad;

    @Column(name="SDO_BASE")
    private Double sdoBase;
    
    @Column(name="RIESGO")
    private Integer riesgo;
    
    @Column(name="SAL_DIA")
    private Double sdoDia;
    
    @Column(name="DIAS_INCAPACIDAD")
    private Integer diasIncapacidad;
    
    @Column(name="TIPO_INCAPACIDAD")
    private String tipoIncapacidad;
    
    @Column(name="DESCUENTO")
    private String descuento;
    
    @Column(name="DIAS_HORAS_EXTRA")
    private String diasHorasExtra;
    
    @Column(name="TIPO_HORAS_EXTRA")
    private String tipoHorasExtra;
    
    @Column(name="HORAS_EXTRA")
    private String horasExtra;
    
    @Column(name="IMPORTE_PAG")
    private String importePag;
    
    @Column(name="HP_DIF_FOLIO")
    private Integer hpDifFolio;
    
    @Column(name="HP_CONSEC_NOMINA")
    private Integer idNomina;

    
	public String getRfcEmpleado() {
		return rfcEmpleado;
	}

	public void setRfcEmpleado(String rfcEmpleado) {
		this.rfcEmpleado = rfcEmpleado;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public String getSerieRecibo() {
		return serieRecibo;
	}

	public void setSerieRecibo(String serieRecibo) {
		this.serieRecibo = serieRecibo;
	}

	public String getCondicionPago() {
		return condicionPago;
	}

	public void setCondicionPago(String condicionPago) {
		this.condicionPago = condicionPago;
	}

	public Double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNumCta() {
		return numCta;
	}

	public void setNumCta(String numCta) {
		this.numCta = numCta;
	}

	public String getRegPatronal() {
		return regPatronal;
	}

	public void setRegPatronal(String regPatronal) {
		this.regPatronal = regPatronal;
	}

	public String getCurpEmpleado() {
		return curpEmpleado;
	}

	public void setCurpEmpleado(String curpEmpleado) {
		this.curpEmpleado = curpEmpleado;
	}

	public String getTipoReg() {
		return tipoReg;
	}

	public void setTipoReg(String tipoReg) {
		this.tipoReg = tipoReg;
	}

	public String getNssEmpleado() {
		return nssEmpleado;
	}

	public void setNssEmpleado(String nssEmpleado) {
		this.nssEmpleado = nssEmpleado;
	}

	public java.util.Date getFecPago() {
		return fecPago;
	}

	public void setFecPago(java.util.Date fecPago) {
		this.fecPago = fecPago;
	}

	public java.util.Date getFecIniPago() {
		return fecIniPago;
	}

	public void setFecIniPago(java.util.Date fecIniPago) {
		this.fecIniPago = fecIniPago;
	}

	public java.util.Date getFecFinPago() {
		return fecFinPago;
	}

	public void setFecFinPago(java.util.Date fecFinPago) {
		this.fecFinPago = fecFinPago;
	}

	public Integer getDiasPagados() {
		return diasPagados;
	}

	public void setDiasPagados(Integer diasPagados) {
		this.diasPagados = diasPagados;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public java.util.Date getFecIngreso() {
		return fecIngreso;
	}

	public void setFecIngreso(java.util.Date fecIngreso) {
		this.fecIngreso = fecIngreso;
	}

	public Integer getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getTipoContrata() {
		return tipoContrata;
	}

	public void setTipoContrata(String tipoContrata) {
		this.tipoContrata = tipoContrata;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public String getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}

	public Double getSdoBase() {
		return sdoBase;
	}

	public void setSdoBase(Double sdoBase) {
		this.sdoBase = sdoBase;
	}

	public Integer getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(Integer riesgo) {
		this.riesgo = riesgo;
	}

	public Double getSdoDia() {
		return sdoDia;
	}

	public void setSdoDia(Double sdoDia) {
		this.sdoDia = sdoDia;
	}

	public Integer getDiasIncapacidad() {
		return diasIncapacidad;
	}

	public void setDiasIncapacidad(Integer diasIncapacidad) {
		this.diasIncapacidad = diasIncapacidad;
	}

	public String getTipoIncapacidad() {
		return tipoIncapacidad;
	}

	public void setTipoIncapacidad(String tipoIncapacidad) {
		this.tipoIncapacidad = tipoIncapacidad;
	}

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getDiasHorasExtra() {
		return diasHorasExtra;
	}

	public void setDiasHorasExtra(String diasHorasExtra) {
		this.diasHorasExtra = diasHorasExtra;
	}

	public String getTipoHorasExtra() {
		return tipoHorasExtra;
	}

	public void setTipoHorasExtra(String tipoHorasExtra) {
		this.tipoHorasExtra = tipoHorasExtra;
	}

	public String getHorasExtra() {
		return horasExtra;
	}

	public void setHorasExtra(String horasExtra) {
		this.horasExtra = horasExtra;
	}

	public String getImportePag() {
		return importePag;
	}

	public void setImportePAg(String importePag) {
		this.importePag = importePag;
	}

	public Integer getHpDifFolio() {
		return hpDifFolio;
	}

	public void setHpDifFolio(Integer hpDifFolio) {
		this.hpDifFolio = hpDifFolio;
	}

	public Integer getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(Integer idNomina) {
		this.idNomina = idNomina;
	}

	public String getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	
}
