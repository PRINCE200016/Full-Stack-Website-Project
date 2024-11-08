package com.itrainu.Ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mypackage.SecondServlet;

@WebServlet(name = "WelcomeCtl", urlPatterns = {"/ctl/WelcomeCtl"})
public class WelcomeCtl extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        SecondServlet.forwardView(getView(),request , response);
    }

    protected String getView() {
        return DynamicView.WELCOME_VIEW;
    }
}
