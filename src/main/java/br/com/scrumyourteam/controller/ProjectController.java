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
    private ProjectDAO dao;
    
     public List<Project> getMemberProjects(int idUser) throws SQLException
    {
        dao = new ProjectDAO();
        return dao.getProjectMemberList(idUser);
    }

    public Project getProject(int idProject) throws SQLException 
    {
        dao = new ProjectDAO();
        return dao.getProject(idProject);
    }

    public void updateProject(Project project) throws SQLException 
    {
        dao = new ProjectDAO();
        dao.updateProject(project);
    }
    
}
