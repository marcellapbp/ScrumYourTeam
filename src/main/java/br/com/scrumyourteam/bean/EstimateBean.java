package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.EstimateController;
import br.com.scrumyourteam.domain.Estimate;
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
@ManagedBean (name = "estimateBean")
@Dependent
@SessionScoped
public class EstimateBean 
{
    private SessionContext context;
    private ListDataModel<Estimate> estimateList;
    private EstimateController control;

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
    
    
    //create Estimate list 
    @PostConstruct
    public void getEstimateListFromBase()
    {
        try 
        {
            control = new EstimateController();
            this.setEstimateList(new ListDataModel<>(control.getEstimateList()));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getEstimateList in EstimateBean: " + ex);
        }
    }

    public void setEstimateList(ListDataModel<Estimate> estimateList) {
        this.estimateList = estimateList;
    }

    public ListDataModel<Estimate> getEstimateList() {
        return estimateList;
    }
}
