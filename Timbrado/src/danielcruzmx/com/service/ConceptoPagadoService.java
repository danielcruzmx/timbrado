package danielcruzmx.com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import danielcruzmx.com.data.ConceptoPagado;

public class ConceptoPagadoService {
	
	private String PERSISTENCE_UNIT_NAME = "BaseSireH";
	private EntityManagerFactory factory;

	public ConceptoPagadoService()  {
        super();
    }
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<ConceptoPagado> NOMINAfolio(Integer idNomina)  {
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

        TypedQuery<ConceptoPagado> query = 
        		em.createNamedQuery(ConceptoPagado.FIND_FOLIO, ConceptoPagado.class).setParameter(1, idNomina);
        return query.getResultList();
    }
    

}
