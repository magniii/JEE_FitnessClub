package session;

import entities.ApplicationState;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author magniii
 */
@Stateless
public class ApplicationStateFacade extends AbstractFacade<ApplicationState> {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApplicationStateFacade() {
        super(ApplicationState.class);
    }
    
}
