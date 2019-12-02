/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.RolEntity;
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
public class RolEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public RolEntity obtenerRol(int id) {
        return em.find(RolEntity.class, id);
    }

    //Metodo para listar Roles en el EJB
    public List<RolEntity> listarRoles() {
        Query query = em.createNamedQuery("RolEntity.findAll");
        return query.getResultList();
    }
    
    public RolEntity obtenerRolMedicoExacto(String rol){
        Query query = em.createQuery("select r from RolEntity r where r.nombreRol  = '"+rol+"'");
        return (RolEntity) query.getSingleResult();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
