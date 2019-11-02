/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.EspecialidadEJB;
import com.ejb.MedicoEJB;
import com.ejb.PersonaEJB;
import com.ejb.RolEJB;
import com.ejb.UsuarioMedicoEJB;
import com.entities.EspecialidadEntity;
import com.entities.MedicoEntity;
import com.entities.PersonaEntity;
import com.entities.RedsocialEntity;
import com.entities.RolEntity;
import com.entities.UsuarioEntity;
import edu.utilidades.JsfUtils;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.jboss.weld.bean.builtin.FacadeInjectionPoint;

/**
 *
 * @author josue
 */
@ManagedBean(name = "usuarioMedicoBean")
@ViewScoped
public class UsuarioMedicoBean implements Serializable {

    @EJB
    private UsuarioMedicoEJB usuarioMedicoEJB;

    List<MedicoEntity> listaMedico = new ArrayList();
    
    private List<EspecialidadEntity> especialidadesMedico = new ArrayList();
    private List<EspecialidadEntity> especialidadesGuardar = new ArrayList();
    private EspecialidadEntity[] especilidadesSeleccionadas;
    
    private RedsocialEntity facebook = new RedsocialEntity();
    private RedsocialEntity twitter = new RedsocialEntity();
    private RedsocialEntity gmail = new RedsocialEntity();
    private List<RedsocialEntity> redes = new ArrayList();
    
    @EJB
    private MedicoEJB medicoEJB;


    @EJB
    private RolEJB rolEJB;

    @EJB
    private EspecialidadEJB especialidadEJB;
    UsuarioEntity usuario = new UsuarioEntity();
    RolEntity rol = new RolEntity();
    List<EspecialidadEntity> especialidades;
    EspecialidadEntity especialidad = new EspecialidadEntity();
    private MedicoEntity medico = new MedicoEntity();
    PersonaEntity persona = new PersonaEntity();

    public UsuarioMedicoBean() {

    }

    public List<EspecialidadEntity> getEspecialidadesGuardar() {
        return especialidadesGuardar;
    }

    public void setEspecialidadesGuardar(List<EspecialidadEntity> especialidadesGuardar) {
        this.especialidadesGuardar = especialidadesGuardar;
    }
    
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    
    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    public EspecialidadEntity getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadEntity especialidad) {
        this.especialidad = especialidad;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public List<EspecialidadEntity> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadEntity> especialidades) {
        this.especialidades = especialidades;
    }

    public List<MedicoEntity> getListaMedico() {
        return listaMedico;
    }

    public void setListaMedico(List<MedicoEntity> listaMedico) {
        this.listaMedico = listaMedico;
    }

    public List<EspecialidadEntity> getEspecialidadesMedico() {
        return especialidadesMedico;
    }

    public void setEspecialidadesMedico(List<EspecialidadEntity> especialidadesMedico) {
        this.especialidadesMedico = especialidadesMedico;
    }

    public EspecialidadEntity[] getEspecilidadesSeleccionadas() {
        return especilidadesSeleccionadas;
    }

    public void setEspecilidadesSeleccionadas(EspecialidadEntity[] especilidadesSeleccionadas) {
        this.especilidadesSeleccionadas = especilidadesSeleccionadas;
    }

    public RedsocialEntity getFacebook() {
        return facebook;
    }

    public void setFacebook(RedsocialEntity facebook) {
        this.facebook = facebook;
    }

    public RedsocialEntity getTwitter() {
        return twitter;
    }

    public void setTwitter(RedsocialEntity twitter) {
        this.twitter = twitter;
    }

    public RedsocialEntity getGmail() {
        return gmail;
    }

    public void setGmail(RedsocialEntity gmail) {
        this.gmail = gmail;
    }

    public List<RolEntity> getListaRoles() {
        return rolEJB.listarRoles();
    }

    public List<EspecialidadEntity> getListarEspecialidades() {
        return this.especialidadEJB.especialidadListar();
    }

    @PostConstruct
    public void init() {
        
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        FacesContext fc = FacesContext.getCurrentInstance();
        
        HttpServletRequest request = (HttpServletRequest) fc
                .getExternalContext().getRequest();
        System.out.println(fc.getExternalContext().getRequestParameterMap());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        String id = fc.getExternalContext().getRequestParameterMap().get("id");
        if (id == null) {
            System.out.println("Error!!!!");
        } else {
             this.medico = new MedicoEntity();
       
        medico = medicoEJB.obtenerMedico(Integer.valueOf(id));
        
        String isEditar = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("isEditar");
        if(isEditar != null && !isEditar.equals("")){
            String valores = "";
        
            for (int i =0; i < medico.getEspecialidadEntityList().size(); i++) {
                valores = valores + medico.getEspecialidadEntityList().get(i).getIdEspecialidad();
                if(medico.getEspecialidadEntityList().size()-1 !=i){
                    valores = valores + ",";
                }
                
            }
            this.especialidadesMedico = medicoEJB.obtenerEspecialidades(valores);
        }else{
            this.especialidadesMedico = this.especialidadEJB.especialidadListar();
        }
        
            for (RedsocialEntity red : medico.getRedsocialEntityList()) {
                if(red.getNombre().equals("Facebook")){
                    this.facebook = red;
                }else if(red.getNombre().equals("Twitter")){
                    this.twitter = red;
                }else{
                    this.gmail = red;
                }
                
            }
        }
         
    
    }
    
    public String editMedico(int id)
    {
        this.medico = new MedicoEntity();
        this.persona = new PersonaEntity();
        medico = medicoEJB.obtenerMedico(id);

        return "/admin/medico/medicoEditar.xhtml"; 
    }
    
    public String updateMedico(){
        for(EspecialidadEntity especialidadesSeleccionada : this.especilidadesSeleccionadas) {
            this.especialidadesGuardar.add(especialidadesSeleccionada);
        }
        for(EspecialidadEntity esp : medico.getEspecialidadEntityList()) {
            this.especialidadesGuardar.add(esp);
        }
        try {
            this.redes.add(this.facebook);
            this.redes.add(gmail);
            this.redes.add(twitter);
            this.medico.setRedsocialEntityList(redes);
            this.medico.setEspecialidadEntityList(especialidadesGuardar);
            medicoEJB.modificarMedico(medico);
           
        } catch (Exception ex) {
            Logger.getLogger(UsuarioMedicoBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR " + ex.getMessage() ));
        }
   
        return "/admin/medico/medicoListar?faces-redirect=true";
      
    }
    
     public String insertarUsuarioMedico() {
        for (EspecialidadEntity especialidadesSeleccionada : this.especilidadesSeleccionadas) {
            this.especialidadesGuardar.add(especialidadesSeleccionada);
        }
        System.out.println(this.persona.getNombrePersona());
        facebook.setNombre("Facebook");
        twitter.setNombre("Twitter");
        gmail.setNombre("Gmail");
        this.redes.add(facebook);
        this.redes.add(twitter);
        this.redes.add(gmail);
        this.medico.setIdPersona(persona);
        this.medico.setRedsocialEntityList(redes);
        this.medico.setEspecialidadEntityList(especialidadesGuardar);
        if (medicoEJB.insertMedico(medico) == 0) {
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR"));
            return null;
        } 
        else {
            usuario.setIdPersona(persona);
            int b = usuarioMedicoEJB.insertUsuarioMedico(usuario);
        }
        return "/admin/medico/medicoListar?faces-redirect=true";
    }
    
}
