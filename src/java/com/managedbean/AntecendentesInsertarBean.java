/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;

import com.ejb.AntecedentesEJB;
import com.ejb.PacienteEJB;
import com.entities.AntecedentesEntity;
import com.entities.PacienteEntity;
import edu.utilidades.JsfUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DTO
 */
@Named(value = "antecendentesInsertarBean")
@ViewScoped
public class AntecendentesInsertarBean implements Serializable{

    @EJB
    private PacienteEJB pacienteEJB;

    @EJB
    private AntecedentesEJB antecedentesEJB;

    PacienteEntity patient;
    List<PacienteEntity> paciente;
    AntecedentesEntity antecedentes;
    
    private String aler;
    private String antefam;
    private String anteper;
    
       
    @PostConstruct
    public void init(){
        patient =new PacienteEntity();
        paciente= new ArrayList<PacienteEntity>();
        paciente=pacienteEJB.listarPacienteAntecedentes();       
    }
    
    public void insertarDatos(){    
        antecedentes= new AntecedentesEntity();
        antecedentes.setAlergico(aler);
        antecedentes.setAnteFamiliar(antefam);
        antecedentes.setAntePersonales(anteper);
        antecedentes.setIdPaciente(this.patient);
        
        try {
            antecedentesEJB.insertarAntecedentes(antecedentes);
            JsfUtils.Redireccionar("listarAntecedentes.xhtml");
        } catch (Exception e) {
            e.toString();
        }
        
    }

    
    public void obtenerPaciente(int id) {
        System.out.println(id);
        patient=pacienteEJB.obtenerPaciente(id);
        
    }
    
    
    public AntecedentesEntity getAntecedentes() {
        return antecedentes;
    }
    
    public void setAntecedentes(AntecedentesEntity antecedentes) {
        this.antecedentes = antecedentes;
    }

    public List<PacienteEntity> getPaciente() {
        return paciente;
    }

    public void setPaciente(List<PacienteEntity> paciente) {
        this.paciente = paciente;
    }

    public PacienteEntity getPatient() {
        return patient;
    }

    public void setPatient(PacienteEntity patient) {
        this.patient = patient;
    }

    public String getAler() {
        return aler;
    }

    public void setAler(String aler) {
        this.aler = aler;
    }

    public String getAntefam() {
        return antefam;
    }

    public void setAntefam(String antefam) {
        this.antefam = antefam;
    }

    public String getAnteper() {
        return anteper;
    }

    public void setAnteper(String anteper) {
        this.anteper = anteper;
    }


    
}
