/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.EspecialidadEntity;
import com.entities.MedicoEntity;
import com.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author josue
 */
@Stateless
public class MedicoEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;
    private static final String PERSISTENCE_UNIT_NAME = "ExpedienteClinicoBBraunPU";
   
    public void persist(Object object) {
        em.persist(object);
    }

    public int insertMedico(MedicoEntity medico) {
        try {
            em.persist(medico);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarMedico(MedicoEntity medico) throws Exception {
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(medico);
            em.merge(medico);
           // em.flush();
            return 1;
        } catch (Exception e) {
            throw new Exception("Error al actualizar " + e.getMessage());
            
        }
    }

    public MedicoEntity obtenerMedico(int codigo) {
        return em.find(MedicoEntity.class, codigo);
    }

    public List<MedicoEntity> listarMedico() {
        Query query = em.createNamedQuery("MedicoEntity.findAll");
        return query.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
