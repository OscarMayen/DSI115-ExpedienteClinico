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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author josue
 */
@ManagedBean(name = "citasListarBean")
@ViewScoped
public class CitasListarBean implements Serializable {

    @EJB
    private CitasEJB citasEJB;

    @EJB
    private PacienteEJB pacienteEJB;

    @EJB
    private MedicoEJB medicoEJB;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

    private ScheduleModel eventModel;

    private DefaultScheduleEvent event;

    private MedicoEntity medicoEntity;
    private PacienteEntity pacienteEntity;
    private UsuarioEntity usuarioEntity;
    private CitasEntity citaEntity;

    private String duiPaciente;

    private boolean esGuardar;

    public CitasListarBean() {
    }

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        usuarioEntity = (UsuarioEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        medicoEntity = this.medicoEJB.obtenerMedicoPorUsuario(usuarioEntity.getUsername());
        event = new DefaultScheduleEvent();
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

    public DefaultScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(DefaultScheduleEvent event) {
        this.event = event;
    }

    public boolean isEsGuardar() {
        return esGuardar;
    }

    public void setEsGuardar(boolean esGuardar) {
        this.esGuardar = esGuardar;
    }

    public void addEvent() throws ParseException {
        if (event.getId() == null) {

            this.event.setDynamicProperty("paciente", this.duiPaciente);
            this.crearCita();
        }
        event = new DefaultScheduleEvent();

    }

    public void editEvent() throws Exception {

        if (this.event.getTitle() == null || this.event.getTitle().equals("")
                || this.event.getStartDate() == null || this.event.getEndDate() == null
                || this.duiPaciente == null || this.duiPaciente.equals("")) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "¡Error!", "Ingrese valores a los campos de la cita"
            ));
        } else {
            citaEntity = this.citasEJB.obtenerCita((Integer) event.getDynamicProperties().get("idEvent"));
            if (citaEntity == null) {
                this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "¡Algo salió mal!", "la cita no ha sido ingresada al sistema"
                ));
            } else {
                citaEntity.setFechaCita(this.formatearFecha(this.event.getStartDate()));
                citaEntity.setFechaCitaFinal(this.formatearFecha(this.event.getEndDate()));
                if (this.validarFechaHoraCita(citaEntity.getFechaCita(), citaEntity.getFechaCitaFinal())) {
                    citaEntity.setIdMedico(medicoEntity);
                    pacienteEntity = pacienteEJB.busquedaPacientePorDui(this.duiPaciente);
                    if (pacienteEntity == null) {
                        this.addMessage(new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "¡Advertencia!", "No existe ningún paciente con ese dui"
                        ));
                    } else {
                        citaEntity.setIdPaciente(pacienteEntity);
                        citaEntity.setTitulo(this.event.getTitle());
                        this.citasEJB.actualizarDatosEvento(citaEntity);
                        this.event.setTitle(citaEntity.getTitulo());
                        this.event.setStartDate(citaEntity.getFechaCita());
                        this.event.setEndDate(citaEntity.getFechaCitaFinal());
                        this.event.setDynamicProperty("idEvent", citaEntity.getIdCita());
                        this.event.setDynamicProperty("paciente", this.pacienteEntity.getIdPersona().getDui());
                        this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "¡Información!", "Cita actualizada con éxito"
                        ));
                        this.eventModel.updateEvent(event);
                        this.event = new DefaultScheduleEvent();
                        PrimeFaces.current().executeScript("PF('myschedule').update();PF('eventDialog').hide();");
                    }
                } else {
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error, verifique la fecha que ingreso", "Hay inconsistencias con las fechas"
                    ));
                }
            }

        }

    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (DefaultScheduleEvent) selectEvent.getObject();
        this.duiPaciente = (String) event.getDynamicProperties().get("paciente");
        this.esGuardar = false;
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        this.esGuardar = true;
    }

    public void crearCita() throws ParseException {

        CitasEntity nuevaCita = new CitasEntity();
        nuevaCita.setTitulo(this.event.getTitle());

        nuevaCita.setFechaCita(this.formatearFecha(this.event.getStartDate()));
        nuevaCita.setFechaCitaFinal(this.formatearFecha(this.event.getEndDate()));

        if (this.validarFechaHoraCita(nuevaCita.getFechaCita(), nuevaCita.getFechaCitaFinal())) {
            if (this.validarColisiones(nuevaCita.getFechaCita(),nuevaCita.getFechaCitaFinal())) {
                this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "¡Error!","Hay colision con alguna cita"));
            } else {
                nuevaCita.setIdMedico(medicoEntity);
                pacienteEntity = pacienteEJB.busquedaPacientePorDui(this.duiPaciente);
                if (pacienteEntity == null) {
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "¡Advertencia!", "No existe ningún paciente con ese dui"
                    ));
                } else {
                    nuevaCita.setIdPaciente(pacienteEntity);
                    this.citasEJB.insertCita(nuevaCita);
                    this.event.setDynamicProperty("idEvent", nuevaCita.getIdCita());
                    this.event.setDynamicProperty("paciente", this.pacienteEntity.getIdPersona().getDui());
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "¡Información!", "Cita creada con éxito"
                    ));
                    eventModel.addEvent(event);
                }
            }

        } else {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error, verifique la fecha que ingreso", "Hay inconsistencias con las fechas"
            ));
        }
        this.event = new DefaultScheduleEvent();
    }

    public void listarCitasMedico() {
        List<CitasEntity> citas = this.citasEJB.listarCitas(this.medicoEntity.getIdMedico());
        for (CitasEntity cita : citas) {
            DefaultScheduleEvent scheduleEvent = new DefaultScheduleEvent();
            scheduleEvent.setDynamicProperty("idEvent", cita.getIdCita());
            scheduleEvent.setTitle(cita.getTitulo());
            try {
                scheduleEvent.setStartDate(this.formatearFecha(cita.getFechaCita()));
                scheduleEvent.setEndDate(this.formatearFecha(cita.getFechaCitaFinal()));
            } catch (ParseException ex) {
                Logger.getLogger(CitasListarBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            scheduleEvent.setDynamicProperty("paciente", cita.getIdPaciente().getIdPersona().getDui());
            eventModel.addEvent(scheduleEvent);
        }
    }

    public void delEvent() {
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

    private Date formatearFecha(Date fecha) throws ParseException {
        String fechaFormeteada = simpleDateFormat.format(fecha);
        Date fechaRetornar = simpleDateFormat.parse(fechaFormeteada);

        return fechaRetornar;
    }

    private boolean validarFechaHoraCita(Date startDateTime, Date endDateTime) {
        long fechaInicio = startDateTime.getTime();
        long fechaFinal = endDateTime.getTime();
        long diferencia = fechaFinal - fechaInicio;
        long diffHours = diferencia / (60 * 60 * 1000);
        Date fechaActual = new Date();

        if (endDateTime.getDate() == startDateTime.getDate()) {
            if (endDateTime.getDate() == fechaActual.getDate()
                    && startDateTime.getDate() == fechaActual.getDate()
                    || endDateTime.after(fechaActual)
                    && startDateTime.after(fechaActual)) {
                if (diffHours >= 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error, la fecha esta caducada", "solo puede ingresar fechas en la fecha actual o superior"
                ));
                return false;
            }

        } else {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en las fechas", "Las fechas no son iguales"
            ));
            return false;
        }
    }

    private boolean validarColisiones(Date fechaI,Date fechaF) throws ParseException {
        List<CitasEntity> lista = citasEJB.listarCitasPorFecha(fechaI, this.medicoEntity.getIdMedico());
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
                System.out.println(fechaInicio);
                System.out.println(fechaListaI);
                System.out.println(fechaListaF);
                ban = true;
                break;
            } else if(fechaFinal.compareTo(fechaListaI) == 0
                    || (fechaFinal.compareTo(fechaListaI)>=1
                    && fechaFinal.compareTo(fechaListaF)<= -1)){
                ban = true;
                System.out.println(fechaFinal);
                System.out.println(fechaListaI);
                System.out.println(fechaListaF);
                break;
            }else{
                System.out.println(fechaFinal.compareTo(fechaListaI));
                System.out.println(fechaFinal);
                System.out.println(fechaListaI);
                System.out.println(fechaListaF);
                ban = false;
                break;
            }
            
        }
        return ban;
    }

}
