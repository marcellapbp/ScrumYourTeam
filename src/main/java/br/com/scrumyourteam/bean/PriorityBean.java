package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.PriorityController;
import br.com.scrumyourteam.domain.Priority;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
/**
 * @author marcella.pereira.a1
 * Date: 04/11/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@ManagedBean (name = "priorityBean")
@Dependent
@SessionScoped
public class PriorityBean 
{
    private SessionContext context;
    private PriorityController control;
    private ListDataModel<Priority> priorityList;

    public PriorityBean() 
    {
        this.context = new SessionContext();
    }
    
    
    //go to page with a estimate list 
    public void goPriorityList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/priority/priority-list.xhtml");
    }
    
    //create Priority list 
    @PostConstruct
    public void getPriorityListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new PriorityController();
            this.setPriorityList(new ListDataModel<>(control.getPriorityList(idProject)));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getPriorityList in PriorityBean: " + ex);
        }
    }

    public void setPriorityList(ListDataModel<Priority> priorityList) {
        this.priorityList = priorityList;
    }

    public ListDataModel<Priority> getPriorityList() {
        return priorityList;
    }
}
