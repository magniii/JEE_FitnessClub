package session;

import entities.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author magniii
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person>{

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }
    
    public Person getPerson(String login, String password){
        try{
            TypedQuery<Person> query = em.createNamedQuery("Person.findByLogin", Person.class);
            query.setParameter("login", login);
            Person p = query.getSingleResult();
            if (p.getPassword().equals(password)) return p;
        } catch (NoResultException ex){}
        return null;
    }
}
