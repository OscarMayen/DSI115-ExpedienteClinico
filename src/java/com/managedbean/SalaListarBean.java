
package com.managedbean;

import com.ejb.SalaEJB;
import com.entities.SalaEntity;
import java.io.Serializable;
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
@ManagedBean(name = "salaListarBean")
@ViewScoped
public class SalaListarBean implements Serializable{

    @EJB
    private SalaEJB salaEJB;

    private List<SalaEntity> listaSala = new ArrayList();
    
    
    public SalaListarBean() {
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
    
    public List<SalaEntity> getListaSala() {
        return listaSala;
    }

    public void setListaSala(List<SalaEntity> listaSala) {
        this.listaSala = listaSala;
    }
    
    
    
}
