
package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.UserController;
import br.com.scrumyourteam.domain.User;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marcella.pereira.a1
 * Date: 03/20/2017
 */
@Dependent
@ManagedBean (name = "userBean")
@SessionScoped
public class UserBean 
{
    private HttpServletRequest request;
    private SessionContext context;
    
    public void login() throws SQLException, IOException
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        String login = request.getParameter("loginForm:login");
        String password = request.getParameter("loginForm:password");

        UserController control = new UserController();
        int idUser = control.loginExists(login, password);
        if ( idUser != -1) 
        { 
            context.currentExternalContext().getSessionMap().put("idUser", idUser);
            context.currentExternalContext().redirect("/ScrumYourTeam/faces/workspace.xhtml");
        }else{
            context.currentExternalContext().redirect("/ScrumYourTeam/faces/login.xhtml");
        }
    }
  
    
    public void userAdd() throws SQLException, IOException 
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        User user = new User();
        
        String nameUser = request.getParameter("registerForm:nameUser");
        String login = request.getParameter("registerForm:login");
        String password = request.getParameter("registerForm:password");
        
        user.setNameUser(nameUser);
        user.setLogin(login);
        user.setPassword(password);
        
         UserController control = new UserController();
         control.userAdd(user);
         
         context.currentExternalContext().redirect("/ScrumYourTeam/faces/index.xhtml");
    }
}
