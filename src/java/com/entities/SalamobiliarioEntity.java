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

/**
 *
 * @author admin
 */
@Entity
@Table(name = "salamobiliario")
@NamedQueries({
    @NamedQuery(name = "SalamobiliarioEntity.findAll", query = "SELECT s FROM SalamobiliarioEntity s")
    , @NamedQuery(name = "SalamobiliarioEntity.findByIdSalaMobiliario", query = "SELECT s FROM SalamobiliarioEntity s WHERE s.idSalaMobiliario = :idSalaMobiliario")
    , @NamedQuery(name = "SalamobiliarioEntity.findByCantidad", query = "SELECT s FROM SalamobiliarioEntity s WHERE s.cantidad = :cantidad")})
public class SalamobiliarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idSalaMobiliario;
    @Basic(optional = false)
    @NotNull
    private int cantidad;
    @JoinColumn(name = "idSala", referencedColumnName = "idSala")
    @ManyToOne
    private SalaEntity idSala;
    @JoinColumn(name = "idMobiliario", referencedColumnName = "idMobiliario")
    @ManyToOne
    private MobiliarioEntity idMobiliario;

    public SalamobiliarioEntity() {
    }

    public SalamobiliarioEntity(SalaEntity idSala, MobiliarioEntity idMobiliario,int cantidad) {
        this.idSala = idSala;
        this.idMobiliario = idMobiliario;
        this.cantidad = cantidad;
    }

    
    public SalamobiliarioEntity(Integer idSalaMobiliario) {
        this.idSalaMobiliario = idSalaMobiliario;
    }

    public SalamobiliarioEntity(Integer idSalaMobiliario, int cantidad) {
        this.idSalaMobiliario = idSalaMobiliario;
        this.cantidad = cantidad;
    }

    public Integer getIdSalaMobiliario() {
        return idSalaMobiliario;
    }

    public void setIdSalaMobiliario(Integer idSalaMobiliario) {
        this.idSalaMobiliario = idSalaMobiliario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public SalaEntity getIdSala() {
        return idSala;
    }

    public void setIdSala(SalaEntity idSala) {
        this.idSala = idSala;
    }

    public MobiliarioEntity getIdMobiliario() {
        return idMobiliario;
    }

    public void setIdMobiliario(MobiliarioEntity idMobiliario) {
        this.idMobiliario = idMobiliario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSalaMobiliario != null ? idSalaMobiliario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalamobiliarioEntity)) {
            return false;
        }
        SalamobiliarioEntity other = (SalamobiliarioEntity) object;
        if ((this.idSalaMobiliario == null && other.idSalaMobiliario != null) || (this.idSalaMobiliario != null && !this.idSalaMobiliario.equals(other.idSalaMobiliario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.SalamobiliarioEntity[ idSalaMobiliario=" + idSalaMobiliario + " ]";
    }
    
}
