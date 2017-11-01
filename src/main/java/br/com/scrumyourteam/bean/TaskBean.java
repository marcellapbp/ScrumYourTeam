
package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.TaskController;
import br.com.scrumyourteam.domain.Estimate;
import br.com.scrumyourteam.domain.Priority;
import br.com.scrumyourteam.domain.Project;
import br.com.scrumyourteam.domain.Sprint;
import br.com.scrumyourteam.domain.Task;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marcella.pereira.a1
 * Date: 09/06/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@ManagedBean (name = "taskBean")
@Dependent
@SessionScoped
public class TaskBean 
{
    private HttpServletRequest request;
    private SessionContext context;
    private TaskController control;
    private Sprint sprint = new Sprint();
    private Task task = new Task();
    
    
    public TaskBean() 
    {
         this.context = new SessionContext();
    }
    
    //Go Pages
    
    //go to page with a ProductBack list 
    public void goProductBacklogList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/task/productbacklog-list.xhtml");
    }
    
    //go to page with a SprintBacklog list 
    public void goSprintBacklogList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/task/sprintbacklog-list.xhtml");
    }
    
    //go to page with a Kanban list 
    public void goKanbanList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/task/kanban.xhtml");
    }
    
    //go to page with a Burndown Chart
    public void goBurndownChart() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/task/burndown-chart.xhtml");
    }
    
    
    //CREATE LISTS
    
    //create Product Backlog list 
    public ListDataModel<br.com.scrumyourteam.domain.Task> getProductBacklogListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new TaskController();
            return new ListDataModel<>(control.getProductBacklogList(idProject));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getProductBacklogList in TaskBean: " + ex);
        }
    }
    
     //create Sprint Backlog list 
    public ListDataModel<br.com.scrumyourteam.domain.Task> getSprintBacklogListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new TaskController();
            return new ListDataModel<>(control.getSprintBacklogList(idProject));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getSprintBacklogList in TaskBean: " + ex);
        }
    }
        
     //create Sprint Backlog list 
    public ListDataModel<br.com.scrumyourteam.domain.Task> getNewSprintBacklogListFromBase()
    {
        try 
        {
            context = new SessionContext();
            request = (HttpServletRequest)context.currentExternalContext().getRequest();
            
            
            sprint.setIdSprint(sprint.getIdSprint());
            sprint.setProject(new Project());
            sprint.getProject().setIdProject((int) context.currentExternalContext().getSessionMap().get("idProject"));
             //throw new RuntimeException("Error to execute getNewSprintBacklogList in TaskBean: " + sprint.getIdSprint());
            control = new TaskController();

            return new ListDataModel<>(control.getSprintBacklogList(sprint.getProject().getIdProject(),sprint.getIdSprint()));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getNewSprintBacklogList in TaskBean: " + ex);
        }
    }
    
    
     //create Sprint Backlog list 
    public ListDataModel<br.com.scrumyourteam.domain.Task> getSprintBacklogListByStatusFromBase(String taskStatus)
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new TaskController();
            return new ListDataModel<>(control.getSprintBacklogListByStatus(idProject, taskStatus));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getSprintBacklogList in TaskBean: " + ex);
        }
    }
    
    //LIST GETTERS
    
    public ListDataModel<Task> getProductBacklogList() {
        return getProductBacklogListFromBase();
    }
    
    public ListDataModel<Task> getSprintBacklogList() {
        return getSprintBacklogListFromBase();
    }

    public ListDataModel<Task> getNewSprintBacklogList() {
        return getNewSprintBacklogListFromBase();
    } 
    
    public ListDataModel<Task> getSprintBacklogListByStatus(String taskStatus) {
        return getSprintBacklogListByStatusFromBase(taskStatus);
    }
    
     
    
    public String resetDataTable()
    {
        return "";
    }
    
    public void populateDataTable()
    {
        getNewSprintBacklogList();
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
    
    
    //it takes data from productbacklog-list.xhtml dialog using the session
    //it sends to TaskController
    public void taskAdd() throws SQLException, IOException 
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");

        task = new Task();

        
        String nameTask = request.getParameter("addTaskForm:nameTask");
        String description = request.getParameter("addTaskForm:description");
        String idPriority = request.getParameter("dialogAddTask:addTaskForm:selectPriority");
        String idEstimate = request.getParameter("dialogAddTask:addTaskForm:selectEstimate");
        String doneDefinition = request.getParameter("dialogAddTask:addTaskForm:doneDefinition");
        
        task.setProject(new Project());
        task.getProject().setIdProject(idProject);
        task.setNameTask(nameTask);
        task.setDescription(description);
        task.setPriority(new Priority());
        task.getPriority().setIdPriority(Integer.parseInt(idPriority));
        task.setEstimate(new Estimate());
        task.getEstimate().setIdEstimate(Integer.parseInt(idEstimate));
        task.setDoneDefinition(doneDefinition);
        
        System.out.println("TESTE "+ task.getNameTask());
        control = new TaskController();
        control.TaskAdd(task);
         
        getProductBacklogList();
    }

    public Task getTask() {
        task.setPriority(new Priority());
        task.setEstimate(new Estimate());
        return task;
    }
    
    //It brings all project sprints to fill the add Minute form
    public List<Task> getArrayTaskList() throws SQLException
    {
        int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
        control = new TaskController();
        return (control.getProductBacklogList(idProject));
    }
    
    //it takes data from sprintbacklog-list.xhtml dialog using the session
    //it sends to TaskController
    public void responsibleAdd() throws SQLException, IOException 
    {    
        
    }
}


