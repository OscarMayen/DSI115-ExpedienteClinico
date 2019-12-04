package com.ejb;

import com.entities.DiagnosticoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DiagnosticoEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public int insertarDiagnostico(DiagnosticoEntity diagnostico) {
        try {
            em.persist(diagnostico);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
     public List<DiagnosticoEntity> listarDiagnostico() {
        Query query = em.createNamedQuery("DiagnosticoEntity.findAll");
        return query.getResultList();
    }
    
    public DiagnosticoEntity obtenerDiagnosticoPorConsulta(int id){
        Query query = em.createQuery("select d from DiagnosticoEntity d JOIN d.idConsulta c where c.idConsulta='"+id+"'");
        return (DiagnosticoEntity) query.getSingleResult();
    }

}
