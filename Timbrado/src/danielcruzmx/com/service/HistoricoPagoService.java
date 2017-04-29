package danielcruzmx.com.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import danielcruzmx.com.data.HistoricoPago;

public class HistoricoPagoService {
	
	private String PERSISTENCE_UNIT_NAME = "BaseSireH";
	private EntityManagerFactory factory;

	public HistoricoPagoService()  {
        super();
    }
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<HistoricoPago> NOMINAfind(Integer idNomina)  {
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

        TypedQuery<HistoricoPago> query = 
        		em.createNamedQuery(HistoricoPago.FIND_NOMINA, HistoricoPago.class).setParameter(1, idNomina);
        return query.getResultList();
    }
    

}
