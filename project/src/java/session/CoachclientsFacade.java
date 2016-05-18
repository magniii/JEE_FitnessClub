package session;

import entities.Client;
import entities.Coach;
import entities.Coachclients;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public List<Client> getCoachesClients(Object coachId){
        try {
            Query q = em.createNamedQuery("Coachclients.findCoachesClients", Client.class);
            q.setParameter("coachId", coachId);
            return q.getResultList();
        }catch(NoResultException ex){
            return null;
        }
    }
    
    public Coach getCoachByClientId(Object clientId){
        try{
            Query q = em.createNamedQuery("Coachclients.findCoachByClient", Coach.class);
            q.setParameter("clientId", clientId);
            return (Coach)q.getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
    }
}
