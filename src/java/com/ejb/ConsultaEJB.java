
package com.ejb;

import com.entities.ConsultaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Oscar Mayen
 */
@Stateless
public class ConsultaEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public int insertarConsulta(ConsultaEntity consulta) {
        System.out.println("///////////////////////////");
        System.out.println("ENTRO AL EJB");
        try {
            System.out.println("//////////////////////");
            em.persist(consulta);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
