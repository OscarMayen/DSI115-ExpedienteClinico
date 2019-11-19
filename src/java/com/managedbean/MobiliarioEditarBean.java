
package com.managedbean;

import com.ejb.MobiliarioEJB;
import com.entities.MobiliarioEntity;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oscar Mayen 
 */
@Named(value = "mobiliarioEditarBean")
@ViewScoped
public class MobiliarioEditarBean {

    @EJB
    private MobiliarioEJB mobiliarioEJB;

     MobiliarioEntity mobiliario = new MobiliarioEntity();
    
    public MobiliarioEditarBean() {
    }

    public MobiliarioEntity getMobiliario() {
        return mobiliario;
    }

    public void setMobiliario(MobiliarioEntity mobiliario) {
        this.mobiliario = mobiliario;
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
            this.mobiliario = new MobiliarioEntity();

            mobiliario = mobiliarioEJB.obtenerMobiliario(Integer.valueOf(id));
            System.out.println("/////////");
            System.out.println("ID: " + mobiliario.getIdMobiliario());
            System.out.println("NOMBRE: " + mobiliario.getNombreMobiliario());
            System.out.println("/////////");
        }
    }
    
    public String updateMobiliario(){
        
        mobiliarioEJB.modificarMobiliario(this.mobiliario);
   
        return "/admin/mobiliario/mobiliarioListar?faces-redirect=true";
     
    }
    
    public String btnCancelarSala(){
        
        return "/admin/mobiliario/mobiliarioListar?faces-redirect=true";
    }

}
