/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "persona")
@NamedQueries({
    @NamedQuery(name = "PersonaEntity.findAll", query = "SELECT p FROM PersonaEntity p")
    , @NamedQuery(name = "PersonaEntity.findByIdPersona", query = "SELECT p FROM PersonaEntity p WHERE p.idPersona = :idPersona")
    , @NamedQuery(name = "PersonaEntity.findByNombrePersona", query = "SELECT p FROM PersonaEntity p WHERE p.nombrePersona = :nombrePersona")
    , @NamedQuery(name = "PersonaEntity.findByApellidoPersona", query = "SELECT p FROM PersonaEntity p WHERE p.apellidoPersona = :apellidoPersona")
    , @NamedQuery(name = "PersonaEntity.findByDepartamento", query = "SELECT p FROM PersonaEntity p WHERE p.departamento = :departamento")
    , @NamedQuery(name = "PersonaEntity.findByMunicipio", query = "SELECT p FROM PersonaEntity p WHERE p.municipio = :municipio")
    , @NamedQuery(name = "PersonaEntity.findByTelefono", query = "SELECT p FROM PersonaEntity p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "PersonaEntity.findByDui", query = "SELECT p FROM PersonaEntity p WHERE p.dui = :dui")
    , @NamedQuery(name = "PersonaEntity.findByGenero", query = "SELECT p FROM PersonaEntity p WHERE p.genero = :genero")
    , @NamedQuery(name = "PersonaEntity.findByFechaNacimiento", query = "SELECT p FROM PersonaEntity p WHERE p.fechaNacimiento = :fechaNacimiento")})
public class PersonaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nombrePersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String apellidoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String departamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String municipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    private String dui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    private String genero;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @OneToMany(mappedBy = "idPersona")
    private List<PacienteEntity> pacienteEntityList;
    @OneToMany(mappedBy = "idPersona")
    private List<MedicoEntity> medicoEntityList;
    @OneToMany(mappedBy = "idPersona")
    private List<Usuario> usuarioList;

    public PersonaEntity() {
    }

    public PersonaEntity(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public PersonaEntity(Integer idPersona, String nombrePersona, String apellidoPersona, String departamento, String municipio, String telefono, String dui, String genero) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.departamento = departamento;
        this.municipio = municipio;
        this.telefono = telefono;
        this.dui = dui;
        this.genero = genero;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<PacienteEntity> getPacienteEntityList() {
        return pacienteEntityList;
    }

    public void setPacienteEntityList(List<PacienteEntity> pacienteEntityList) {
        this.pacienteEntityList = pacienteEntityList;
    }

    public List<MedicoEntity> getMedicoEntityList() {
        return medicoEntityList;
    }

    public void setMedicoEntityList(List<MedicoEntity> medicoEntityList) {
        this.medicoEntityList = medicoEntityList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaEntity)) {
            return false;
        }
        PersonaEntity other = (PersonaEntity) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.PersonaEntity[ idPersona=" + idPersona + " ]";
    }
    
}
