
package edu.utilidades;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author admin
 */
public class JsfUtils 
{
    public static void addErrorMessage(String id, String message)
    {
        FacesContext.getCurrentInstance().addMessage(id, 
                new FacesMessage(message));
    }
    
    public static void addFlashMessage(String key, Object obj)
    {
         FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, obj);
    }
    
    public static HttpServletRequest getRequest()
    {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    
    }
}
