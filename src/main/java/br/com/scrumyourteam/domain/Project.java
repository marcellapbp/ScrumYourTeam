package br.com.scrumyourteam.domain;

import java.util.Calendar;

/**
 *
 * @author marcella.pereira.a1
 */
public class Project 
{
    private int IdProject;
    private String NameProject;
    private String Description;
    private Calendar StartingDate;
    private int LengthInSprint;
    private int SprintLength;

    public int getIdProject() {
        return IdProject;
    }

    public void setIdProject(int IdProject) {
        this.IdProject = IdProject;
    }

    public String getNameProject() {
        return NameProject;
    }

    public void setNameProject(String NameProject) {
        this.NameProject = NameProject;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Calendar getStartingDate() {
        return StartingDate;
    }

    public void setStartingDate(Calendar StartingDate) {
        this.StartingDate = StartingDate;
    }

    public int getLengthInSprint() {
        return LengthInSprint;
    }

    public void setLengthInSprint(int LengthInSprint) {
        this.LengthInSprint = LengthInSprint;
    }

    public int getSprintLength() {
        return SprintLength;
    }

    public void setSprintLength(int SprintLength) {
        this.SprintLength = SprintLength;
    }
    
}
