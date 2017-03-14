package br.com.scrumyourteam.domain;

/**
 *
 * @author marcella
 * Date: 03/02/2017
 * Objective: Create a Bean to a User
 */

public class User 
{
    private int IdUser;
    private String NameUser;
    private String Login;
    private String Password;

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public String getNameUser() {
        return NameUser;
    }

    public void setNameUser(String NameUser) {
        this.NameUser = NameUser;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
