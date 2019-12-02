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
import com.entities.MedicoEntity;
import com.entities.PersonaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Oscar Mayen
 */
//ManagedBean
@ManagedBean(name = "medicoListar")
@ViewScoped
public class MedicoListar implements Serializable {

    
    @EJB
    private PersonaEJB personaEJB;

    @EJB
    private MedicoEJB medicoEJB;

    private MedicoEntity medico = new MedicoEntity();
    private PersonaEntity persona = new PersonaEntity();
    
    private String dui;
    private String esp;
    private List<MedicoEntity> listaMedico = new ArrayList();
    
    public List<MedicoEntity> getListaMedico() {
        return listaMedico;
    }

    public void setListaMedico(List<MedicoEntity> listaMedico) {
        this.listaMedico = listaMedico;
    }
    
    /**
     * Creates a new instance of MedicoListar
     */
    public MedicoListar() {
    }

     @PostConstruct
    public void init() {
         System.out.println("!!!!!!!!!!!!!");
       listaMedico = buscarMedicos();
    
    }
    
    private List<MedicoEntity> buscarMedicos() {
         this.listaMedico = medicoEJB.listarMedico();
        return listaMedico;
    }
    
     public String editMedico(int id)
    {
//        this.medico = new MedicoEntity();
//        this.persona = new PersonaEntity();
//        medico = medicoEJB.obtenerMedico(id);
//       
//        Map<String, Object> sessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//        sessionMap.put("medico", medico);
//        persona = personaEJB.obtenerPersona(this.medico.getIdPersona().getIdPersona());
        return "/admin/medico/medicoEditar.xhtml"; 
    }
     
    public String detalleMedico(int id)
    {

        return "/admin/medico/medicoDetalle.xhtml"; 
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }
    
    public void filtrar(){
        this.listaMedico = this.medicoEJB.filtrar(dui, esp);
    }
    
    
}
