
package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.UserController;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
    public void login() throws SQLException, IOException
    {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        String login = request.getParameter("loginForm:login");
        String password = request.getParameter("loginForm:password");

        UserController control = new UserController();
        
        if (control.loginExists(login, password)) 
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/index.xhtml");
        }else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ScrumYourTeam/faces/login.xhtml");
        }

    }

    
    
    
}
