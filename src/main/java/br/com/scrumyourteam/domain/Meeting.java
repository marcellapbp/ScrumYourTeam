
package br.com.scrumyourteam.domain;

/**
 * @author marcella.pereira
 * Date: 07/18/2017
 * Objective: Create a Bean to a Meeting(getters and setters)
 */
public class Meeting 
{
    private int IdMeeting;
    private Project Project;
    private String NameMeeting;
    private boolean FlagNotification;

    public int getIdMeeting() {
        return IdMeeting;
    }

    public void setIdMeeting(int IdMeeting) {
        this.IdMeeting = IdMeeting;
    }

    public Project getProject() {
        return Project;
    }

    public void setProject(Project Project) {
        this.Project = Project;
    }

    public String getNameMeeting() {
        return NameMeeting;
    }

    public void setNameMeeting(String NameMeeting) {
        this.NameMeeting = NameMeeting;
    }

    public boolean isFlagNotification() {
        return FlagNotification;
    }

    public void setFlagNotification(boolean FlagNotification) {
        this.FlagNotification = FlagNotification;
    }
    
    
}
