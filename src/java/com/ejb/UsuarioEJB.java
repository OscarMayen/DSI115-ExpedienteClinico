/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

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
public class UsuarioEJB {

    @PersistenceContext(unitName = "ExpedienteClinicoBBraunPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Usuario> listarUsuario() {
        Query query = em.createNamedQuery("Usuario.findAll");
        return query.getResultList();
    }
    
    public int insertUsuario(Usuario usuario) {
        try {
            em.persist(usuario);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public Usuario obtenerUsuario(int codigo) {
        return em.find(Usuario.class, codigo);
    }

    public int modificarUsuario(Usuario usuario) throws Exception {
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(usuario);
            em.merge(usuario);
            em.flush();
            return 1;
        } catch (Exception e) {
            throw new Exception("Error al actualizar " + e.getMessage());
            
        }
    }
}
