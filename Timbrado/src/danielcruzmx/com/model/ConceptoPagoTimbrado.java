package danielcruzmx.com.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConceptoPagoTimbrado implements Serializable {
	 	private static final long serialVersionUID = 1L;
	 	
	 	private String  tipoPercepcion;
	 	private String  tipoDeduccion;
	 	private String  cveCptoTimbrado;
	 	private String  descripcionCpto;
	 	private String  tipoCptoNom;
	 	private String  cveCptoNom;
	 	private Integer numCpto;
	 	private BigDecimal montoCpto;
	 	private String  tieneIsr;
	 	private BigDecimal  montoGravado;
	 	private BigDecimal  montoExento;
	 	
		public String getTipoPercepcion() {
			return tipoPercepcion;
		}
		public void setTipoPercepcion(String tipoPercepcion) {
			this.tipoPercepcion = tipoPercepcion;
		}
		public String getTipoDeduccion() {
			return tipoDeduccion;
		}
		public void setTipoDeduccion(String tipoDeduccion) {
			this.tipoDeduccion = tipoDeduccion;
		}
		public String getCveCptoTimbrado() {
			return cveCptoTimbrado;
		}
		public void setCveCptoTimbrado(String cveCptoTimbrado) {
			this.cveCptoTimbrado = cveCptoTimbrado;
		}
		public String getDescripcionCpto() {
			return descripcionCpto;
		}
		public void setDescripcionCpto(String descripcionCpto) {
			this.descripcionCpto = descripcionCpto.replace(".","").replace("(", "").replace(")", "");
		}
		public String getTipoCptoNom() {
			return tipoCptoNom;
		}
		public void setTipoCptoNom(String tipoCptoNom) {
			this.tipoCptoNom = tipoCptoNom;
		}
		public String getCveCptoNom() {
			return cveCptoNom;
		}
		public void setCveCptoNom(String cveCptoNom) {
			this.cveCptoNom = cveCptoNom;
		}
		public Integer getNumCpto() {
			return numCpto;
		}
		public void setNumCpto(Integer numCpto) {
			this.numCpto = numCpto;
		}
		public BigDecimal getMontoCpto() {
			return montoCpto;
		}
		public void setMontoCpto(BigDecimal montoCpto) {
			this.montoCpto = montoCpto;
		}
		public String getTieneIsr() {
			return tieneIsr;
		}
		public void setTieneIsr(String tieneIsr) {
			this.tieneIsr = tieneIsr;
		}
		public BigDecimal getMontoGravado() {
			return montoGravado;
		}
		public void setMontoGravado(BigDecimal montoGravado) {
			this.montoGravado = montoGravado;
		}
		public BigDecimal getMontoExento() {
			return montoExento;
		}
		public void setMontoExento(BigDecimal montoExento) {
			this.montoExento = montoExento;
		}
}
