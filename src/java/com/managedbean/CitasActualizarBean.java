/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.CitasEJB;
import com.entities.CitasEntity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.model.DefaultScheduleEvent;

/**
 *
 * @author josue
 */
@Named(value = "citasActualizarBean")
@ViewScoped
public class CitasActualizarBean implements Serializable{

     @EJB
     private CitasEJB citasEJB;
     private DefaultScheduleEvent event;
    private SimpleDateFormat simpleDateFormat;
    
    public CitasActualizarBean() {
        this.simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    }
    
    public void onEventMove(ScheduleEntryMoveEvent eventDrag) throws Exception {
        this.event = (DefaultScheduleEvent) eventDrag.getScheduleEvent();
        CitasEntity citaEntity = this.citasEJB.obtenerCita((Integer) event.getDynamicProperties().get("idEvent"));
        String fechaNueva = simpleDateFormat.format(this.event.getStartDate());
        Date nuevaDate = simpleDateFormat.parse(fechaNueva);
        citaEntity.setFechaCita(nuevaDate);

        if(this.citasEJB.actualizarFechaEvento(citaEntity) == 1){

            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Información","Fecha del evento actualizada"
            ));
        }else{
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Información", "Algo salió mal"
            ));
        }
        this.event = new DefaultScheduleEvent();
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
