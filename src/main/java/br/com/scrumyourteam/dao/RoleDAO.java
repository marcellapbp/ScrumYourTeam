
package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Role;
import br.com.scrumyourteam.persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcella
 * Date: 07/13/2017
 * Objective: To create Role CRUD
 */
public class RoleDAO
{
    private Connection conn;
    
    //set a connection every PriorityDAO instance
    public RoleDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    
    //it inserts a register to Role table
    public void addRole(Role role) throws SQLException 
    {
        //call role_insert(<name_role>,<project_id_project>)
        String sql = "call role_insert(?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setString(1, role.getNameRole());
            psmt.setInt(2, role.getProject().getIdProject());

            psmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute addRole in RoleDAO: " + ex);
        }finally{
            conn.close();
        }
    }

        //it selects Role list
    public List<Role> getRoleList (int idProject) throws SQLException
    {
        //call Role_select_list(<id_project>);
        String sql = "call role_select_list(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Role> roleList = new ArrayList<>();
            while(rs.next())
            {
                Role role = new Role();
                role.setIdRole(rs.getInt("id_role"));
                role.setNameRole(rs.getString("name_role"));
                roleList.add(role);
            }
            return roleList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getRoleList in RoleDAO: " + ex);
        }finally{
            conn.close();
        }
    }

}
