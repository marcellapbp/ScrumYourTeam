package br.com.scrumyourteam.bean;

//keep actionlistener for xhtml pages
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcella
 * Date: 03/13/2017
 * Objective: Creating menu
 */
@Named(value = "menuView")
@Dependent
@ManagedBean
public class MenuView 
{
    public void login() throws IOException 
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/login.xhtml");
    }
     
    public void register() throws IOException 
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/pages/user/user-add.xhtml");
    }
    
    public void goHome() throws IOException 
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/index.xhtml");
    }
     
 }
