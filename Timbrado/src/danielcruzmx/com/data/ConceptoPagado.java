package danielcruzmx.com.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQuery;

@NamedNativeQuery( name="ConceptoPagado.NOMINAfolio",query = "" +
"		 SELECT HP_RFC, HP_FOLIO, HP_DIF_FOLIO, C.CP_NUM_CONCEPTO, C.ID_TIPO_CPTO, " +
"		 case when C.id_cpto_pago = '0A' then '42A' "+
"             when C.id_cpto_pago = '0B' then '42B' "+
"             when C.id_cpto_pago = '0C' then '199' "+
"             when C.id_cpto_pago = '0D' then '102' "+
"             when C.id_cpto_pago = '0E' then '140' "+
"             else LPAD(C.ID_CPTO_PAGO,3,'0') END id_cpto_pago, " +
"         C.CP_MONTO, CP.DESC_CON_PAG, SAT_2016, CP_TIENE_ISR_2016 " +
"    FROM SIREH.TN_HISTORICO_PAGO@PSIREH_CRA H, SIREH.TN_CONCEPTO_PAGADO@PSIREH_CRA C, SIREH.TD_EMPLEADO@PSIREH_CRA E, " +
"         SIREH.TC_CONCEPTO_PAGO@PSIREH_CRA CP, SIREH.TN_NOMINA@PSIREH_CRA R " +
"    WHERE H.HP_CONSEC_NOMINA = ?1 AND " +
"         H.HP_NUM_PENSION = 0 AND " + 
"         H.HP_FOLIO = C.CP_FOLIO AND " +
"         H.ID_PLAZA IN (6667,6495,6391,6464,5462,6385,12194,12810,9460,1015) AND  " +
"         H.HP_DIF_FOLIO = C.CP_DIF_FOLIO AND " +
"         C.ID_TIPO_CPTO <> 'C' AND " +
"         H.HP_RFC = E.RFC_EMPLEADO AND " +
"         C.ID_TIPO_CPTO = CP.ID_TIPO_CPTO(+) AND " +
"         C.ID_CPTO_PAGO = CP.ID_CONCEPTO(+) AND  " +
"         H.HP_QNA_PAGO = R.NOM_QNA_CAPTURA(+) AND " +
"         H.ID_TIPO_NOMINA = R.ID_TIPO_NOMINA(+) AND " +
"         H.HP_NUM_COMPLEM = R.NOM_NUM_COMPLEM(+) " +
"   ORDER BY HP_FOLIO, HP_RFC, C.ID_TIPO_CPTO, C.ID_CPTO_PAGO " +
" ", resultClass=ConceptoPagado.class)

@Entity
@IdClass(ConceptoPagadoPk.class)
public class ConceptoPagado implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String FIND_FOLIO = "ConceptoPagado.NOMINAfolio";
	
	@Column(name="HP_FOLIO")
	@Id
	private Integer folio;
	
	@Column(name="HP_DIF_FOLIO")
	@Id
	private Integer difFolio;
	
	@Column(name="CP_NUM_CONCEPTO")
	@Id	
	private Integer numConcepto;

	@Column(name="HP_RFC")
	private String  rfc;
	
	@Column(name="ID_TIPO_CPTO")
	private String  tipoCpto;
	
	@Column(name="ID_CPTO_PAGO")
	private String  cptoPago;
	
	@Column(name="CP_MONTO")
	private BigDecimal  monto;
	
	@Column(name="DESC_CON_PAG")
	private String  descripcion;
	
	@Column(name="SAT_2016")
	private String  cveSat;
	
	@Column(name="CP_TIENE_ISR_2016")
	private String  tieneIsr;
	
	
	public Integer getFolio() {
		return folio;
	}
	public void setFolio(Integer folio) {
		this.folio = folio;
	}
	public Integer getDifFolio() {
		return difFolio;
	}
	public void setDifFolio(Integer difFolio) {
		this.difFolio = difFolio;
	}
	public Integer getNumConcepto() {
		return numConcepto;
	}
	public void setNumConcepto(Integer numCpto) {
		this.numConcepto = numCpto;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getTipoCpto() {
		return tipoCpto;
	}
	public void setTipoCpto(String tipoCpto) {
		this.tipoCpto = tipoCpto;
	}
	public String getCptoPago() {
		return cptoPago;
	}
	public void setCptoPago(String cptoPago) {
		this.cptoPago = cptoPago;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getTieneISr() {
		return tieneIsr;
	}
	public void setTieneISr(String tieneISr) {
		this.tieneIsr = tieneISr;
	}
	public String getCveSat() {
		return cveSat;
	}
	public void setCveSat(String cveSat) {
		this.cveSat = cveSat;
	}
}
