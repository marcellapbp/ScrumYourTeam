package br.com.scrumyourteam.controller;

import br.com.scrumyourteam.dao.EstimateDAO;
import br.com.scrumyourteam.domain.Estimate;
import java.sql.SQLException;
import java.util.List;

/**
 * @author marcella
 * Date: 04/11/2017
 * Objective: To Intermediate comunication between DAO and ManagedBean layers
 */
public class EstimateController 
{
    private EstimateDAO dao;
    
    //it selects estimate list
    public List<Estimate> getEstimateList (int idProject) throws SQLException 
    {
        dao = new EstimateDAO();
        return dao.getEstimateList(idProject);
    }
}
