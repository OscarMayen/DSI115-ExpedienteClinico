/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.CitasEJB;
import com.entities.CitasEntity;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author josue
 */
@Named(value = "citasActualizarBean")
@ViewScoped
public class CitasActualizarBean implements Serializable {

    @EJB
    private CitasEJB citasEJB;
    private DefaultScheduleEvent event;
    private SimpleDateFormat simpleDateFormat;

    public CitasActualizarBean() {
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void onEventMove(ScheduleEntryMoveEvent eventDrag) throws Exception {
        this.event = (DefaultScheduleEvent) eventDrag.getScheduleEvent();
        if (event.getStartDate().compareTo(new Date())>=1 ||
            event.getStartDate().compareTo(new Date())==0) {
            CitasEntity citaEntity = this.citasEJB.obtenerCita((Integer) event.getDynamicProperties().get("idEvent"));
            citaEntity.setFechaCita(this.formatearFecha(this.event.getStartDate()));
            citaEntity.setFechaCitaFinal(this.formatearFecha(this.event.getEndDate()));
            if (this.citasEJB.actualizarFechaEvento(citaEntity) == 1) {

                this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Información", "Fecha del evento actualizada"
                ));
            } else {
                this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Información", "Algo salió mal"
                ));
            }
            this.event = new DefaultScheduleEvent();
        }else{
            this.event.setStartDate(this.event.getStartDate());
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "¡Error!","la fecha no será actualizada, no puede editar un cita a una fecha caducada"));
        }
    }

    public DefaultScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(DefaultScheduleEvent event) {
        this.event = event;
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private Date formatearFecha(Date fecha) throws ParseException {
        String fechaFormeteada = simpleDateFormat.format(fecha);
        Date fechaRetornar = simpleDateFormat.parse(fechaFormeteada);

        return fechaRetornar;
    }

}
