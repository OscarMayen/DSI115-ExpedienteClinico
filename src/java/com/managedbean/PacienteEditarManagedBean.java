/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.PacienteEJB;
import com.ejb.PersonaEJB;
import com.entities.PacienteEntity;
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
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author josue
 */
@Named(value = "pacienteEditarManagedBean")
@ViewScoped
public class PacienteEditarManagedBean implements Serializable{

    @EJB
    private PacienteEJB pacienteEjb;
    
    private PacienteEntity pacienteEntity = new PacienteEntity();
    
    public PacienteEditarManagedBean() {
    }
    
    @PostConstruct
    public void init(){
        int id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        pacienteEntity = pacienteEjb.obtenerPaciente(id);
    }
    
    public void updatePaciente() throws IOException {
        try {
            pacienteEjb.modificarPaciente(pacienteEntity);

        } catch (Exception ex) {
            Logger.getLogger(PacienteBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("paciente", new FacesMessage("ERROR AL INSERTAR " + ex.getMessage()));
        }
        JsfUtils.Redireccionar("pacienteListar.xhtml");
    }

    public PacienteEntity getPacienteEntity() {
        return pacienteEntity;
    }

    public void setPacienteEntity(PacienteEntity pacienteEntity) {
        this.pacienteEntity = pacienteEntity;
    }
    
    
}
