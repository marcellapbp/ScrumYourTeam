
package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.RoleController;
import br.com.scrumyourteam.domain.Project;
import br.com.scrumyourteam.domain.Role;
import java.io.IOException;
import java.sql.SQLException;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marcella.pereira.a1
 * Date: 07/13/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@ManagedBean (name = "roleBean")
@Dependent
@SessionScoped
public class RoleBean
{
    private HttpServletRequest request;
    private SessionContext context;
    private RoleController control;
    private Role role;

    public RoleBean() 
    {
        this.context = new SessionContext();
    }
    
    
    //go to page with a role list 
    public void goRoleList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/role/role-list.xhtml");
    }
    
    //create Role list 
    public ListDataModel<br.com.scrumyourteam.domain.Role> getRoleListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new RoleController();
            return new ListDataModel<>(control.getRoleList(idProject));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getRoleList in RoleBean: " + ex);
        }
    }

    //it takes data from role-list.xhtml dialog using the session
    //it sends to RoleController
    public void roleAdd() throws SQLException, IOException 
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        role = new Role();
        
        String nameRole = request.getParameter("newRoleForm:nameRole");
        
        role.setNameRole(nameRole);
        role.setProject(new Project());
        role.getProject().setIdProject((int) context.currentExternalContext().getSessionMap().get("idProject"));
        
        
         control = new RoleController();
         control.roleAdd(role);
         
         getRoleListFromBase();
    }
    

    public ListDataModel<Role> getRoleList() {
        return getRoleListFromBase();
    }
    
}
