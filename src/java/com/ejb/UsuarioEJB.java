/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.UsuarioEntity;
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

    public List<UsuarioEntity> listarUsuario() {
        Query query = em.createNamedQuery("UsuarioEntity.findAll");
        return query.getResultList();
    }
    
    public UsuarioEntity obtenerUsuarioLogIn(String username){
        Query query = em.createNamedQuery("UsuarioEntity.findByUsername").setParameter("username", username);
        return (UsuarioEntity) query.getSingleResult();
    }
    
    public int insertUsuario(UsuarioEntity usuario) {
        try {
            em.persist(usuario);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public List filtrarUsuarios(String user, String rol, String dui){
        Query query = em.createQuery("SELECT u FROM Usuario u JOIN u.idPersona per WHERE (u.username LIKE '%"+user+"%') AND (u.idRol.nombreRol LIKE '%"+rol+"%') AND (per.dui LIKE '%"+dui+"%') ");
        return query.getResultList();
    }
    
    public UsuarioEntity obtenerUsuario(int codigo) {
        return em.find(UsuarioEntity.class, codigo);
    }

    public int modificarUsuario(UsuarioEntity usuario) throws Exception {
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
