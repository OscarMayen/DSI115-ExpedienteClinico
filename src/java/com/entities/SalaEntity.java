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
@Table(name = "sala")
@NamedQueries({
    @NamedQuery(name = "SalaEntity.findAll", query = "SELECT s FROM SalaEntity s")
    , @NamedQuery(name = "SalaEntity.findByIdSala", query = "SELECT s FROM SalaEntity s WHERE s.idSala = :idSala")
    , @NamedQuery(name = "SalaEntity.findByNombreSala", query = "SELECT s FROM SalaEntity s WHERE s.nombreSala = :nombreSala")})
public class SalaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idSala;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String nombreSala;
    @OneToMany(mappedBy = "idSala")
    private List<ConsultaEntity> consultaEntityList;

    public SalaEntity() {
    }

    public SalaEntity(Integer idSala) {
        this.idSala = idSala;
    }

    public SalaEntity(Integer idSala, String nombreSala) {
        this.idSala = idSala;
        this.nombreSala = nombreSala;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public List<ConsultaEntity> getConsultaEntityList() {
        return consultaEntityList;
    }

    public void setConsultaEntityList(List<ConsultaEntity> consultaEntityList) {
        this.consultaEntityList = consultaEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSala != null ? idSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalaEntity)) {
            return false;
        }
        SalaEntity other = (SalaEntity) object;
        if ((this.idSala == null && other.idSala != null) || (this.idSala != null && !this.idSala.equals(other.idSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.SalaEntity[ idSala=" + idSala + " ]";
    }
    
}
