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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "docentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docentes.findAll", query = "SELECT d FROM Docentes d")
    , @NamedQuery(name = "Docentes.findByDocId", query = "SELECT d FROM Docentes d WHERE d.docId = :docId")
    , @NamedQuery(name = "Docentes.findByDocDocumento", query = "SELECT d FROM Docentes d WHERE d.docDocumento = :docDocumento")
    , @NamedQuery(name = "Docentes.findByDocNombres", query = "SELECT d FROM Docentes d WHERE d.docNombres = :docNombres")
    , @NamedQuery(name = "Docentes.findByDocApellidos", query = "SELECT d FROM Docentes d WHERE d.docApellidos = :docApellidos")
    , @NamedQuery(name = "Docentes.findByDocCorreo", query = "SELECT d FROM Docentes d WHERE d.docCorreo = :docCorreo")
    , @NamedQuery(name = "Docentes.findByDocTipocontrato", query = "SELECT d FROM Docentes d WHERE d.docTipocontrato = :docTipocontrato")})
public class Docentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doc_id")
    private Integer docId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doc_documento")
    private long docDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "doc_nombres")
    private String docNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "doc_apellidos")
    private String docApellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "doc_correo")
    private String docCorreo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doc_tipocontrato")
    private int docTipocontrato;
    @OneToMany(mappedBy = "carDocente")
    private Collection<CargaAcademica> cargaAcademicaCollection;
    @JoinColumn(name = "doc_programa", referencedColumnName = "pro_id")
    @ManyToOne
    private Programas docPrograma;

    public Docentes() {
    }

    public Docentes(Integer docId) {
        this.docId = docId;
    }

    public Docentes(Integer docId, long docDocumento, String docNombres, String docApellidos, String docCorreo, int docTipocontrato) {
        this.docId = docId;
        this.docDocumento = docDocumento;
        this.docNombres = docNombres;
        this.docApellidos = docApellidos;
        this.docCorreo = docCorreo;
        this.docTipocontrato = docTipocontrato;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public long getDocDocumento() {
        return docDocumento;
    }

    public void setDocDocumento(long docDocumento) {
        this.docDocumento = docDocumento;
    }

    public String getDocNombres() {
        return docNombres;
    }
    
    public String getDocNombresApellidos(){
        return docNombres + " " + docApellidos;
    }

    public void setDocNombres(String docNombres) {
        this.docNombres = docNombres;
    }

    public String getDocApellidos() {
        return docApellidos;
    }

    public void setDocApellidos(String docApellidos) {
        this.docApellidos = docApellidos;
    }

    public String getDocCorreo() {
        return docCorreo;
    }

    public void setDocCorreo(String docCorreo) {
        this.docCorreo = docCorreo;
    }

    public int getDocTipocontrato() {
        return docTipocontrato;
    }

    public void setDocTipocontrato(int docTipocontrato) {
        this.docTipocontrato = docTipocontrato;
    }
    
    public String getLabelTipoContrato(){
        switch(this.docTipocontrato){
            case 1:
                return "Prestación de servicios";
            case 2:
                return "Planta";
            case 3:
                return "Catedra";
            default:
                return "otro";
        }
    }

    @XmlTransient
    public Collection<CargaAcademica> getCargaAcademicaCollection() {
        return cargaAcademicaCollection;
    }

    public void setCargaAcademicaCollection(Collection<CargaAcademica> cargaAcademicaCollection) {
        this.cargaAcademicaCollection = cargaAcademicaCollection;
    }

    public Programas getDocPrograma() {
        return docPrograma;
    }

    public void setDocPrograma(Programas docPrograma) {
        this.docPrograma = docPrograma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docId != null ? docId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docentes)) {
            return false;
        }
        Docentes other = (Docentes) object;
        if ((this.docId == null && other.docId != null) || (this.docId != null && !this.docId.equals(other.docId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return docNombres + " " + docApellidos;
    }
    
}
