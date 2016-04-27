package session;

import entities.Doctorclients;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author magniii
 */
@Stateless
public class DoctorclientsFacade extends AbstractFacade<Doctorclients> {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DoctorclientsFacade() {
        super(Doctorclients.class);
    }
    
}
