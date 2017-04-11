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
@ManagedBean (name = "estimateBean")
@Dependent
@SessionScoped
public class EstimateBean 
{
    private SessionContext context;

    public EstimateBean() 
    {
        this.context = new SessionContext();
    }
    
    //go to page with a estimate list 
    public void goEstimateList() throws IOException 
    {
        context.currentExternalContext()
                .redirect("/ScrumYourTeam/faces/pages/estimate/estimate-list.xhtml");
    }
}
