package br.com.scrumyourteam.bean;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcella
 * Date: 03/23/2017
 * Objective: Menu for workspace
 */
@Named(value = "panelMenuWorkspace")
@Dependent
public class PanelMenuWorkspace 
{
    public void goMyProjects() throws IOException 
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/pages/project/myprojects.xhtml");
    }

    
}
