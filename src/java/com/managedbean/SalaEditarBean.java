
package com.managedbean;

import com.ejb.MobiliarioEJB;
import com.ejb.SalaEJB;
import com.ejb.SalaMobiliarioEJB;
import com.entities.MobiliarioEntity;
import com.entities.SalaEntity;
import com.entities.SalamobiliarioEntity;
import edu.utilidades.JsfUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Oscar Mayen
 */
@Named(value = "salaEditarBean")
@ViewScoped
public class SalaEditarBean implements Serializable{

    @EJB
    private MobiliarioEJB mobiliarioEJB;

    @EJB
    private SalaMobiliarioEJB salaMobiliarioEJB;

    @EJB
    private SalaEJB salaEjb;
    
    private List<SalamobiliarioEntity> listaSalaMobiliario = new ArrayList();
    private List<SalamobiliarioEntity> listaSalaMobiliarioNuevosDatos = new ArrayList();
    
    SalaEntity sala = new SalaEntity();
    private SalamobiliarioEntity salaM = new SalamobiliarioEntity();
    MobiliarioEntity mobiliario = new MobiliarioEntity();
    SalamobiliarioEntity salaMobiliario = new SalamobiliarioEntity();
    private List < MobiliarioEntity > listaMobiliario = new ArrayList();
    
    public static int seleccion=0;
    private int cantidad;
    
    public SalaEditarBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        FacesContext fc = FacesContext.getCurrentInstance();
        
        HttpServletRequest request = (HttpServletRequest) fc
                .getExternalContext().getRequest();
        System.out.println(fc.getExternalContext().getRequestParameterMap());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        String id = fc.getExternalContext().getRequestParameterMap().get("id");
        if (id == null) {
            System.out.println("Error!!!!");
        } else {
             this.sala = new SalaEntity();
             sala = salaEjb.obtenerSala(Integer.valueOf(id)); 
             listaSalaMobiliario = buscarSalasMobiliario();
          }
    }
    
    private List<SalamobiliarioEntity> buscarSalasMobiliario() {
         this.listaSalaMobiliario = salaMobiliarioEJB.obtenerlistadoSalaMobiliarioPorSala(sala.getIdSala());
         return listaSalaMobiliario;
    }

    public List<MobiliarioEntity> getListaMobiliario() {
        listaMobiliario = mobiliarioEJB.listarMobiliario();
        return listaMobiliario;
    }

    public void setListaMobiliario(List<MobiliarioEntity> listaMobiliario) {
        this.listaMobiliario = listaMobiliario;
    }
    
    public SalaEntity getSala() {
        return sala;
    }

    public void setSala(SalaEntity sala) {
        this.sala = sala;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public MobiliarioEntity getMobiliario() {
        return mobiliario;
    }

    public void setMobiliario(MobiliarioEntity mobiliario) {
        this.mobiliario = mobiliario;
    }
    
    
    public List<SalamobiliarioEntity> getListaSalaMobiliario() {
        return listaSalaMobiliario;
    }

    public void setListaSalaMobiliario(List<SalamobiliarioEntity> listaSalaMobiliario) {
        this.listaSalaMobiliario = listaSalaMobiliario;
    }

    public SalamobiliarioEntity getSalaMobiliario() {
        return salaMobiliario;
    }

    public void setSalaMobiliario(SalamobiliarioEntity salaMobiliario) {
        this.salaMobiliario = salaMobiliario;
    }

    public List<SalamobiliarioEntity> getListaSalaMobiliarioNuevosDatos() {
        return listaSalaMobiliarioNuevosDatos;
    }

    public void setListaSalaMobiliarioNuevosDatos(List<SalamobiliarioEntity> listaSalaMobiliarioNuevosDatos) {
        this.listaSalaMobiliarioNuevosDatos = listaSalaMobiliarioNuevosDatos;
    }

    public SalamobiliarioEntity getSalaM() {
        return salaM;
    }

    public void setSalaM(SalamobiliarioEntity salaM) {
        this.salaM = salaM;
    }
    
    
    public void pedirCantidadMobiliario(int codMobiliario)
    {
       this.seleccion = codMobiliario;
    }
    
    public void agregarMobiliario() {
        int validacion=1;
        try {
            if (this.cantidad <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad debe ser mayor que cero"));

            } else {
                this.mobiliario = mobiliarioEJB.obtenerMobiliario(seleccion);
                
                for (SalamobiliarioEntity dato : this.listaSalaMobiliario) {
                    int a;
                    if (dato.getIdMobiliario().getIdMobiliario() == seleccion) {
                        validacion=0;
                        break;
                    }
                }
                for (SalamobiliarioEntity dato2 : this.listaSalaMobiliarioNuevosDatos) {
                    int a;
                    if (dato2.getIdMobiliario().getIdMobiliario() == seleccion) {
                        validacion=0;
                        break;
                    }
                }
                if (validacion == 1) {
                    this.listaSalaMobiliarioNuevosDatos.add(new SalamobiliarioEntity(this.sala, this.mobiliario, this.cantidad));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Mobiliario agregado"));
                    System.out.println("CANTIDAD DE DATOS: " + this.listaSalaMobiliarioNuevosDatos.size());
                    this.cantidad = 0;
                }
                else{
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "MOBILIARIO YA SE ENCUENTRA SELECCIONADO"));
                  cantidad =0;
                }
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
        this.listaSalaMobiliario = buscarSalasMobiliario();
        validacion =1;
    }
    
    
    
    public void eliminarSalaMobiliario(SalamobiliarioEntity sm) throws Exception {
         int a;
         this.salaMobiliario = sm;
         FacesContext context = FacesContext.getCurrentInstance();
             a = salaMobiliarioEJB.eliminarSalaMobiliario(salaMobiliario.getIdSalaMobiliario());
             if (a == 1) {
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Editorial eliminada correctamente."));
             }
             
             else {
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Editorial no pudo ser eliminada."));
             }
             salaMobiliario = new SalamobiliarioEntity();
             this.listaSalaMobiliario = buscarSalasMobiliario();
     } 
    
    
    
    public void updateSala() throws IOException{
        
        salaEjb.modificarSala(this.sala);
        for (int i = 0; i < this.listaSalaMobiliarioNuevosDatos.size(); i++) {
            this.salaM = this.listaSalaMobiliarioNuevosDatos.get(i);
            this.salaM.setIdSala(sala);
            int b = this.salaMobiliarioEJB.insertarSalaMobiliario(salaM);
        }
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "SALA INGRESADA CON EXITO"));
                 JsfUtils.Redireccionar("salaListar.xhtml");
     
    }
    
    public String btnCancelarSala(){
        
        return "/admin/sala/salaListar?faces-redirect=true";
    }
    
    
    public void quitarMobiliario(int idMobiliario, Integer filaSeleccionada)
    {
        try 
        {
            int i=0;
            for (SalamobiliarioEntity item : this.listaSalaMobiliarioNuevosDatos) 
            {
                if (item.getIdMobiliario().getIdMobiliario() == idMobiliario && filaSeleccionada.equals(i)) 
                {
                   this.listaSalaMobiliarioNuevosDatos.remove(i);
                   break;
                }
                i++;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", "Se retiro el mobiliario de la lista"));
            
        } 
        catch (Exception e) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
        }
    }
     
    public void onRowEdit(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se modificio la cantidad"));
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "No se hizo ningun cambio"));
    }
    
}