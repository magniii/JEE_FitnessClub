package session;

import entities.Client;
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
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    
    public Client getClientByPersonId(Object personId){
        try {
            Query q = em.createNamedQuery("Client.findByPersonId", Client.class);
            q.setParameter("personId", personId);
            return (Client)q.getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
    }
    
    public List<Client> getClients(){
        try {
            em.flush();
            Query q = em.createNamedQuery("Client.findAll", Client.class);
            return q.getResultList();
        }catch(NoResultException ex){
            return null;
        }
    }
}
