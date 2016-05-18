package managed;

import auth.ManagerAuthBean;
import entities.Application;
import entities.Client;
import entities.Coach;
import entities.Doctor;
import entities.Doctorclients;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import session.ApplicationFacade;
import session.ApplicationStateFacade;
import session.ClientFacade;
import session.CoachFacade;
import session.DoctorFacade;
import session.DoctorclientsFacade;

/**
 *
 * @author magniii
 */
@ManagedBean(name = "managerIndexBean")
@SessionScoped
public class ManagerIndexBean implements Serializable{
    
    @EJB
    ClientFacade cf;
    
    @EJB
    CoachFacade cof;
    
    @EJB
    DoctorFacade df;
    
    @EJB
    ApplicationFacade af;
    
    @EJB
    ApplicationStateFacade asf;
    
    @EJB
    DoctorclientsFacade dcf;
    
    @ManagedProperty(value = "#{managerAuthBean}")
    ManagerAuthBean mab;
    
    List<Client> clients;
    List<Doctor> doctors;
    List<Coach> coaches;
    
    private Integer tmpDoctorID;
    private Integer tmpClientID;
    boolean renderAssignDoctor;
    boolean renderSendContract;
    String tmpText;

    public Integer getTmpDoctorID() {
        return tmpDoctorID;
    }

    public void setTmpDoctorID(Integer tmpDoctorID) {
        this.tmpDoctorID = tmpDoctorID;
    }

    public Integer getTmpClientID() {
        return tmpClientID;
    }

    public boolean isRenderAssignDoctor() {
        return renderAssignDoctor;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public String getTmpText() {
        return tmpText;
    }

    public void setTmpText(String tmpText) {
        this.tmpText = tmpText;
    }

    public boolean isRenderSendContract() {
        return renderSendContract;
    }
    
    public ManagerIndexBean() {
    }
    
    @PostConstruct
    public void init(){
        clients = cf.findAll();
        coaches = cof.findAll();
        doctors = df.findAll();
        tmpDoctorID = 0;
        tmpClientID = 0;
        tmpText = "";
        renderAssignDoctor = false;
        renderSendContract = false;
    }

    public ManagerAuthBean getMab() {
        return mab;
    }

    public void setMab(ManagerAuthBean mab) {
        this.mab = mab;
    }
    
    public String modifyAppStatus(){
        Map<String,String> params;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	Integer clientId = new Integer(params.get("modifyappstate"));

        Application app = cf.find(clientId).getApplicationList().get(0);
        
        tmpClientID = clientId;
        
        int stateId = app.getStateId().getId();
        switch(stateId){
            case 4: //offer contract
                app.setText(getContractText());
            case 1: //initial request  
            case 6: //register client
                renderAssignDoctor = false;
                app.setStateId(asf.find(stateId + 1));
                af.edit(app);
                break;
            case 2: //assign doctor
                renderAssignDoctor = true;
                break;
            default:
                renderAssignDoctor = false;
                break;
        }
        
        return "manager.xhtml?faces-redirect=true";
    }
    
    public String assignDoctorToClient(){
        if(tmpClientID < 1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect client ID"));
            return null;
        }
        
        if(tmpDoctorID < 1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect doctor ID"));
            return null;
        }
        
        if(dcf.getDoctorByClientId(tmpClientID) != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client already has a doctor"));
            return null;
        }
        
        if(df.find(tmpDoctorID) != null){
            Client c = cf.find(tmpClientID);
            Doctor d = df.find(tmpDoctorID);
            Doctorclients dc = new Doctorclients();
            dc.setClientId(c);
            dc.setDoctorId(d);
            c.getDoctorclientsList().add(dc);
            d.getDoctorclientsList().add(dc);
            
            dcf.create(dc);
            
            c.getApplicationList().get(0).setStateId(asf.find(3));
            af.edit(c.getApplicationList().get(0));
            
            renderAssignDoctor = false;
            
            return "manager.xhtml?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No such doctor"));
            return null;
        }
    }
    
    public String offerContractToClient(){
        if(tmpClientID < 1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect client ID"));
            return null;
        }
        
        Application app = cf.find(tmpClientID).getApplicationList().get(0);
        app.setText(tmpText);
        app.setStateId(asf.find(5));
        
        return "manager.xhtml?faces-redirect=true";
    }
    
    public String getContractText(){
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Duis rhoncus, nunc vitae convallis tristique, nulla mi tristique ante, ac dictum nisi orci eu leo. "
                + "Etiam molestie pellentesque condimentum. Curabitur in pretium orci, sed maximus enim. "
                + "Morbi quis pharetra metus. Nulla velit turpis, elementum vitae pharetra a, condimentum et est. Duis.";
    }
}
