/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.UsuarioEJB;
import com.entities.UsuarioEntity;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
public class perfilBean implements Serializable {

    @EJB
    private UsuarioEJB usuarioEJB;

    private String oldPass;
    private String pass1;
    private String pass2;
    private UsuarioEntity user = new UsuarioEntity();

    public perfilBean() {
    }

    @PostConstruct
    public void init() {
        user = (UsuarioEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }

    public void actualizarCuenta() throws Exception {
        this.usuarioEJB.modificarUsuario(user);
    }

    public void cambiarContrasenia() throws Exception {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] hash = md.digest(oldPass.getBytes());
        StringBuffer sb = new StringBuffer();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        

        if (sb.toString().equals(this.user.getPassword())) {
            if (pass1.equals(pass2)) {
                this.user.setPassword(pass1);
                this.usuarioEJB.modificarUsuario(user);
            }
        } else {
            System.out.println("no es igual");
        }
    }

    public UsuarioEntity getUser() {
        return user;
    }

    public void setUser(UsuarioEntity user) {
        this.user = user;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

}
