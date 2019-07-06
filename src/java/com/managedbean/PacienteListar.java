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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DTO
 */
@ManagedBean(name = "pacienteListar")
@ViewScoped
public class PacienteListar implements Serializable {

    /**
     * Creates a new instance of PacienteListar
     */
    @PostConstruct
    public void init() {
        listaPaciente = buscarPacientes();
    }
    @EJB
    private PersonaEJB personaEJB;

    @EJB
    private PacienteEJB pacienteEJB;

    private PacienteEntity paciente = new PacienteEntity();
    private PersonaEntity persona = new PersonaEntity();

    private List<PacienteEntity> listaPaciente = new ArrayList();

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

    public List<PacienteEntity> getListaPaciente() {
        return listaPaciente;
    }

    public void setListaPaciente(List<PacienteEntity> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }

    /**
     * Creates a new instance of MedicoListar
     */
    private List<PacienteEntity> buscarPacientes() {
        this.listaPaciente = pacienteEJB.listarPaciente();
        return listaPaciente;
    }

    public String editPaciente(int id) {
    
        return "/admin/paciente/pacienteEditar.xhtml?faces-redirect=false";
    }
}
