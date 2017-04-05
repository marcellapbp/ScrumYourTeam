package br.com.scrumyourteam.bean;

//keep actionlistener for xhtml pages
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author marcella
 * Date: 03/13/2017
 */

@Dependent
@ManagedBean (name = "menuBarIndex")
@ViewScoped
public class MenuBarIndex 
{
    private SessionContext context;
    
    public MenuBarIndex()
    {
        this.context = new SessionContext();
    }
    
    public void goHome() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/index.xhtml");
    }
    
    public void login() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/login.xhtml");
    }
    
    public void register() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/user/user-add.xhtml");
    }
     
    public void goWorkspace() throws IOException
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/workspace.xhtml");
    }

}
