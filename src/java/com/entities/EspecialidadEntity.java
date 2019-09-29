/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "especialidad")
@NamedQueries({
    @NamedQuery(name = "EspecialidadEntity.findAll", query = "SELECT e FROM EspecialidadEntity e")
    , @NamedQuery(name = "EspecialidadEntity.findByIdEspecialidad", query = "SELECT e FROM EspecialidadEntity e WHERE e.idEspecialidad = :idEspecialidad")
    , @NamedQuery(name = "EspecialidadEntity.findByNombreEspecialidad", query = "SELECT e FROM EspecialidadEntity e WHERE e.nombreEspecialidad = :nombreEspecialidad")})
public class EspecialidadEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idEspecialidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nombreEspecialidad;
    @ManyToMany(mappedBy = "especialidadEntityList")
    private List<MedicoEntity> medicoEntityList;

    public EspecialidadEntity() {
    }

    public EspecialidadEntity(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public EspecialidadEntity(Integer idEspecialidad, String nombreEspecialidad) {
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public List<MedicoEntity> getMedicoEntityList() {
        return medicoEntityList;
    }

    public void setMedicoEntityList(List<MedicoEntity> medicoEntityList) {
        this.medicoEntityList = medicoEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecialidad != null ? idEspecialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecialidadEntity)) {
            return false;
        }
        EspecialidadEntity other = (EspecialidadEntity) object;
        if ((this.idEspecialidad == null && other.idEspecialidad != null) || (this.idEspecialidad != null && !this.idEspecialidad.equals(other.idEspecialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.EspecialidadEntity[ idEspecialidad=" + idEspecialidad + " ]";
    }
    
}
