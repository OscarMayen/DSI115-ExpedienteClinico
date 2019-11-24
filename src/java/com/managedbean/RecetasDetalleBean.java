/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.RecetasEJB;
import com.entities.RecetaEntity;
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
@Named(value = "recetasDetalleBean")
@ViewScoped
public class RecetasDetalleBean implements Serializable{

    @EJB
    private RecetasEJB recetasEJB;
    RecetaEntity receta= new RecetaEntity();
   
    public RecetasDetalleBean() {
    }
    
    @PostConstruct
    public void init(){       
        int id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idrec"));
        System.out.println(id);
       this.receta = recetasEJB.obtenerReceta(id);        
    }

    public RecetaEntity getReceta() {
        return receta;
    }

    public void setReceta(RecetaEntity receta) {
        this.receta = receta;
    }
    
}
