package br.com.scrumyourteam.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marcella
 * Date: 03/02/2017
 * Objective: Create a Global Servlet to attend all Controllers
 */

@WebServlet(name = "SystemServlet", urlPatterns = {"/System"})
public class SystemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
       
        String actionRequested = request.getParameter("request");
        
        if (actionRequested.equals("UserAdd")) 
        {
            UserAdd userAdd = new UserAdd();
            userAdd.execute(request, response);
            
            request.getRequestDispatcher("/index.html").forward(request,response);;
        }
    }

   

}
