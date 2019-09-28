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
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import edu.utilidades.JsfUtils;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "pacienteBean")
@ViewScoped
public class PacienteBean implements Serializable {

    @EJB
    private PersonaEJB personaEJB;
    @EJB
    private PacienteEJB pacienteEJB;
    private List<PacienteEntity> listaPaciente = new ArrayList();
    private PacienteEntity paciente = new PacienteEntity();
    private PersonaEntity persona = new PersonaEntity();
    private String nombres;
    private String apellidos;
    private String dui;

    @PostConstruct
    public void init() {
        listaPaciente = this.pacienteEJB.listarPaciente();
    }

    public void filtrar() {
        this.listaPaciente = this.pacienteEJB.filtroPaciente(nombres, apellidos, dui);
    }
    
    //Metodo para ver los datos del paciente
    public String detallePaciente(int idPaciente){
        
        return "/admin/paciente/pacienteDetalle.xhtml";
    }
   
    public String editPaciente(int id) {
        return "/admin/paciente/pacienteEditar.xhtml";
    }

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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

}
