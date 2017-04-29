package danielcruzmx.domain;

import java.math.BigDecimal;

public class ConceptoOtrosPagos {

	// otros pagos
	private String  	tipoSAT;
	private String  	claveCptoMiNomina;
	private String  	cptoDescMiNomina;
	private BigDecimal 	importe;

	// Subsidio al empleo
	private BigDecimal 	subsidioCausado;
	
	// Compensación saldos a favor
	private BigDecimal 	saldoAFavor;
	private BigDecimal 	anio;
	private BigDecimal 	remananteSaldo;
	
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
	public BigDecimal getSubsidioCausado() {
		return subsidioCausado;
	}
	public void setSubsidioCausado(BigDecimal subsidioCausado) {
		this.subsidioCausado = subsidioCausado;
	}
	public BigDecimal getSaldoAFavor() {
		return saldoAFavor;
	}
	public void setSaldoAFavor(BigDecimal saldoAFavor) {
		this.saldoAFavor = saldoAFavor;
	}
	public BigDecimal getAnio() {
		return anio;
	}
	public void setAnio(BigDecimal anio) {
		this.anio = anio;
	}
	public BigDecimal getRemananteSaldo() {
		return remananteSaldo;
	}
	public void setRemananteSaldo(BigDecimal remananteSaldo) {
		this.remananteSaldo = remananteSaldo;
	}
}
