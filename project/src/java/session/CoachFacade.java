package session;

import entities.Coach;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author magniii
 */
@Stateless
public class CoachFacade extends AbstractFacade<Coach> {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoachFacade() {
        super(Coach.class);
    }
    
    public Coach getCoachByPersonId(Object personId){
        Query q = em.createNamedQuery("Coach.findByPersonId", Coach.class);
        q.setParameter("personId", personId);
        return (Coach)q.getSingleResult();
    }
}
