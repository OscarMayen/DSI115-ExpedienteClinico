
package com.managedbean;

import com.ejb.DiagnosticoEJB;
import com.entities.DiagnosticoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Oscar Mayen 
 */

@ManagedBean(name = "diagnosticosListar")
@ViewScoped
public class DiagnosticosListarBean {

    @EJB
    private DiagnosticoEJB diagnosticoEJB;

    public DiagnosticosListarBean() {
    }
    
    private List<DiagnosticoEntity> listaDiagnostico = new ArrayList ();
    
    @PostConstruct
    public void init() {
        System.out.println("!!!!!!!!!!!!!");
        listaDiagnostico = buscarDiagnosticos();
    
    }
    
    private List<DiagnosticoEntity> buscarDiagnosticos() {
         this.listaDiagnostico = diagnosticoEJB.listarDiagnostico();
         return listaDiagnostico;
    }

    public List<DiagnosticoEntity> getListaDiagnostico() {
        return listaDiagnostico;
    }

    public void setListaDiagnostico(List<DiagnosticoEntity> listaDiagnostico) {
        this.listaDiagnostico = listaDiagnostico;
    }
    
    
}
