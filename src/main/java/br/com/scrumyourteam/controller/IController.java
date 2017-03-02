package br.com.scrumyourteam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marcella
 * Date: 03/02/2017
 * Objective: Create a interface to all Controllers follow
 */

public interface IController {
    
    public void execute (HttpServletRequest request, HttpServletResponse response);
    
}
