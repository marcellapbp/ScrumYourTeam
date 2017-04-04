package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.ProjectController;
import br.com.scrumyourteam.domain.Project;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marcella.pereira.a1
 * Date: 03/20/2017
 */
@Dependent
@ManagedBean (name = "projectBean")
@SessionScoped
public class ProjectBean 
{
    private ListDataModel<Project> projectList;
    private SessionContext context;
    private HttpServletRequest request;
    
    public ProjectBean()
    {
        this.context = new SessionContext();
    }
    
    public void goMyProjects() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/project/myprojects.xhtml");
    }
    @PostConstruct
    public void getMemberProjects()
    {
        try 
        {
            int idUser = (int) context.currentExternalContext().getSessionMap().get("idUser");
            ProjectController project = new ProjectController();
            projectList = new ListDataModel<>(project.getMemberProjects(idUser));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getMemberProjects in Project Bean: " + ex);
        }
    }
    
    public void goToProjectChoosen(int idProject) throws IOException
    {
        //throw new RuntimeException("Erro: " + idProject);
        request = (HttpServletRequest) context.currentExternalContext().getRequest();
        //int idProject = Integer.parseInt(request.getParameter("chooseProjectForm:<#####>"));
        
        context.currentExternalContext().getSessionMap().put("idProject", idProject);
        context.currentExternalContext().redirect("/ScrumYourTeam/faces/workspace.xhtml");
    }

    public ListDataModel<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(ListDataModel<Project> projectList) {
        this.projectList = projectList;
    }
    
    
}
