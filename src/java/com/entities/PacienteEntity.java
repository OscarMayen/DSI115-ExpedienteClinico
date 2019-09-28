/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josue
 */
@Entity
@Table(name = "Paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PacienteEntity.findAll", query = "SELECT p FROM PacienteEntity p")
    , @NamedQuery(name = "PacienteEntity.findByIdPaciente", query = "SELECT p FROM PacienteEntity p WHERE p.idPaciente = :idPaciente")
    , @NamedQuery(name = "PacienteEntity.findByNombreResponsable", query = "SELECT p FROM PacienteEntity p WHERE p.nombreResponsable = :nombreResponsable")
    , @NamedQuery(name = "PacienteEntity.findByTelefonoEmergencia", query = "SELECT p FROM PacienteEntity p WHERE p.telefonoEmergencia = :telefonoEmergencia")
    , @NamedQuery(name = "PacienteEntity.findByVinculoResponsable", query = "SELECT p FROM PacienteEntity p WHERE p.vinculoResponsable = :vinculoResponsable")
    , @NamedQuery(name = "PacienteEntity.findByEstadoCivil", query = "SELECT p FROM PacienteEntity p WHERE p.estadoCivil = :estadoCivil")
    , @NamedQuery(name = "PacienteEntity.findByEstadoPaciente", query = "SELECT p FROM PacienteEntity p WHERE p.estadoPaciente = :estadoPaciente")})
public class PacienteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idPaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nombreResponsable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    private String telefonoEmergencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String vinculoResponsable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String estadoCivil;
    @Basic(optional = false)
    @NotNull
    private boolean estadoPaciente;
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    @OneToOne( cascade =CascadeType.ALL )
    private PersonaEntity idPersona;
    @OneToMany(mappedBy = "idPaciente")
    private List<CitasEntity> citasEntityList;
    @OneToMany(mappedBy = "idPaciente")
    private List<ConsultaEntity> consultaEntityList;
    @OneToMany(mappedBy = "idPaciente")
    private List<AntecedentesEntity> antecedentesEntityList;

    public PacienteEntity() {
    }

    public PacienteEntity(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public PacienteEntity(Integer idPaciente, String nombreResponsable, String telefonoEmergencia, String vinculoResponsable, String estadoCivil, boolean estadoPaciente) {
        this.idPaciente = idPaciente;
        this.nombreResponsable = nombreResponsable;
        this.telefonoEmergencia = telefonoEmergencia;
        this.vinculoResponsable = vinculoResponsable;
        this.estadoCivil = estadoCivil;
        this.estadoPaciente = estadoPaciente;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public String getVinculoResponsable() {
        return vinculoResponsable;
    }

    public void setVinculoResponsable(String vinculoResponsable) {
        this.vinculoResponsable = vinculoResponsable;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public boolean getEstadoPaciente() {
        return estadoPaciente;
    }

    public void setEstadoPaciente(boolean estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }

    public PersonaEntity getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(PersonaEntity idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public List<CitasEntity> getCitasEntityList() {
        return citasEntityList;
    }

    public void setCitasEntityList(List<CitasEntity> citasEntityList) {
        this.citasEntityList = citasEntityList;
    }

    @XmlTransient
    public List<ConsultaEntity> getConsultaEntityList() {
        return consultaEntityList;
    }

    public void setConsultaEntityList(List<ConsultaEntity> consultaEntityList) {
        this.consultaEntityList = consultaEntityList;
    }

    @XmlTransient
    public List<AntecedentesEntity> getAntecedentesEntityList() {
        return antecedentesEntityList;
    }

    public void setAntecedentesEntityList(List<AntecedentesEntity> antecedentesEntityList) {
        this.antecedentesEntityList = antecedentesEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PacienteEntity)) {
            return false;
        }
        PacienteEntity other = (PacienteEntity) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.PacienteEntity[ idPaciente=" + idPaciente + " ]";
    }
    
}
