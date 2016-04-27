package session;

import entities.Coachclients;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author magniii
 */
@Stateless
public class CoachclientsFacade extends AbstractFacade<Coachclients> {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoachclientsFacade() {
        super(Coachclients.class);
    }
    
}
