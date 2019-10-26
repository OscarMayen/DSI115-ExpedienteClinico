/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.PacienteEJB;
import com.entities.PacienteEntity;
import edu.utilidades.invocarReporte;
import java.io.File;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author josue
 */
@ManagedBean(name = "reportePacienteListaBean")
@ViewScoped
public class ReportePacienteListaBean implements Serializable{

    @EJB
    private PacienteEJB pacienteEJB;

    private List<PacienteEntity> paciente = new ArrayList();

    public ReportePacienteListaBean() {
    }

    @PostConstruct
    public void init() {
        paciente = pacienteEJB.listarPaciente();
    }

    public List<PacienteEntity> getPaciente() {
        return paciente;
    }

    public void setPaciente(List<PacienteEntity> paciente) {
        this.paciente = paciente;
    }


    public void verReportePaciente() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //Instancia hacia la clase reporteClientes        
        invocarReporte r = new invocarReporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Integer id = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("idPaciente"));
        System.out.println(id);
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/admin/reportes/reporteDePaciente/historialDeConsultas.jasper");
        r.getReportePaciente(ruta,id);        
        FacesContext.getCurrentInstance().responseComplete();               
    }
}
