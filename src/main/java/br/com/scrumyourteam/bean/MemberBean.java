
package br.com.scrumyourteam.bean;


import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author marcella.pereira.a1
 * Date: 07/13/2017
 * Objective: To Intermediate comunication between View and Controller layers
 */
@ManagedBean (name = "memberBean")
@Dependent
@SessionScoped
public class MemberBean {

    public MemberBean() {
    }
    
}
