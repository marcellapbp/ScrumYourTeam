package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.UserDAO;
import br.com.scrumyourteam.domain.User;
import java.sql.SQLException;

/**
 * @author marcella
 * Date: 03/02/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */

public class UserController
{
    private UserDAO dao;
    //it checks if there is the login and password informed by the user
    //returns the id if exists or -1 if doesn't
    public int loginExists(String login, String password) throws SQLException
    {
        dao = new UserDAO();
        
        return dao.loginExists(login, password);
    }
    
    //it inserts a register to User table
    public void userAdd(User user) throws SQLException
    {
        dao = new UserDAO();
        dao.addUser(user);
    }
    
    //it selects a User
    public User getUser (int idUser) throws SQLException 
    {
        dao = new UserDAO(); 
        return dao.getUser(idUser);
    }
    
}
