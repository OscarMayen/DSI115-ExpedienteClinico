/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.entities.UsuarioEntity;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author josue
 */
@ManagedBean(name = "perfilBean")
@ViewScoped
public class perfilBean implements Serializable{

    private UsuarioEntity user = new UsuarioEntity();
    
    public perfilBean() {
    }
    
    @PostConstruct
    public void init(){
        user = (UsuarioEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }

    public UsuarioEntity getUser() {
        return user;
    }

    public void setUser(UsuarioEntity user) {
        this.user = user;
    }
    
    
    
}
