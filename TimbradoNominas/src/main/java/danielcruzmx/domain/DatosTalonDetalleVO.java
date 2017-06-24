package danielcruzmx.domain;

public class DatosTalonDetalleVO {

	private long idnominadetalle;
	private String concepto;
	private String descripcion;
	private double importe;
	private double exento;
	private String cvesat;
	private long idperded;

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getExento() {
		return exento;
	}

	public void setExento(double exento) {
		this.exento = exento;
	}

	public long getIdnominadetalle() {
		return idnominadetalle;
	}

	public void setIdnominadetalle(long idnominadetalle) {
		this.idnominadetalle = idnominadetalle;
	}

	public String getCvesat() {
		return cvesat;
	}

	public void setCvesat(String cvesat) {
		this.cvesat = cvesat;
	}

	public long getIdperded() {
		return idperded;
	}

	public void setIdperded(long idperded) {
		this.idperded = idperded;
	}
}
