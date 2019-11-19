
package com.managedbean;

import com.ejb.MobiliarioEJB;
import com.entities.MobiliarioEntity;
import edu.utilidades.JsfUtils;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Oscar Mayen 
 */
@ManagedBean(name = "mobiliarioInsertarBean")
@ViewScoped
public class MobiliarioInsertarBean {

    @EJB
    private MobiliarioEJB mobiliarioEJB;

    public MobiliarioInsertarBean() {
    }

    private MobiliarioEntity mobiliario = new MobiliarioEntity();

    public void insertarMobiliario() throws IOException {
        mobiliarioEJB.insertarMobiliario(mobiliario);
        this.mobiliario = new MobiliarioEntity();
        JsfUtils.Redireccionar("mobiliarioListar.xhtml");
    }

    public MobiliarioEntity getMobiliario() {
        return mobiliario;
    }

    public void setMobiliario(MobiliarioEntity mobiliario) {
        this.mobiliario = mobiliario;
    }
    
}
