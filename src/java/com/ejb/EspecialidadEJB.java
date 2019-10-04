/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.EspecialidadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author josue
 */
@Stateless
public class EspecialidadEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;
    
    

    public List<EspecialidadEntity> especialidadListar(){
        Query query = em.createNamedQuery("EspecialidadEntity.findAll");
        return query.getResultList();
    }
    
    public int insertEspecialidad(EspecialidadEntity especialidad) {
        try {
            em.persist(especialidad);
            //em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int modificarEspecialidad(EspecialidadEntity especialidad) throws Exception {
          try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(especialidad);
            em.merge(especialidad);
           // em.flush();
            return 1;
        } catch (Exception e) {
            throw new Exception("Error al actualizar " + e.getMessage());
            
        }
    }
    
     public EspecialidadEntity obtenerEspecialidad(int id)
     {
        return em.find(EspecialidadEntity.class, id);
     }

    public void persist(Object object) {
        em.persist(object);
    }
}
