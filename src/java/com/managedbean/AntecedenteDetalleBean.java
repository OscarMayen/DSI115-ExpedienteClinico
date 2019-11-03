/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.AntecedentesEJB;
import com.entities.AntecedentesEntity;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DTO
 */
@Named(value = "antecedenteDetalleBean")
@ViewScoped
public class AntecedenteDetalleBean implements Serializable{

    /**
     * Creates a new instance of AntecedenteDetalleBean
     */
    public AntecedenteDetalleBean() {
    }
        @EJB
    private AntecedentesEJB antecedentesEJB;

   
    
    AntecedentesEntity antecedente=new AntecedentesEntity();
    
    @PostConstruct
    public void init(){       
        int id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idant"));
        System.out.println(id);
       this.antecedente = antecedentesEJB.obtenerAntecedentes(id);        
    }

    public AntecedentesEntity getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(AntecedentesEntity antecedente) {
        this.antecedente = antecedente;
    }
    
    
}
