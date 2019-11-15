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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "medico")
@NamedQueries({
    @NamedQuery(name = "MedicoEntity.findAll", query = "SELECT m FROM MedicoEntity m")
    , @NamedQuery(name = "MedicoEntity.findByIdMedico", query = "SELECT m FROM MedicoEntity m WHERE m.idMedico = :idMedico")})
public class MedicoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idMedico;
    @JoinTable(name = "medicoespecialidad", joinColumns = {
        @JoinColumn(name = "idMedico", referencedColumnName = "IDMEDICO")}, inverseJoinColumns = {
        @JoinColumn(name = "idEspecialidad", referencedColumnName = "IDESPECIALIDAD")})
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.MERGE
            })
    private List<EspecialidadEntity> especialidadEntityList;
    @JoinTable(name = "medicored", joinColumns = {
        @JoinColumn(name = "idMedico", referencedColumnName = "IDMEDICO")}, inverseJoinColumns = {
        @JoinColumn(name = "idRedSocial", referencedColumnName = "IDREDSOCIAL")})
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    private List<RedsocialEntity> redsocialEntityList;
    @OneToMany(mappedBy = "idMedico")
    private List<CitasEntity> citasEntityList;
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
   @OneToOne(cascade=CascadeType.ALL)
    private PersonaEntity idPersona;
    @OneToMany(mappedBy = "idMedico")
    private List<ConsultaEntity> consultaEntityList;

    public MedicoEntity() {
    }

    public MedicoEntity(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public List<EspecialidadEntity> getEspecialidadEntityList() {
        return especialidadEntityList;
    }

    public void setEspecialidadEntityList(List<EspecialidadEntity> especialidadEntityList) {
        this.especialidadEntityList = especialidadEntityList;
    }

    public List<RedsocialEntity> getRedsocialEntityList() {
        return redsocialEntityList;
    }

    public void setRedsocialEntityList(List<RedsocialEntity> redsocialEntityList) {
        this.redsocialEntityList = redsocialEntityList;
    }

    public List<CitasEntity> getCitasEntityList() {
        return citasEntityList;
    }

    public void setCitasEntityList(List<CitasEntity> citasEntityList) {
        this.citasEntityList = citasEntityList;
    }

    public PersonaEntity getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(PersonaEntity idPersona) {
        this.idPersona = idPersona;
    }

    public List<ConsultaEntity> getConsultaEntityList() {
        return consultaEntityList;
    }

    public void setConsultaEntityList(List<ConsultaEntity> consultaEntityList) {
        this.consultaEntityList = consultaEntityList;
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