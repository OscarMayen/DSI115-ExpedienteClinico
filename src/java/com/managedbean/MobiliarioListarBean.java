
package com.managedbean;

import com.ejb.MobiliarioEJB;
import com.entities.MobiliarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author admin
 */
@ManagedBean(name = "mobiliarioListarBean")
@ViewScoped
public class MobiliarioListarBean {

    @EJB
    private MobiliarioEJB mobiliarioEJB;

    private List<MobiliarioEntity> listaMobiliario = new ArrayList();
     
    public MobiliarioListarBean() {
    }
    
    @PostConstruct
    public void init() {
       System.out.println("!!!!!!!!!!!!!");
       listaMobiliario = buscarMobiliario();
    
    }
    
    private List<MobiliarioEntity> buscarMobiliario() {
         this.listaMobiliario = mobiliarioEJB.listarMobiliario();
         return listaMobiliario;
    }

    public List<MobiliarioEntity> getListaMobiliario() {
        return listaMobiliario;
    }

    public void setListaMobiliario(List<MobiliarioEntity> listaMobiliario) {
        this.listaMobiliario = listaMobiliario;
    }
    
    public String editMobiliario(int id)
    {
        //this.sala = new SalaEntity();
        
        //sala = salaEjb.obtenerSala(id);

        return "/admin/mobiliario/mobiliarioEditar.xhtml"; 
    }
}
