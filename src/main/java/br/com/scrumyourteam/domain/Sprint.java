package br.com.scrumyourteam.domain;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author marcella.pereira
 * Date: 08/18/2017
 * Objective: Create a Bean to a Sprint(getters and setters)
 */
public class Sprint 
{
    private int IdSprint;
    private Project project;
    private int SprintNumber;
    private LocalDate StartingDate;
    private LocalDate EndingDate;

    public int getIdSprint() {
        return IdSprint;
    }

    public void setIdSprint(int IdSprint) {
        this.IdSprint = IdSprint;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getStartingDateLocalDate() {
        return StartingDate;
    }
    
    public java.sql.Date getStartingDate() {
        return Date.valueOf(StartingDate);
    }
    
    public void setStartingDate(LocalDate StartingDate) {
        this.StartingDate = StartingDate;
    }

    public java.sql.Date getEndingDate() {
        return Date.valueOf(EndingDate);
    }

    public LocalDate getEndingDateLocalDate() {
        return EndingDate;
    }
    public void setEndingDate(LocalDate EndingDate) {
        this.EndingDate = EndingDate;
    }

    public int getSprintNumber() {
        return SprintNumber;
    }

    public void setSprintNumber(int SprintNumber) {
        this.SprintNumber = SprintNumber;
    }
    
}
