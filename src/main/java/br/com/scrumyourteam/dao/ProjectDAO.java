package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Project;
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
 * Date: 04/03/2017
 * Objective: To create Project CRUD
 */

public class ProjectDAO 
{
    private Connection conn;
    
    //set a connection every UserDAO instance
    public ProjectDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    //it selects all projects associated to the user searching by user id informed
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
                projectList.add(project);
            }
            return projectList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getProjectMemberList in ProjectDAO: " + ex);
        }finally{
            conn.close();
        }
    }

    //it selects a single register in the Project table
    public Project getProject(int idProject) throws SQLException 
    {
        //call project_select(<id_project>)
        String sql = "call project_select(?);";
        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);

            ResultSet rs = psmt.executeQuery();
            
            Project project = new Project();
            while(rs.next())
            {
                project.setIdProject(rs.getInt("id_project"));
                project.setNameProject(rs.getString("name_project"));
                project.setDescription(rs.getString("description"));
                project.setStartingDate(rs.getDate("starting_date").toLocalDate());
                project.setLengthInSprint(rs.getInt("project_length_in_sprint"));
                project.setSprintLength(rs.getInt("sprint_length"));
                project.setProjectStatus(rs.getString("project_status"));
                project.setWeekdaySprint(rs.getString("weekday_sprint"));
                
            }
            return project;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getProject in ProjectDAO: " + ex);
        }finally{
            conn.close();
        }
    }

    //it updates a existent register to Project table
    public void updateProject(Project project) throws SQLException 
    {
        //call project_update(<id_project>, <name_project>, <description>, 
        //<starting_date>, <project_length_in_sprint>, <sprint_length>, 
        //<project_status>, <weekday_sprint>)
        String sql = "call project_update(?,?,?,?,?,?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, project.getIdProject());
            psmt.setString(2, project.getNameProject());
            psmt.setString(3, project.getDescription());
            psmt.setDate(4, project.getStartingDate());
            psmt.setInt(5, project.getLengthInSprint());
            psmt.setInt(6, project.getSprintLength());
            psmt.setString(7, project.getProjectStatus());
            psmt.setString(8, project.getWeekdaySprint());

            psmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute updateProject in ProjectDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
}
