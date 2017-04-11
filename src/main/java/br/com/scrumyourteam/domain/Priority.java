package br.com.scrumyourteam.domain;

/**
 * @author marcella.pereira
 * Date: 04/11/2017
 * Objective: Create a Bean to a Priority(getters and setters)
 */
public class Priority 
{
    private int IdPriority;
    private String NamePriority;
    private int PriorityValue;

    public int getIdPriority() {
        return IdPriority;
    }

    public void setIdPriority(int IdPriority) {
        this.IdPriority = IdPriority;
    }

    public String getNamePriority() {
        return NamePriority;
    }

    public void setNamePriority(String NamePriority) {
        this.NamePriority = NamePriority;
    }

    public int getPriorityValue() {
        return PriorityValue;
    }

    public void setPriorityValue(int PriorityValue) {
        this.PriorityValue = PriorityValue;
    }
}
