package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.MinuteController;
import br.com.scrumyourteam.domain.Meeting;
import br.com.scrumyourteam.domain.Minute;
import br.com.scrumyourteam.domain.Project;
import br.com.scrumyourteam.domain.Sprint;
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
 * Date: 08/31/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@ManagedBean (name = "minuteBean")
@Dependent
@SessionScoped
public class MinuteBean {

    private HttpServletRequest request;
    private SessionContext context;
    private MinuteController control;
    private Minute minute;

    public MinuteBean() 
    {
        this.context = new SessionContext();
    }
    
    //go to page with a Minute list 
    public void goMinuteList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/minute/minute-list.xhtml");
    }
    
    
     //create Minute list 
    public ListDataModel<br.com.scrumyourteam.domain.Minute> getMinuteListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new MinuteController();
            return new ListDataModel<>(control.getMinuteList(idProject));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getMinuteList in MinuteBean: " + ex);
        }
    }

    //it takes data from minute-list.xhtml dialog using the session
    //it sends to MinuteController
    public void minuteAdd() throws SQLException, IOException 
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        minute = new Minute();
        
        String sprintNumber = request.getParameter("addMinuteForm:selectMenuSprint");
        String meetingName = request.getParameter("addMinuteForm:selectMenuMeeting");
        String minuteDescription = request.getParameter("addMinuteForm:minuteDescription");
        
        minute.setMeeting(new Meeting());
        minute.getMeeting().setIdMeeting(Integer.parseInt(meetingName));
        minute.setProject(new Project());
        minute.getProject().setIdProject((int) context.currentExternalContext().getSessionMap().get("idProject"));
        minute.setSprint(new Sprint());
        minute.getSprint().setIdSprint(Integer.parseInt(sprintNumber));
        minute.setDescription(minuteDescription);
        
        
         control = new MinuteController();
         control.MinuteAdd(minute);
         
         getMinuteList();
    }
    
    
    //It was need to every time create a new class
    //this way will not get information from others project
    //because there is no more objects in the memory
    public ListDataModel<Minute> getMinuteList() {
        return getMinuteListFromBase();
    }
    
}
