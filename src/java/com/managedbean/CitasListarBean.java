/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.CitasEJB;
import com.ejb.MedicoEJB;
import com.ejb.PacienteEJB;
import com.entities.CitasEntity;
import com.entities.MedicoEntity;
import com.entities.PacienteEntity;
import com.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author josue
 */
@ManagedBean(name = "citasListarBean")
@ViewScoped
public class CitasListarBean implements Serializable{

    @EJB
    private CitasEJB citasEJB;

    @EJB
    private PacienteEJB pacienteEJB;

    @EJB
    private MedicoEJB medicoEJB;
    
    

    private ScheduleModel eventModel;
    
    private DefaultScheduleEvent event = new DefaultScheduleEvent();
    
    private MedicoEntity medicoEntity;
    private PacienteEntity pacienteEntity;
    private UsuarioEntity usuarioEntity;
    
    private String duiPaciente;
 
    public CitasListarBean() {
    }
    
    @PostConstruct
    public void init() {
       eventModel = new DefaultScheduleModel();
       usuarioEntity = (UsuarioEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
       medicoEntity = this.medicoEJB.obtenerMedicoPorUsuario(usuarioEntity.getUsername());
       listarCitasMedico();
    }

    public PacienteEntity getPacienteEntity() {
        return pacienteEntity;
    }

    public void setPacienteEntity(PacienteEntity pacienteEntity) {
        this.pacienteEntity = pacienteEntity;
    }

    public String getDuiPaciente() {
        return duiPaciente;
    }

    public void setDuiPaciente(String duiPaciente) {
        this.duiPaciente = duiPaciente;
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(DefaultScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent() {
        if(event.getId() == null){
            
            this.event.setDynamicProperty("paciente", this.duiPaciente);
            this.crearCita();
        }
        event = new DefaultScheduleEvent();
        
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
        event = (DefaultScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        
    }
     
    public void crearCita(){
        CitasEntity nuevaCita = new CitasEntity();
        nuevaCita.setFechaCita(event.getStartDate());
        nuevaCita.setIdMedico(medicoEntity);
        pacienteEntity = pacienteEJB.busquedaPacientePorDui(this.duiPaciente);
        if(pacienteEntity == null){
            this.addMessage(new FacesMessage( FacesMessage.SEVERITY_WARN ,
                    "¡Advertencia!","No existe ningún paciente con ese dui"
            ));
        }else{
            nuevaCita.setIdPaciente(pacienteEntity);
            this.citasEJB.insertCita(nuevaCita);
            this.event.setDynamicProperty("idEvent", nuevaCita.getIdCita());
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "¡Información!", "Cita creada con éxito"
            ));
            eventModel.addEvent(event);
        }
        
    }
    
    public void listarCitasMedico(){
        List<CitasEntity> citas = this.citasEJB.listarCitas(this.medicoEntity.getIdMedico());
        
        for (CitasEntity cita : citas) {
            DefaultScheduleEvent scheduleEvent = new DefaultScheduleEvent();
            scheduleEvent.setDynamicProperty("idEvent",cita.getIdCita());
            System.out.println(scheduleEvent.getDynamicProperties().get("idEvent"));
            scheduleEvent.setTitle("Titulo provisional");
            scheduleEvent.setStartDate(cita.getFechaCita());
            scheduleEvent.setEndDate(cita.getFechaCita());
            scheduleEvent.setDynamicProperty("paciente", cita.getIdPaciente().getIdPersona().getDui());
            eventModel.addEvent(scheduleEvent);
        }
    }
    
    public void delEvent(){
        eventModel.deleteEvent(event);
        CitasEntity citaEntity = this.citasEJB.obtenerCita((Integer) event.getDynamicProperties().get("idEvent"));
        this.citasEJB.eliminarCita(citaEntity);
        this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Información:", "Evento eliminado con éxito"
        ));
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
