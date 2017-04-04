package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.ProjectController;
import br.com.scrumyourteam.domain.Project;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

/**
 * @author marcella.pereira.a1
 * Date: 03/20/2017
 */
@Dependent
@ManagedBean (name = "projectBean")
@ViewScoped
public class ProjectBean 
{
    private ListDataModel<Project> projectList;
    
    @PostConstruct
    public void getMemberProjects()
    {
        try 
        {
            ProjectController project = new ProjectController();
            projectList = new ListDataModel<>(project.getMemberProjects(3));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getMemberProjects in Project Bean: " + ex);
        }
    }
    
    public void goToProjectChoosen() throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("/ScrumYourTeam/faces/workspace.xhtml");
    }

    public ListDataModel<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(ListDataModel<Project> projectList) {
        this.projectList = projectList;
    }
    
    
}
