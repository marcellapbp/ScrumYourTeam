package br.com.scrumyourteam.dao;

import br.com.scrumyourteam.domain.Estimate;
import br.com.scrumyourteam.persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcella
 * Date: 04/11/2017
 * Objective: To create Estimate CRUD
 */
public class EstimateDAO 
{
    private Connection conn;
    
    //set a connection every EstimateDAO instance
    public EstimateDAO()
    {
        this.conn = new ConnectionFactory().getConnection();
    }
    
    
    //it inserts a register to Estimate table
    public void addEstimate(Estimate estimate) throws SQLException 
    {
        //call estimate_insert(<name_estimate>, <estimate_value>, <project_id_project>)
        String sql = "call estimate_insert(?,?,?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setString(1, estimate.getNameEstimate());
            psmt.setInt(2, estimate.getEstimateValue());
            psmt.setInt(3, estimate.getProject().getIdProject());

            psmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute addEstimate in EstimateDAO: " + ex);
        }finally{
            conn.close();
        }
        
    }
    
    //it selects estimate list
    public List<Estimate> getEstimateList (int idProject) throws SQLException
    {
        //call estimate_select_list(<id_project>);
        String sql = "call estimate_select_list(?);";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) 
        {
            psmt.setInt(1, idProject);
            ResultSet rs = psmt.executeQuery();
            
            ArrayList<Estimate> estimateList = new ArrayList<>();
            while(rs.next())
            {
                Estimate estimate = new Estimate();
                estimate.setIdEstimate(rs.getInt("id_estimate"));
                estimate.setNameEstimate(rs.getString("name_estimate"));
                estimate.setEstimateValue(rs.getInt("estimate_value"));
                estimateList.add(estimate);
            }
            return estimateList;
        } catch (SQLException ex) {
            throw new RuntimeException("Error to execute getEstimateList in EstimateDAO: " + ex);
        }finally{
            conn.close();
        }
    }
    
}
