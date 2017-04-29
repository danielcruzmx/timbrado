package danielcruzmx.domain;

import java.math.BigDecimal;

public class ConceptoDeduccion  {

		// deduccion
		private String  	tipoSAT;
		private String  	claveCptoMiNomina;
		private String  	cptoDescMiNomina;
		private BigDecimal 	importe;
		
		// incapacidades
		private BigDecimal 	diasIncapacidad;
		private String		tipoIncapacidad;
		
		public String getTipoSAT() {
			return tipoSAT;
		}
		public void setTipoSAT(String tipoSAT) {
			this.tipoSAT = tipoSAT;
		}
		public String getClaveCptoMiNomina() {
			return claveCptoMiNomina;
		}
		public void setClaveCptoMiNomina(String claveCptoMiNomina) {
			this.claveCptoMiNomina = claveCptoMiNomina;
		}
		public String getCptoDescMiNomina() {
			return cptoDescMiNomina;
		}
		public void setCptoDescMiNomina(String cptoDescMiNomina) {
			this.cptoDescMiNomina = cptoDescMiNomina;
		}
		public BigDecimal getImporte() {
			return importe;
		}
		public void setImporte(BigDecimal importe) {
			this.importe = importe;
		}
		public BigDecimal getDiasIncapacidad() {
			return diasIncapacidad;
		}
		public void setDiasIncapacidad(BigDecimal diasIncapacidad) {
			this.diasIncapacidad = diasIncapacidad;
		}
		public String getTipoIncapacidad() {
			return tipoIncapacidad;
		}
		public void setTipoIncapacidad(String tipoIncapacidad) {
			this.tipoIncapacidad = tipoIncapacidad;
		}
		
	 	
}
