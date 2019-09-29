/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean;


import com.ejb.MedicoEJB;
import com.ejb.PacienteEJB;
import com.entities.MedicoEntity;
import com.entities.PacienteEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mireya Flores
 */
@ManagedBean
@javax.faces.bean.ViewScoped
public class ConsultaInsertarBean implements Serializable{

    @EJB
    private MedicoEJB medicoEJB;

    @EJB
    private PacienteEJB pacienteEJB;
    
    public ConsultaInsertarBean() {
    }
    
    private String txtDuiPaciente;
     private String txtDuiMedico;
    
    
    private String duiMedBuscq;
    private String duiPacBuscq;
    
    private String nomMedBuscq;
    private String nomPacBuscq;
    
    private int idPaciente;
    private int idMedico;
    
    private PacienteEntity paciente = new PacienteEntity();
    private MedicoEntity medico = new MedicoEntity();
    
    private MedicoEntity medSelect = new MedicoEntity();
    private PacienteEntity pacSelect = new PacienteEntity();
    
    private List < PacienteEntity > lstPac = new ArrayList();
    private List < MedicoEntity > lstMed = new ArrayList();

    
    
    
    public String getDuiMedBuscq() {
        return duiMedBuscq;
    }

    public void setDuiMedBuscq(String duiMedBuscq) {
        this.duiMedBuscq = duiMedBuscq;
    }

    public String getDuiPacBuscq() {
        return duiPacBuscq;
    }

    public void setDuiPacBuscq(String duiPacBuscq) {
        this.duiPacBuscq = duiPacBuscq;
    }

    public String getNomMedBuscq() {
        return nomMedBuscq;
    }

    public void setNomMedBuscq(String nomMedBuscq) {
        this.nomMedBuscq = nomMedBuscq;
    }

    public String getNomPacBuscq() {
        return nomPacBuscq;
    }

    public void setNomPacBuscq(String nomPacBuscq) {
        this.nomPacBuscq = nomPacBuscq;
    }

    public List<PacienteEntity> getLstPac() {
        return lstPac;
    }

    public void setLstPac(List<PacienteEntity> lstPac) {
        this.lstPac = lstPac;
    }

    public List<MedicoEntity> getLstMed() {
        return lstMed;
    }

    public void setLstMed(List<MedicoEntity> lstMed) {
        this.lstMed = lstMed;
    }

    public MedicoEntity getMedSelect() {
        return medSelect;
    }

    public void setMedSelect(MedicoEntity medSelect) {
        this.medSelect = medSelect;
    }

    public PacienteEntity getPacSelect() {
        return pacSelect;
    }

    public void setPacSelect(PacienteEntity pacSelect) {
        this.pacSelect = pacSelect;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }
    

    public String getTxtDuiPaciente() {
        return txtDuiPaciente;
    }

    public void setTxtDuiPaciente(String txtDuiPaciente) {
        this.txtDuiPaciente = txtDuiPaciente;
    }

    public String getTxtDuiMedico() {
        return txtDuiMedico;
    }

    public void setTxtDuiMedico(String txtDuiMedico) {
        this.txtDuiMedico = txtDuiMedico;
    }
    
    
    
    
    public void itemBuscarListenerMedico() {
        
        try {
            lstMed = medicoEJB.filtroMedicoConsulta(duiPacBuscq, nomPacBuscq);
            System.out.println("/********************");
            System.out.println(lstMed.size());
        } catch (Exception ex) {
            Logger.getLogger(ConsultaInsertarBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error", "Error " + ex.getMessage()));
        }
        
    }
    
    
    public void itemBuscarListenerPaciente() {
        
        try {
            lstPac = pacienteEJB.filtroPacienteConsulta(duiPacBuscq, nomPacBuscq);
            System.out.println("/********************");
            System.out.println(lstPac.size());
        } catch (Exception ex) {
            Logger.getLogger(ConsultaInsertarBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error", "Error " + ex.getMessage()));
        }
        
    }
    
    
    public void onSelecttblPaciente(SelectEvent event) {
       
        System.out.println("--------------- " + event.getObject());
        if (event.getObject() == null) {
           FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error", "Error al seleccionar "));
            return;
        }
          PacienteEntity pacSel = (PacienteEntity) event.getObject();
          this.txtDuiPaciente = pacSel.getIdPersona().getDui();

        PrimeFaces.current().executeScript("PF('dlgPaciente').hide();");
    }
    
    
    public void onSelecttblMedico(SelectEvent event) {
       
        System.out.println("--------------- " + event.getObject());
        if (event.getObject() == null) {
           FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error", "Error al seleccionar "));
            return;
        }
         MedicoEntity medSel = (MedicoEntity) event.getObject();
         this.txtDuiMedico = medSel.getIdPersona().getDui();
         
        

        PrimeFaces.current().executeScript("PF('dlgMedico').hide();");
    }
}
