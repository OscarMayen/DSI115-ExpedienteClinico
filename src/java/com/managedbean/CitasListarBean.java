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
import com.entities.HorarioEntity;
import com.entities.MedicoEntity;
import com.entities.PacienteEntity;
import com.entities.UsuarioEntity;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.primefaces.event.ScheduleEntryMoveEvent;
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

    private List<MedicoEntity> listaDeMedicos;

    private List<HorarioEntity> horarioMedico;

    public CitasListarBean() {
    }

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        usuarioEntity = (UsuarioEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        event = new DefaultScheduleEvent();
        listaDeMedicos = medicoEJB.listarMedico();
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

    public List<MedicoEntity> getListaDeMedicos() {
        return listaDeMedicos;
    }

    public void setListaDeMedicos(List<MedicoEntity> listaDeMedicos) {
        this.listaDeMedicos = listaDeMedicos;
    }

    public MedicoEntity getMedicoEntity() {
        return medicoEntity;
    }

    public void setMedicoEntity(MedicoEntity medicoEntity) {
        this.medicoEntity = medicoEntity;
    }

    public List<HorarioEntity> getHorarioMedico() {
        return horarioMedico;
    }

    public void setHorarioMedico(List<HorarioEntity> horarioMedico) {
        this.horarioMedico = horarioMedico;
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
                    if (this.validarColisionesEditar(citaEntity.getFechaCita(), citaEntity.getFechaCitaFinal(), citaEntity.getIdCita())) {
                        this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "¡Error!", "Hay citas que colisionan"));

                    } else {
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
            if (this.validarColisiones(nuevaCita.getFechaCita(), nuevaCita.getFechaCitaFinal())) {
                this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "¡Error!", "Hay colision con alguna cita"));
            } else {
                if (medicoEntity != null && 
                        this.verificarDisponibilidadMedico(nuevaCita.getFechaCita(), nuevaCita.getFechaCitaFinal())) {
                    
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
                } else {
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "¡Error!",
                            "Elija el médico al cual le asignara la cita y verifique el rango de disponibilidad del médico"));
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

    public void listarCitasDelMedico() {
        System.out.println("medico" + medicoEntity.getIdPersona().getNombrePersona());
        eventModel.clear();
        this.horarioMedico = this.medicoEJB.obtenerHorarioMedico(medicoEntity.getIdMedico());
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

    private boolean validarColisiones(Date fechaI, Date fechaF) throws ParseException {
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
                ban = true;
                break;
            } else if (fechaFinal.compareTo(fechaListaI) == 0
                    || (fechaFinal.compareTo(fechaListaI) >= 1
                    && fechaFinal.compareTo(fechaListaF) <= -1)) {
                ban = true;
                break;
            } else if( (fechaListaI.compareTo(fechaInicio)>=0
                    && fechaListaI.compareTo(fechaFinal) <= -1)){
                ban = true;
                break;
            }else if( (fechaListaF.compareTo(fechaInicio) >= 0
                    && fechaListaF.compareTo(fechaFinal) <= -1) ){
                ban = true;
                break;
            }else{
                ban = false;
            }

        }
        return ban;
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
            } else if( (fechaListaI.compareTo(fechaInicio)>=0
                    && fechaListaI.compareTo(fechaFinal) <= -1)){
                ban = true;
                break;
            }else if( (fechaListaF.compareTo(fechaInicio) >= 0
                    && fechaListaF.compareTo(fechaFinal) <= -1) ){
                ban = true;
                break;
            }else{
                ban = false;
            }

        }
        return ban;
    }

    public void onEventMove(ScheduleEntryMoveEvent eventDrag) throws Exception {
        this.event = (DefaultScheduleEvent) eventDrag.getScheduleEvent();
        Object object = this.citasEJB.obtenerFechas((Integer) event.getDynamicProperties().get("idEvent"));
        Object[] o = (Object[]) object;
        
        citaEntity = this.citasEJB.obtenerCita((Integer) event.getDynamicProperties().get("idEvent"));

        if (event.getStartDate().compareTo(new Date()) >= 1
                || event.getStartDate().compareTo(new Date()) == 0) {

            citaEntity.setFechaCita(this.formatearFecha(this.event.getStartDate()));
            citaEntity.setFechaCitaFinal(this.formatearFecha(this.event.getEndDate()));

            if (this.validarColisionesEditarM(citaEntity.getFechaCita(),
                    citaEntity.getFechaCitaFinal(),
                    citaEntity.getIdCita())) {
                this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "¡Error!", "El evento colisiona"));

                this.event.setStartDate((Date) o[0]);
                this.event.setEndDate((Date) o[1]);
                
                PrimeFaces.current().executeScript("PF('myschedule').update();");
            } else {
                if (this.citasEJB.actualizarFechaEvento(citaEntity) == 1) {

                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Información", "Fecha del evento actualizada"
                    ));
                    PrimeFaces.current().executeScript("PF('myschedule').update();");
                } else {
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Información", "Algo salió mal"
                    ));
                    PrimeFaces.current().executeScript("PF('myschedule').update();");
                }
                this.event = new DefaultScheduleEvent();
            }
        } else {
            this.event.setStartDate((Date) o[0]);
            this.event.setEndDate((Date) o[1]);
            PrimeFaces.current().executeScript("PF('myschedule').update();");
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "¡Error!", "la fecha no será actualizada, no puede editar un cita a una fecha caducada"));
        }
        this.event = new DefaultScheduleEvent();

    }

    private boolean validarColisionesEditarM(Date fechaI, Date fechaF, Integer id) throws ParseException {
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
            } else if( (fechaListaI.compareTo(fechaInicio)>=0
                    && fechaListaI.compareTo(fechaFinal) <= -1)){
                ban = true;
                break;
            }else if( (fechaListaF.compareTo(fechaInicio) >= 0
                    && fechaListaF.compareTo(fechaFinal) <= -1) ){
                ban = true;
                break;
            }else{
                ban = false;
            }

        }
        return ban;
    }

    private boolean verificarDisponibilidadMedico(Date fechaInicio, Date fechaFinal) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        int dia = calendar.get(Calendar.DAY_OF_WEEK);
        String nombreDia = null                                       ,
                 horaInicio = formatoHora.format(fechaInicio),
                 horaFinal = formatoHora.format(fechaFinal) ;
        Date horaInicioComparar = formatoHora.parse(horaInicio) ,
                horaFinalComparar = formatoHora.parse(horaFinal)  ;

        boolean resultado = false;
        switch (dia) {
            case 1:
                nombreDia = "Domingo";
                for (HorarioEntity horarioEntity : horarioMedico) {
                    if(horarioEntity.getIdDia().getNombreDia().equals(nombreDia)){
                        Date horaInicioDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraInicio()),
                                horaFinalDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraFin());
                        
                        if(verificarRangoDeDisponibilidad(horaInicioComparar,horaFinalComparar,
                                        horaInicioDisponibilidad, horaFinalDisponibilidad)){
                            resultado = true;
                        }
                    }
                }
                break;
            case 2:
                nombreDia = "Lunes";
                for (HorarioEntity horarioEntity : horarioMedico) {
                    if(horarioEntity.getIdDia().getNombreDia().equals(nombreDia)){
                        Date horaInicioDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraInicio()),
                                horaFinalDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraFin());
                        
                        if(verificarRangoDeDisponibilidad(horaInicioComparar,horaFinalComparar,
                                        horaInicioDisponibilidad, horaFinalDisponibilidad)){
                            resultado = true;
                        }
                    }
                }
                break;
            case 3:
                nombreDia = "Martes";
                for (HorarioEntity horarioEntity : horarioMedico) {
                    if(horarioEntity.getIdDia().getNombreDia().equals(nombreDia)){
                        Date horaInicioDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraInicio()),
                                horaFinalDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraFin());
                        
                        if(verificarRangoDeDisponibilidad(horaInicioComparar,horaFinalComparar,
                                        horaInicioDisponibilidad, horaFinalDisponibilidad)){
                            resultado = true;
                        }
                    }
                }
                break;
            case 4:
                nombreDia = "Miércoles";
                for (HorarioEntity horarioEntity : horarioMedico) {
                    if(horarioEntity.getIdDia().getNombreDia().equals(nombreDia)){
                        Date horaInicioDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraInicio()),
                                horaFinalDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraFin());
                        
                        if(verificarRangoDeDisponibilidad(horaInicioComparar,horaFinalComparar,
                                        horaInicioDisponibilidad, horaFinalDisponibilidad)){
                            resultado = true;
                        }
                    }
                }
                break;
            case 5:
                nombreDia = "Jueves";
                for (HorarioEntity horarioEntity : horarioMedico) {
                    if(horarioEntity.getIdDia().getNombreDia().equals(nombreDia)){
                        Date horaInicioDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraInicio()),
                                horaFinalDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraFin());
                        
                        if(verificarRangoDeDisponibilidad(horaInicioComparar,horaFinalComparar,
                                        horaInicioDisponibilidad, horaFinalDisponibilidad)){
                            resultado = true;
                        }
                    }
                }
                break;
            case 6:
                nombreDia = "Viernes";
                for (HorarioEntity horarioEntity : horarioMedico) {
                    if(horarioEntity.getIdDia().getNombreDia().equals(nombreDia)){
                        Date horaInicioDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraInicio()),
                                horaFinalDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraFin());
                        
                        if(verificarRangoDeDisponibilidad(horaInicioComparar,horaFinalComparar,
                                        horaInicioDisponibilidad, horaFinalDisponibilidad)){
                            resultado = true;
                        }
                    }
                }
                break;
            case 7:
                nombreDia = "Sabado";
                for (HorarioEntity horarioEntity : horarioMedico) {
                    if(horarioEntity.getIdDia().getNombreDia().equals(nombreDia)){
                        Date horaInicioDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraInicio()),
                                horaFinalDisponibilidad = formatoHora.parse(horarioEntity.getIdHora().getHoraFin());
                        
                        if(verificarRangoDeDisponibilidad(horaInicioComparar,horaFinalComparar,
                                        horaInicioDisponibilidad, horaFinalDisponibilidad)){
                            resultado = true;
                        }
                    }
                }
                break;
        }
        return resultado;
    }
    
    private boolean verificarRangoDeDisponibilidad(
            Date horaCitaInicio,Date horaCitaFinal,
            Date horaDisponibleInicio, Date horaDisponibleFinal){
        
        if(horaCitaInicio.compareTo(horaDisponibleInicio)>=0 &&
                horaCitaInicio.compareTo(horaDisponibleFinal)<=-1){
            if(horaCitaFinal.compareTo(horaDisponibleInicio)>=1 &&
                    horaCitaFinal.compareTo(horaDisponibleFinal)<=0){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
}
