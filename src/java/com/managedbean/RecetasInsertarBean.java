/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.ConsultaEJB;
import com.ejb.RecetasEJB;
import com.entities.ConsultaEntity;
import com.entities.RecetaEntity;
import edu.utilidades.JsfUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DTO
 */
@Named(value = "recetasInsertarBean")
@ViewScoped
public class RecetasInsertarBean implements Serializable{
    
   @EJB
   private RecetasEJB recetasEJB;
   @EJB
   private ConsultaEJB consultasEJB;
   
   private ConsultaEntity consulta;
   private RecetaEntity receta;
   List<ConsultaEntity> consultas= new ArrayList<ConsultaEntity>();
   
   
    public RecetasInsertarBean() {
        
    }
    @PostConstruct
    public void init(){          
        consultas=recetasEJB.listarConsultas();
        consulta= new ConsultaEntity();
        receta= new RecetaEntity();
    }
    
    public void insetarReceta(){
        receta.setIdConsulta(consulta);
        receta.setNombrePaciente(consulta.getIdPaciente().getIdPersona().getNombrePersona());
        receta.setApellidoPaciente(consulta.getIdPaciente().getIdPersona().getApellidoPersona());
        receta.setNombreMedico(consulta.getIdMedico().getIdPersona().getNombrePersona());
        try {
           recetasEJB.insertarReceta(receta);
            JsfUtils.Redireccionar("recetasListar.xhtml");
        } catch (Exception e) {
            e.toString();
        }
    }
    
    public void obtenerConsulta( int id){        
        consulta=consultasEJB.obtenerConsulta(id);
    }

    public List<ConsultaEntity> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaEntity> consultas) {
        this.consultas = consultas;
    }

    public ConsultaEntity getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaEntity consulta) {
        this.consulta = consulta;
    }

    public RecetaEntity getReceta() {
        return receta;
    }

    public void setReceta(RecetaEntity receta) {
        this.receta = receta;
    }

    
}
