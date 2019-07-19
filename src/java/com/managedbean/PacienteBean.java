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

@Named(value = "pacienteBean")
@SessionScoped
public class PacienteBean implements Serializable {

    @EJB
    private PersonaEJB personaEJB;
    @EJB
    private PacienteEJB pacienteEJB;
    private List<PacienteEntity> listaPaciente;
    private PacienteEntity paciente = new PacienteEntity();
    private PersonaEntity persona = new PersonaEntity();

    
    //Metodo para insertar paciente
    public void insertarPaciente() throws IOException 
    {
        this.paciente.setIdPersona(persona);
        pacienteEJB.insertPaciente(paciente);
        JsfUtils.Redireccionar("pacienteListar.xhtml");
    }
    
    //Metodo para actualizar paciente
    public void updatePaciente() throws IOException 
    {
        try {
            pacienteEJB.modificarPaciente(paciente);

        } catch (Exception ex) {
            Logger.getLogger(PacienteBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("paciente", new FacesMessage("ERROR AL INSERTAR " + ex.getMessage()));
        }
        JsfUtils.Redireccionar("pacienteListar.xhtml");
    }
    
    //Metodo para ver los datos del paciente
    public void detallePaciente() throws IOException 
    {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        paciente = pacienteEJB.obtenerPaciente(Integer.valueOf(id));
        JsfUtils.Redireccionar("pacienteDetalle.xhtml");
    }
    
    
    //Metodo para Seleccionar al paciente a editar
    public void irEditar() throws IOException 
    {
        String dato = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        paciente = pacienteEJB.obtenerPaciente(Integer.valueOf(dato));
        JsfUtils.Redireccionar("pacienteEditar.xhtml");

    }

    //Metodo para recuperar todos los pacientes registrados de la base de datos
    public List<PacienteEntity> listarPacientes() 
    {
        listaPaciente = pacienteEJB.listarPaciente();
        return listaPaciente;
    }
    
    //Metodo para redireccionar a insersion
    public void irInsertar() throws IOException
    {
        paciente= new PacienteEntity();
        persona=new PersonaEntity();
        JsfUtils.Redireccionar("pacienteInsertar.xhtml");
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
}
