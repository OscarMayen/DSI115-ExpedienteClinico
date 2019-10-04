
package com.managedbean;

import com.ejb.EspecialidadEJB;
import com.entities.EspecialidadEntity;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Oscar Mayen
 */
@ManagedBean(name = "especialidadInsertarBean")
@ViewScoped
public class EspecialidadInsertarBean implements Serializable{

    @EJB
    private EspecialidadEJB especialidadEJB;

    private EspecialidadEntity especialidad = new EspecialidadEntity();
    
    public EspecialidadInsertarBean() {
    }

    public EspecialidadEntity getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadEntity especialidad) {
        this.especialidad = especialidad;
    }
    
    
     public String insertarEspecialidad()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        if(especialidadEJB.insertEspecialidad(especialidad)==0)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR","NO SE PUDO INSERTAR LA ESPECIALIDAD"));
            return null;
        }
        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"EXITO","ESPECIALIDAD INSERTADO CORRECTAMENTE"));
        this.especialidad = new EspecialidadEntity();
        return "/admin/especialidad/especialidadListar?faces-redirect=true";
    }
     
     public String btnCancelar()
     {
         return "/admin/especialidad/especialidadListar?faces-redirect=true";
     }
}
