package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.ProjectDAO;
import br.com.scrumyourteam.domain.Project;
import java.sql.SQLException;
import java.util.List;

/**
 * @author marcella
 * Date: 04/03/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */
public class ProjectController 
{
    private ProjectDAO dao;
    
    //it selects all projects associated to the user searching by user id informed
    public List<Project> getMemberProjects(int idUser) throws SQLException
    {
        dao = new ProjectDAO();
        return dao.getProjectMemberList(idUser);
    }

    //it selects a single register in the Project table
    public Project getProject(int idProject) throws SQLException 
    {
        dao = new ProjectDAO();
        return dao.getProject(idProject);
    }

    //it updates a existent register to Project table
    public void updateProject(Project project) throws SQLException 
    {
        dao = new ProjectDAO();
        dao.updateProject(project);
    }
    
    //it selects a single register in the Project table
    public List<Project> getProjectByName(Project project) throws SQLException 
    {
        dao = new ProjectDAO();
        return dao.getProjectByName(project);
    }

}
