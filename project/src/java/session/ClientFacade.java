package session;

import entities.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
        Query q = em.createNamedQuery("Client.findByPersonId", Client.class);
        q.setParameter("personId", personId);
        return (Client)q.getSingleResult();
    }
}
