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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Mireya Flores
 */
@Entity
@Table(name = "redsocial")
@NamedQueries({
    @NamedQuery(name = "RedsocialEntity.findAll", query = "SELECT r FROM RedsocialEntity r")
    , @NamedQuery(name = "RedsocialEntity.findByIdRedSocial", query = "SELECT r FROM RedsocialEntity r WHERE r.idRedSocial = :idRedSocial")
    , @NamedQuery(name = "RedsocialEntity.findByNombre", query = "SELECT r FROM RedsocialEntity r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RedsocialEntity.findByUrl", query = "SELECT r FROM RedsocialEntity r WHERE r.url = :url")})
public class RedsocialEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idRedSocial;
    @Size(max = 20)
    private String nombre;
    @Size(max = 500)
    private String url;

    public RedsocialEntity() {
    }

    public RedsocialEntity(Integer idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    public Integer getIdRedSocial() {
        return idRedSocial;
    }

    public void setIdRedSocial(Integer idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRedSocial != null ? idRedSocial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedsocialEntity)) {
            return false;
        }
        RedsocialEntity other = (RedsocialEntity) object;
        if ((this.idRedSocial == null && other.idRedSocial != null) || (this.idRedSocial != null && !this.idRedSocial.equals(other.idRedSocial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.RedsocialEntity[ idRedSocial=" + idRedSocial + " ]";
    }
    
}
