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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josue
 */
@Entity
@Table(name = "SignosVitales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SignosVitalesEntity.findAll", query = "SELECT s FROM SignosVitalesEntity s")
    , @NamedQuery(name = "SignosVitalesEntity.findByIdSignosVitales", query = "SELECT s FROM SignosVitalesEntity s WHERE s.idSignosVitales = :idSignosVitales")
    , @NamedQuery(name = "SignosVitalesEntity.findByPeso", query = "SELECT s FROM SignosVitalesEntity s WHERE s.peso = :peso")
    , @NamedQuery(name = "SignosVitalesEntity.findByPresion", query = "SELECT s FROM SignosVitalesEntity s WHERE s.presion = :presion")
    , @NamedQuery(name = "SignosVitalesEntity.findByAltura", query = "SELECT s FROM SignosVitalesEntity s WHERE s.altura = :altura")})
public class SignosVitalesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idSignosVitales;
    @Basic(optional = false)
    @NotNull
    private float peso;
    @Basic(optional = false)
    @NotNull
    private float presion;
    @Basic(optional = false)
    @NotNull
    private float altura;
    @OneToMany(mappedBy = "idSignosVitales")
    private List<ConsultaEntity> consultaEntityList;

    public SignosVitalesEntity() {
    }

    public SignosVitalesEntity(Integer idSignosVitales) {
        this.idSignosVitales = idSignosVitales;
    }

    public SignosVitalesEntity(Integer idSignosVitales, float peso, float presion, float altura) {
        this.idSignosVitales = idSignosVitales;
        this.peso = peso;
        this.presion = presion;
        this.altura = altura;
    }

    public Integer getIdSignosVitales() {
        return idSignosVitales;
    }

    public void setIdSignosVitales(Integer idSignosVitales) {
        this.idSignosVitales = idSignosVitales;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getPresion() {
        return presion;
    }

    public void setPresion(float presion) {
        this.presion = presion;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    @XmlTransient
    public List<ConsultaEntity> getConsultaEntityList() {
        return consultaEntityList;
    }

    public void setConsultaEntityList(List<ConsultaEntity> consultaEntityList) {
        this.consultaEntityList = consultaEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSignosVitales != null ? idSignosVitales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SignosVitalesEntity)) {
            return false;
        }
        SignosVitalesEntity other = (SignosVitalesEntity) object;
        if ((this.idSignosVitales == null && other.idSignosVitales != null) || (this.idSignosVitales != null && !this.idSignosVitales.equals(other.idSignosVitales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.SignosVitalesEntity[ idSignosVitales=" + idSignosVitales + " ]";
    }
    
}
