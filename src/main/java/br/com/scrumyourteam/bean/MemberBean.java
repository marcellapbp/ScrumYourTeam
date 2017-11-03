
package br.com.scrumyourteam.bean;


import br.com.scrumyourteam.controller.MemberController;
import br.com.scrumyourteam.domain.Member;
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
 * Date: 07/24/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@ManagedBean (name = "memberBean")
@Dependent
@SessionScoped
public class MemberBean 
{
    private HttpServletRequest request;
    private SessionContext context;
    private MemberController control;
    private Member member;
    private ListDataModel<Member> MemberList;
    
    public MemberBean() 
    {
        this.context = new SessionContext();
    }
    
    
    //go to page with a Member list 
    public void goMemberList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/member/member-list.xhtml");
    }
    
    //create Meeting list 
    public ListDataModel<br.com.scrumyourteam.domain.Member> getMemberListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new MemberController();
            return new ListDataModel<>(control.getMemberList(idProject));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getMemberList in MemberBean: " + ex);
        }
    }

    //it takes data from member-list.xhtml dialog using the session
    //it sends to MemberController
    public void MemberAdd() throws SQLException, IOException 
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
    

    public ListDataModel<Member> getMemberList() {
        this.MemberList= getMemberListFromBase();
        return this.MemberList;
    }
    
    
    public List<Member> getArrayMemberList() throws SQLException
    {
        int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
        control = new MemberController();
        return (control.getMemberList(idProject));
    }

    public Member getMember() {
        return member;
    }
}
