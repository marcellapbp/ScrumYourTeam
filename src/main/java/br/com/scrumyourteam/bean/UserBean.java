
package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.UserController;
import br.com.scrumyourteam.domain.User;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marcella.pereira.a1
 * Date: 03/20/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@Dependent
@ManagedBean (name = "userBean")
@SessionScoped
public class UserBean 
{
    private HttpServletRequest request;
    private SessionContext context;
    private User user;
    private UserController control;
    
    //it takes the session if there is
    public UserBean()
    {
        this.context = new SessionContext();
    }
    
    //it takes data from user-add.xhtml using the session
    //it sends to UserController and last redirect to index
    public void userAdd() throws SQLException, IOException 
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        user = new User();
        
        String nameUser = request.getParameter("registerForm:nameUser");
        String login = request.getParameter("registerForm:login");
        String password = request.getParameter("registerForm:password");
        
        user.setNameUser(nameUser);
        user.setLogin(login);
        user.setPassword(password);
        
         control = new UserController();
         control.userAdd(user);
         
         context.currentExternalContext().redirect("/ScrumYourTeam/faces/index.xhtml");
    }
    
    //it takes data from login.xhtml, checks if login exists
    //creates a session putting the id on it and redirect to workspace.xhtml
    public void login() throws SQLException, IOException
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        String login = request.getParameter("loginForm:login");
        String password = request.getParameter("loginForm:password");

        control = new UserController();
        int idUser = control.loginExists(login, password);
        if ( idUser != -1) 
        { 
            context.currentExternalContext()
                    .getSessionMap().put("idUser", idUser);
            context.currentExternalContext()
                    .redirect("/ScrumYourTeam/faces/pages/project/myprojects.xhtml");
        }else{
            context.currentExternalContext()
                    .redirect("/ScrumYourTeam/faces/login.xhtml");
        }
    }
  
    //it replace project and user id saved on the session to null
    //and redirects to index.xhtml
    public void logout() throws IOException
    {
        context.currentExternalContext()
                    .getSessionMap().put("idUser", null);
        context.currentExternalContext()
                    .getSessionMap().put("idProject", null);
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/index.xhtml");
    }
}
