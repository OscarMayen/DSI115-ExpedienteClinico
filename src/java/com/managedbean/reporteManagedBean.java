/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import edu.utilidades.invocarReporte;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;

/**
 *
 * @author josue
 */
@ManagedBean(name = "reporteManagedBean")
@ViewScoped
public class reporteManagedBean implements Serializable{

    private Integer idPaciente;
    
    
    public reporteManagedBean() {
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    
    
     public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //Instancia hacia la clase reporteClientes        
        invocarReporte r = new invocarReporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/admin/reportes/historialConsultas.jasper");
       
        r.getReporte(ruta);        
        FacesContext.getCurrentInstance().responseComplete();               
    }
    
}
