package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Priority;
import br.com.scrumyourteam.persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcella
 * Date: 04/11/2017
 * Objective: To create Priority CRUD
 */
public class PriorityDAO 
{
    private Connection conn;
    
    //set a connection every PriorityDAO instance
    public PriorityDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    
    //it inserts a register to Priority table
    public void addPriority(Priority priority) throws SQLException 
    {
        //call priority_insert(<name_priority>, <priority_value>,<project_id_project>)
        String sql = "call priority_insert(?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setString(1, priority.getNamePriority());
            psmt.setInt(2, priority.getPriorityValue());
            psmt.setInt(3, priority.getProject().getIdProject());

            psmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute addPriority in PriorityDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    //it selects Priority list
    public List<Priority> getPriorityList (int idProject) throws SQLException
    {
        //call priority_select_list();
        String sql = "call priority_select_list(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Priority> priorityList = new ArrayList<>();
            while(rs.next())
            {
                Priority priority = new Priority();
                priority.setIdPriority(rs.getInt("id_priority"));
                priority.setNamePriority(rs.getString("name_priority"));
                priority.setPriorityValue(rs.getInt("priority_value"));
                priorityList.add(priority);
            }
            return priorityList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getPriorityList in PriorityDAO: " + ex);
        }finally{
            conn.close();
        }
    }
}
