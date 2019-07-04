/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.EspecialidadEJB;
import com.ejb.MedicoEJB;
import com.ejb.RolEJB;
import com.ejb.UsuarioMedicoEJB;
import com.entities.EspecialidadEntity;
import com.entities.MedicoEntity;
import com.entities.PersonaEntity;
import com.entities.RolEntity;
import com.entities.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author josue
 */
@ManagedBean
@ViewScoped
public class UsuarioMedicoBean implements Serializable {

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
    
    

    /**
     * **Obtenemos las listas para llenar los combobox***
     */
    public List<RolEntity> getListaRoles() {
        return rolEJB.listarRoles();
    }
    
    public List<EspecialidadEntity> getListarEspecialidades(){
        return this.especialidadEJB.especialidadListar();
    }
    
    
    public String insertarUsuarioMedico(){
        
        System.out.println("alv :v--------------------------------------------------------");
        System.out.println("nombre:"+this.persona.getNombrePersona());
        System.out.println("apellido:"+this.persona.getApellidoPersona());
        System.out.println("email:"+this.medico.getEmailMedico());
        System.out.println("id:"+this.medico.getIdEspecialidad());
        //this.usuario.setIdPersona(persona);
        //this.medico.setIdPersona(persona);
        
        
        /*if(usuarioMedicoEJB.insertUsuarioMedico(usuario) == 0){
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Ya existe un usuario con ese codigo"));
            return null;
        }
        
        
        if(medicoEJB.insertMedico(medico) == 0){
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Ya existe un usuario con ese codigo"));
            return null;
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("exito","usuario insertado con exito");
        this.usuario = new Usuario();
        this.medico = new MedicoEntity();
        this.persona = new PersonaEntity();*/
        return "/medico/listadoMedicos?faces-redirect=true";
    }
    
    public void imprimir(){
        System.out.println("alv tu madre :v--------------------------------------------------------");
    }
}
