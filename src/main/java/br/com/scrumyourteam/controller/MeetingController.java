
package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.MeetingDAO;
import br.com.scrumyourteam.domain.Meeting;
import java.sql.SQLException;
import java.util.List;

/**
 * @author marcella
 * Date: 07/21/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */
public class MeetingController 
{
    private MeetingDAO dao;
    
    //it selects Meeting list
    public List<Meeting> getMeetingList (int idProject) throws SQLException 
    {
        dao = new MeetingDAO();
        return dao.getMeetingList(idProject);
    }

    
}
