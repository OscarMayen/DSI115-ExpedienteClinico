/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.CitasEJB;
import com.ejb.MedicoEJB;
import com.entities.CitasEntity;
import com.entities.MedicoEntity;
import com.entities.UsuarioEntity;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
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

    @EJB
    private MedicoEJB medicoEJB;
    
   

    private DefaultScheduleEvent event;
    private SimpleDateFormat simpleDateFormat;
    private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
    private UsuarioEntity usuarioEntity;
    private MedicoEntity medicoEntity;
    private CitasEntity citaEntity;

    public CitasActualizarBean() {
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        citaEntity = new CitasEntity();
    }

    @PostConstruct
    public void init() {
        usuarioEntity = (UsuarioEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        medicoEntity = this.medicoEJB.obtenerMedicoPorUsuario(usuarioEntity.getUsername());
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

   

}
