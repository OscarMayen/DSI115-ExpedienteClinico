
package com.managedbean;


import com.ejb.ConsultaEJB;
import com.ejb.DiagnosticoEJB;
import com.ejb.MedicoEJB;
import com.ejb.PacienteEJB;
import com.ejb.SalaEJB;
import com.entities.ConsultaEntity;
import com.entities.DiagnosticoEntity;
import com.entities.MedicoEntity;
import com.entities.PacienteEntity;
import com.entities.SalaEntity;
import com.entities.SignosvitalesEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Oscar Mayen
 */
@ManagedBean(name = "consultaInsertarBean")
@javax.faces.bean.ViewScoped
public class ConsultaInsertarBean implements Serializable{

    @EJB
    private DiagnosticoEJB diagnosticoEJB;

    @EJB
    private ConsultaEJB consultaEJB;

    @EJB
    private SalaEJB salaEJB;
    
    @EJB
    private MedicoEJB medicoEJB;

    @EJB
    private PacienteEJB pacienteEJB;
    
    public ConsultaInsertarBean() {
    }
    
    private List<SalaEntity> buscarSalas() {
         this.listaSala = salaEJB.listarSalas();
         return listaSala;
    }
    
    private String txtDuiPaciente;
    private String txtDuiMedico;
    
    private String txtNombrePaciente;
    private String txtNombreMedico;
    
    private String txtApellidoPaciente;
    private String txtApellidoMedico;
    
    private String duiMedBuscq;
    private String duiPacBuscq;
    
    private String nomMedBuscq;
    private String nomPacBuscq;
    
    private int idPaciente;
    private int idMedico;
    
    private ConsultaEntity consulta = new ConsultaEntity();
    private DiagnosticoEntity diagnostico = new  DiagnosticoEntity();
    private PacienteEntity paciente = new PacienteEntity();
    private MedicoEntity medico = new MedicoEntity();
    private SignosvitalesEntity signosVitables = new SignosvitalesEntity();
    
    private MedicoEntity medSelect = new MedicoEntity();
    private PacienteEntity pacSelect = new PacienteEntity();
    
    private List < PacienteEntity > lstPac = new ArrayList();
    private List < MedicoEntity > lstMed = new ArrayList();
    
    private List<SalaEntity> listaSala = new ArrayList();
   
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

    
    public ConsultaEntity getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaEntity consulta) {
        this.consulta = consulta;
    }

    public List<SalaEntity> getListaSala() {
        return listaSala;
    }

    public void setListaSala(List<SalaEntity> listaSala) {
        this.listaSala = listaSala;
    }

    public SignosvitalesEntity getSignosVitables() {
        return signosVitables;
    }

    public void setSignosVitables(SignosvitalesEntity signosVitables) {
        this.signosVitables = signosVitables;
    }

    public String getTxtNombrePaciente() {
        return txtNombrePaciente;
    }

    public void setTxtNombrePaciente(String txtNombrePaciente) {
        this.txtNombrePaciente = txtNombrePaciente;
    }

    public String getTxtNombreMedico() {
        return txtNombreMedico;
    }

    public void setTxtNombreMedico(String txtNombreMedico) {
        this.txtNombreMedico = txtNombreMedico;
    }

    public String getTxtApellidoPaciente() {
        return txtApellidoPaciente;
    }

    public void setTxtApellidoPaciente(String txtApellidoPaciente) {
        this.txtApellidoPaciente = txtApellidoPaciente;
    }

    public String getTxtApellidoMedico() {
        return txtApellidoMedico;
    }

    public void setTxtApellidoMedico(String txtApellidoMedico) {
        this.txtApellidoMedico = txtApellidoMedico;
    }

    public DiagnosticoEntity getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(DiagnosticoEntity diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    

     @PostConstruct
    public void init() {
       System.out.println("!!!!!!!!!!!!!");
       listaSala = buscarSalas();
    
    }
    
    
    public void itemBuscarListenerMedico() {
         System.out.println("/********************");
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
          paciente=pacSel;
          this.txtDuiPaciente = pacSel.getIdPersona().getDui();
          this.txtNombrePaciente = pacSel.getIdPersona().getNombrePersona();
          this.txtApellidoPaciente = pacSel.getIdPersona().getApellidoPersona();
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
         medico = medSel;
         this.txtDuiMedico = medSel.getIdPersona().getDui();
         this.txtNombreMedico = medSel.getIdPersona().getNombrePersona();
         this.txtApellidoMedico = medSel.getIdPersona().getApellidoPersona();
         
        PrimeFaces.current().executeScript("PF('dlgMedico').hide();");
    }
    
    public String insertarConsulta() {
    
        FacesContext context = FacesContext.getCurrentInstance();
        
        this.consulta.setIdMedico(medico);
        System.out.println("DATOS DEL MEDICO");
        System.out.println("IdMedico: " + medico.getIdMedico());
        System.out.println("idPersona: " + medico.getIdPersona().getNombrePersona());
        
        this.consulta.setIdPaciente(paciente);
        System.out.println("DATOS DEL MEDICO");
        System.out.println("IdPaciente: " + paciente.getIdPaciente());
        System.out.println("idPersona: " + paciente.getIdPersona());
        
        
        this.consulta.setIdSignosVitales(signosVitables);
        System.out.println("Peso: " +signosVitables.getPeso());
        System.out.println("Altura: " +signosVitables.getPeso());
        System.out.println("Presion: " +signosVitables.getPeso());
        
        
        int a =this.consultaEJB.insertarConsulta(this.consulta);
        
        if (a == 0) {
            FacesContext.getCurrentInstance().addMessage("Consulta", new FacesMessage("ERROR AL INSERTAR"));
             System.out.println("valor de A: " +a);
            return null;  
        } 
       
        else{
            diagnostico.setIdConsulta(consulta);
            int b = this.diagnosticoEJB.insertarDiagnostico(diagnostico);
        }
        this.medico = new MedicoEntity();
        this.paciente = new PacienteEntity();
        this.signosVitables = new SignosvitalesEntity();
        this.consulta= new ConsultaEntity();
        this.diagnostico=new DiagnosticoEntity();
        
        
        return "/admin/usuario/usuarioListar?faces-redirect=true";
    }
}
