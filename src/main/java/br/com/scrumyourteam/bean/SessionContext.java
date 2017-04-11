package br.com.scrumyourteam.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @author marcella.pereira.a1
 * data: 04/11/2017
 * Objective: To centralize the way of getting session in one method
 */
@Named(value = "sessionContext")
@Dependent
public class SessionContext 
{
    public ExternalContext currentExternalContext(){
         if (FacesContext.getCurrentInstance() == null){
             throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
         }else{
             return FacesContext.getCurrentInstance().getExternalContext();
         }
      }

    
}
