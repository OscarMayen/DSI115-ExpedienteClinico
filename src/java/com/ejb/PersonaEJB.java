/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.PersonaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author josue
 */
@Stateless
public class PersonaEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
   
    
    public PersonaEntity obtenerPersona(int codigo){
         return em.find(PersonaEntity.class, codigo);
     }

    public int modificarPersona(PersonaEntity persona) {
        
        try {
            em.merge(persona);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }


    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    
    
    
}
