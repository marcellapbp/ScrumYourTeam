
package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.MemberDAO;
import br.com.scrumyourteam.domain.Member;
import java.sql.SQLException;
import java.util.List;

/**
 * @author marcella
 * Date: 07/24/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */
public class MemberController 
{
    private MemberDAO dao;
    private List<Member> MemberList;
    
    //it selects Member list
    public List<Member> getMemberList (int idProject) throws SQLException 
    {
        dao = new MemberDAO();
        MemberList = dao.getMemberList(idProject);
        
//        for (Member member : MemberList) 
//        {
//            UserController user = new UserController();
//            user.getUser(member.getUser().getIdUser());
//            RoleController role = new RoleController();
//            role.getRole(member.getRole().getIdRole(), member.getProject().getIdProject());
//            
//        }
        return MemberList;
    }

    public void memberAdd(Member member) throws SQLException 
    {
        dao = new MemberDAO();
       // dao.addMember(member);

    }
}
