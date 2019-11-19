/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Oscar Mayen
 */
@ManagedBean(name = "salaInsertarBean")
@ViewScoped
public class SalaInsertarBean implements Serializable{

    @EJB
    private SalaMobiliarioEJB salaMobiliarioEJB;

    @EJB
    private MobiliarioEJB mobiliarioEJB;
    
    @EJB
    private SalaEJB salaEJB;

    
    public static int seleccion=0;
    
    private SalaEntity salaEntity = new SalaEntity();
    private MobiliarioEntity mobiliario = new MobiliarioEntity();
    private SalamobiliarioEntity salaM = new SalamobiliarioEntity();
    private List < MobiliarioEntity > listaMobiliario = new ArrayList();
    private List < SalamobiliarioEntity > listaSalaMobiliario;
    
    private int cantidad;
   
    
    public SalaInsertarBean() {
        this.listaSalaMobiliario = new ArrayList<>();
    }


    public SalaEntity getSalaEntity() {
        return salaEntity;
    }

    public void setSalaEntity(SalaEntity salaEntity) {
        this.salaEntity = salaEntity;
    }

    public List<MobiliarioEntity> getListaMobiliario() {
        listaMobiliario = mobiliarioEJB.listarMobiliario();
        return listaMobiliario;
    }

    public void setListaMobiliario(List<MobiliarioEntity> listaMobiliario) {
        this.listaMobiliario = listaMobiliario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<SalamobiliarioEntity> getListaSalaMobiliario() {
        return listaSalaMobiliario;
    }

    public void setListaSalaMobiliario(List<SalamobiliarioEntity> listaSalaMobiliario) {
        this.listaSalaMobiliario = listaSalaMobiliario;
    }

    public MobiliarioEntity getMobiliario() {
        return mobiliario;
    }

    public void setMobiliario(MobiliarioEntity mobiliario) {
        this.mobiliario = mobiliario;
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
                if (validacion == 1) {
                    this.listaSalaMobiliario.add(new SalamobiliarioEntity(this.salaEntity, this.mobiliario, this.cantidad));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Mobiliario agregado"));
                    System.out.println("CANTIDAD DE DATOS: " + this.listaSalaMobiliario.size());
                    this.cantidad = 0;
                }
                else{
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "MOBILIARIO YA SE ENCUENTRA SELECCIONADO"));
                }
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
        validacion =1;
    }
     
     public void insertarSalaMobiliario() throws IOException
     {
         int a;
         
         if(this.listaSalaMobiliario.size()>0)
         {
             a = salaEJB.insertSala(this.salaEntity);
             System.out.println("VALOR DE A: " + a);
             if(a==0)
             {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "EL NOMBRE DE LA SALA DEBE SER UNICO"));
             }
             
             
             else
             {
                 this.salaM.setIdSala(salaEntity);
                 System.out.println("ID SALA INGRESADA " + salaM.getIdSala().getIdSala());
                 for (int i = 0; i < this.listaSalaMobiliario.size(); i++) 
                 {
                     this.salaM = this.listaSalaMobiliario.get(i);
                     this.salaM.setIdSala(salaEntity);
                     int b = this.salaMobiliarioEJB.insertarSalaMobiliario(salaM);
                     System.out.println("VALOR DE B: " + b);
                 }
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "SALA INGRESADA CON EXITO"));
                 JsfUtils.Redireccionar("salaListar.xhtml");
             }
            
         }
         
         else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "SE DEBE AGREGAR MOBILIARIO A LA SALA"));
         }
         
         this.listaSalaMobiliario = new ArrayList<>();
         this.salaEntity = new SalaEntity();
     }
     
     public void quitarMobiliario(int idMobiliario, Integer filaSeleccionada)
    {
        try 
        {
            int i=0;
            for (SalamobiliarioEntity item : this.listaSalaMobiliario) 
            {
                if (item.getIdMobiliario().getIdMobiliario() == idMobiliario && filaSeleccionada.equals(i)) 
                {
                   this.listaSalaMobiliario.remove(i);
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