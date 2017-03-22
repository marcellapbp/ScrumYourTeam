package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.UserDAO;
import br.com.scrumyourteam.domain.User;
import java.sql.SQLException;

/**
 *
 * @author marcella
 * Date: 03/02/2017
 * Objective: Create a Controller to add User after a request from the Servlet system
 */

public class UserController
{
    public boolean loginExists(String login, String password) throws SQLException
    {
        UserDAO dao = new UserDAO();
        
        if(dao.loginExists(login, password))
        {
            return true;
        }
        return false;
    }
    
    public void userAdd(User user) throws SQLException
    {
        UserDAO dao = new UserDAO();
        dao.addUser(user);
    }
    
}
