package danielcruzmx.domain;

import java.math.BigDecimal;

public class ConceptoPercepcion {

		// percepcion
		private String  	tipoSAT;
		private String  	claveCptoMiNomina;
		private String  	cptoDescMiNomina;
		private BigDecimal  importeGravado;
		private BigDecimal  importeExcento;
		 	
		// horas extra
		private Integer  	diasHoras;
		private String	 	tipoHoras;
		private Integer  	horasExtra;
		private BigDecimal  importePagado;
			
		// jubilacion pension retiro
		private BigDecimal totalUnaExhibicion;
		private BigDecimal totalParcialidad;
		private BigDecimal montoDiario;
		
		// separacion indemnizacion
		private BigDecimal totalPagado;
		private Integer numAniosServ;
		private BigDecimal ultimoSdoMenOrd;

		// comun a jubilacion separacion
		private BigDecimal ingresoAcumulable;
		private BigDecimal ingresoNoAcumulable;
		
		
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
		public BigDecimal getImporteGravado() {
			return importeGravado;
		}
		public void setImporteGravado(BigDecimal importeGravado) {
			this.importeGravado = importeGravado;
		}
		public BigDecimal getImporteExcento() {
			return importeExcento;
		}
		public void setImporteExcento(BigDecimal importeExcento) {
			this.importeExcento = importeExcento;
		}
		public Integer getDiasHoras() {
			return diasHoras;
		}
		public void setDiasHoras(Integer diasHoras) {
			this.diasHoras = diasHoras;
		}
		public String getTipoHoras() {
			return tipoHoras;
		}
		public void setTipoHoras(String tipoHoras) {
			this.tipoHoras = tipoHoras;
		}
		public Integer getHorasExtra() {
			return horasExtra;
		}
		public void setHorasExtra(Integer horasExtra) {
			this.horasExtra = horasExtra;
		}
		public BigDecimal getImportePagado() {
			return importePagado;
		}
		public void setImportePagado(BigDecimal importePagado) {
			this.importePagado = importePagado;
		}
		public BigDecimal getTotalUnaExhibicion() {
			return totalUnaExhibicion;
		}
		public void setTotalUnaExhibicion(BigDecimal totalUnaExhibicion) {
			this.totalUnaExhibicion = totalUnaExhibicion;
		}
		public BigDecimal getTotalParcialidad() {
			return totalParcialidad;
		}
		public void setTotalParcialidad(BigDecimal totalParcialidad) {
			this.totalParcialidad = totalParcialidad;
		}
		public BigDecimal getMontoDiario() {
			return montoDiario;
		}
		public void setMontoDiario(BigDecimal montoDiario) {
			this.montoDiario = montoDiario;
		}
		public BigDecimal getIngresoAcumulable() {
			return ingresoAcumulable;
		}
		public void setIngresoAcumulable(BigDecimal ingresoAcumulable) {
			this.ingresoAcumulable = ingresoAcumulable;
		}
		public BigDecimal getTotalPagado() {
			return totalPagado;
		}
		public void setTotalPagado(BigDecimal totalPagado) {
			this.totalPagado = totalPagado;
		}
		public Integer getNumAniosServ() {
			return numAniosServ;
		}
		public void setNumAniosServ(Integer numAniosServ) {
			this.numAniosServ = numAniosServ;
		}
		public BigDecimal getUltimoSdoMenOrd() {
			return ultimoSdoMenOrd;
		}
		public void setUltimoSdoMenOrd(BigDecimal ultimoSdoMenOrd) {
			this.ultimoSdoMenOrd = ultimoSdoMenOrd;
		}
		public BigDecimal getIngresoNoAcumulable() {
			return ingresoNoAcumulable;
		}
		public void setIngresoNoAcumulable(BigDecimal ingresoNoAcumulable) {
			this.ingresoNoAcumulable = ingresoNoAcumulable;
		}

}
