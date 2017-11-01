

package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.MinuteDAO;
import br.com.scrumyourteam.domain.Minute;
import java.sql.SQLException;
import java.util.List;

/**
 * @author marcella
 * Date: 08/31/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */
public class MinuteController 
{
    private MinuteDAO dao;
    
    //it selects Minute list
    public List<Minute> getMinuteList (int idProject) throws SQLException 
    {
        dao = new MinuteDAO();
        return dao.getMinuteList(idProject);
    }

    
    public void MinuteAdd(Minute minute) throws SQLException 
    {
        dao = new MinuteDAO();
        dao.addMinute(minute);
    }
    
}
