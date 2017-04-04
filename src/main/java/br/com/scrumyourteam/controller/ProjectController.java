package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.ProjectDAO;
import br.com.scrumyourteam.domain.Project;
import java.sql.SQLException;
import java.util.List;

/**
 * @author marcella
 * Date: 04/03/2017
 */
public class ProjectController 
{
     public List<Project> getMemberProjects(int idUser) throws SQLException
    {
        ProjectDAO dao = new ProjectDAO();

        return dao.getProjectMemberList(idUser);
    }
    
}
