
package com.ejb;

import com.entities.MobiliarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author admin
 */
@Stateless
public class MobiliarioEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public int insertarMobiliario(MobiliarioEntity mobiliario) {
        try {
            em.persist(mobiliario);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int modificarMobiliario(MobiliarioEntity mobiliario) {
        try {
            em.merge(mobiliario);
            //em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    //Metodo para listar Mobiliario en el EJB
    public List<MobiliarioEntity> listarMobiliario() {
        Query query = em.createNamedQuery("MobiliarioEntity.findAll");
        return query.getResultList();
    }
    
    public MobiliarioEntity obtenerMobiliario(int id) {
        return em.find(MobiliarioEntity.class, id);
    }
  
}
