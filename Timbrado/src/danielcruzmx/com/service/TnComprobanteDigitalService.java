package danielcruzmx.com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import danielcruzmx.com.data.TnComprobanteDig;

public class TnComprobanteDigitalService {

	private String PERSISTENCE_UNIT_NAME = "BaseSireH";
	private EntityManagerFactory factory;
	private EntityManager em;
	
	
	public TnComprobanteDigitalService()  {
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
	
	public void save(TnComprobanteDig comprobante){
		em.persist(comprobante);
	}
	
	public void close(){
		em.close();
	}
	
	public List<TnComprobanteDig> NOMINAfind(Integer idNomina)  {
        TypedQuery<TnComprobanteDig> query = 
        		em.createNamedQuery(TnComprobanteDig.FIND_NOMINA, TnComprobanteDig.class).setParameter(1, idNomina);
        return query.getResultList();
    }
	
}
