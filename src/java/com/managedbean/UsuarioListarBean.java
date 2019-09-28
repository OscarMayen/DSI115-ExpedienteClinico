/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.PersonaEJB;
import com.ejb.UsuarioEJB;
import com.entities.PersonaEntity;
import com.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author josue
 */
@ManagedBean(name = "usuarioListarBean")
@ViewScoped
public class UsuarioListarBean implements Serializable{

    @EJB
    private UsuarioEJB usuarioEJB;

    @EJB
    private PersonaEJB personaEJB;

    private Usuario usuario = new Usuario();
    private PersonaEntity persona = new PersonaEntity();
    
    private String username, rol, dui;
    
    private List<Usuario> listaUsuario = new ArrayList();

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }
    
    public void filtrar(){
        listaUsuario = this.usuarioEJB.filtrarUsuarios(username,rol,dui);
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }
    
    
    
    public UsuarioListarBean() {
        
    }
    
    @PostConstruct
    public void init() {
         System.out.println("!!!!!!!!!!!!!");
       listaUsuario = buscarUsuarios();
    
    }
    
    private List<Usuario> buscarUsuarios() {
         this.listaUsuario = usuarioEJB.listarUsuario();
         return listaUsuario;
    }
    
    public String editUsuario(int id)
    {
        return "/admin/usuario/usuarioEditar.xhtml"; 
    }
    
    public String detalleUsuario(int id)
    {

        return "/admin/usuario/usuarioDetalle.xhtml"; 
    }
}
