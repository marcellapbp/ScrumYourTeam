package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.UserDAO;
import br.com.scrumyourteam.domain.User;
import java.sql.SQLException;

/**
 * @author marcella
 * Date: 03/02/2017
 */

public class UserController
{
    public int loginExists(String login, String password) throws SQLException
    {
        UserDAO dao = new UserDAO();
        
        return dao.loginExists(login, password);
    }
    
    public void userAdd(User user) throws SQLException
    {
        UserDAO dao = new UserDAO();
        dao.addUser(user);
    }
    
}
