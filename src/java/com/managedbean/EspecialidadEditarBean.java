
package com.managedbean;

import com.ejb.EspecialidadEJB;
import com.entities.EspecialidadEntity;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Oscar Mayen
 */
@Named(value = "especialidadEditarBean")
@ViewScoped
public class EspecialidadEditarBean implements Serializable{

    
    
    @EJB
    private EspecialidadEJB especialidadEJB;

    private EspecialidadEntity especialidad = new EspecialidadEntity();
    
    public EspecialidadEditarBean() {
    }

    public EspecialidadEntity getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadEntity especialidad) {
        this.especialidad = especialidad;
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
             this.especialidad = new EspecialidadEntity();
             
            this.especialidad = especialidadEJB.obtenerEspecialidad(Integer.valueOf(id));
            
        }
    }
    
    public String actualizarEspecialidad(){
        try {
            
            especialidadEJB.modificarEspecialidad(especialidad);
            
        } catch (Exception ex) {
             Logger.getLogger(EspecialidadEditarBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("Especialidad", new FacesMessage("ERROR AL EDITAR " + ex.getMessage() ));
        }
   
        return "/admin/especialidad/especialidadListar?faces-redirect=true";
      
    }
    
    
    
}
