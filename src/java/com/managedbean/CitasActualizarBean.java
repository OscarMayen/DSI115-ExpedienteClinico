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

    public CitasActualizarBean() {
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @PostConstruct
    public void init() {
        usuarioEntity = (UsuarioEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        medicoEntity = this.medicoEJB.obtenerMedicoPorUsuario(usuarioEntity.getUsername());
    }

    public void onEventMove(ScheduleEntryMoveEvent eventDrag) throws Exception {
        this.event = (DefaultScheduleEvent) eventDrag.getScheduleEvent();
        
        CitasEntity citaEntity = this.citasEJB.obtenerCita((Integer) event.getDynamicProperties().get("idEvent"));
        Date auxI = citaEntity.getFechaCita();
        Date auxF = citaEntity.getFechaCitaFinal();
        if (event.getStartDate().compareTo(new Date()) >= 1
                || event.getStartDate().compareTo(new Date()) == 0) {            
            citaEntity.setFechaCita(this.formatearFecha(this.event.getStartDate()));
            citaEntity.setFechaCitaFinal(this.formatearFecha(this.event.getEndDate()));
            
            if (this.validarColisionesEditar(citaEntity.getFechaCita(),
                    citaEntity.getFechaCitaFinal(),
                    citaEntity.getIdCita())) {
                this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "¡Error!", "El evento colisiona"));
                
                this.event.setStartDate(auxI);
                this.event.setEndDate(auxF);
                
            } else {
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
            }
        }else{
            this.event.setStartDate(auxI);
            this.event.setEndDate(auxF);
            
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

    private boolean validarColisionesEditar(Date fechaI, Date fechaF, Integer id) throws ParseException {
        List<CitasEntity> lista = citasEJB.listarCitasPorFechaEditar(fechaI, this.medicoEntity.getIdMedico(), id);
        boolean ban = false;
        String fi = formatoHora.format(fechaI);
        String ff = formatoHora.format(fechaF);
        for (CitasEntity citasEntity : lista) {
            String li = formatoHora.format(citasEntity.getFechaCita());
            String lf = formatoHora.format(citasEntity.getFechaCitaFinal());
            Date fechaInicio = this.formatoHora.parse(fi);
            Date fechaFinal = this.formatoHora.parse(ff);
            Date fechaListaI = this.formatoHora.parse(li);
            Date fechaListaF = this.formatoHora.parse(lf);
            if (fechaInicio.compareTo(fechaListaI) == 0
                    || (fechaInicio.compareTo(fechaListaI) >= 1
                    && fechaInicio.compareTo(fechaListaF) <= -1)) {
                ban = true;
                break;
            } else if (fechaFinal.compareTo(fechaListaI) == 0
                    || (fechaFinal.compareTo(fechaListaI) >= 1
                    && fechaFinal.compareTo(fechaListaF) <= -1)) {
                ban = true;
                break;
            } else {
                ban = false;
            }

        }
        return ban;
    }

}
