package auth;

import entities.Coach;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import session.CoachFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "coachAuthBean")
@SessionScoped
public class CoachAuthBean extends AuthBean implements Serializable{

    @EJB
    CoachFacade cf;
    
    Coach coach;
    
    public CoachAuthBean() {
    }

    @Override
    protected String authorize(Integer personId) {
        coach = cf.getCoachByPersonId(personId);
        
        if(coach == null){
            return null;
        }else{
            setLoggedIn(true);
            return "/coach/coach.xhtml?faces-redirect=true";
        }
    }
    
}
