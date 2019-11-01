/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.AntecedentesEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Oscar
 */
@Stateless
public class AntecedentesEJB {
     @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public int insertarAntecedentes(AntecedentesEntity antecedente) {
        System.out.println("///////////////////////////");        
        System.out.println("ENTRO AL EJB"+antecedente.getIdAntecedente());
        try {
            System.out.println("//////////////////////");
            em.persist(antecedente);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public AntecedentesEntity obtenerAntecedentes(int codigo) {
        return em.find(AntecedentesEntity.class, codigo);

    }

    public List<AntecedentesEntity> listarAntecedentes() {
        Query query = em.createNamedQuery("AntecedentesEntity.findAll");
        return query.getResultList();
    }
    
    public int modificarAntecedente(AntecedentesEntity antecedente) {
        System.out.println("nooooooooooooooooooooooooo");
        try {
            em.merge(antecedente);
            //em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public AntecedentesEntity obtenerAntecedentesPorPaciente(int idPaciente){
        Query query = em.createQuery("select a from AntecedentesEntity a JOIN a.idPaciente p  where (p.idPaciente = '"+idPaciente+"')");
        return (AntecedentesEntity) query.getSingleResult();
    }
}
