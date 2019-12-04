
package com.managedbean;

import com.ejb.DiagnosticoEJB;
import com.entities.DiagnosticoEntity;
import java.io.Serializable;
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
@Named(value = "consultaDetalleBean")
@ViewScoped
public class ConsultaDetalleBean implements Serializable{

    @EJB
    private DiagnosticoEJB diagnosticoEJB;

    DiagnosticoEntity diagnostico = new DiagnosticoEntity();
    
    public ConsultaDetalleBean() {
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
             this.diagnostico = new DiagnosticoEntity();
             
            this.diagnostico = diagnosticoEJB.obtenerDiagnosticoPorConsulta(Integer.valueOf(id));
            System.out.println("ID DIAGNOSTICO: " + diagnostico.getIdDiagnostico());
            System.out.println("DESCRIPCION: " + diagnostico.getDescripcion());
        }
    }
    
    public DiagnosticoEntity getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(DiagnosticoEntity diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    
}
