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
@Table(name = "horasdisponibles")
@NamedQueries({
    @NamedQuery(name = "HorasdisponiblesEntity.findAll", query = "SELECT h FROM HorasdisponiblesEntity h")
    , @NamedQuery(name = "HorasdisponiblesEntity.findByIdHora", query = "SELECT h FROM HorasdisponiblesEntity h WHERE h.idHora = :idHora")
    , @NamedQuery(name = "HorasdisponiblesEntity.findByHoraInicio", query = "SELECT h FROM HorasdisponiblesEntity h WHERE h.horaInicio = :horaInicio")
    , @NamedQuery(name = "HorasdisponiblesEntity.findByHoraFin", query = "SELECT h FROM HorasdisponiblesEntity h WHERE h.horaFin = :horaFin")})
public class HorasdisponiblesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idHora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String horaInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String horaFin;
    @OneToMany(mappedBy = "idHora")
    private List<HorarioEntity> horarioEntityList;

    public HorasdisponiblesEntity() {
    }

    public HorasdisponiblesEntity(Integer idHora) {
        this.idHora = idHora;
    }

    public HorasdisponiblesEntity(Integer idHora, String horaInicio, String horaFin) {
        this.idHora = idHora;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Integer getIdHora() {
        return idHora;
    }

    public void setIdHora(Integer idHora) {
        this.idHora = idHora;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
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
        hash += (idHora != null ? idHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorasdisponiblesEntity)) {
            return false;
        }
        HorasdisponiblesEntity other = (HorasdisponiblesEntity) object;
        if ((this.idHora == null && other.idHora != null) || (this.idHora != null && !this.idHora.equals(other.idHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.HorasdisponiblesEntity[ idHora=" + idHora + " ]";
    }
    
}
