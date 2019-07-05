/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.RolEJB;
import com.ejb.UsuarioEJB;
import com.entities.RolEntity;
import com.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author josue
 */
@ManagedBean(name = "usuarioDetalleBean")
@ViewScoped
public class UsuarioDetalleBean {

    @EJB
    private RolEJB rolEJB;

    @EJB
    private UsuarioEJB usuarioEJB;

    Usuario usuario = new Usuario();
    RolEntity rol = new RolEntity();
    List<RolEntity> list = new ArrayList<RolEntity>();
    
    public UsuarioDetalleBean() {
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
            System.out.println(":vvvvvvvvvvvvvvvvvvvvvvvvvvvvv" + id);
            this.usuario = new Usuario();

            usuario = usuarioEJB.obtenerUsuario(Integer.valueOf(id));
        }

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

    public List<RolEntity> getList() {
        return list;
    }

    public void setList(List<RolEntity> list) {
        this.list = list;
    }
    
    public List<RolEntity> getListarRoles() {
        return this.rolEJB.listarRoles();
    }
    
    
}
