/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter;

import com.ejb.SalaEJB;
import com.entities.SalaEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

@FacesConverter(forClass=SalaEntity.class)
public class SalaConverter implements Converter {

    SalaEJB salaEJB = lookupSalaEJBBean();

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value == null || value.isEmpty()){
            return "";
        }
        
        return salaEJB.obtenerSala(Integer.parseInt(value)); 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        if(value instanceof SalaEntity){
            return ((SalaEntity) value).getIdSala().toString();
        }
        return "";
    }

    private SalaEJB lookupSalaEJBBean() {
        try {
            Context c = new InitialContext();
            return (SalaEJB) c.lookup("java:global/ExpedienteClinicoBBraun/SalaEJB!com.ejb.SalaEJB");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
