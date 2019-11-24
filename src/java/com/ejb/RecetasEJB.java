/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.ConsultaEntity;
import com.entities.RecetaEntity;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DTO
 */
@Stateless
public class RecetasEJB implements Serializable {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public int insertarReceta(RecetaEntity receta) {
        try {
            em.persist(receta);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }

    }

    public RecetaEntity obtenerReceta(int codigo) {
        return em.find(RecetaEntity.class, codigo);

    }

    public List<RecetaEntity> listarRecetass() {
        Query query = em.createNamedQuery("RecetaEntity.findAll");
        return query.getResultList();
    }

   public List<ConsultaEntity> listarConsultas() {
        Query query = em.createNamedQuery("ConsultaEntity.findAll");
        return query.getResultList();
    }

}
