/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.AntecedentesEJB;
import com.ejb.PacienteEJB;
import com.entities.AntecedentesEntity;
import com.entities.PacienteEntity;
import edu.utilidades.JsfUtils;
import java.io.IOException;
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
@Named(value = "antecedentesListarBean")
@ViewScoped
public class AntecedentesListarBean implements Serializable{

    @EJB
    private PacienteEJB pacienteEJB;

    @EJB
    private AntecedentesEJB antecedentesEJB;
        
    List<AntecedentesEntity> antecedentes= new ArrayList<AntecedentesEntity>();   
    /**
     * Creates a new instance of AntecedentesListarBean
     */
    public AntecedentesListarBean()  {
    }    
    @PostConstruct
    public void init(){      
        antecedentes=antecedentesEJB.listarAntecedentes();
    }

     public String obtenerAntecedente()  {         
         return "/admin/antecedentes/editarAntecedentes.xhtml";
     }  
     
     public String verAntecedente(){
         return "/admin/antecedentes/detalleAntecedente.xhtml";
     }

    public List<AntecedentesEntity> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(List<AntecedentesEntity> antecedentes) {
        this.antecedentes = antecedentes;
    }
    
    
}
