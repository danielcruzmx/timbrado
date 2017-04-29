package danielcruzmx.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import danielcruzmx.data.ComprobanteDigital;

public class ComprobanteDigitalService {

	private String PERSISTENCE_UNIT_NAME = "BaseSireH";
	private EntityManagerFactory factory;
	private EntityManager em;
	
	
	public ComprobanteDigitalService()  {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
        //super();
    }
	
	public void beginTransaction(){
		em.getTransaction().begin();;		
	}
	
	public void commitTransaction(){
		em.getTransaction().commit();		
	}
	
	public void rollBackTransaction(){
		em.getTransaction().rollback();		
	}
	
	public void save(ComprobanteDigital comprobante){
		em.persist(comprobante);
	}
	
	public void close(){
		em.close();
	}
	
	public List<ComprobanteDigital> NOMINAfind(Integer idNomina)  {
        TypedQuery<ComprobanteDigital> query = 
        		em.createNamedQuery(ComprobanteDigital.FIND_NOMINA, ComprobanteDigital.class).setParameter(1, idNomina);
        return query.getResultList();
    }
	
}
