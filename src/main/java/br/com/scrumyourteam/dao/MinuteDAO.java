

package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Meeting;
import br.com.scrumyourteam.domain.Sprint;
import br.com.scrumyourteam.domain.Project;
import br.com.scrumyourteam.domain.Minute;
import java.sql.Connection;
import br.com.scrumyourteam.persistence.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcella
 * Date: 08/31/2017
 * Objective: To create Minute CRUD
 */
public class MinuteDAO 
{
    private Connection conn;
    
    //set a connection every MinuteDAO instance
    public MinuteDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    
    //it inserts a register to Minute table
    public void addMinute(Minute minute) throws SQLException 
    {
        //call Minute_insert( <meeting_id_meeting>,<sprint_id_sprint>, <sprint_id_project>, <description>)
        String sql = "call Minute_insert(?,?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, minute.getMeeting().getIdMeeting());
            psmt.setInt(2,minute.getSprint().getIdSprint());
            psmt.setInt(3, minute.getProject().getIdProject());
            psmt.setString(4, minute.getDescription());

            psmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute addMinute in MinuteDAO: " + ex);
        }finally{
            conn.close();
        }
        
    }
    
    //it selects Minute list
    public List<Minute> getMinuteList (int idProject) throws SQLException
    {
        //call minute_select_list(<id_project>);
        String sql = "call minute_select_list(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Minute> MinuteList = new ArrayList<>();
            while(rs.next())
            {
                Minute minute = new Minute();
                minute.setProject(new Project());
                minute.getProject().setIdProject(rs.getInt("id_project"));
                minute.getProject().setNameProject(rs.getString("name_project"));
                minute.getProject().setStartingDate(rs.getDate("starting_date").toLocalDate());
                minute.getProject().setLengthInSprint(rs.getInt("project_length_in_sprint"));
                minute.getProject().setSprintLength(rs.getInt("sprint_length"));
                minute.getProject().setProjectStatus(rs.getString("project_status"));
                minute.getProject().setWeekdaySprint(rs.getString("weekday_sprint"));
                
                minute.setMeeting(new Meeting());
                minute.getMeeting().setIdMeeting(rs.getInt("id_meeting"));
                minute.getMeeting().setProject(minute.getProject());
                minute.getMeeting().setNameMeeting(rs.getString("name_meeting"));
                minute.getMeeting().setFlagNotification(rs.getBoolean("flag_notification"));
                
                minute.setSprint(new Sprint());
                minute.getSprint().setProject(minute.getProject());
                minute.getSprint().setSprintNumber(rs.getInt("sprint_number"));
                minute.getSprint().setStartingDate(rs.getDate("starting_date").toLocalDate());
                minute.getSprint().setEndingDate(rs.getDate("ending_date").toLocalDate());
                
                minute.setDescription(rs.getString("description"));
                minute.setPostingDate(rs.getDate("posting_date").toLocalDate());
                MinuteList.add(minute);
            }
            return MinuteList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getMinutetList in MinuteDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    
}
