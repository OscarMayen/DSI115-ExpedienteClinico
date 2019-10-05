/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.PacienteEJB;
import com.ejb.PersonaEJB;
import com.entities.PacienteEntity;
import com.entities.PersonaEntity;
import edu.utilidades.JsfUtils;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author josue
 */
@ManagedBean(name = "pacienteInsertarManagedBean")
@ViewScoped
public class PacienteInsertarManagedBean implements Serializable {

    @EJB
    private PersonaEJB personaEJB;
    @EJB
    private PacienteEJB pacienteEJB;

    private PersonaEntity personaEntity = new PersonaEntity();
    private PacienteEntity pacienteEntity = new PacienteEntity();
    private JsfUtils utilidades = new JsfUtils();

    public PacienteInsertarManagedBean() {
    }

     
    public void insertarPaciente() throws IOException {
        this.pacienteEntity.setIdPersona(personaEntity);
        int a = pacienteEJB.insertPaciente(pacienteEntity);
        if (a == 0) {
            FacesContext context = FacesContext.getCurrentInstance();
             FacesContext.getCurrentInstance().addMessage("Paciente", new FacesMessage("ERROR AL INSERTAR DUI REPETIDO"));
        } else {
            this.pacienteEntity = new PacienteEntity();
            this.personaEntity = new PersonaEntity();
            JsfUtils.Redireccionar("pacienteListar.xhtml");
        }

    }

    public JsfUtils getUtilidades() {
        return utilidades;
    }

    public void setUtilidades(JsfUtils utilidades) {
        this.utilidades = utilidades;
    }

    public PersonaEntity getPersonaEntity() {
        return personaEntity;
    }

    public void setPersonaEntity(PersonaEntity personaEntity) {
        this.personaEntity = personaEntity;
    }

    public PacienteEntity getPacienteEntity() {
        return pacienteEntity;
    }

    public void setPacienteEntity(PacienteEntity pacienteEntity) {
        this.pacienteEntity = pacienteEntity;
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
    
   

}
