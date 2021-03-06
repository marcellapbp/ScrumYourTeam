package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.ProjectController;
import br.com.scrumyourteam.domain.Project;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marcella.pereira.a1
 * Date: 03/20/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@Dependent
@ManagedBean (name = "projectBean")
@SessionScoped
public class ProjectBean 
{
    private ListDataModel<Project> projectList;
    private SessionContext context;
    private HttpServletRequest request;
    private ProjectController projectCont;
    private Project project;
    private List<Project> projectsFound = new ArrayList<>();
    private ProjectController control;
    
    public ProjectBean()
    {
        this.context = new SessionContext();
    }
    
    //go to page with a user's projects list 
    public void goMyProjects() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/project/myprojects.xhtml");
    }
    
    //create the user's projects list based on his/her id on myprojects load 
    @PostConstruct
    public void getMemberProjects()
    {
        try 
        {
            int idUser = (int) context.currentExternalContext().getSessionMap().get("idUser");
            control = new ProjectController();
            this.setProjectList(new ListDataModel<>(control.getMemberProjects(idUser)));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getMemberProjects in Project Bean: " + ex);
        }
    }
    
    //after seeing the project list, when user choose one will be redirect to workspace
    public void goToProjectChoosen(int idProject) throws IOException
    {
        context.currentExternalContext().getSessionMap().put("idProject", idProject);
        context.currentExternalContext().redirect("/ScrumYourTeam/faces/workspace.xhtml");
    }
    
    //go to about page with project information
    public void currentProject() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/project/about.xhtml");
    }
    
    //it takes project when about.xhtml page was loaded
    public Project getProject() throws SQLException
    {
        int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
        projectCont = new ProjectController();
        return projectCont.getProject(idProject);
    }
    
    //it updates the project register on current project 
    //if user press button on about.xhtml page
    public void updateProject() throws IOException, SQLException
    {
        request = (HttpServletRequest) context.currentExternalContext().getRequest();

        project = new Project();
        project.setIdProject((int)context.currentExternalContext().getSessionMap().get("idProject"));
        project.setNameProject(request.getParameter("currentProject:nameProject"));
        project.setDescription(request.getParameter("currentProject:description"));
        project.setLengthInSprint(Integer.parseInt(request.getParameter("currentProject:projectLengthInSprint")));
        project.setSprintLength(Integer.parseInt(request.getParameter("currentProject:sprintLength")));
        project.setStartingDate(LocalDate.parse(request.getParameter("currentProject:button")));
        project.setProjectStatus(request.getParameter("currentProject:projectStatus"));
        project.setWeekdaySprint(request.getParameter("currentProject:weekdaySprint"));
        
        projectCont = new ProjectController();
        projectCont.updateProject(project);
        
        context.currentExternalContext().redirect("/ScrumYourTeam/faces/workspace.xhtml");
    }
    
    //it goes to page where you can create a new project
    public void createProject() throws IOException
    {   
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/project/new.xhtml");
    }
    
    //it takes the new project object to insert on database
    public void setNewProject()
    {}
    
    //it goes to page where you can search for project
    public void searchProject() throws IOException
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/project/search.xhtml");
    }
    
    //it searches a project on database by name
    public void getProjectByName() throws SQLException, IOException
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        project = new Project();
        String nameProject = request.getParameter("searchProject:nameProject");
        
        project.setNameProject(nameProject);
        
        control = new ProjectController();
        projectsFound = control.getProjectByName(project);

        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/project/search-result.xhtml");

    }
    
    //getters setters
    public ListDataModel<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(ListDataModel<Project> projectList) {
        this.projectList = projectList;
    }

    //set disable equals true on panelmenu items if user didn't choose a project
    public boolean isProjectSelected() {
        //before idProject to be setted is null, after is int, then below used Integer
        Integer proj = (Integer) context.currentExternalContext().getSessionMap().get("idProject");
        return (proj == null);
    }

    public List<Project> getProjectFound() {
        return projectsFound;
    }

    
    
    
    
    
}
