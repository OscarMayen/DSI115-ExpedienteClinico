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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josue
 */
@Entity
@Table(name = "Consulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultaEntity.findAll", query = "SELECT c FROM ConsultaEntity c")
    , @NamedQuery(name = "ConsultaEntity.findByIdConsulta", query = "SELECT c FROM ConsultaEntity c WHERE c.idConsulta = :idConsulta")
    , @NamedQuery(name = "ConsultaEntity.findByFechaConsulta", query = "SELECT c FROM ConsultaEntity c WHERE c.fechaConsulta = :fechaConsulta")})
public class ConsultaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idConsulta;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConsulta;
    @OneToMany(mappedBy = "idConsulta")
    private List<DiagnosticoEntity> diagnosticoEntityList;
    @JoinColumn(name = "idMedico", referencedColumnName = "IdMedico")
    @ManyToOne
    private MedicoEntity idMedico;
    @JoinColumn(name = "idSignosVitales", referencedColumnName = "idSignosVitales")
    @ManyToOne
    private SignosVitalesEntity idSignosVitales;
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    @ManyToOne
    private PacienteEntity idPaciente;

    public ConsultaEntity() {
    }

    public ConsultaEntity(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public ConsultaEntity(Integer idConsulta, Date fechaConsulta) {
        this.idConsulta = idConsulta;
        this.fechaConsulta = fechaConsulta;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    @XmlTransient
    public List<DiagnosticoEntity> getDiagnosticoEntityList() {
        return diagnosticoEntityList;
    }

    public void setDiagnosticoEntityList(List<DiagnosticoEntity> diagnosticoEntityList) {
        this.diagnosticoEntityList = diagnosticoEntityList;
    }

    public MedicoEntity getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(MedicoEntity idMedico) {
        this.idMedico = idMedico;
    }

    public SignosVitalesEntity getIdSignosVitales() {
        return idSignosVitales;
    }

    public void setIdSignosVitales(SignosVitalesEntity idSignosVitales) {
        this.idSignosVitales = idSignosVitales;
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
        hash += (idConsulta != null ? idConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaEntity)) {
            return false;
        }
        ConsultaEntity other = (ConsultaEntity) object;
        if ((this.idConsulta == null && other.idConsulta != null) || (this.idConsulta != null && !this.idConsulta.equals(other.idConsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.ConsultaEntity[ idConsulta=" + idConsulta + " ]";
    }
    
}
