package com.itrainu.Ctl;

public interface DynamicView {
public String APP_CONTEXT ="/FirstDynamic";
public String LAYOUT_VIEW = "/BaseLayout.jsp";

public String PAGE_FOLDER = "/jsp";
//JSP
public String INDEX_PAGE ="/index.jsp";
public String USER_LIST = PAGE_FOLDER + "/Userlist.jsp";
public String LOG_IN = PAGE_FOLDER + "/Login.jsp";
public String REGISTER = PAGE_FOLDER + "/Register.jsp";
public String WELCOME_VIEW = PAGE_FOLDER + "/WelcomeView.jsp";
//CTL
public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
public String WELCOME_CTL = APP_CONTEXT + "/ctl/WelcomeCtl";
public String LOGIN_CTL = APP_CONTEXT + "/ctl/LoginCtl";
//CSS STYLESHEET
public String STYLE_CSS = "/css";
public String WElCOME_CSS = STYLE_CSS + "/Welcome.css";
}
