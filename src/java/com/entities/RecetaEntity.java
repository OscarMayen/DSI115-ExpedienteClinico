/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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

/**
 *
 * @author admin
 */
@Entity
@Table(name = "receta")
@NamedQueries({
    @NamedQuery(name = "RecetaEntity.findAll", query = "SELECT r FROM RecetaEntity r")
    , @NamedQuery(name = "RecetaEntity.findByIdReceta", query = "SELECT r FROM RecetaEntity r WHERE r.idReceta = :idReceta")
    , @NamedQuery(name = "RecetaEntity.findByNombrePaciente", query = "SELECT r FROM RecetaEntity r WHERE r.nombrePaciente = :nombrePaciente")
    , @NamedQuery(name = "RecetaEntity.findByApellidoPaciente", query = "SELECT r FROM RecetaEntity r WHERE r.apellidoPaciente = :apellidoPaciente")
    , @NamedQuery(name = "RecetaEntity.findByNombreMedico", query = "SELECT r FROM RecetaEntity r WHERE r.nombreMedico = :nombreMedico")
    , @NamedQuery(name = "RecetaEntity.findByDescripcionReceta", query = "SELECT r FROM RecetaEntity r WHERE r.descripcionReceta = :descripcionReceta")})
public class RecetaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idReceta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nombrePaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String apellidoPaciente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nombreMedico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String descripcionReceta;
    @JoinColumn(name = "idConsulta", referencedColumnName = "idConsulta")
    @ManyToOne
    private ConsultaEntity idConsulta;

    public RecetaEntity() {
    }

    public RecetaEntity(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public RecetaEntity(Integer idReceta, String nombrePaciente, String apellidoPaciente, String nombreMedico, String descripcionReceta) {
        this.idReceta = idReceta;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.nombreMedico = nombreMedico;
        this.descripcionReceta = descripcionReceta;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getDescripcionReceta() {
        return descripcionReceta;
    }

    public void setDescripcionReceta(String descripcionReceta) {
        this.descripcionReceta = descripcionReceta;
    }

    public ConsultaEntity getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(ConsultaEntity idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReceta != null ? idReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaEntity)) {
            return false;
        }
        RecetaEntity other = (RecetaEntity) object;
        if ((this.idReceta == null && other.idReceta != null) || (this.idReceta != null && !this.idReceta.equals(other.idReceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.RecetaEntity[ idReceta=" + idReceta + " ]";
    }
    
}
