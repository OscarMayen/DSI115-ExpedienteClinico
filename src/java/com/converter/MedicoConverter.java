/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.converter;

import com.ejb.MedicoEJB;
import com.entities.MedicoEntity;
import com.entities.MedicoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author josue
 */
@FacesConverter(forClass=MedicoEntity.class)

public class MedicoConverter implements Converter{
    MedicoEJB medicoEJB = lookupRolEJBBean();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value == null || value.isEmpty()){
            return "";
        }
        
        return medicoEJB.obtenerMedico(Integer.parseInt(value)); 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        if(value instanceof MedicoEntity){
            return ((MedicoEntity) value).getIdMedico().toString();
        }
        return "";
    }

    private MedicoEJB lookupRolEJBBean() {
        try {
            Context c = new InitialContext();
            return (MedicoEJB) c.lookup("java:global/ExpedienteClinicoBBraun/MedicoEJB!com.ejb.MedicoEJB");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
