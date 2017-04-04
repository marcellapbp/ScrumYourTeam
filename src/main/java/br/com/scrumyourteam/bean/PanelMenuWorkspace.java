package br.com.scrumyourteam.bean;

import java.io.IOException;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * @author marcella
 * Date: 03/23/2017
 */

@Dependent
@ManagedBean (name = "panelMenuWorkspace")
@ViewScoped
public class PanelMenuWorkspace 
{
    public void goMyProjects() throws IOException 
    {
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/project/myprojects.xhtml");
    }

    
}
