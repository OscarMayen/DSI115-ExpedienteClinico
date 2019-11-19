
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author josue
 */
//@ManagedBean(name = "usuarioEditarBean")
@Named(value = "usuarioEditarBean")
@ViewScoped
public class UsuarioEditarBean implements Serializable{

    @EJB
    private PersonaEJB personaEJB;

    @EJB
    private RolEJB rolEJB;

    @EJB
    private UsuarioEJB usuarioEJB;
    
     UsuarioEntity usuario = new UsuarioEntity();
     RolEntity rol = new RolEntity();
     PersonaEntity persona = new PersonaEntity();
     List<RolEntity> listaRoles = new ArrayList<RolEntity>();
    
    public UsuarioEditarBean() {
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public List<RolEntity> getListaRoles() {
        return this.rolEJB.listarRoles();
    }

    public void setListaRoles(List<RolEntity> listaRoles) {
        this.listaRoles = listaRoles;
    }
    
    private List<RolEntity> buscarRoles() {
         this.listaRoles = rolEJB.listarRoles();
        return this.listaRoles;
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
             this.usuario = new UsuarioEntity();
             
        usuario = usuarioEJB.obtenerUsuario(Integer.valueOf(id));
        }
         
    
    }
    
     public String editUsuario(int id)
    {
        this.usuario = new UsuarioEntity();
        
        usuario = usuarioEJB.obtenerUsuario(id);

        return "/admin/usuario/usuariooEditar.xhtml"; 
    }
    
    
    public String updateUsuario(){
        try {
            personaEJB.modificarPersona(usuario.getIdPersona());
            usuarioEJB.modificarUsuario(usuario);
           
        } catch (Exception ex) {
            Logger.getLogger(UsuarioMedicoBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("ERROR AL INSERTAR " + ex.getMessage() ));
        }
   
        return "/admin/usuario/usuarioListar?faces-redirect=true";
      
    }
    
    
}
