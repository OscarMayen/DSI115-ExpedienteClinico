
package com.ejb;

import com.entities.SalaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Oscar Mayen
 */
@Stateless
public class SalaEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public int insertSala(SalaEntity sala) {
        try {
            em.persist(sala);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int modificarSala(SalaEntity sala) {
        try {
            em.merge(sala);
            //em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    //Metodo para listar Salas en el EJB
    public List<SalaEntity> listarSalas() {
        Query query = em.createNamedQuery("SalaEntity.findAll");
        return query.getResultList();
    }
    
    public SalaEntity obtenerSala(int id) {
        return em.find(SalaEntity.class, id);
    }
    

}
