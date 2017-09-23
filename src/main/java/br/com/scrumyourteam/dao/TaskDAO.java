
package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Estimate;
import br.com.scrumyourteam.domain.Priority;
import br.com.scrumyourteam.domain.Project;
import br.com.scrumyourteam.domain.Task;
import br.com.scrumyourteam.persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcella
 * Date: 09/06/2017
 * Objective: To create Estimate CRUD
 */
public class TaskDAO 
{
    private Connection conn;
    
    //set a connection every MinuteDAO instance
    public TaskDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
     //it selects Product Backlog list
    public List<Task> getProductBacklogList (int idProject) throws SQLException
    {
        //call minute_task_list(<id_project>);
        String sql = "call task_select_list(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Task> TaskList = new ArrayList<>();
            while(rs.next())
            {
                Task task = new Task();
                task.setProject(new Project());
                task.getProject().setIdProject(rs.getInt("project_id_project"));
                task.getProject().setNameProject(rs.getString("name_project"));
                task.getProject().setStartingDate(rs.getDate("starting_date").toLocalDate());
                task.getProject().setLengthInSprint(rs.getInt("project_length_in_sprint"));
                task.getProject().setSprintLength(rs.getInt("sprint_length"));
                task.getProject().setProjectStatus(rs.getString("project_status"));
                task.getProject().setWeekdaySprint(rs.getString("weekday_sprint"));
                
                task.setPriority(new Priority());
                task.getPriority().setIdPriority(rs.getInt("priority_id_priority"));
                task.getPriority().setProject(task.getProject());
                task.getPriority().setNamePriority(rs.getString("name_priority"));
                task.getPriority().setPriorityValue(rs.getInt("priority_value"));
                
                task.setEstimate(new Estimate());
                task.getEstimate().setIdEstimate(rs.getInt("estimate_id_estimate"));
                task.getEstimate().setProject(task.getProject());
                task.getEstimate().setNameEstimate(rs.getString("name_estimate"));
                task.getEstimate().setEstimateValue(rs.getInt("estimate_value"));
                
                task.setIdTask(rs.getInt("id_task"));
                task.setNameTask(rs.getString("name_task"));
                task.setDescription(rs.getString("description"));
                task.setDoneDefinition(rs.getString("done_definition"));
                
                task.setTask(new Task());
                task.getTask().setIdTask(rs.getInt("task_id_task"));
                
                TaskList.add(task);
            }
            return TaskList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getProductBacklogList in TaskDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
      //it selects Sprint Backlog list
    public List<Task> getSprintBacklogList (int idProject, int idSprint) throws SQLException
    {
        //call task_sprint_select_list(<id_project>,<id_sprint>);
        String sql = "call task_sprint_select_list(?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            psmt.setInt(2, idSprint);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Task> TaskList = new ArrayList<>();
            while(rs.next())
            {
                Task task = new Task();
                task.setProject(new Project());
                task.getProject().setIdProject(rs.getInt("project_id_project"));
                task.getProject().setNameProject(rs.getString("name_project"));
                task.getProject().setStartingDate(rs.getDate("starting_date").toLocalDate());
                task.getProject().setLengthInSprint(rs.getInt("project_length_in_sprint"));
                task.getProject().setSprintLength(rs.getInt("sprint_length"));
                task.getProject().setProjectStatus(rs.getString("project_status"));
                task.getProject().setWeekdaySprint(rs.getString("weekday_sprint"));
                
                task.setPriority(new Priority());
                task.getPriority().setIdPriority(rs.getInt("priority_id_priority"));
                task.getPriority().setProject(task.getProject());
                task.getPriority().setNamePriority(rs.getString("name_priority"));
                task.getPriority().setPriorityValue(rs.getInt("priority_value"));
                
                task.setEstimate(new Estimate());
                task.getEstimate().setIdEstimate(rs.getInt("estimate_id_estimate"));
                task.getEstimate().setProject(task.getProject());
                task.getEstimate().setNameEstimate(rs.getString("name_estimate"));
                task.getEstimate().setEstimateValue(rs.getInt("estimate_value"));
                
                task.setIdTask(rs.getInt("id_task"));
                task.setNameTask(rs.getString("name_task"));
                task.setDescription(rs.getString("description"));
                task.setDoneDefinition(rs.getString("done_definition"));
                
                task.setTask(new Task());
                task.getTask().setIdTask(rs.getInt("task_id_task"));
                
                TaskList.add(task);
            }
            return TaskList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getSprintBacklogList in TaskDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    
}
