
package com.managedbean;

import com.ejb.PersonaEJB;
import com.ejb.RolEJB;
import com.ejb.UsuarioEJB;
import com.entities.PersonaEntity;
import com.entities.RolEntity;
import com.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author josue
 */
@ManagedBean(name = "usuarioInsertarBean")
@ViewScoped
public class UsuarioInsertarBean implements Serializable{

    @EJB
    private RolEJB rolEJB;

    @EJB
    private UsuarioEJB usuarioEJB;


    private Usuario usuario = new Usuario();
    private PersonaEntity persona = new PersonaEntity();
    private List<RolEntity> listaRoles = new ArrayList<RolEntity>();
    
    public UsuarioInsertarBean() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println("!!!!!!!!!!!!!");
        this.listaRoles = buscarRoles();//Buscar lista de roles del EJB
    
    }
    
    
    private List<RolEntity> buscarRoles() {
         this.listaRoles = rolEJB.listarRoles();
        return listaRoles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public List<RolEntity> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<RolEntity> listaRoles) {
        this.listaRoles = listaRoles;
    }
    
    
    
    public String insertarUsuario() {
        
        this.usuario.setIdPersona(persona);
        
        if (this.usuarioEJB.insertUsuario(usuario) == 0) {
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR"));
            return null;  
        } 
        
        this.usuario = new Usuario();
        this.persona = new PersonaEntity();

        return "/admin/usuario/usuarioListar?faces-redirect=true";
    }
    
    
    
    
}
