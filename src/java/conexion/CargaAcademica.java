/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gersain
 */
@Entity
@Table(name = "carga_academica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargaAcademica.findAll", query = "SELECT c FROM CargaAcademica c")
    , @NamedQuery(name = "CargaAcademica.findByCarId", query = "SELECT c FROM CargaAcademica c WHERE c.carId = :carId")
    , @NamedQuery(name = "CargaAcademica.findByCarGrupo", query = "SELECT c FROM CargaAcademica c WHERE c.carGrupo = :carGrupo")
    , @NamedQuery(name = "CargaAcademica.findByCarSala", query = "SELECT c FROM CargaAcademica c WHERE c.carSala = :carSala")
    , @NamedQuery(name = "CargaAcademica.findByCarDia", query = "SELECT c FROM CargaAcademica c WHERE c.carDia = :carDia")})
public class CargaAcademica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "car_id")
    private Integer carId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "car_grupo")
    private String carGrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "car_sala")
    private String carSala;
    @Size(max = 255)
    @Column(name = "car_dia")
    private String carDia;
    @JoinColumn(name = "car_asignatura", referencedColumnName = "asi_id")
    @ManyToOne
    private Asignaturas carAsignatura;
    @JoinColumn(name = "car_docente", referencedColumnName = "doc_id")
    @ManyToOne
    private Docentes carDocente;

    public CargaAcademica() {
    }

    public CargaAcademica(Integer carId) {
        this.carId = carId;
    }

    public CargaAcademica(Integer carId, String carGrupo, String carSala) {
        this.carId = carId;
        this.carGrupo = carGrupo;
        this.carSala = carSala;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarGrupo() {
        return carGrupo;
    }

    public void setCarGrupo(String carGrupo) {
        this.carGrupo = carGrupo;
    }

    public String getCarSala() {
        return carSala;
    }

    public void setCarSala(String carSala) {
        this.carSala = carSala;
    }

    public String getCarDia() {
        return carDia;
    }

    public void setCarDia(String carDia) {
        this.carDia = carDia;
    }

    public Asignaturas getCarAsignatura() {
        return carAsignatura;
    }

    public void setCarAsignatura(Asignaturas carAsignatura) {
        this.carAsignatura = carAsignatura;
    }

    public Docentes getCarDocente() {
        return carDocente;
    }

    public void setCarDocente(Docentes carDocente) {
        this.carDocente = carDocente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargaAcademica)) {
            return false;
        }
        CargaAcademica other = (CargaAcademica) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.CargaAcademica[ carId=" + carId + " ]";
    }
    
}
