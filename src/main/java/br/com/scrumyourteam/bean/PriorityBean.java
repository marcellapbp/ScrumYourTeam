package br.com.scrumyourteam.bean;

import java.io.IOException;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
}
