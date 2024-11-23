package com.itrainu.Ctl;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itrainu.Bean.BaseBean;
import com.itrainu.Bean.RoleBean;
import com.itrainu.Bean.UserBean;
import com.itrainu.Ctl.BaseCtl;
import com.itrainu.Ctl.DynamicView;
import com.itrainu.Exception.ApplicationException;
import com.itrainu.Module.RoleModel;
import com.itrainu.Module.UserModule;
import com.itrainu.util.DataUtility;
import com.itrainu.util.DataValidator;
import com.itrainu.util.PropertyReader;

import mypackage.SecondServlet;

@WebServlet(name = "LoginCtl", urlPatterns = {"/LoginCtl"})
public class LoginCtl extends BaseCtl {
    private static final long serialVersionUID = 1L;
    public static final String OP_REGISTER = "Register";
    public static final String OP_SIGN_IN = "SignIn";
    public static final String OP_SIGN_UP = "SignUp";
    public static final String OP_LOG_OUT = "logout";

    private static final Logger log = Logger.getLogger(LoginCtl.class.getName());

    @Override
    protected boolean validate(HttpServletRequest request) {
        log.info("LoginCtl Method validate Started");

        boolean pass = true;
        String op = request.getParameter("operation");

        if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
            return pass;
        }

        if (DataValidator.isNull(request.getParameter("login"))) {
            request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
            pass = false;
        } else if (!DataValidator.isEmail(request.getParameter("login"))) {
            request.setAttribute("login", PropertyReader.getValue("error.email", "Login Id"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("password"))) {
            request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
            pass = false;
        }

        log.info("LoginCtl Method validate Ended");

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        log.info("LoginCtl Method populateBean Started");

        UserBean bean = new UserBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setLogin(DataUtility.getString(request.getParameter("login")));
        bean.setPassword(DataUtility.getString(request.getParameter("password")));

        log.info("LoginCtl Method populateBean Ended");

        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        log.info("LoginCtl Method doGet Started");

        HttpSession session = request.getSession(false);
        String op = DataUtility.getString(request.getParameter("operation"));

        if (OP_LOG_OUT.equals(op)) {
            session.invalidate();
            SecondServlet.setSuccessMessage("User Logged Out Successfully", request);
            SecondServlet.forwardView(getView(), request, response);
            return;
        }

        SecondServlet.forwardView(getView(), request, response);
        log.info("LoginCtl Method doGet Ended");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("LoginCtl Method doPost Started");

        HttpSession session = request.getSession(true);
        String op = DataUtility.getString(request.getParameter("operation"));

        UserModule model = new UserModule();
        RoleModel role = new RoleModel();

        if (OP_SIGN_IN.equalsIgnoreCase(op)) {
        	System.out.println("L ctl Do post done");
            UserBean bean = (UserBean) populateBean(request);

            try {
                bean = model.authenticate(bean.getLogin(), bean.getPassword());

                String str = request.getParameter("URI");

                if (bean != null) {
                    session.setAttribute("user", bean);
                    long roleId = bean.getRoleId();

                    RoleBean roleBean = role.findByPK(roleId);

                    if (roleBean != null) {
                        session.setAttribute("role", roleBean.getName());
                    }

                    if (str == null || str.isEmpty()) {
                        SecondServlet.redirect(DynamicView.WELCOME_CTL, request, response);
                    } else {
                        SecondServlet.redirect(str, request, response);
                    }
                    return;
                } else {
                    SecondServlet.setErrorMessage("Invalid Login Id and Password", request);
                    SecondServlet.setBean(bean, request);
                }

            } catch (ApplicationException e) {
                log.severe("ApplicationException: " + e.getMessage());
                SecondServlet.handleException(e, request, response);
                return;
            }
        } else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
            log.info("Redirecting to Sign Up page");
            // Redirect to sign-up page or handle sign-up operation
        }

        SecondServlet.forwardView(getView(), request, response);
        log.info("LoginCtl Method doPost Ended");
    }

    @Override
    protected String getView() {
        return DynamicView.LOG_IN;
    }
}
