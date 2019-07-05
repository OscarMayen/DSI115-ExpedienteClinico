/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.PacienteEJB;
import com.entities.PacienteEntity;
import com.entities.PersonaEntity;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.swing.JOptionPane;

/**
 *
 * @author DTO
 */
@ManagedBean(name = "pacienteBean")
@RequestScoped
public class PacienteBean implements Serializable {

    /**
     * Creates a new instance of PacienteBean
     */
    public PacienteBean() {
    }

    List<PacienteEntity> listaPaciente;

    @EJB
    private PacienteEJB pacienteEJB;

    PacienteEntity paciente = new PacienteEntity();
    PersonaEntity persona = new PersonaEntity();

    public List<PacienteEntity> getListaPaciente() {
        return listaPaciente;
    }

    public void setListaPaciente(List<PacienteEntity> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }

    public PacienteEJB getPacienteEJB() {
        return pacienteEJB;
    }

    public void setPacienteEJB(PacienteEJB pacienteEJB) {
        this.pacienteEJB = pacienteEJB;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public String insertarPaciente() {
        System.out.println("////////////////");
        System.out.println("Nombre: "+ persona.getNombrePersona());
        System.out.println("////////////////");
        
        this.paciente.setIdPersona(persona);
        
        if (this.pacienteEJB.insertPaciente(paciente) == 0) {
            FacesContext.getCurrentInstance().addMessage("paciente", new FacesMessage("ERROR AL INSERTAR"));
            return null;
            
        } 
        
        this.paciente = new PacienteEntity();
        this.persona = new PersonaEntity();

        return "/admin/paciente/pacienteListar?faces-redirect=true";
    }
    
    public String detallePaciente(int id)
    {

        return "/admin/paciente/pacienteDetalle.xhtml"; 
    }

}
