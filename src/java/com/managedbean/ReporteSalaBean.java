/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import edu.utilidades.invocarReporte;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.sql.SQLException;

/**
 *
 * @author Oscar Mayen
 */
@ManagedBean(name = "reporteSalaBean")
@ViewScoped
public class ReporteSalaBean implements Serializable{

    private String estado;
    
    public ReporteSalaBean() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //Instancia hacia la clase reporteClientes        
        invocarReporte rCliente = new invocarReporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/admin/reportes/ReporteSalas.jasper");
        rCliente.getReporteSala(ruta,estado);        
        FacesContext.getCurrentInstance().responseComplete();               
    }  
}
