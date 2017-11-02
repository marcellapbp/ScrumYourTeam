
package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.TaskDAO;
import br.com.scrumyourteam.domain.ChartInformation;
import br.com.scrumyourteam.domain.ChartPoints;
import br.com.scrumyourteam.domain.Sprint;
import br.com.scrumyourteam.domain.Task;
import br.com.scrumyourteam.domain.TaskResponsible;
import java.sql.SQLException;
import java.util.List;


/**
 * @author marcella
 * Date: 09/06/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */
public class TaskController 
{
    private TaskDAO dao;
    private SprintController controlSprint;
    private Sprint sprint;
    
    //it selects Product Backlog list
    public List<Task> getProductBacklogList (int idProject) throws SQLException 
    {
        dao = new TaskDAO();
        return dao.getProductBacklogList(idProject);
    }

    //it selects Sprint Backlog list from a specific sprint
    public List<Task> getSprintBacklogList (int idProject, int idSprint) throws SQLException 
    {
        dao = new TaskDAO();
        return dao.getSprintBacklogList(idProject, idSprint);
    }

    //it selects the last Sprint Backlog list
    public List<Task> getSprintBacklogList (int idProject) throws SQLException 
    {
        dao = new TaskDAO();
        controlSprint = new SprintController();
        sprint = controlSprint.getLastSprint(idProject);
        return dao.getSprintBacklogList(idProject, sprint.getIdSprint());
    }
    
    //it selects the last Sprint Backlog list
    public List<TaskResponsible> getSprintBacklogListByStatus (int idProject, String taskStatus) throws SQLException 
    {
        dao = new TaskDAO();
        controlSprint = new SprintController();
        sprint = controlSprint.getLastSprint(idProject);
        return dao.getSprintBacklogListByStatus(idProject, sprint.getIdSprint(),taskStatus);
    }
    
    //it selects some information to put on the chart
    public ChartInformation getChartInformation (int idProject) throws SQLException 
    {
        dao = new TaskDAO();
        controlSprint = new SprintController();
        sprint = controlSprint.getLastSprint(idProject);
        return dao.getChartInformation(idProject, sprint.getIdSprint());
    }
    
    
    //it selects the points to put on the chart
    public List<ChartPoints> getChartPoints (int idProject) throws SQLException 
    {
        dao = new TaskDAO();
        controlSprint = new SprintController();
        sprint = controlSprint.getLastSprint(idProject);
        return dao.getChartPoints(idProject, sprint.getIdSprint());
    }
    
        //it selects some information from the Individual to put on the chart
    public ChartInformation getChartInformationIndividual (int idProject, int idUser) throws SQLException 
    {
        dao = new TaskDAO();
        controlSprint = new SprintController();
        sprint = controlSprint.getLastSprint(idProject);
        return dao.getChartInformationIndividual(idProject, sprint.getIdSprint(), idUser);
    }
    
    
    //it selects the points from the Individual to put on the chart
    public List<ChartPoints> getChartPointsIndividual (int idProject, int idUser) throws SQLException 
    {
        dao = new TaskDAO();
        controlSprint = new SprintController();
        sprint = controlSprint.getLastSprint(idProject);
        return dao.getChartPointsIndividual(idProject, sprint.getIdSprint(), idUser);
    }
    
    public void TaskAdd(Task task) throws SQLException 
    {
        dao = new TaskDAO();
        dao.addTask(task);
    }

}
