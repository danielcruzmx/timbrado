package danielcruzmx.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TN_COMPROBANTE_DIG database table.
 * 
 */
@Embeddable
public class ComprobanteDigitalPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CN_FOLIO")
	private long cnFolio;

	@Column(name="CN_DIF_FOLIO")
	private long cnDifFolio;

	public ComprobanteDigitalPK() {
	}
	public long getCnFolio() {
		return this.cnFolio;
	}
	public void setCnFolio(long cnFolio) {
		this.cnFolio = cnFolio;
	}
	public long getCnDifFolio() {
		return this.cnDifFolio;
	}
	public void setCnDifFolio(long cnDifFolio) {
		this.cnDifFolio = cnDifFolio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ComprobanteDigitalPK)) {
			return false;
		}
		ComprobanteDigitalPK castOther = (ComprobanteDigitalPK)other;
		return 
			(this.cnFolio == castOther.cnFolio)
			&& (this.cnDifFolio == castOther.cnDifFolio);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.cnFolio ^ (this.cnFolio >>> 32)));
		hash = hash * prime + ((int) (this.cnDifFolio ^ (this.cnDifFolio >>> 32)));
		
		return hash;
	}
}