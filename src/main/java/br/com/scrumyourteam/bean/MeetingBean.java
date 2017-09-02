
package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.MeetingController;
import br.com.scrumyourteam.controller.SprintController;
import br.com.scrumyourteam.domain.Meeting;
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
 * Date: 07/13/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@ManagedBean (name = "meetingBean")
@Dependent
@SessionScoped
public class MeetingBean
{
    private HttpServletRequest request;
    private SessionContext context;
    private MeetingController control;
    private Meeting meeting;
    private ListDataModel<Meeting> MeetingList;
    
    public MeetingBean() 
    {
        this.context = new SessionContext();
    }
    
    
    //go to page with a meeting list 
    public void goMeetingList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/meeting/meeting-list.xhtml");
    }
    
    //create Meeting list 
    public ListDataModel<br.com.scrumyourteam.domain.Meeting> getMeetingListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new MeetingController();
            return new ListDataModel<>(control.getMeetingList(idProject));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getMeetingList in MeetingBean: " + ex);
        }
    }

    //it takes data from meeting-list.xhtml dialog using the session
    //it sends to MeetingController
    public void MeetingAdd() throws SQLException, IOException 
    {
//        context = new SessionContext();
//        request = (HttpServletRequest)context.currentExternalContext().getRequest();
//        
//        meeting = new Meeting();
//        
//        String nameMeeting = request.getParameter("newMeetingForm:nameMeeting");
//        int meetingValue = Integer.parseInt(request.getParameter("newMeetingForm:meetingValue"));
//        
//        meeting.setNameMeeting(nameMeeting);
//        meeting.setMeetingValue(meetingValue);
//        meeting.setProject(new Project());
//        meeting.getProject().setIdProject((int) context.currentExternalContext().getSessionMap().get("idProject"));
//        
//        
//         control = new MeetingController();
//         control.meetingAdd(meeting);
//         
//         getMeetingListFromBase();
    }
    

    public ListDataModel<Meeting> getMeetingList() {
        this.MeetingList= getMeetingListFromBase();
        return this.MeetingList;
    }

    //It brings all project Meetings to fill the add Minute form
    public List<Meeting> getArrayMeetingList() throws SQLException
    {
        int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
        control = new MeetingController();
        return (control.getMeetingList(idProject));
    }
}
