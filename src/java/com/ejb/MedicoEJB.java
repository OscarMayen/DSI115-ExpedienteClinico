/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.EspecialidadEntity;
import com.entities.MedicoEntity;
import com.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Oscar
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
    
    public List<EspecialidadEntity> obtenerEspecialidades(String esp){
        Query query = em.createQuery("SELECT esp from EspecialidadEntity esp where esp.idEspecialidad not in("+esp+")");
        return query.getResultList();
    }
    
    public List obtenerHorarioMedico(Integer id){
        Query query = em.createQuery("SELECT DISTINCT h from HorarioEntity h  JOIN  h.idMedico m JOIN  h.idDia dd JOIN  h.idHora hd where h.idMedico.idMedico = "+id);
        return query.getResultList();
    }

    public List<MedicoEntity> listarMedico() {
        Query query = em.createNamedQuery("MedicoEntity.findAll");
        return query.getResultList();
    }
    
    public List filtroMedicoConsulta(String dui, String nombres){
        Query query = em.createQuery("SELECT p FROM MedicoEntity p JOIN p.idPersona per WHERE (per.dui LIKE '%"+dui+"%') AND (per.nombrePersona LIKE '%"+nombres+"%') AND (per.estadoPersonal=true) ");
        return query.getResultList();
    }
    
    public List filtrar(String dui, String especialidad){
        Query query = em.createQuery("SELECT m FROM MedicoEntity m JOIN m.idPersona per JOIN m.especialidadEntityList esp WHERE (per.dui LIKE '%"+dui+"%') AND (esp.nombreEspecialidad LIKE '%"+especialidad+"%') ");
        return query.getResultList();
    } 

     public MedicoEntity obtenerMedicoPorUsuario(String usuario){
        Query query = em.createQuery("select m from MedicoEntity m JOIN m.idPersona p JOIN UsuarioEntity u where (u.idPersona = m.idPersona) AND (u.username = '"+usuario+"')");
        return (MedicoEntity) query.getSingleResult();
    }
     
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
