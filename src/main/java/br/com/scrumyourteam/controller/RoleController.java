
package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.RoleDAO;
import br.com.scrumyourteam.domain.Role;
import java.sql.SQLException;
import java.util.List;

/**
 * @author marcella
 * Date: 07/13/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */
public class RoleController 
{
    private RoleDAO dao;
    
    //it selects role list
    public List<Role> getRoleList (int idProject) throws SQLException 
    {
        dao = new RoleDAO(); 
        return dao.getRoleList(idProject);
    }
    
    //it selects a role
    public Role getRole (int idRole,int idProject) throws SQLException 
    {
        dao = new RoleDAO(); 
        return dao.getRole(idRole,idProject);
    }

    public void roleAdd(Role role) throws SQLException 
    {
        dao = new RoleDAO();
        dao.addRole(role);
    }
    
}
