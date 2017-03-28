    package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.ProjectController;
import br.com.scrumyourteam.domain.Project;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author marcella.pereira.a1
 */
@Named(value = "dashboardView")
@Dependent
public class DashboardView implements Serializable{

    private DashboardModel model;
    private ArrayList<Project> projectList;
    
    
    @PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        
        for (Project item : getMemberProjects()) {
            DashboardColumn column = new DefaultDashboardColumn();
            column.addWidget(item.getNameProject());
            model.addColumn(column);
        }
        
    }
    
    public ArrayList<Project> getMemberProjects()
    {
        ProjectController project = new ProjectController();
        
        projectList = new ArrayList<>();
        try { 
            projectList = (ArrayList<Project>) project.getMemberProjects(2);
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute init in DashboardView:  "+ ex);
        }
        return projectList;
    }
}
