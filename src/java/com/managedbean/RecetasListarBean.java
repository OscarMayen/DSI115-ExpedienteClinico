/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.RecetasEJB;
import com.entities.ConsultaEntity;
import com.entities.RecetaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DTO
 */
@Named(value = "recetasListarBean")
@ViewScoped
public class RecetasListarBean implements Serializable {

    @EJB
    private RecetasEJB recetasEJB;
    List<RecetaEntity> recetas= new ArrayList<RecetaEntity>();
    
   
    public RecetasListarBean() {
    }
    
    @PostConstruct
    public void init(){      
        recetas=recetasEJB.listarRecetass();        
    }

   
    public String verReceta(){
        return "/admin/recetas/recetasDetalle.xhtml";
    }    

    public List<RecetaEntity> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<RecetaEntity> recetas) {
        this.recetas = recetas;
    }

   
    
    
}
