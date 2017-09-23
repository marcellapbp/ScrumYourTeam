package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.SprintDAO;
import br.com.scrumyourteam.domain.Sprint;
import java.sql.SQLException;
import java.util.List;

/**
 * @author marcella
 * Date: 08/18/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */
public class SprintController
{
    private SprintDAO dao;
    
    //it selects Sprint list
    public List<Sprint> getSprintList (int idProject) throws SQLException 
    {
        dao = new SprintDAO();
        return dao.getSprintList(idProject);
    }

    
    public void sprintAdd(Sprint sprint) throws SQLException 
    {
        dao = new SprintDAO();
        dao.addSprint(sprint);

    }
    
    public Sprint getLastSprint (int idProject) throws SQLException 
    {
        dao = new SprintDAO();
        return dao.getLastSprint(idProject);
    }
           
}
