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
import javax.persistence.TypedQuery;

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

    public List<RecetaEntity> listarRecetas(int id) {        
        TypedQuery<RecetaEntity> query = em.createQuery(
        "select r from RecetaEntity r where r.idConsulta.idMedico.idMedico=:id" , RecetaEntity.class);        
        query.setParameter("id", id);
        return query.getResultList();
    }

   public List<ConsultaEntity> listarConsultas(int id) {
        TypedQuery<ConsultaEntity> query = em.createQuery(
        "select c from ConsultaEntity c where c.idMedico.idMedico=:id and"
        + "  not exists (SELECT r FROM RecetaEntity r where r.idConsulta.idConsulta=c.idConsulta)"
        + " order by c.fechaConsulta DESC" , ConsultaEntity.class);        
        query.setParameter("id", id);
        return query.getResultList();
    }

}
