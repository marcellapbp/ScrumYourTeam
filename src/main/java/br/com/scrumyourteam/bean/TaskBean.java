
package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.TaskController;
import br.com.scrumyourteam.domain.Task;
import java.io.IOException;
import java.sql.SQLException;
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
    private TaskBean task;

    
    public TaskBean() 
    {
         this.context = new SessionContext();
    }
    
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
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            
            context = new SessionContext();
            request = (HttpServletRequest)context.currentExternalContext().getRequest();
            int idSprint = Integer.parseInt(request.getParameter("TaskForm:idSprint"));
                    
            control = new TaskController();
            return new ListDataModel<>(control.getSprintBacklogList(idProject,idSprint));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getNewSprintBacklogList in TaskBean: " + ex);
        }
    }
    
    public ListDataModel<Task> getProductBacklogList() {
        return getProductBacklogListFromBase();
    }
    
    public ListDataModel<Task> getSprintBacklogList() {
        return getSprintBacklogListFromBase();
    }

    public ListDataModel<Task> getNewSprintBacklogList() {
        return getNewSprintBacklogListFromBase();
    }
}
