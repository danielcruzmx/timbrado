package danielcruzmx.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import danielcruzmx.data.NominasEjercicio;

public class NominasEjercicioService {
	private String PERSISTENCE_UNIT_NAME = "BaseSireH";
	private EntityManagerFactory factory;
	private EntityManager em;

	public NominasEjercicioService()  {
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
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
	
	public void save(NominasEjercicio nomina){
		em.persist(nomina);
	}
	
	public void close(){
		em.close();
	}
	
    public List<NominasEjercicio> Ejerciciofind()  {
        TypedQuery<NominasEjercicio> query = 
        		em.createNamedQuery(NominasEjercicio.FIND_EJERCICIO, NominasEjercicio.class);
        return query.getResultList();
    }

}
