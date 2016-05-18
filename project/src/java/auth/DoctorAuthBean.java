package auth;

import entities.Doctor;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import session.DoctorFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "doctorAuthBean")
@SessionScoped
public class DoctorAuthBean extends AuthBean implements Serializable{

    @EJB
    DoctorFacade df;
    
    Doctor doctor;
    
    public DoctorAuthBean() {}

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    protected String authorize(Integer personId) {
        doctor = df.getDoctorByPersonId(personId);
        
        if(doctor == null){
            return null;
        }else{
            setLoggedIn(true);
            return "/doctor/doctor.xhtml?faces-redirect=true";
        }
    }
    
}
