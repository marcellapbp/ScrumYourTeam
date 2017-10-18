
package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Estimate;
import br.com.scrumyourteam.domain.Priority;
import br.com.scrumyourteam.domain.Project;
import br.com.scrumyourteam.domain.Task;
import br.com.scrumyourteam.domain.ChartInformation;
import br.com.scrumyourteam.domain.ChartPoints;
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
        System.out.println("PASSOU AQUI");
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
    
    
     public List<Task> getSprintBacklogListByStatus (int idProject, int idSprint, String taskStatus) throws SQLException
    {
        //call task_sprint_select_list_by_status(<id_project>,<id_sprint>,<task_status>);
        String sql = "call task_sprint_select_list_by_status(?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            psmt.setInt(2, idSprint);
            psmt.setString(3, taskStatus);
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
            throw new RuntimeException("Error to execute getSprintBacklogListByStatus in TaskDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    public ChartInformation getChartInformation (int idProject, int idSprint) throws SQLException
    {
        //call chart_information_select(<id_project>,<id_sprint>);
        String sql = "call chart_information_select(?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            psmt.setInt(2, idSprint);
            ResultSet rs = psmt.executeQuery();

            ChartInformation chartInfo = new ChartInformation();
            while(rs.next())
            {
                chartInfo.setTotalEstimate(rs.getInt("total_estimate"));
                chartInfo.setStartingDate(rs.getDate("starting_date").toLocalDate());
                chartInfo.setEndingDate(rs.getDate("ending_date").toLocalDate());
                chartInfo.setSetDateDiffTotal(rs.getInt("date_diff_total"));
            }
            return chartInfo;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getChartInformation in TaskDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    
    public List<ChartPoints> getChartPoints (int idProject, int idSprint) throws SQLException
    {
        //call chart_information_select(<id_project>,<id_sprint>);
        String sql = "call chart_points_select(?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            psmt.setInt(2, idSprint);
            ResultSet rs = psmt.executeQuery();

            ArrayList<ChartPoints> listChart = new ArrayList<>();
            while(rs.next())
            {
                ChartPoints chartPoints = new ChartPoints();
                chartPoints.setDatePoint(rs.getDate("date_point").toLocalDate());
                chartPoints.setNumberPoint(rs.getInt("number_point"));
                chartPoints.setEstimatePoint(rs.getInt("estimate_point"));
                listChart.add(chartPoints);
            }
            return listChart;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getChartPoints in TaskDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    public ChartInformation getChartInformationIndividual (int idProject, int idSprint, int idUser) throws SQLException
    {
        //call chart_information_select_individual(<id_project>,<id_sprint>,<id_user>);
        String sql = "call chart_information_select_individual(?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            psmt.setInt(2, idSprint);
            psmt.setInt(3, idUser);
            ResultSet rs = psmt.executeQuery();

            ChartInformation chartInfo = new ChartInformation();
            while(rs.next())
            {
                chartInfo.setTotalEstimate(rs.getInt("total_estimate"));
                chartInfo.setStartingDate(rs.getDate("starting_date").toLocalDate());
                chartInfo.setEndingDate(rs.getDate("ending_date").toLocalDate());
                chartInfo.setSetDateDiffTotal(rs.getInt("date_diff_total"));
            }
            return chartInfo;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getChartInformationIndividual in TaskDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
    
    public List<ChartPoints> getChartPointsIndividual (int idProject, int idSprint, int idUser) throws SQLException
    {
        //call chart_information_select_individual(<id_project>,<id_sprint>,<id_user>);
        String sql = "call chart_points_select_individual(?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            psmt.setInt(2, idSprint);
            psmt.setInt(3, idUser);
            ResultSet rs = psmt.executeQuery();

            ArrayList<ChartPoints> listChart = new ArrayList<>();
            while(rs.next())
            {
                ChartPoints chartPoints = new ChartPoints();
                chartPoints.setDatePoint(rs.getDate("date_point").toLocalDate());
                chartPoints.setNumberPoint(rs.getInt("number_point"));
                chartPoints.setEstimatePoint(rs.getInt("estimate_point"));
                listChart.add(chartPoints);
            }
            return listChart;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getChartPointsIndividual in TaskDAO: " + ex);
        }finally{
            conn.close();
        }
    }
}
