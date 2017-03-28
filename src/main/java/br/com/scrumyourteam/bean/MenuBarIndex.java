package br.com.scrumyourteam.bean;

//keep actionlistener for xhtml pages
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcella
 * Date: 03/13/2017
 * Objective: Menu for Index
 */
@Named(value = "menuBarIndex")
@Dependent
@ManagedBean
public class MenuBarIndex 
{
    public void goHome() throws IOException 
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/index.xhtml");
    }
    
    public void login() throws IOException 
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/login.xhtml");
    }
    
    public void logout() throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/index.xhtml");
    }
     
    public void register() throws IOException 
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/pages/user/user-add.xhtml");
    }
     
    public void goWorkspace() throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/workspace.xhtml");
    }

}
