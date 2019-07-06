/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.PacienteEJB;
import com.entities.PacienteEntity;
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
@ManagedBean(name = "pacienteDetalleBean")
@ViewScoped
public class PacienteDetalleBean {

    @EJB
    private PacienteEJB pacienteEJB;

    PacienteEntity paciente = new PacienteEntity();
    
    public PacienteDetalleBean() {
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
            this.paciente = new PacienteEntity();

            paciente = pacienteEJB.obtenerPaciente(Integer.valueOf(id));
        }

    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }
    
    
}
