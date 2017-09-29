
package br.com.scrumyourteam.domain;

/**
 * @author marcella.pereira
 * Date: 09/06/2017
 * Objective: Create a Bean to a Task(getters and setters)
 */
public class Task 
{
    private int IdTask;
    private Project Project;
    private Priority Priority;
    private Estimate Estimate;
    private String NameTask;
    private Task Task;
    private String Description;
    private String DoneDefinition;

    public int getIdTask() {
        return IdTask;
    }

    public void setIdTask(int IdTask) {
        this.IdTask = IdTask;
    }

    public Project getProject() {
        return Project;
    }

    public void setProject(Project Project) {
        this.Project = Project;
    }

    public Priority getPriority() {
        return Priority;
    }

    public void setPriority(Priority Priority) {
        this.Priority = Priority;
    }

    public Estimate getEstimate() {
        return Estimate;
    }

    public void setEstimate(Estimate Estimate) {
        this.Estimate = Estimate;
    }

    public String getNameTask() {
        return NameTask;
    }

    public void setNameTask(String NameTask) {
        this.NameTask = NameTask;
    }

    public Task getTask() {
        return Task;
    }

    public void setTask(Task Task) {
        this.Task = Task;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDoneDefinition() {
        return DoneDefinition;
    }

    public void setDoneDefinition(String DoneDefinition) {
        this.DoneDefinition = DoneDefinition;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getIdTask());
    }    
}
