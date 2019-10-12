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

    private String errorPass;
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
        byte[] hash = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            hash = md.digest(oldPass.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            if (this.oldPass.equals("") || this.pass1.equals("") || this.pass2.equals("")) {
                this.errorPass = "Campos vacios, llenelos todos por favor";
            } else {
                System.out.println(this.user);
                System.out.println("pass de usuario"+this.user.getPassword());
                System.out.println("pass de campo" + sb.toString());
                System.out.println(pass1);
                System.out.println(pass2);
                if (sb.toString().equals(this.user.getPassword())) {
                    if (pass1.equals(pass2)) {
                        MessageDigest md1 = null;
                        byte[] hash1 = null;
                        try {
                            md1 = MessageDigest.getInstance("SHA-256");
                            hash1 = md.digest(pass1.getBytes());
                            StringBuilder sb1 = new StringBuilder();

                            for (byte b : hash1) {
                                sb1.append(String.format("%02x", b));
                            }
                            this.user.setPassword(sb1.toString());
                            this.usuarioEJB.modificarUsuario(user);
                            this.pass1 = "";
                            this.pass2 = "";
                            this.oldPass = "";
                        }catch (NoSuchAlgorithmException e) {
                        }
                    } else {
                        this.errorPass = "Las contraseñas deben ser iguales";
                        this.pass1 = "";
                        this.pass2 = "";
                        this.oldPass = "";
                    }
                } else {
                    this.errorPass = "Error, digite su contraseña actual";
                    this.pass1 = "";
                    this.pass2 = "";
                    this.oldPass = "";
                }
            }
        } catch (NoSuchAlgorithmException e) {
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

    public String getErrorPass() {
        return errorPass;
    }

    public void setErrorPass(String errorPass) {
        this.errorPass = errorPass;
    }

}
