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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
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

    List<MedicoEntity> listaMedico;

    @EJB
    private MedicoEJB medicoEJB;


    @EJB
    private RolEJB rolEJB;

    @EJB
    private EspecialidadEJB especialidadEJB;

    UsuarioEntity usuario = new UsuarioEntity();
    RolEntity rol = new RolEntity();
    EspecialidadEntity especialidad = new EspecialidadEntity();
    private MedicoEntity medico = new MedicoEntity();
    PersonaEntity persona = new PersonaEntity();

    public UsuarioMedicoBean() {

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
      
        try {
            
            medicoEJB.modificarMedico(medico);
           
        } catch (Exception ex) {
            Logger.getLogger(UsuarioMedicoBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR " + ex.getMessage() ));
        }
   
        return "/admin/medico/medicoListar?faces-redirect=true";
      
    }
    
     public String insertarUsuarioMedico() {
        this.medico.setIdPersona(persona);
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
