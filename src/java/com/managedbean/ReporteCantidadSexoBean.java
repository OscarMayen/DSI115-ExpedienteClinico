
package com.managedbean;

import edu.utilidades.invocarReporte;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;


@ManagedBean(name = "reporteCantidadSexoBean")
@ViewScoped
public class ReporteCantidadSexoBean implements Serializable{

    private Date fechaI;
    private Date fechaF;
    
    public ReporteCantidadSexoBean() {
    }

    public Date getFechaI() {
        return fechaI;
    }

    public void setFechaI(Date fechaI) {
        this.fechaI = fechaI;
    }

    public Date getFechaF() {
        return fechaF;
    }

    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }
    
    //Metodo para invocar el reporte y enviarle los parametros si es que necesita
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaInicial = formato.format(this.fechaI);
        String fechaFinal = formato.format(this.fechaF);
        System.out.println("DATO DE FECHA I: " + fechaInicial);
        System.out.println("DATO DE FECHA F: " + fechaFinal);
        //Instancia hacia la clase reporteClientes        
        invocarReporte rCliente = new invocarReporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/admin/reportes/ReporteEstadisticaPorGeneroEnFechas.jasper");

       
        rCliente.getReporteEstadisticaPorGenero(ruta,fechaInicial,fechaFinal);        
        FacesContext.getCurrentInstance().responseComplete();               
    }        
    
}
