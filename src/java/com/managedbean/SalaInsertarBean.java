/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.SalaEJB;
import com.entities.SalaEntity;
import edu.utilidades.JsfUtils;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DIAZ
 */
@ManagedBean(name = "salaInsertarBean")
@ViewScoped
public class SalaInsertarBean implements Serializable{

    @EJB
    private SalaEJB salaEJB;
    
    private SalaEntity salaEntity = new SalaEntity();
    
    public SalaInsertarBean() {
    }

    public void insertarSala() throws IOException 
    {
    salaEJB.insertSala(salaEntity);
    this.salaEntity = new SalaEntity();
    JsfUtils.Redireccionar("salaListar.xhtml");
    }

    public SalaEJB getSalaEJB() {
        return salaEJB;
    }

    public SalaEntity getSalaEntity() {
        return salaEntity;
    }

    public void setSalaEJB(SalaEJB salaEJB) {
        this.salaEJB = salaEJB;
    }

    public void setSalaEntity(SalaEntity salaEntity) {
        this.salaEntity = salaEntity;
    }
    
}