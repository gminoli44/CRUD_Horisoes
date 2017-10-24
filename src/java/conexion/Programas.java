/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gersain
 */
@Entity
@Table(name = "programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programas.findAll", query = "SELECT p FROM Programas p")
    , @NamedQuery(name = "Programas.findByProId", query = "SELECT p FROM Programas p WHERE p.proId = :proId")
    , @NamedQuery(name = "Programas.findByProNombre", query = "SELECT p FROM Programas p WHERE p.proNombre = :proNombre")})
public class Programas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pro_id")
    private Integer proId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pro_nombre")
    private String proNombre;
    @OneToMany(mappedBy = "docPrograma")
    private Collection<Docentes> docentesCollection;

    public Programas() {
    }

    public Programas(Integer proId) {
        this.proId = proId;
    }

    public Programas(Integer proId, String proNombre) {
        this.proId = proId;
        this.proNombre = proNombre;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    @XmlTransient
    public Collection<Docentes> getDocentesCollection() {
        return docentesCollection;
    }

    public void setDocentesCollection(Collection<Docentes> docentesCollection) {
        this.docentesCollection = docentesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proId != null ? proId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programas)) {
            return false;
        }
        Programas other = (Programas) object;
        if ((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.proNombre;
    }
    
}
