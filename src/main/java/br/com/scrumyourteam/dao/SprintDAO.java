package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Sprint;
import br.com.scrumyourteam.persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcella
 * Date: 08/18/2017
 * Objective: To create Sprint CRUD
 */

public class SprintDAO 
{
    private Connection conn;
    
    //set a connection every SprintDAO instance
    public SprintDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    
    //it inserts a register to Sprint table
    public void addSprint(Sprint sprint) throws SQLException 
    {
        //call sprint_insert( <project_id_project>,<sprint_number>, <starting_date>, <ending_date>)
        String sql = "call sprint_insert(?,?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, sprint.getProject().getIdProject());
            psmt.setInt(2,sprint.getSprintNumber());
            psmt.setDate(3, Date.valueOf(sprint.getStartingDate()));
            psmt.setDate(4, Date.valueOf(sprint.getEndingDate()));

            psmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute addSprint in SprintDAO: " + ex);
        }finally{
            conn.close();
        }
        
    }
    
    //it selects Sprint list
    public List<Sprint> getSprintList (int idProject) throws SQLException
    {
        //call sprint_select_list(<id_project>);
        String sql = "call sprint_select_list(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Sprint> SprintList = new ArrayList<>();
            while(rs.next())
            {
                Sprint sprint = new Sprint();
                sprint.setIdSprint(rs.getInt("id_sprint"));
                sprint.setSprintNumber(rs.getInt("sprint_number"));
                sprint.setStartingDate(rs.getDate("starting_date").toLocalDate());
                sprint.setEndingDate(rs.getDate("ending_date").toLocalDate());
                SprintList.add(sprint);
            }
            return SprintList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getSprintList in SprintDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    

    
}
