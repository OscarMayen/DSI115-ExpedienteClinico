/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.SalamobiliarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author admin
 */
@Stateless
public class SalaMobiliarioEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

     public int insertarSalaMobiliario(SalamobiliarioEntity salaMobiliario) {
        try {
            em.persist(salaMobiliario);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int modificarSalaMobiliario(SalamobiliarioEntity salaMobiliario) {
        try {
            em.merge(salaMobiliario);
            //em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    //Metodo para listar Mobiliario en el EJB
    public List<SalamobiliarioEntity> listarSalaMobiliario() {
        Query query = em.createNamedQuery("SalamobiliarioEntity.findAll");
        return query.getResultList();
    }
    
    public SalamobiliarioEntity obtenerSalaMobiliario(int id) {
        return em.find(SalamobiliarioEntity.class, id);
    }    
    
}
