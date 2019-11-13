/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.CitasEntity;
import java.util.Date;
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
public class CitasEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public int insertCita(CitasEntity citasEntity) {
        try {
            em.persist(citasEntity);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public CitasEntity obtenerCita(Integer id){
        Query query = em.createQuery("SELECT c FROM CitasEntity c WHERE c.idCita ="+id);
        return (CitasEntity) query.getSingleResult();
    }
    
    public List<CitasEntity> listarCitas(Integer idMedico){
        Query query = em.createQuery("SELECT c FROM CitasEntity c INNER JOIN c.idMedico m WHERE m.idMedico ="+idMedico);
        return query.getResultList();
    }
    
    public int actualizarFechaEvento(CitasEntity citaEntity) throws Exception {
        
        try{
            em.merge(citaEntity);
            em.flush();
            return 1;
        } catch(Exception e){
            throw new Exception("Error al actualizar " + e.getMessage());
        }
    }
    
    public int actualizarDatosEvento(CitasEntity citaEntity) throws Exception{
        try{
            em.merge(citaEntity);
            return 1;
        }catch(Exception e){
            throw new Exception("Error al actualizar" + e.getMessage());
        }
    }
    
    public void eliminarCita(CitasEntity citaEntity){
        try
        {
            em.remove(em.merge(citaEntity));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
