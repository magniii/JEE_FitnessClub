package session;

import entities.Client;
import entities.Doctor;
import entities.Doctorclients;
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
    
    public Doctor getDoctorByClientId(Object clientId){
        try {
            Query q = em.createNamedQuery("Doctorclients.findDoctorByClientId", Doctor.class);
            q.setParameter("clientId", clientId);
            return (Doctor)q.getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
        
    }
    
    public List<Client> getDoctorsClients(Object doctorId){
        Query q = em.createNamedQuery("Doctorclients.findDoctorsClients", Client.class);
        q.setParameter("doctorId", doctorId);
        return q.getResultList();
    }
}
