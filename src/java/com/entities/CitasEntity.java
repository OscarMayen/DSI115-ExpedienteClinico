/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "citas")
@NamedQueries({
    @NamedQuery(name = "CitasEntity.findAll", query = "SELECT c FROM CitasEntity c")
    , @NamedQuery(name = "CitasEntity.findByIdCita", query = "SELECT c FROM CitasEntity c WHERE c.idCita = :idCita")
    , @NamedQuery(name = "CitasEntity.findByFechaCita", query = "SELECT c FROM CitasEntity c WHERE c.fechaCita = :fechaCita")})
public class CitasEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idCita;
    @NotNull
    @Size(min = 1, max = 50)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCita;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCitaFinal;
    @JoinColumn(name = "idMedico", referencedColumnName = "idMedico")
    @ManyToOne
    private MedicoEntity idMedico;
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    @ManyToOne
    private PacienteEntity idPaciente;

    public CitasEntity() {
    }

    public CitasEntity(Integer idCita) {
        this.idCita = idCita;
    }

    public CitasEntity(Integer idCita, String titulo, Date fechaCita, Date fechaCitaFinal) {
        this.idCita = idCita;
        this.titulo = titulo;
        this.fechaCita = fechaCita;
        this.fechaCitaFinal = fechaCitaFinal;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public String getTituto() {
        return titulo;
    }

    public void setTituto(String tituto) {
        this.titulo = tituto;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getFechaCitaFinal() {
        return fechaCitaFinal;
    }

    public void setFechaCitaFinal(Date fechaCitaFinal) {
        this.fechaCitaFinal = fechaCitaFinal;
    }
    
    

    public MedicoEntity getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(MedicoEntity idMedico) {
        this.idMedico = idMedico;
    }

    public PacienteEntity getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(PacienteEntity idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCita != null ? idCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitasEntity)) {
            return false;
        }
        CitasEntity other = (CitasEntity) object;
        if ((this.idCita == null && other.idCita != null) || (this.idCita != null && !this.idCita.equals(other.idCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.CitasEntity[ idCita=" + idCita + " ]";
    }
    
}
