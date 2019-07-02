
package com.converter;

import com.ejb.EspecialidadEJB;
import com.entities.EspecialidadEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@FacesConverter(forClass=EspecialidadEntity.class)
public class EspecialidadConverter implements Converter{

    EspecialidadEJB especialidadEJB = lookupEspecialidadEJBBean();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value == null || value.isEmpty()){
            return "";
        }
        
        return especialidadEJB.obtenerEspecialidad(Integer.parseInt(value)); 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        if(value instanceof EspecialidadEntity){
            return ((EspecialidadEntity) value).getIdEspecialidad().toString();
        }
        return "";
    }

    private EspecialidadEJB lookupEspecialidadEJBBean() {
        try {
            Context c = new InitialContext();
            return (EspecialidadEJB) c.lookup("java:global/ExpedienteClinicoBBraun/EspecialidadEJB!com.ejb.EspecialidadEJB");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
