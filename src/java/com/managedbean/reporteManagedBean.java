/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.MedicoEJB;
import com.ejb.PacienteEJB;
import com.entities.MedicoEntity;
import com.entities.PacienteEntity;
import edu.utilidades.invocarReporte;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 *
 * @author josue
 */
@Named(value = "reporteManagedBean")
@ViewScoped
public class reporteManagedBean implements Serializable{

 @EJB
   private PacienteEJB pacienteEJB;   
   @EJB 
   private MedicoEJB medicoEJB;
   private Integer idPaciente;
   private PacienteEntity paciente;
   private MedicoEntity medico;
   List<PacienteEntity> pacientes=new ArrayList<PacienteEntity>();
   List<MedicoEntity> medicos=new ArrayList<MedicoEntity>();

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
     
       public void verReporteRecetas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
//         Instancia hacia la clase reporteClientes   
           System.out.println(paciente.getIdPaciente());
           System.out.println(medico.getIdMedico());
        invocarReporte r = new invocarReporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        
        if(paciente!=null&&medico!=null){
        String ruta = servletContext.getRealPath("/admin/reportes/ReporteRecetasParametros.jasper");                
        r.getReporteRecetasParametros(ruta, paciente, medico);            
        FacesContext.getCurrentInstance().responseComplete();    
        }
        else{
        String ruta = servletContext.getRealPath("/admin/reportes/ReporteRecetasNoParametros.jasper");                
        r.getReporteRecetas(ruta);
        FacesContext.getCurrentInstance().responseComplete();    
        }
        
                   
     }
     
     
     public void verReporteReceta(Integer id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
//         Instancia hacia la clase reporteClientes        
        invocarReporte r = new invocarReporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();                
        String ruta = servletContext.getRealPath("/admin/reportes/ReporteReceta.jasper");                
        r.getReporteReceta(ruta,id);            
        FacesContext.getCurrentInstance().responseComplete();    
       
     }
     public void verReporteConsultasAtendidas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
//         Instancia hacia la clase reporteClientes        
        invocarReporte r = new invocarReporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();                
        String ruta = servletContext.getRealPath("/admin/reportes/ReporteConsultasPorMedico.jasper");                
        r.getReporteConsultasAtendidas(ruta);
        FacesContext.getCurrentInstance().responseComplete();    
       
     }
     public void verReporteUsuarios() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
//         Instancia hacia la clase reporteClientes        
        invocarReporte r = new invocarReporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();                
        String ruta = servletContext.getRealPath("/admin/reportes/ReporteDeUsuarios.jasper");                
        r.getReporteDeUsuarios(ruta);
        FacesContext.getCurrentInstance().responseComplete();    
       
     }
     
     
    public void obtenerPaciente(int id) {
        System.out.println(id);
        paciente=pacienteEJB.obtenerPaciente(id);
        
    }
    
    public void obtenerMedico(int id) {        
        System.out.println(id);        
        this.medico=medicoEJB.obtenerMedico(id);     
        
    }
    
     
     public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public MedicoEntity getMedico() {
        medicos=medicoEJB.listarMedico();
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public List<PacienteEntity> getPacientes() {
        pacientes=pacienteEJB.listarPaciente();
        return pacientes;
    }

    public void setPacientes(List<PacienteEntity> pacientes) {
        this.pacientes = pacientes;
    }

    public List<MedicoEntity> getMedicos() {
        medicos=medicoEJB.listarMedico();
        return medicos;
    }

    public void setMedicos(List<MedicoEntity> medicos) {
        this.medicos = medicos;
    }
       
    
}
