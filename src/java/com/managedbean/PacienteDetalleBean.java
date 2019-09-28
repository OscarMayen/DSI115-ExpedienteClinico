/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.PacienteEJB;
import com.ejb.PersonaEJB;
import com.entities.PacienteEntity;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author josue
 */
@ManagedBean(name = "pacienteDetalleBean")
@ViewScoped
public class PacienteDetalleBean implements Serializable{

    @EJB
    private PersonaEJB personaEJB;
    @EJB
    private PacienteEJB pacienteEJB;
    
    private PacienteEntity pacienteEntity = new PacienteEntity();
    
    @PostConstruct
    public void init(){
        
        int id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        System.out.println(id);
        pacienteEntity = pacienteEJB.obtenerPaciente(id);
    }
    
    public PacienteDetalleBean() {
    }

    public PersonaEJB getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonaEJB personaEJB) {
        this.personaEJB = personaEJB;
    }

    public PacienteEJB getPacienteEJB() {
        return pacienteEJB;
    }

    public void setPacienteEJB(PacienteEJB pacienteEJB) {
        this.pacienteEJB = pacienteEJB;
    }

    public PacienteEntity getPacienteEntity() {
        return pacienteEntity;
    }

    public void setPacienteEntity(PacienteEntity pacienteEntity) {
        this.pacienteEntity = pacienteEntity;
    }
    
    
}
