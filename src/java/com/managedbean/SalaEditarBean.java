
package com.managedbean;

import com.ejb.SalaEJB;
import com.entities.SalaEntity;
import edu.utilidades.JsfUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author DIAZ
 */
@Named(value = "salaEditarBean")
@ViewScoped
public class SalaEditarBean implements Serializable{

    @EJB
    private SalaEJB salaEjb;
    
    
    SalaEntity sala = new SalaEntity();
    
    public SalaEditarBean() {
    }

    public SalaEntity getSala() {
        return sala;
    }

    public void setSala(SalaEntity sala) {
        this.sala = sala;
    }
 

     @PostConstruct
    public void init() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        FacesContext fc = FacesContext.getCurrentInstance();
        
        HttpServletRequest request = (HttpServletRequest) fc
                .getExternalContext().getRequest();
        System.out.println(fc.getExternalContext().getRequestParameterMap());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        String id = fc.getExternalContext().getRequestParameterMap().get("id");
        if (id == null) {
            System.out.println("Error!!!!");
        } else {
             this.sala = new SalaEntity();
             
        sala = salaEjb.obtenerSala(Integer.valueOf(id)); 
        System.out.println("********************");
        System.out.println("nombre: " +sala.getNombreSala());
        System.out.println("ID: " +sala.getIdSala());
        }
    }
    
    public String updateSala(){
        System.out.println("//////////////***************");
        System.out.println("ID A MODIFICAR: " + this.sala.getIdSala());
        System.out.println("NOMBRE A MODIFICAR: " + this.sala.getNombreSala());
        System.out.println("//////////////***************");
        salaEjb.modificarSala(this.sala);
   
        return "/admin/sala/salaListar?faces-redirect=true";
     
    }
    
    public String btnCancelarSala(){
        
        return "/admin/sala/salaListar?faces-redirect=true";
    }
}