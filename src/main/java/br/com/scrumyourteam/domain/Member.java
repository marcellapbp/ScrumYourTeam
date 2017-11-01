
package br.com.scrumyourteam.domain;

/**
 * @author marcella.pereira
 * Date: 07/18/2017
 * Objective: Create a Bean to a Meeting(getters and setters)
 */
public class Member 
{
     private Project Project;
     private User User;
     private Role Role;
     //private Date StartingDate;

    public Project getProject() {
        return Project;
    }

    public void setProject(Project Project) {
        this.Project = Project;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public Role getRole() {
        return Role;
    }

    public void setRole(Role Role) {
        this.Role = Role;
    }
}
