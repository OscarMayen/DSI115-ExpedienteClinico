/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.PacienteEntity;
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
public class PacienteEJB implements Serializable {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")

    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public int insertPaciente(PacienteEntity paciente) {
        try {
            em.persist(paciente);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarPaciente(PacienteEntity paciente) {
        try {
            em.merge(paciente);
            //em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public PacienteEntity obtenerPaciente(int codigo) {
        return em.find(PacienteEntity.class, codigo);

    }

    public List<PacienteEntity> listarPaciente() {
        Query query = em.createNamedQuery("PacienteEntity.findAll");
        return query.getResultList();
    }
    
    public List filtroPaciente(String nombres, String apellidos, String dui){
        Query query = em.createQuery("SELECT p FROM PacienteEntity p JOIN p.idPersona per WHERE (per.nombrePersona LIKE '%"+nombres+"%') AND (per.apellidoPersona LIKE '%"+apellidos+"%') AND (per.dui LIKE '%"+dui+"%') ");
        return query.getResultList();
    }
    
    public List filtroPacienteConsulta(String dui, String nombres){
        Query query = em.createQuery("SELECT p FROM PacienteEntity p JOIN p.idPersona per WHERE (per.dui LIKE '%"+dui+"%') AND (per.nombrePersona LIKE '%"+nombres+"%') AND (per.estadoPersonal=true) ");
        return query.getResultList();
    }
    
}
