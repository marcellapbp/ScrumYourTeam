package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.PriorityDAO;
import br.com.scrumyourteam.domain.Priority;
import java.sql.SQLException;
import java.util.List;

/**
 * @author marcella
 * Date: 04/11/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */
public class PriorityController
{
    private PriorityDAO dao;
    
    //it selects estimate list
    public List<Priority> getPriorityList (int idProject) throws SQLException 
    {
        dao = new PriorityDAO();
        return dao.getPriorityList(idProject);
    }

    public void priorityAdd(Priority priority) throws SQLException 
    {
        dao = new PriorityDAO();
        dao.addPriority(priority);
    }
}
