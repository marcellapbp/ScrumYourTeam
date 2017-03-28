
package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.UserController;
import br.com.scrumyourteam.domain.User;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author marcella.pereira.a1
 * Date: 03/20/2017
 * Objective: Validate login
 */
@Named(value = "userBean")
@Dependent
public class UserBean 
{
    private HttpServletRequest request;
    public void login() throws SQLException, IOException
    {
        request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        String login = request.getParameter("loginForm:login");
        String password = request.getParameter("loginForm:password");

        UserController control = new UserController();
        
        if (control.loginExists(login, password)) 
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/workspace.xhtml");
        }else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/login.xhtml");
        }

        
    }
    
  
    
    public void userAdd() throws SQLException, IOException 
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        request = (HttpServletRequest) context.getRequest();
        
        User user = new User();
        
        String nameUser = request.getParameter("registerForm:nameUser");
        String login = request.getParameter("registerForm:login");
        String password = request.getParameter("registerForm:password");
        
        user.setNameUser(nameUser);
        user.setLogin(login);
        user.setPassword(password);
        
         UserController control = new UserController();
         control.userAdd(user);
         
         context.redirect("/ScrumYourTeam/faces/index.xhtml");
         
        
    }
    
    
}
