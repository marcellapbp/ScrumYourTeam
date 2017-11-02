
package br.com.scrumyourteam.domain;

public class TaskResponsible 
{
    private Member member;
    private Task task;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
}