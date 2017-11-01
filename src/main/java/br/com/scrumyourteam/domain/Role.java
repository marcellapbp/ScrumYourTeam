
package br.com.scrumyourteam.domain;

/**
 * @author marcella.pereira
 * Date: 07/13/2017
 * Objective: Create a Bean to a Role(getters and setters)
 */
public class Role 
{
    private int IdRole;
    private String NameRole;
    private Project project;

    public int getIdRole() {
        return IdRole;
    }

    public void setIdRole(int IdRole) {
        this.IdRole = IdRole;
    }

    public String getNameRole() {
        return NameRole;
    }

    public void setNameRole(String NameRole) {
        this.NameRole = NameRole;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
