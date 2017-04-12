package br.com.scrumyourteam.domain;

/**
 * @author marcella.pereira
 * Date: 04/11/2017
 * Objective: Create a Bean to a Estimate(getters and setters)
 */
public class Estimate 
{
    private int IdEstimate;
    private String NameEstimate;
    private int EstimateValue;
    private Project project;

    public int getIdEstimate() {
        return IdEstimate;
    }

    public void setIdEstimate(int IdEstimate) {
        this.IdEstimate = IdEstimate;
    }

    public String getNameEstimate() {
        return NameEstimate;
    }

    public void setNameEstimate(String NameEstimate) {
        this.NameEstimate = NameEstimate;
    }

    public int getEstimateValue() {
        return EstimateValue;
    }

    public void setEstimateValue(int EstimateValue) {
        this.EstimateValue = EstimateValue;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
