
package com.managedbean;

import com.ejb.SalaEJB;
import com.ejb.SalaMobiliarioEJB;
import com.entities.SalaEntity;
import com.entities.SalamobiliarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Oscar Mayen
 */
@Named(value = "salaEditarBean")
@ViewScoped
public class SalaEditarBean implements Serializable{

    @EJB
    private SalaMobiliarioEJB salaMobiliarioEJB;

    @EJB
    private SalaEJB salaEjb;
    
    private List<SalamobiliarioEntity> listaSalaMobiliario = new ArrayList();
    
    SalaEntity sala = new SalaEntity();
    SalamobiliarioEntity salaMobiliario = new SalamobiliarioEntity();
    
    public SalaEditarBean() {
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
             this.sala = new SalaEntity();
             sala = salaEjb.obtenerSala(Integer.valueOf(id)); 
             listaSalaMobiliario = buscarSalasMobiliario();
          }
    }
    
    private List<SalamobiliarioEntity> buscarSalasMobiliario() {
         this.listaSalaMobiliario = salaMobiliarioEJB.obtenerlistadoSalaMobiliarioPorSala(sala.getIdSala());
         return listaSalaMobiliario;
    }
    
    public SalaEntity getSala() {
        return sala;
    }

    public void setSala(SalaEntity sala) {
        this.sala = sala;
    }

    public List<SalamobiliarioEntity> getListaSalaMobiliario() {
        return listaSalaMobiliario;
    }

    public void setListaSalaMobiliario(List<SalamobiliarioEntity> listaSalaMobiliario) {
        this.listaSalaMobiliario = listaSalaMobiliario;
    }

    public SalamobiliarioEntity getSalaMobiliario() {
        return salaMobiliario;
    }

    public void setSalaMobiliario(SalamobiliarioEntity salaMobiliario) {
        this.salaMobiliario = salaMobiliario;
    }
 
    
    
    public void eliminarSalaMobiliario(SalamobiliarioEntity sm) throws Exception {
         int a;
         this.salaMobiliario = sm;
         FacesContext context = FacesContext.getCurrentInstance();
             a = salaMobiliarioEJB.eliminarSalaMobiliario(salaMobiliario.getIdSalaMobiliario());
             if (a == 1) {
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Editorial eliminada correctamente."));
             }
             
             else {
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Editorial no pudo ser eliminada."));
             }
             salaMobiliario = new SalamobiliarioEntity();
             this.listaSalaMobiliario = buscarSalasMobiliario();
     } 
    
    public String updateSala(){
        System.out.println("//////////////***************");
        System.out.println("ID A MODIFICAR: " + this.sala.getIdSala());
        System.out.println("NOMBRE A MODIFICAR: " + this.sala.getNombreSala());
        System.out.println("//////////////***************");
        salaEjb.modificarSala(this.sala);
   
        return "/admin/sala/salaListar?faces-redirect=true";
     
    }
    
    public String btnCancelarSala(){
        
        return "/admin/sala/salaListar?faces-redirect=true";
    }
}