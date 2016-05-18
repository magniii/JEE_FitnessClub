package managed;

import auth.ClientAuthBean;
import entities.Application;
import entities.Client;
import entities.Coach;
import entities.Coachclients;
import entities.Doctor;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import session.ApplicationFacade;
import session.ApplicationStateFacade;
import session.ClientFacade;
import session.CoachFacade;
import session.CoachclientsFacade;
import session.DoctorFacade;
import session.DoctorclientsFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "clientIndexBean")
@RequestScoped
public class ClientIndexBean {

    @EJB
    DoctorclientsFacade dcf;
    
    @EJB
    DoctorFacade df;
    
    @EJB
    CoachFacade cof;
    
    @EJB
    ClientFacade cf;
    
    @EJB
    ApplicationStateFacade asf;
    
    @EJB
    ApplicationFacade af;
    
    @EJB
    CoachclientsFacade ccf;
    
    @ManagedProperty(value = "#{clientAuthBean}")
    ClientAuthBean cab;
    
    String currAppStateInfo;
    String tmpAppText;
    boolean showText;
    boolean showForm;
    boolean renderRequestCoach;
    List<Coach> coaches;
    
    public ClientIndexBean() {
    }
    
    @PostConstruct
    public void init(){
        currAppStateInfo = getAppState();
        showText = (af.getAppByClient(cab.getClient()).getStateId().getId() == 5) ||
                (af.getAppByClient(cab.getClient()).getStateId().getId() == 11);
        showForm = af.getAppByClient(cab.getClient()).getStateId().getId() == 9;
        tmpAppText = showAppText();
        renderRequestCoach = af.getAppByClient(cab.getClient()).getStateId().getId() == 7;
        coaches = cof.findAll();
    }

    public boolean isRenderRequestCoach() {
        return renderRequestCoach;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public boolean isShowText() {
        return showText;
    }

    public boolean isShowForm() {
        return showForm;
    }
    
    public String getCurrAppStateInfo() {
        return currAppStateInfo;
    }

    public String getTmpAppText() {
        return tmpAppText;
    }

    public void setTmpAppText(String tmpAppText) {
        this.tmpAppText = tmpAppText;
    }
    
    public ClientAuthBean getCab() {
        return cab;
    }

    public void setCab(ClientAuthBean cab) {
        this.cab = cab;
    }
    
    public String getAppState(){
        int tmpAppState = af.getAppByClient(cab.getClient()).getStateId().getId();
        
        switch(tmpAppState){
            case 1:
                return "Please wait until manager accepts your application";
            case 2:
                return "Please wait for invite to doctor";
            case 3:
                Doctor doc = dcf.getDoctorByClientId(cab.getClient().getId());
                String docname = doc.getPersonId().getForname() + " " + doc.getPersonId().getSurname();
                return "Your doctor: " + docname;
            case 4:
                return "Please wait until manager offers you the contract";
            case 5:
                return "Please read the contract and sign it";
            case 6:
                return "You accepted the contract";
            case 7:
                return "Manager registered you. Please select coach";
            case 8:
                return "Please wait until coach accepts you request";
            case 9:
                return "Please fill the form";
            case 10:
                return "Please wait for your program";
            case 11:
                return "Your program";
            default:
                return "Something gone wrong";
        }
    }
    
    public String modifyAppState(){
        Application app = af.getAppByClient(cab.getClient());
        
        switch(app.getStateId().getId()){
            case 5: // accept the contract
                app.setStateId(asf.find(6));
                af.edit(app);
                break;
            case 7: //request coach
                assignClientToCoach();
                break;
            case 9: // fill form
                app.setStateId(asf.find(10));
                app.setText(tmpAppText);
                af.edit(app);
                break;
            case 11:
                //showProgram = false;
                break;
            default:
                break;
        }
        
        
        return "client.xhtml?faces-redirect=true";
    }

    public String showAppText(){
        Application app = af.getAppByClient(cab.getClient());
        
        return app.getText();
    }
    
    public String assignClientToCoach(){
        Map<String,String> params;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	Integer coachId = new Integer(params.get("coachid"));
        Integer clientId = cab.getClient().getId();
        
        if(clientId < 1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect client ID"));
            return null;
        }
        
        if(coachId < 1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect coach ID"));
            return null;
        }
        
        if(ccf.getCoachByClientId(clientId) != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client already has a coach"));
            return null;
        }
        
        if(cof.find(coachId) != null){
            Client c = cf.find(clientId);
            Coach co = cof.find(coachId);
            Coachclients cc = new Coachclients();
            cc.setClientId(c);
            cc.setCoachId(co);
            c.getCoachclientsList().add(cc);
            co.getCoachclientsList().add(cc);
            
            ccf.create(cc);
            
            c.getApplicationList().get(0).setStateId(asf.find(8));
            af.edit(c.getApplicationList().get(0));
            
            return "client.xhtml?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No such coach"));
            return null;
        }
    }
}
