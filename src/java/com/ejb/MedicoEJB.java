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

    
    
    public int insertMedico(MedicoEntity medico){
        try{
            em.persist(medico);
            em.flush();
            return 1;
        }catch(Exception e){
            return 0;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
     public List<MedicoEntity> listarMedico(){
        Query query = em.createNamedQuery("MedicoEntity.findAll");
        return query.getResultList();
    }

}
