package br.com.scrumyourteam.bean;

import java.util.List;
import br.com.scrumyourteam.controller.SprintController;
import br.com.scrumyourteam.domain.Project;
import br.com.scrumyourteam.domain.Sprint;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marcella.pereira.a1
 * Date: 08/18/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@ManagedBean (name = "sprintBean")
@Dependent
@SessionScoped
public class SprintBean
{
    private HttpServletRequest request;
    private SessionContext context;
    private SprintController control;
    private Sprint sprint;

    public SprintBean() 
    {
        this.context = new SessionContext();
    }
    
    
    //go to page with a sprint list 
    public void goSprintList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/sprint/sprint-list.xhtml");
    }
    
    public ListDataModel<br.com.scrumyourteam.domain.Sprint> getSprintListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new SprintController();
            return new ListDataModel<>(control.getSprintList(idProject));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getSprintList in SprintBean: " + ex);
        }
    }
    
    //it takes data from sprint-list.xhtml dialog using the session
    //it sends to SprintController
    public void SprintAdd() throws SQLException, IOException 
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        sprint = new Sprint();
        
        String sprintNumber = request.getParameter("newSprintForm:sprintNumber");
        String startingDate = request.getParameter("newSprintForm:startingDate");
        String endingDate = request.getParameter("newSprintForm:endingDate");

        sprint.setSprintNumber(Integer.parseInt(sprintNumber));
        sprint.setStartingDate(LocalDate.parse(startingDate));
        sprint.setEndingDate(LocalDate.parse(endingDate));
        sprint.setProject(new Project());
        sprint.getProject().setIdProject((int) context.currentExternalContext().getSessionMap().get("idProject"));
        
        
         control = new SprintController();
         control.sprintAdd(sprint);
         
         getSprintList();
    }
    
    //It was need to every time create a new class
    //this way will not get information from others project
    //because there is no more objects in the memory
    public ListDataModel<Sprint> getSprintList() {
        return getSprintListFromBase();
    }
    
    //It brings all project sprints to fill the add Minute form
    public List<Sprint> getArraySprintList() throws SQLException
    {
        int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
        control = new SprintController();
        return (control.getSprintList(idProject));
    }
}
