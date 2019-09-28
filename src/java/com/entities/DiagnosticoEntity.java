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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josue
 */
@Entity
@Table(name = "Diagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoEntity.findAll", query = "SELECT d FROM DiagnosticoEntity d")
    , @NamedQuery(name = "DiagnosticoEntity.findByIdDiagnostico", query = "SELECT d FROM DiagnosticoEntity d WHERE d.idDiagnostico = :idDiagnostico")
    , @NamedQuery(name = "DiagnosticoEntity.findByDescripcion", query = "SELECT d FROM DiagnosticoEntity d WHERE d.descripcion = :descripcion")})
public class DiagnosticoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idDiagnostico;
    @Size(max = 500)
    private String descripcion;
    @JoinColumn(name = "idConsulta", referencedColumnName = "idConsulta")
    @ManyToOne
    private ConsultaEntity idConsulta;

    public DiagnosticoEntity() {
    }

    public DiagnosticoEntity(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ConsultaEntity getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(ConsultaEntity idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiagnostico != null ? idDiagnostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoEntity)) {
            return false;
        }
        DiagnosticoEntity other = (DiagnosticoEntity) object;
        if ((this.idDiagnostico == null && other.idDiagnostico != null) || (this.idDiagnostico != null && !this.idDiagnostico.equals(other.idDiagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.DiagnosticoEntity[ idDiagnostico=" + idDiagnostico + " ]";
    }
    
}
