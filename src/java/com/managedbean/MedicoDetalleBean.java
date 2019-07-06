/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.EspecialidadEJB;
import com.ejb.MedicoEJB;
import com.entities.EspecialidadEntity;
import com.entities.MedicoEntity;
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
@ManagedBean(name = "medicoDetalleBean")
@ViewScoped
public class MedicoDetalleBean {

    @EJB
    private EspecialidadEJB especialidadEJB;

    @EJB
    private MedicoEJB medicoEJB;

    MedicoEntity medico = new MedicoEntity();
    EspecialidadEntity especialidad = new EspecialidadEntity();
    List<EspecialidadEntity> list = new ArrayList<EspecialidadEntity>();

    /**
     * Creates a new instance of MedicoDetalleBean
     */
    public MedicoDetalleBean() {
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
            this.medico = new MedicoEntity();

            medico = medicoEJB.obtenerMedico(Integer.valueOf(id));
        }

    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public EspecialidadEntity getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadEntity especialidad) {
        this.especialidad = especialidad;
    }

    public List<EspecialidadEntity> getList() {
        return list;
    }

    public void setList(List<EspecialidadEntity> list) {
        this.list = list;
    }

    public List<EspecialidadEntity> getListarEspecialidades() {
        return this.especialidadEJB.especialidadListar();
    }

}
