
package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Member;
import br.com.scrumyourteam.domain.Project;
import br.com.scrumyourteam.domain.Role;
import br.com.scrumyourteam.domain.User;
import br.com.scrumyourteam.persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcella
 * Date: 07/21/2017
 * Objective: To create Member CRUD
 */
public class MemberDAO 
{
        private Connection conn;
    
    //set a connection every MemberDAO instance
    public MemberDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
        //it selects Member list
    public List<Member> getMemberList (int idProject) throws SQLException
    {
        //call member_select_list(<id_project>);
        String sql = "call member_select_list(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Member> memberList = new ArrayList<>();
            while(rs.next())
            {
                Member member = new Member();
                
                member.setProject(new Project());
                member.getProject().setIdProject(rs.getInt("project_id_project"));
                member.getProject().setNameProject(rs.getString("name_project"));
                member.getProject().setDescription(rs.getString("description"));
                member.getProject().setStartingDate(rs.getDate("starting_date").toLocalDate());
                member.getProject().setLengthInSprint(rs.getInt("project_length_in_sprint"));
                member.getProject().setSprintLength(rs.getInt("sprint_length"));
                member.getProject().setProjectStatus(rs.getString("project_status"));
                member.getProject().setWeekdaySprint(rs.getString("weekday_sprint"));
                
                member.setUser(new User());
                member.getUser().setIdUser(rs.getInt("user_id_user"));
                member.getUser().setNameUser(rs.getString("name_user"));
                member.getUser().setLogin(rs.getString("login"));
                
                member.setRole(new Role());
                member.getRole().setIdRole(rs.getInt("role_id_role"));
                member.getRole().setNameRole(rs.getString("name_role"));
                
                memberList.add(member);
            }
            return memberList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getMemberList in MemberDAO: " + ex);
        }finally{
            conn.close();
        }
    }

    
    
    
}
