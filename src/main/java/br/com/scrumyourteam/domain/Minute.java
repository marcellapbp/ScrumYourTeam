package br.com.scrumyourteam.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Minute 
{
    private Project Project;
    private Meeting Meeting;
    private Sprint Sprint;
    private String Description;
    private LocalDate PostingDate;

    public Project getProject() {
        return Project;
    }

    public void setProject(Project Project) {
        this.Project = Project;
    }

    public Meeting getMeeting() {
        return Meeting;
    }

    public void setMeeting(Meeting Meeting) {
        this.Meeting = Meeting;
    }

    public Sprint getSprint() {
        return Sprint;
    }

    public void setSprint(Sprint Sprint) {
        this.Sprint = Sprint;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public java.sql.Date getPostingDate() {
        return Date.valueOf(PostingDate);
    }
    
    public LocalDate getPostingDateLocalDate() {
        return PostingDate;
    }


    public void setPostingDate(LocalDate PostingDate) {
        this.PostingDate = PostingDate;
    }
    
}
