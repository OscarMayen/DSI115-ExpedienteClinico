/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.AntecedentesEJB;
import com.entities.AntecedentesEntity;
import edu.utilidades.JsfUtils;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


/**
 *
 * @author DTO
 */
@ManagedBean(name = "antecedentesEditarBean")
@ViewScoped
public class AntecedentesEditarBean implements Serializable {

    @EJB
    private AntecedentesEJB antecedentesEJB;

   
    public AntecedentesEditarBean() {
    }
    
    AntecedentesEntity antecedente=new AntecedentesEntity();
    
    @PostConstruct
    public void init(){       
        int id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        System.out.println(id);
       antecedente = antecedentesEJB.obtenerAntecedentes(id);
    
    }
    
    public void  actualizarAntecedente() throws IOException{
        
        antecedentesEJB.modificarAntecedente(this.antecedente);
        JsfUtils.Redireccionar("listarAntecedentes.xhtml");
              
    }

    public AntecedentesEntity getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(AntecedentesEntity antecedente) {
        this.antecedente = antecedente;
    }
    
    
}
