package session;

import entities.Application;
import entities.Client;
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
public class ApplicationFacade extends AbstractFacade<Application> {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApplicationFacade() {
        super(Application.class);
    }
    
    public Application getAppByClient(Client client){
        try {
            Query q = em.createNamedQuery("Application.findByClientId", Application.class);
            q.setParameter("clientId", client);
            return (Application)q.getSingleResult();
        }catch(NoResultException | NullPointerException ex){
            return null;
        }
    } 
    
//    public String getAppStateInfo(Client c){
//        return "";
//    }
//    
//    public void modifyAppState(){
//        
//    }
}
