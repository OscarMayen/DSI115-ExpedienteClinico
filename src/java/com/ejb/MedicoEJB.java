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
     private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

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

    public int modificarMedico(MedicoEntity medico) {
        try {
            em.merge(medico);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    //prueba
    public int updateDoctor(MedicoEntity medico) {
        EntityManager m = factory.createEntityManager();
        m.getTransaction().begin();

        MedicoEntity medicox = em.find(MedicoEntity.class, medico.getIdMedico());
        medicox.setEmailMedico(medico.getEmailMedico());
        medicox.setEstadoMedico(medico.getEstadoMedico());
        medicox.setIdEspecialidad(medico.getIdEspecialidad());
        m.merge(medicox);
        m.getTransaction().commit();
        m.close();
        return 0;

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
