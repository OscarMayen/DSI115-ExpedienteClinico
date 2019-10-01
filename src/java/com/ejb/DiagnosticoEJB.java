package com.ejb;

import com.entities.DiagnosticoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
