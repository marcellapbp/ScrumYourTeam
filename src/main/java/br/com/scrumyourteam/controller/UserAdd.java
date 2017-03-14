package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.domain.User;
import br.com.scrumyourteam.dao.UserDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marcella
 * Date: 03/02/2017
 * Objective: Create a Controller to add User after a request from the Servlet system
 */

public class UserAdd implements IController
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
    {
        try {
            User user = new User();
            user.setNameUser(request.getParameter("nameUser"));
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            
            UserDAO dao = new UserDAO();
            dao.addUser(user);
        } catch (SQLException ex) {
            throw new RuntimeException("Error to get parameters "
                    + "from request in UserAdd Controller: " + ex);
        }

    }
    
}
