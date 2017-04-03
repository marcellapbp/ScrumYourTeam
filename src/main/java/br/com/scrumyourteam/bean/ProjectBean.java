package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.ProjectController;
import br.com.scrumyourteam.domain.Project;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

/**
 *
 * @author marcella.pereira.a1
 * Date: 03/20/2017
 * Objective: Project CRUD
 */
//@Named(value = "projectBean"
@Dependent
@ManagedBean (name = "projectBean")
@ViewScoped
public class ProjectBean 
{
    private ListDataModel<Project> projectList;
    
    
    public List<Project> getMemberProjects() throws SQLException
    {
        
        ProjectController project = new ProjectController();
        
        project.getMemberProjects(2);
        return null;
    }

    public ListDataModel<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(ListDataModel<Project> projectList) {
        this.projectList = projectList;
    }

    
}
