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
import com.entities.Usuario;
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
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author josue
 */
@ManagedBean(name = "usuarioMedicoBean")
@RequestScoped
public class UsuarioMedicoBean implements Serializable {

    @EJB
    private PersonaEJB personaEJB;

    List<MedicoEntity> listaMedico;

    @EJB
    private MedicoEJB medicoEJB;

    @EJB
    private UsuarioMedicoEJB usuarioMedicoEJB;

    @EJB
    private RolEJB rolEJB;

    @EJB
    private EspecialidadEJB especialidadEJB;
    
    

    Usuario usuario = new Usuario();
    RolEntity rol = new RolEntity();
    EspecialidadEntity especialidad = new EspecialidadEntity();
    MedicoEntity medico = new MedicoEntity();
    PersonaEntity persona = new PersonaEntity();

    public UsuarioMedicoBean() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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

    public List<MedicoEntity> getListaMedico() {
        this.listaMedico = medicoEJB.listarMedico();
        return listaMedico;
    }

    public void setListaMedico(List<MedicoEntity> listaMedico) {
        this.listaMedico = listaMedico;
    }

    public List<RolEntity> getListaRoles() {
        return rolEJB.listarRoles();
    }

    public List<EspecialidadEntity> getListarEspecialidades() {
        return this.especialidadEJB.especialidadListar();
    }

    public String insertarUsuarioMedico() {
        
        this.medico.setIdPersona(persona);

        if (medicoEJB.insertMedico(medico) == 0) {
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR"));
            return null;
        } else {
            usuario.setIdPersona(persona);
            int b = usuarioMedicoEJB.insertUsuarioMedico(usuario);
            
        }

        this.usuario = new Usuario();
        this.medico = new MedicoEntity();
        this.persona = new PersonaEntity();

        return "/admin/medico/medicoListar?faces-redirect=true";
    }
    
    public String editMedico(int id)
    {
        this.medico = new MedicoEntity();
        this.persona = new PersonaEntity();
        medico = medicoEJB.obtenerMedico(id);
        Map<String, Object> sessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("medico", medico);
        persona = personaEJB.obtenerPersona(this.medico.getIdPersona().getIdPersona());
        return "/admin/medico/medicoEditar.xhtml"; 
    }
    
    public String updateMedico(){
        
        /*if(this.personaEJB.updatePerson(persona)==true){
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR"));
            return null;
        }
        
        if(this.medicoEJB.updateDoctor(medico)==1){
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR"));
            return null;
        }*/
        
        
        this.medico.setIdPersona(persona);

        if (medicoEJB.modificarMedico(medico) == 0) {
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR"));
            return null;
        }
        
        
        return "/admin/medico/medicoListar?faces-redirect=true";
    }

}
