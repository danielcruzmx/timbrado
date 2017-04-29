package danielcruzmx.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import danielcruzmx.data.ConceptoPagado;

public class ConceptoPagadoService {
	
	private String PERSISTENCE_UNIT_NAME = "BaseSireH";
	private EntityManagerFactory factory;
    private EntityManager em;

	public ConceptoPagadoService()  {
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
    }
    
    public List<ConceptoPagado> NOMINAfolio(Integer idNomina)  {
        TypedQuery<ConceptoPagado> query = 
        		em.createNamedQuery(ConceptoPagado.FIND_FOLIO, ConceptoPagado.class).setParameter(1, idNomina);
        return query.getResultList();
    }
    

}
