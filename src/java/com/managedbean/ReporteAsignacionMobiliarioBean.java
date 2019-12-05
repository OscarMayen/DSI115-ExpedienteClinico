/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import edu.utilidades.invocarReporte;
import com.ejb.SalaEJB;
import com.entities.SalaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.Serializable;
import java.sql.SQLException;

/**
 *
 * @author Oscar Mayen
 */
@ManagedBean(name = "reporteAsignacionMobiliarioBean")
@ViewScoped
public class ReporteAsignacionMobiliarioBean implements Serializable{

    @EJB
    private SalaEJB salaEJB;
    
    private SalaEntity sala = new SalaEntity();
    private List<SalaEntity> listaSala = new ArrayList();
    
    public ReporteAsignacionMobiliarioBean() {
    }

     @PostConstruct
    public void init() {
       System.out.println("!!!!!!!!!!!!!");
       listaSala = buscarSalas();
    
    }
    
    private List<SalaEntity> buscarSalas() {
         this.listaSala = salaEJB.listarSalas();
         return listaSala;
    }
    
    public SalaEntity getSala() {
        return sala;
    }

    public void setSala(SalaEntity sala) {
        this.sala = sala;
    }

    public List<SalaEntity> getListaSala() {
        return listaSala;
    }

    public void setListaSala(List<SalaEntity> listaSala) {
        this.listaSala = listaSala;
    }
    
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //Instancia hacia la clase reporteClientes        
        invocarReporte rCliente = new invocarReporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/admin/reportes/ReporteAsignacionMobiliario.jasper");
        rCliente.getReporteAsignacionMobiliario(ruta,sala.getNombreSala());        
        FacesContext.getCurrentInstance().responseComplete();               
    }    
}
