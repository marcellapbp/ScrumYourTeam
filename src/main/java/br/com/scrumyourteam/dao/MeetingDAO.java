
package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Meeting;
import br.com.scrumyourteam.domain.Project;
import br.com.scrumyourteam.persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcella
 * Date: 07/21/2017
 * Objective: To create Meeting CRUD
 */
public class MeetingDAO 
{
        private Connection conn;
    
    //set a connection every MeetingDAO instance
    public MeetingDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
        //it selects Meeting list
    public List<Meeting> getMeetingList (int idProject) throws SQLException
    {
        //call meeting_select_list(<id_project>);
        String sql = "call meeting_select_list(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Meeting> meetingList = new ArrayList<>();
            while(rs.next())
            {
                Meeting meeting = new Meeting();
                meeting.setProject(new Project());
                meeting.getProject().setIdProject(rs.getInt("project_id_project"));
                meeting.setIdMeeting(rs.getInt("id_meeting"));
                meeting.setNameMeeting(rs.getString("name_meeting"));
                //meeting.setMeetingTime...
                meeting.setFlagNotification(rs.getBoolean("flag_notification"));
                meetingList.add(meeting);
            }
            return meetingList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getMeetingList in MeetingDAO: " + ex);
        }finally{
            conn.close();
        }
    }


    
    
}
