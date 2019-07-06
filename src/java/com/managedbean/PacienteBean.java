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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;


/**
 *
 * @author DTO
 */
@ManagedBean(name = "pacienteBean")
@ViewScoped
public class PacienteBean implements Serializable {

    /**
     * Creates a new instance of PacienteBean
     */
    

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
    @PostConstruct
    public void init() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        String id = fc.getExternalContext().getRequestParameterMap().get("id");
        if (id == null) {
            System.out.println("Error!!!!");
        } else {
             this.paciente = new PacienteEntity();
       
        paciente = pacienteEJB.obtenerPaciente(Integer.valueOf(id));
//            System.out.println(paciente.getIdPersona()+
//                                paciente.getEstadoCivil()+
//                                paciente.getEstadoPaciente()+
//                                paciente.getVinculoResponsable()+
//                                paciente.getNombreResponsable()+
//                                paciente.getTelefonoEmergencia()+
//                                paciente.getIdPersona().getNombrePersona()+
//                                paciente.getIdPersona().getApellidoPersona()+
//                                paciente.getIdPersona().getDui()+
//                                paciente.getIdPersona().getDepartamento()+
//                                paciente.getIdPersona().getMunicipio() );
        }
    }

    public String insertarPaciente() {
        this.paciente.setIdPersona(persona);
        if (this.pacienteEJB.insertPaciente(paciente) == 0) {
            FacesContext.getCurrentInstance().addMessage("paciente", new FacesMessage("ERROR AL INSERTAR"));
            return null;
        } 
        
        this.paciente = new PacienteEntity();
        this.persona = new PersonaEntity();
        return "/admin/paciente/pacienteListar?faces-redirect=true";
    }
    
    
      
    
     public String updatePaciente(){
         System.out.println("HOLAHOALHAOALAHAOALAHAOALA");
        try {
            pacienteEJB.modificarPaciente(paciente);
           
        } catch (Exception ex) {
            Logger.getLogger(PacienteBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR " + ex.getMessage() ));
        }
   
        return "/admin/paciente/pacienteListar?faces-redirect=false";
      
    }
        
    public String detallePaciente(int id)
    {

        return "/admin/paciente/pacienteDetalle.xhtml"; 
    }

}
