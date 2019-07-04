/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
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

/**
 *
 * @author admin
 */
@Entity
@Table(name = "medico")
@NamedQueries({
    @NamedQuery(name = "MedicoEntity.findAll", query = "SELECT m FROM MedicoEntity m")
    , @NamedQuery(name = "MedicoEntity.findByIdMedico", query = "SELECT m FROM MedicoEntity m WHERE m.idMedico = :idMedico")
    , @NamedQuery(name = "MedicoEntity.findByEmailMedico", query = "SELECT m FROM MedicoEntity m WHERE m.emailMedico = :emailMedico")
    , @NamedQuery(name = "MedicoEntity.findByEstadoMedico", query = "SELECT m FROM MedicoEntity m WHERE m.estadoMedico = :estadoMedico")})
public class MedicoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idMedico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String emailMedico;
    @Basic(optional = false)
    @NotNull
    private boolean estadoMedico;
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    @OneToOne(cascade=CascadeType.PERSIST)
    private PersonaEntity idPersona;
    @JoinColumn(name = "idEspecialidad", referencedColumnName = "idEspecialidad")
    @ManyToOne
    private EspecialidadEntity idEspecialidad;

    public MedicoEntity() {
    }

    public MedicoEntity(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public MedicoEntity(Integer idMedico, String emailMedico, boolean estadoMedico) {
        this.idMedico = idMedico;
        this.emailMedico = emailMedico;
        this.estadoMedico = estadoMedico;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public String getEmailMedico() {
        return emailMedico;
    }

    public void setEmailMedico(String emailMedico) {
        this.emailMedico = emailMedico;
    }

    public boolean getEstadoMedico() {
        return estadoMedico;
    }

    public void setEstadoMedico(boolean estadoMedico) {
        this.estadoMedico = estadoMedico;
    }

    public PersonaEntity getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(PersonaEntity idPersona) {
        this.idPersona = idPersona;
    }

    public EspecialidadEntity getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(EspecialidadEntity idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedico != null ? idMedico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicoEntity)) {
            return false;
        }
        MedicoEntity other = (MedicoEntity) object;
        if ((this.idMedico == null && other.idMedico != null) || (this.idMedico != null && !this.idMedico.equals(other.idMedico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.MedicoEntity[ idMedico=" + idMedico + " ]";
    }
    
}
