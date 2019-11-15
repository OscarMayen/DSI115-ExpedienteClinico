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

/**
 *
 * @author admin
 */
@Entity
@Table(name = "horario")
@NamedQueries({
    @NamedQuery(name = "HorarioEntity.findAll", query = "SELECT h FROM HorarioEntity h")
    , @NamedQuery(name = "HorarioEntity.findByIdHorario", query = "SELECT h FROM HorarioEntity h WHERE h.idHorario = :idHorario")})
public class HorarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idHorario;
    @JoinColumn(name = "idMedico", referencedColumnName = "idMedico")
    @ManyToOne
    private MedicoEntity idMedico;
    @JoinColumn(name = "idDia", referencedColumnName = "idDia")
    @ManyToOne
    private DiasdisponiblesEntity idDia;
    @JoinColumn(name = "idHora", referencedColumnName = "idHora")
    @ManyToOne
    private HorasdisponiblesEntity idHora;

    public HorarioEntity() {
    }

    public HorarioEntity(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public MedicoEntity getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(MedicoEntity idMedico) {
        this.idMedico = idMedico;
    }

    public DiasdisponiblesEntity getIdDia() {
        return idDia;
    }

    public void setIdDia(DiasdisponiblesEntity idDia) {
        this.idDia = idDia;
    }

    public HorasdisponiblesEntity getIdHora() {
        return idHora;
    }

    public void setIdHora(HorasdisponiblesEntity idHora) {
        this.idHora = idHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioEntity)) {
            return false;
        }
        HorarioEntity other = (HorarioEntity) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.HorarioEntity[ idHorario=" + idHorario + " ]";
    }
    
}
