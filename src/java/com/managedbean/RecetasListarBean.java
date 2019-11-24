/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.MedicoEJB;
import com.ejb.RecetasEJB;
import com.entities.ConsultaEntity;
import com.entities.MedicoEntity;
import com.entities.RecetaEntity;
import com.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DTO
 */
@Named(value = "recetasListarBean")
@ViewScoped
public class RecetasListarBean implements Serializable {

    @EJB
    private MedicoEJB medicoEJB;
    @EJB
    private RecetasEJB recetasEJB;    
    List<RecetaEntity> recetas= new ArrayList<RecetaEntity>();
    MedicoEntity medico= new MedicoEntity();
    UsuarioEntity user= new UsuarioEntity();    
    
   
    public RecetasListarBean() {
    }
    
    @PostConstruct
    public void init(){        
       user = (UsuarioEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
       medico = medicoEJB.obtenerMedicoPorUsuario(user.getUsername());
       recetas=recetasEJB.listarRecetas(medico.getIdMedico());
    }
    
       
    public String verReceta(){
        return "/admin/recetas/recetasDetalle.xhtml";
    }    

    public List<RecetaEntity> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<RecetaEntity> recetas) {
        this.recetas = recetas;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public UsuarioEntity getUser() {
        return user;
    }

    public void setUser(UsuarioEntity user) {
        this.user = user;
    }

   
    
    
}
