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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "mobiliario")
@NamedQueries({
    @NamedQuery(name = "MobiliarioEntity.findAll", query = "SELECT m FROM MobiliarioEntity m")
    , @NamedQuery(name = "MobiliarioEntity.findByIdMobiliario", query = "SELECT m FROM MobiliarioEntity m WHERE m.idMobiliario = :idMobiliario")
    , @NamedQuery(name = "MobiliarioEntity.findByNombreMobiliario", query = "SELECT m FROM MobiliarioEntity m WHERE m.nombreMobiliario = :nombreMobiliario")})
public class MobiliarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idMobiliario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    private String nombreMobiliario;
    @OneToMany(mappedBy = "idMobiliario")
    private List<SalamobiliarioEntity> salamobiliarioEntityList;

    public MobiliarioEntity() {
    }

    public MobiliarioEntity(Integer idMobiliario) {
        this.idMobiliario = idMobiliario;
    }

    public MobiliarioEntity(Integer idMobiliario, String nombreMobiliario) {
        this.idMobiliario = idMobiliario;
        this.nombreMobiliario = nombreMobiliario;
    }

    public Integer getIdMobiliario() {
        return idMobiliario;
    }

    public void setIdMobiliario(Integer idMobiliario) {
        this.idMobiliario = idMobiliario;
    }

    public String getNombreMobiliario() {
        return nombreMobiliario;
    }

    public void setNombreMobiliario(String nombreMobiliario) {
        this.nombreMobiliario = nombreMobiliario;
    }

    public List<SalamobiliarioEntity> getSalamobiliarioEntityList() {
        return salamobiliarioEntityList;
    }

    public void setSalamobiliarioEntityList(List<SalamobiliarioEntity> salamobiliarioEntityList) {
        this.salamobiliarioEntityList = salamobiliarioEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMobiliario != null ? idMobiliario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MobiliarioEntity)) {
            return false;
        }
        MobiliarioEntity other = (MobiliarioEntity) object;
        if ((this.idMobiliario == null && other.idMobiliario != null) || (this.idMobiliario != null && !this.idMobiliario.equals(other.idMobiliario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.MobiliarioEntity[ idMobiliario=" + idMobiliario + " ]";
    }
    
}
