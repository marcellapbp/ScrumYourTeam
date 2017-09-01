package br.com.scrumyourteam.domain;

public class Minute 
{
    private Project Project;
    private Meeting Meeting;
    private Sprint Sprint;
    private String Description;

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
    
}
