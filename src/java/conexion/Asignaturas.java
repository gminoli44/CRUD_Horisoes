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
@Table(name = "asignaturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignaturas.findAll", query = "SELECT a FROM Asignaturas a")
    , @NamedQuery(name = "Asignaturas.findByAsiId", query = "SELECT a FROM Asignaturas a WHERE a.asiId = :asiId")
    , @NamedQuery(name = "Asignaturas.findByAsiNombre", query = "SELECT a FROM Asignaturas a WHERE a.asiNombre = :asiNombre")
    , @NamedQuery(name = "Asignaturas.findByAsiTipo", query = "SELECT a FROM Asignaturas a WHERE a.asiTipo = :asiTipo")
    , @NamedQuery(name = "Asignaturas.findByAsiCreditos", query = "SELECT a FROM Asignaturas a WHERE a.asiCreditos = :asiCreditos")})
public class Asignaturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "asi_id")
    private Integer asiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "asi_nombre")
    private String asiNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "asi_tipo")
    private String asiTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "asi_creditos")
    private int asiCreditos;
    @OneToMany(mappedBy = "carAsignatura")
    private Collection<CargaAcademica> cargaAcademicaCollection;

    public Asignaturas() {
    }

    public Asignaturas(Integer asiId) {
        this.asiId = asiId;
    }

    public Asignaturas(Integer asiId, String asiNombre, String asiTipo, int asiCreditos) {
        this.asiId = asiId;
        this.asiNombre = asiNombre;
        this.asiTipo = asiTipo;
        this.asiCreditos = asiCreditos;
    }

    public Integer getAsiId() {
        return asiId;
    }

    public void setAsiId(Integer asiId) {
        this.asiId = asiId;
    }

    public String getAsiNombre() {
        return asiNombre;
    }

    public void setAsiNombre(String asiNombre) {
        this.asiNombre = asiNombre;
    }

    public String getAsiTipo() {
        return asiTipo;
    }

    public void setAsiTipo(String asiTipo) {
        this.asiTipo = asiTipo;
    }

    public int getAsiCreditos() {
        return asiCreditos;
    }

    public void setAsiCreditos(int asiCreditos) {
        this.asiCreditos = asiCreditos;
    }

    @XmlTransient
    public Collection<CargaAcademica> getCargaAcademicaCollection() {
        return cargaAcademicaCollection;
    }

    public void setCargaAcademicaCollection(Collection<CargaAcademica> cargaAcademicaCollection) {
        this.cargaAcademicaCollection = cargaAcademicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asiId != null ? asiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignaturas)) {
            return false;
        }
        Asignaturas other = (Asignaturas) object;
        if ((this.asiId == null && other.asiId != null) || (this.asiId != null && !this.asiId.equals(other.asiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return asiNombre;
    }
    
}
