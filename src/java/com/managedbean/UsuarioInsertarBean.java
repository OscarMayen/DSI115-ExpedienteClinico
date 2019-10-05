
package com.managedbean;

import com.ejb.PersonaEJB;
import com.ejb.RolEJB;
import com.ejb.UsuarioEJB;
import com.entities.PersonaEntity;
import com.entities.RolEntity;
import com.entities.UsuarioEntity;
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
    private PersonaEJB personaEJB;

   @EJB
    private RolEJB rolEJB;

    @EJB
    private UsuarioEJB usuarioEJB;


    private UsuarioEntity usuario = new UsuarioEntity();
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

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
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
        int a =this.personaEJB.insertPersona(this.persona);
        
        if (a == 0) {
             FacesContext context = FacesContext.getCurrentInstance();
             FacesContext.getCurrentInstance().addMessage("Usuario", new FacesMessage("ERROR AL INSERTAR DUI REPETIDO"));
            return null;  
        } 
        else{
            usuario.setIdPersona(persona);
            int b = this.usuarioEJB.insertUsuario(usuario);
        }
        this.persona= new PersonaEntity();
        this.usuario=new UsuarioEntity();
        return "/admin/usuario/usuarioListar?faces-redirect=true";
    }
    
    
}
