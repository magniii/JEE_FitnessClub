package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author magniii
 */
@Entity
@Table(name = "DOCTORCLIENTS")
@NamedQueries({
    @NamedQuery(name = "Doctorclients.findAll", query = "SELECT d FROM Doctorclients d"),
    @NamedQuery(name = "Doctorclients.findById", query = "SELECT d FROM Doctorclients d WHERE d.id = :id"),
    @NamedQuery(name = "Doctorclients.findDoctorByClientId", query = "SELECT d.doctorId FROM Doctorclients d WHERE d.clientId.id = :clientId"),
    @NamedQuery(name = "Doctorclients.findDoctorsClients", query = "SELECT d.clientId FROM Doctorclients d WHERE d.doctorId.id = :doctorId")})
public class Doctorclients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_doctorclients")
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Doctor doctorId;

    public Doctorclients() {
    }

    public Doctorclients(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
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
        if (!(object instanceof Doctorclients)) {
            return false;
        }
        Doctorclients other = (Doctorclients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Doctorclients[ id=" + id + " ]";
    }
    
}
