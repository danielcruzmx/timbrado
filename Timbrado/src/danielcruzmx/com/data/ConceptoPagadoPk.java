package danielcruzmx.com.data;

import java.io.Serializable;

public class ConceptoPagadoPk implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer folio;
    private Integer difFolio;
    private Integer numConcepto;
    
	public boolean equals(Object other) {
	    if (this == other)
	        return true;
	    if (!(other instanceof ConceptoPagadoPk))
	        return false;
	    ConceptoPagadoPk castOther = (ConceptoPagadoPk) other;
	    return folio.equals(castOther.folio) && difFolio.equals(castOther.difFolio);
	}

	public int hashCode() {
	    final int prime = 31;
	    int hash = 17;
	    hash = hash * prime + this.folio.hashCode();
	    hash = hash * prime + this.difFolio.hashCode();
	    hash = hash * prime + this.numConcepto.hashCode();
	    return hash;
	}
	
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
	public Integer numConcepto() {
		return numConcepto;
	}
	public void setFecFin(Integer numConcepto) {
		this.numConcepto = numConcepto;
	}
}
