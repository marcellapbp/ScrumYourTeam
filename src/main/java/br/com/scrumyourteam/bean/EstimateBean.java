package br.com.scrumyourteam.bean;

import br.com.scrumyourteam.controller.EstimateController;
import br.com.scrumyourteam.domain.Estimate;
import br.com.scrumyourteam.domain.Project;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

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
    private HttpServletRequest request;
    private SessionContext context;
    private EstimateController control;
    private Estimate estimate;

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
    public ListDataModel<br.com.scrumyourteam.domain.Estimate> getEstimateListFromBase()
    {
        try 
        {
            int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
            control = new EstimateController();
            return new ListDataModel<>(control.getEstimateList(idProject));
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getEstimateList in EstimateBean: " + ex);
        }
    }

    //it takes data from estimate-list.xhtml dialog using the session
    //it sends to EstimateController
    public void estimateAdd() throws SQLException, IOException 
    {
        context = new SessionContext();
        request = (HttpServletRequest)context.currentExternalContext().getRequest();
        
        estimate = new Estimate();
        
        String nameEstimate = request.getParameter("newEstimateForm:nameEstimate");
        int estimateValue = Integer.parseInt(request.getParameter("newEstimateForm:estimateValue"));
        
        estimate.setNameEstimate(nameEstimate);
        estimate.setEstimateValue(estimateValue);
        estimate.setProject(new Project());
        estimate.getProject().setIdProject((int) context.currentExternalContext().getSessionMap().get("idProject"));
        
        
         control = new EstimateController();
         control.estimateAdd(estimate);
         
         getEstimateList();
    }
    
    
    //It was need to every time create a new class
    //this way will not get information from others project
    //because there is no more objects in the memory
    public ListDataModel<Estimate> getEstimateList() {
        return getEstimateListFromBase();
    }
    
    public List<Estimate> getArrayEstimatetList() throws SQLException {
        int idProject = (int) context.currentExternalContext().getSessionMap().get("idProject");
        control = new EstimateController();
        return control.getEstimateList(idProject);
    }
}
