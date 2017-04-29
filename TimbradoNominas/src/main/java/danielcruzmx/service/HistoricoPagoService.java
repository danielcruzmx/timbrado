package danielcruzmx.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import danielcruzmx.data.HistoricoPago;

public class HistoricoPagoService {
	
	private String PERSISTENCE_UNIT_NAME = "BaseSireH";
	private EntityManagerFactory factory;
	private EntityManager em;

	public HistoricoPagoService()  {
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
    }
    
    public List<HistoricoPago> NOMINAfind(Integer idNomina)  {
        TypedQuery<HistoricoPago> query = 
        		em.createNamedQuery(HistoricoPago.FIND_NOMINA, HistoricoPago.class).setParameter(1, idNomina);
        return query.getResultList();
    }
}
