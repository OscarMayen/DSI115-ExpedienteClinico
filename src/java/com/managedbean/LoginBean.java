package com.managedbean;

import com.ejb.UsuarioEJB;
import com.entities.UsuarioEntity;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

//login

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    @EJB
    private UsuarioEJB usuarioEJB;

    String usuario;
    String password;
    private UsuarioEntity user;
    
    
    public UsuarioEntity getUser() {
        return user;
    }

    public void setUser(UsuarioEntity user) {
        this.user = user;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        String outcome = "/Login";
        try {

            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(usuario, password);
            this.user = this.usuarioEJB.obtenerUsuarioLogIn(usuario);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
            
            outcome = "/plantillas/usuario/sesion?faces-redirect=true";

        } catch (Exception e) {
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario o clave invalidos"));
        }
        System.out.println("La ruta es: " + outcome);
        return outcome;
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(), null,
                        "/Login?faces-redirect=true");
    }
}
