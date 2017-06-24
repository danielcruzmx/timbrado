package danielcruzmx.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Temporal;

@NamedNativeQuery( name="Nominas.Ejerciciofind",query = "" +
"   SELECT ID_NOMINA, describe_edo_nom as ID_EDO_NOM, NOM_QNA_CAPTURA, NOM_NUM_COMPLEM, " +  
"	       ID_TIPO_NOMINA, NOM_INI_PAGO, NOM_FIN_PAGO, " +
"	       NOM_FEC_PAGO, NOM_REF_TIMBRADO, NOM_FEC_TIMBRADO, DESC_NOMINA " + 
"	 FROM   tn_NOMINA n, tc_estado_nomina e " +
"	 WHERE  n.ID_EDO_NOM IN ('S','C') " +
"	 and    nom_ref_timbrado is null " +
"	 and    substr(nom_qna_captura,1,4) >= 2017 " + 
"	 and    e.id_edo_nom = n.ID_EDO_NOM " +
"	 ORDER BY ID_NOMINA DESC " +
"  ", resultClass=NominasEjercicio.class)


@Entity
public class NominasEjercicio implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String FIND_EJERCICIO = "Nominas.Ejerciciofind";
	
	 @Column(name="ID_NOMINA")
	 @Id
	 private Integer id;
	
	 @Column(name="ID_EDO_NOM")
	 private String estado;
	    
	 @Column(name="NOM_QNA_CAPTURA")
	 private Integer quincena;
	    
	 @Column(name="NOM_NUM_COMPLEM")
	 private Integer complemento;
	 
	 @Column(name="ID_TIPO_NOMINA")
	 private String tipo;
	 
	 @Column(name="NOM_INI_PAGO")
	 @Temporal(javax.persistence.TemporalType.DATE)
	 private java.util.Date fecIni;
	 
	 @Column(name="NOM_FIN_PAGO")
	 @Temporal(javax.persistence.TemporalType.DATE)
	 private java.util.Date fecFin;
	 
	 @Column(name="NOM_FEC_PAGO")
	 @Temporal(javax.persistence.TemporalType.DATE)
	 private java.util.Date fecPago;
	 
	 @Column(name="NOM_FEC_TIMBRADO")
	 @Temporal(javax.persistence.TemporalType.DATE)
	 private java.util.Date fecTimbrado;
	 
	 @Column(name="NOM_REF_TIMBRADO")
	 private String situacion;
	 
	 @Column(name="DESC_NOMINA")
	 private String descripcion;

	 public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getComplemento() {
		return complemento;
	}

	public void setComplemento(Integer complemento) {
		this.complemento = complemento;
	}

	public Integer getQuincena() {
		return quincena;
	}

	public void setQuincena(Integer quincena) {
		this.quincena = quincena;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public java.util.Date getFecIni() {
		return fecIni;
	}

	public void setFecIni(java.util.Date fecIni) {
		this.fecIni = fecIni;
	}

	public java.util.Date getFecFin() {
		return fecFin;
	}

	public void setFecFin(java.util.Date fecFin) {
		this.fecFin = fecFin;
	}

	public java.util.Date getFecPago() {
		return fecPago;
	}

	public void setFecPago(java.util.Date fecPago) {
		this.fecPago = fecPago;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public java.util.Date getFecTimbrado() {
		return fecTimbrado;
	}

	public void setFecTimbrado(java.util.Date fecTimbrado) {
		this.fecTimbrado = fecTimbrado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
		return this.getId().toString() + "-" + this.getTipo() + "-" + 
	           this.getQuincena().toString() + "-C" + this.getComplemento().toString() + " " +
			   this.getEstado();
	}

}
