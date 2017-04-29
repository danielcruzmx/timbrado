package danielcruzmx.com.model;

import java.math.BigDecimal;

public class PaqueteNomina {
	
	private BigDecimal idNomina;
	private BigDecimal idPaquete;
	private Long folio;
	private Long difFolio;
	private Integer totalRegs;
	private Integer num;
	private String  xmlComp;
	
	
	public String getId() {
		return this.getIdNomina().toString() + "_" + this.getIdPaquete().toString();
	}
	public BigDecimal getIdNomina() {
		return idNomina;
	}
	public void setIdNomina(BigDecimal idNomina) {
		this.idNomina = idNomina;
	}
	public BigDecimal getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(BigDecimal idPaquete) {
		this.idPaquete = idPaquete;
	}
	public Long getFolio() {
		return folio;
	}
	public void setFolio(Long folio) {
		this.folio = folio;
	}
	public Long getDifFolio() {
		return difFolio;
	}
	public void setDifFolio(Long difFolio) {
		this.difFolio = difFolio;
	}
	public Integer getTotalRegs() {
		return totalRegs;
	}
	public void setTotalRegs(Integer totalRegs) {
		this.totalRegs = totalRegs;
	}
	public String getIdComprobante() {
		return this.getFolio() + "_" + this.getDifFolio();
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
	


}
