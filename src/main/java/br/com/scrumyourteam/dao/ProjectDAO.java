package br.com.scrumyourteam.dao;

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
 * Date: 04/03/2017
 */

public class ProjectDAO 
{
    private Connection conn;
    
    public ProjectDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    public List<Project> getProjectMemberList (int idUser) throws SQLException
    {
        //call project_select_member_list(<id_user>);
        String sql = "call project_select_member_list(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idUser);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Project> projectList = new ArrayList<>();
            while(rs.next())
            {
                Project project = new Project();
                project.setIdProject(rs.getInt("id_project"));
                project.setNameProject(rs.getString("name_project"));
                project.setDescription(rs.getString("description"));
                //project.setStartingDate(rs.getString("starting_date"));
                project.setLengthInSprint(rs.getInt("project_length_in_sprint"));
                project.setSprintLength(rs.getInt("sprint_length"));
                projectList.add(project);
            }
            return projectList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getProjectMemberList in ProjectDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
}
