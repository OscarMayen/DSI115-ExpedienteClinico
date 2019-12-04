/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author admin
 */
@Named(value = "salaDetalleBean")
@ViewScoped
public class SalaDetalleBean implements Serializable{

    @EJB
    private SalaEJB salaEJB;

    @EJB
    private SalaMobiliarioEJB salaMobiliarioEJB;

    
    SalaEntity sala = new SalaEntity();
    private List<SalamobiliarioEntity> listaSalaMobiliario = new ArrayList();
    
    public SalaDetalleBean() {
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
             sala = salaEJB.obtenerSala(Integer.valueOf(id)); 
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
    
}
