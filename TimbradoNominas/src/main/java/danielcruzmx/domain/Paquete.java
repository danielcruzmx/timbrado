package danielcruzmx.domain;

import java.math.BigDecimal;

public class Paquete {
	
	private Integer    totalRegs;
	private BigDecimal idPaquete;
	private BigDecimal idComprobante;
	private Integer    num;
	private String     xmlComp;
	
	public BigDecimal getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(BigDecimal idPaquete) {
		this.idPaquete = idPaquete;
	}
	public Integer getTotalRegs() {
		return totalRegs;
	}
	public void setTotalRegs(Integer totalRegs) {
		this.totalRegs = totalRegs;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getXmlComp() {
		return xmlComp;
	}
	public void setXmlComp(String xmlComp) {
		this.xmlComp = xmlComp;
	}
	public BigDecimal getIdComprobante() {
		return idComprobante;
	}
	public void setIdComprobante(BigDecimal idComprobante) {
		this.idComprobante = idComprobante;
	}
}
