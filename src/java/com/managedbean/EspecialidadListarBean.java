
package com.managedbean;

import com.ejb.EspecialidadEJB;
import com.entities.EspecialidadEntity;
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
@ManagedBean(name = "especialidadListarBean")
@ViewScoped
public class EspecialidadListarBean {

    @EJB
    private EspecialidadEJB especialidadEJB;

    private List<EspecialidadEntity> listaEspecialidad = new ArrayList();
    
    public EspecialidadListarBean() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println("!!!!!!!!!!!!!");
        listaEspecialidad = buscarDiagnosticos();
    
    }
    
    private List<EspecialidadEntity> buscarDiagnosticos() {
         this.listaEspecialidad = especialidadEJB.especialidadListar();
         return listaEspecialidad;
    }

    public List<EspecialidadEntity> getListaEspecialidad() {
        return listaEspecialidad;
    }

    public void setListaEspecialidad(List<EspecialidadEntity> listaEspecialidad) {
        this.listaEspecialidad = listaEspecialidad;
    }
 
    public String editSala(int id)
    {
        //this.sala = new SalaEntity();
        
        //sala = salaEjb.obtenerSala(id);

        return "/admin/especialidad/especialidadEditar.xhtml"; 
    }
}
