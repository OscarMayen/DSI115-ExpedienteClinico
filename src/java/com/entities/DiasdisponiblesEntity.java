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
@Table(name = "diasdisponibles")
@NamedQueries({
    @NamedQuery(name = "DiasdisponiblesEntity.findAll", query = "SELECT d FROM DiasdisponiblesEntity d")
    , @NamedQuery(name = "DiasdisponiblesEntity.findByIdDia", query = "SELECT d FROM DiasdisponiblesEntity d WHERE d.idDia = :idDia")
    , @NamedQuery(name = "DiasdisponiblesEntity.findByNombreDia", query = "SELECT d FROM DiasdisponiblesEntity d WHERE d.nombreDia = :nombreDia")})
public class DiasdisponiblesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idDia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    private String nombreDia;
    @OneToMany(mappedBy = "idDia")
    private List<HorarioEntity> horarioEntityList;

    public DiasdisponiblesEntity() {
    }

    public DiasdisponiblesEntity(Integer idDia) {
        this.idDia = idDia;
    }

    public DiasdisponiblesEntity(Integer idDia, String nombreDia) {
        this.idDia = idDia;
        this.nombreDia = nombreDia;
    }

    public Integer getIdDia() {
        return idDia;
    }

    public void setIdDia(Integer idDia) {
        this.idDia = idDia;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public List<HorarioEntity> getHorarioEntityList() {
        return horarioEntityList;
    }

    public void setHorarioEntityList(List<HorarioEntity> horarioEntityList) {
        this.horarioEntityList = horarioEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDia != null ? idDia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiasdisponiblesEntity)) {
            return false;
        }
        DiasdisponiblesEntity other = (DiasdisponiblesEntity) object;
        if ((this.idDia == null && other.idDia != null) || (this.idDia != null && !this.idDia.equals(other.idDia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.DiasdisponiblesEntity[ idDia=" + idDia + " ]";
    }
    
}
