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
    
     private static final String PERSISTENCE_UNIT_NAME = "ExpedienteClinicoBBraunPU";
     private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    
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
    //prueba
    public boolean updatePerson(PersonaEntity persona){
        EntityManager m = this.factory.createEntityManager();
        m.getTransaction().begin();
        PersonaEntity personx = m.find(PersonaEntity.class,persona.getIdPersona());
        System.out.println("datos "+ persona.getNombrePersona() + persona.getApellidoPersona() +persona.getDepartamento());
        
        personx.setNombrePersona(persona.getNombrePersona());
        personx.setApellidoPersona(persona.getApellidoPersona());
        personx.setDepartamento(persona.getDepartamento());
        personx.setMunicipio(persona.getMunicipio());
        personx.setGenero(persona.getGenero());
        personx.setDui(persona.getDui());
        personx.setFechaNacimiento(persona.getFechaNacimiento());
        personx.setTelefono(persona.getTelefono());
        m.merge(personx);
        m.getTransaction().commit();
        m.close();
        return false;
        
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    
    
    
}
