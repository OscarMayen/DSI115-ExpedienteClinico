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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josue
 */
@Entity
@Table(name = "Antecedentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntecedentesEntity.findAll", query = "SELECT a FROM AntecedentesEntity a")
    , @NamedQuery(name = "AntecedentesEntity.findByIdAntecedente", query = "SELECT a FROM AntecedentesEntity a WHERE a.idAntecedente = :idAntecedente")
    , @NamedQuery(name = "AntecedentesEntity.findByAlergico", query = "SELECT a FROM AntecedentesEntity a WHERE a.alergico = :alergico")
    , @NamedQuery(name = "AntecedentesEntity.findByAnteFamiliar", query = "SELECT a FROM AntecedentesEntity a WHERE a.anteFamiliar = :anteFamiliar")
    , @NamedQuery(name = "AntecedentesEntity.findByAntepersonales", query = "SELECT a FROM AntecedentesEntity a WHERE a.antepersonales = :antepersonales")})
public class AntecedentesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idAntecedente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    private String alergico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    private String anteFamiliar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    private String antepersonales;
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    @ManyToOne
    private PacienteEntity idPaciente;

    public AntecedentesEntity() {
    }

    public AntecedentesEntity(Integer idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public AntecedentesEntity(Integer idAntecedente, String alergico, String anteFamiliar, String antepersonales) {
        this.idAntecedente = idAntecedente;
        this.alergico = alergico;
        this.anteFamiliar = anteFamiliar;
        this.antepersonales = antepersonales;
    }

    public Integer getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(Integer idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public String getAlergico() {
        return alergico;
    }

    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    public String getAnteFamiliar() {
        return anteFamiliar;
    }

    public void setAnteFamiliar(String anteFamiliar) {
        this.anteFamiliar = anteFamiliar;
    }

    public String getAntepersonales() {
        return antepersonales;
    }

    public void setAntepersonales(String antepersonales) {
        this.antepersonales = antepersonales;
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
        hash += (idAntecedente != null ? idAntecedente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AntecedentesEntity)) {
            return false;
        }
        AntecedentesEntity other = (AntecedentesEntity) object;
        if ((this.idAntecedente == null && other.idAntecedente != null) || (this.idAntecedente != null && !this.idAntecedente.equals(other.idAntecedente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.AntecedentesEntity[ idAntecedente=" + idAntecedente + " ]";
    }
    
}
