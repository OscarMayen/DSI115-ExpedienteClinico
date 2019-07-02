package com.converter;
import com.ejb.RolEJB;
import com.entities.RolEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@FacesConverter(forClass=RolEntity.class)

public class RolConverter implements Converter{

    RolEJB rolEJB = lookupRolEJBBean();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value == null || value.isEmpty()){
            return "";
        }
        
        return rolEJB.obtenerRol(Integer.parseInt(value)); 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        if(value instanceof RolEntity){
            return ((RolEntity) value).getIdRol().toString();
        }
        return "";
    }

    private RolEJB lookupRolEJBBean() {
        try {
            Context c = new InitialContext();
            return (RolEJB) c.lookup("java:global/ExpedienteClinicoBBraun/RolEJB!com.ejb.RolEJB");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
