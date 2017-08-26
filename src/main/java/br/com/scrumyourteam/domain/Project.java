package br.com.scrumyourteam.domain;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Date: 11/04/2017
 * @author marcella.pereira.a1
 * Objective: Create a Bean to a Project(getters and setters)
 */
public class Project 
{
    private int IdProject;
    private String NameProject;
    private String Description;
    private LocalDate StartingDate;
    private int LengthInSprint;
    private int SprintLength;
    private String ProjectStatus;
    private String WeekdaySprint;

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

    public java.sql.Date getStartingDate() {
        return Date.valueOf(StartingDate);
    }
    
    public LocalDate getStartingDateLocalDate() {
        return StartingDate;
    }

    public void setStartingDate(LocalDate StartingDate) {
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

    public String getProjectStatus() {
        return ProjectStatus;
    }

    public void setProjectStatus(String ProjectStatus) {
        this.ProjectStatus = ProjectStatus;
    }

    public String getWeekdaySprint() {
        return WeekdaySprint;
    }

    public void setWeekdaySprint(String WeekdaySprint) {
        this.WeekdaySprint = WeekdaySprint;
    }
    
}
