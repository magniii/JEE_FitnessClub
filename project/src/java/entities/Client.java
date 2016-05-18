package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author magniii
 */
@Entity
@Table(name = "CLIENT")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
    @NamedQuery(name = "Client.findByBirthdate", query = "SELECT c FROM Client c WHERE c.birthdate = :birthdate"),
    @NamedQuery(name = "Client.findByPersonId", query = "SELECT c FROM Client c WHERE c.personId.id = :personId")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_client")
    @Column(name = "ID")
    private Integer id;
    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private List<Doctorclients> doctorclientsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private List<Coachclients> coachclientsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private List<Application> applicationList;
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Person personId;

    public Client() {
    }

    public Client(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<Doctorclients> getDoctorclientsList() {
        return doctorclientsList;
    }

    public void setDoctorclientsList(List<Doctorclients> doctorclientsList) {
        this.doctorclientsList = doctorclientsList;
    }

    public List<Coachclients> getCoachclientsList() {
        return coachclientsList;
    }

    public void setCoachclientsList(List<Coachclients> coachclientsList) {
        this.coachclientsList = coachclientsList;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ id=" + id + " ]";
    }
    
}
