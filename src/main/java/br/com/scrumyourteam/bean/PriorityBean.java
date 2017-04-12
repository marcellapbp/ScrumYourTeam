package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.PriorityController;
import br.com.scrumyourteam.domain.Priority;
import br.com.scrumyourteam.domain.Project;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
/**
 * @author marcella.pereira.a1
 * Date: 04/11/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@ManagedBean (name = "priorityBean")
@Dependent
@SessionScoped
public class PriorityBean 
{
    private HttpServletRequest request;
    private SessionContext context;
    private PriorityController control;
    private ListDataModel<Priority> priorityList;
    private Priority priority;

    public PriorityBean() 
    {
        this.context = new SessionContext();
    }
    
    
    //go to page with a estimate list 
    public void goPriorityList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/priority/priority-list.xhtml");
    }
    
    //create Priority list 
    @PostConstruct
    public void getPriorityListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new PriorityController();
            this.setPriorityList(new ListDataModel<>(control.getPriorityList(idProject)));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getPriorityList in PriorityBean: " + ex);
        }
    }

    //it takes data from priority-list.xhtml dialog using the session
    //it sends to PriorityController
    public void priorityAdd() throws SQLException, IOException 
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        priority = new Priority();
        
        String namePriority = request.getParameter("newPriorityForm:namePriority");
        int priorityValue = Integer.parseInt(request.getParameter("newPriorityForm:priorityValue"));
        
        priority.setNamePriority(namePriority);
        priority.setPriorityValue(priorityValue);
        priority.setProject(new Project());
        priority.getProject().setIdProject((int) context.currentExternalContext().getSessionMap().get("idProject"));
        
        
         control = new PriorityController();
         control.priorityAdd(priority);
         
         control = new PriorityController();
         this.setPriorityList(new ListDataModel<>(control.getPriorityList(priority.getProject().getIdProject())));
    }
    
    //getters and setters
    public void setPriorityList(ListDataModel<Priority> priorityList) {
        this.priorityList = priorityList;
    }

    public ListDataModel<Priority> getPriorityList() {
        return priorityList;
    }
}