
package com.managedbean;


import com.ejb.AntecedentesEJB;
import com.ejb.ConsultaEJB;
import com.ejb.DiagnosticoEJB;
import com.ejb.MedicoEJB;
import com.ejb.PacienteEJB;
import com.ejb.SalaEJB;
import com.entities.AntecedentesEntity;
import com.entities.ConsultaEntity;
import com.entities.DiagnosticoEntity;
import com.entities.MedicoEntity;
import com.entities.PacienteEntity;
import com.entities.SalaEntity;
import com.entities.SignosvitalesEntity;
import com.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import java.text.SimpleDateFormat;
/**
 *
 * @author Oscar Mayen
 */
@ManagedBean(name = "consultaInsertarBean")
@javax.faces.bean.ViewScoped
public class ConsultaInsertarBean implements Serializable{

    @EJB
    private AntecedentesEJB antecedentesEJB;

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
    
    private UsuarioEntity user = new UsuarioEntity();
    
    private String txtDuiPaciente;
    private String txtDuiMedico;
    
    private String txtNombrePaciente;
    private String txtNombreMedico;
    
    private String txtApellidoPaciente;
    private String txtApellidoMedico;
    
    private String txtDiagnostico;
    
    private String duiPacBuscq;
    private String nomPacBuscq;
    
    private int idPaciente;
 
    private String txtEdadPaciente;
    
    private ConsultaEntity consulta = new ConsultaEntity();
    private DiagnosticoEntity diagnostico = new  DiagnosticoEntity();
    private PacienteEntity paciente = new PacienteEntity();
    private MedicoEntity medico = new MedicoEntity();
    private SignosvitalesEntity signosVitables = new SignosvitalesEntity();
    private AntecedentesEntity antecedentes = new AntecedentesEntity();
    
    private MedicoEntity medSelect = new MedicoEntity();
    private PacienteEntity pacSelect = new PacienteEntity();
    
    private List < PacienteEntity > lstPac = new ArrayList();
    private List < MedicoEntity > lstMed = new ArrayList();
    
    private List<SalaEntity> listaSala = new ArrayList();
   
    public String getDuiPacBuscq() {
        return duiPacBuscq;
    }

    public void setDuiPacBuscq(String duiPacBuscq) {
        this.duiPacBuscq = duiPacBuscq;
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

    public String getTxtEdadPaciente() {
        return txtEdadPaciente;
    }

    public void setTxtEdadPaciente(String txtEdadPaciente) {
        this.txtEdadPaciente = txtEdadPaciente;
    }

    public String getTxtDiagnostico() {
        return txtDiagnostico;
    }

    public void setTxtDiagnostico(String txtDiagnostico) {
        this.txtDiagnostico = txtDiagnostico;
    }
    

    public DiagnosticoEntity getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(DiagnosticoEntity diagnostico) {
        this.diagnostico = diagnostico;
    }

    public AntecedentesEntity getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(AntecedentesEntity antecedentes) {
        this.antecedentes = antecedentes;
    }
    
    

     @PostConstruct
    public void init() {
       System.out.println("!!!!!!!!!!!!!");
       listaSala = buscarSalas();
       user = (UsuarioEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
       medico = medicoEJB.obtenerMedicoPorUsuario(user.getUsername());         
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
          
          Date fechaActual = new Date();
          SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
          String hoy = formato.format(fechaActual);
          String fechaNacimientoPaciente = formato.format(pacSel.getIdPersona().getFechaNacimiento());
          String[] dat1 = fechaNacimientoPaciente.split("/");
          String[] dat2 = hoy.split("/");
          int anios = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
          int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
          if (mes < 0) {
             anios = anios - 1;
          } 
          else if (mes == 0) {
          int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
            if (dia > 0) {
                anios = anios - 1;
            }
          }
          this.txtEdadPaciente = String.valueOf(anios);
          PrimeFaces.current().executeScript("PF('dlgPaciente').hide();");
    }

    
    public void verAntecententes()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        if (paciente.getIdPaciente() != null) {
            int codigo = paciente.getIdPaciente();
            PrimeFaces.current().executeScript("PF('dlgVerAntecedentes').show();");
            antecedentes = this.antecedentesEJB.obtenerAntecedentesPorPaciente(codigo);
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "DEBE DE SELECCIONAR UN PACIENTE PARA VER SUS ANTECEDENTES"));
        }
    }
    
    public String insertarConsulta() {
    
        int a=0;
        FacesContext context = FacesContext.getCurrentInstance();
        this.consulta.setIdMedico(medico);
        this.consulta.setFechaConsulta(new Date());
        
        if(paciente.getIdPaciente()!=null){
            this.consulta.setIdPaciente(paciente);
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR DE VALIDACION", "DEBE DE SELECCIONAR UN PACIENTE"));
        }
        
        if(signosVitables.getPeso()>0 && signosVitables.getAltura()>0 &&!(signosVitables.getPresion().isEmpty())){
             this.consulta.setIdSignosVitales(signosVitables);
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR DE VALIDACION", "TODOS LOS SIGNOS VITALES NECESITAN UN VALOR"));
        }
        
        if(!(txtDiagnostico.isEmpty())){
             this.diagnostico.setDescripcion(txtDiagnostico);
        }
        
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR DE VALIDACION", "EL CAMPO DIAGNOSTICO NECESITAN UN VALOR"));
        }
        
        
        if(paciente.getIdPaciente()!=null &&signosVitables.getPeso()>0 && signosVitables.getAltura()>0 &&!(signosVitables.getPresion().isEmpty()) &&!(txtDiagnostico.isEmpty()) )
        {
              a =this.consultaEJB.insertarConsulta(this.consulta);
        }
       
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACION", "POR FAVOR COMPLETE TODOS LOS CAMPOS"));
        }
        
        //int a =this.consultaEJB.insertarConsulta(this.consulta); 
        
        if (a == 0) { 
            //FacesContext.getCurrentInstance().addMessage("Consulta", new FacesMessage("ERROR AL INSERTAR DATOS"));
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
        return "/admin/consulta/consultaListar?faces-redirect=true";
        
    }
    
    public String btnCancelarConsulta()
    {
        String outcome ="/admin/consulta/consultaListar?faces-redirect=true";
        return outcome;
    }
}
