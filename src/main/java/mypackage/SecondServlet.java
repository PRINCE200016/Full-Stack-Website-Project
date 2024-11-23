package mypackage;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itrainu.Bean.BaseBean;
import com.itrainu.Bean.RoleBean;
import com.itrainu.Bean.UserBean;
import com.itrainu.Doa.UserDao;
import com.itrainu.Doa.UserDaoImpl;
import com.itrainu.Exception.ApplicationException;
import com.itrainu.Module.RoleModel;
import com.itrainu.Module.UserModule;
import com.itrainu.util.DataUtility;
import com.itrainu.util.DataValidator;
import com.itrainu.util.PropertyReader;

@WebServlet("/login")
public class SecondServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final UserDao userdao = new UserDaoImpl();
    private static final Logger log = Logger.getLogger(SecondServlet.class.getName());

    public SecondServlet() {
        super();
    }

    // Validation method for login form
    private boolean validate(HttpServletRequest request) {
        log.info("SecondServlet Method validate Started");
        boolean pass = true;

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

        log.info("SecondServlet Method validate Ended");
        return pass;
    }

    // Populating the UserBean from request parameters
    private BaseBean populateBean(HttpServletRequest request) {
        log.info("SecondServlet Method populateBean Started");

        UserBean bean = new UserBean();
        bean.setLogin(DataUtility.getString(request.getParameter("login")));
        bean.setPassword(DataUtility.getString(request.getParameter("password")));

        log.info("SecondServlet Method populateBean Ended");
        return bean;
    }

    // Handling GET requests (e.g., logout)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("SecondServlet Method doGet Started");

        String op = DataUtility.getString(request.getParameter("operation"));
        HttpSession session = request.getSession(false);

        if ("logout".equalsIgnoreCase(op) && session != null) {
            session.invalidate(); // End the user session
            request.setAttribute("successMessage", "User logged out successfully.");
            forwardView("/jsp/Login.jsp", request, response);
            return;
        }

        forwardView("/jsp/Login.jsp", request, response); // Default to login page
        log.info("SecondServlet Method doGet Ended");
    }

    // Handling POST requests (e.g., login form submission)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("SecondServlet Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));
        HttpSession session = request.getSession();

        if ("SignIn".equalsIgnoreCase(op)) {
            UserBean bean = (UserBean) populateBean(request);

            try {
                UserModule model = new UserModule();
                RoleModel role = new RoleModel();

                bean = model.authenticate(bean.getLogin(), bean.getPassword());

                if (bean != null) {
                    session.setAttribute("user", bean);
                    long roleId = bean.getRoleId();

                    RoleBean roleBean = role.findByPK(roleId);
                    if (roleBean != null) {
                        session.setAttribute("role", roleBean.getName());
                    }

                    // Redirect to WelcomeView.jsp after successful login
                    response.sendRedirect(request.getContextPath() + "/jsp/WelcomeView.jsp");
                    return;
                } else {
                    request.setAttribute("errorMessage", "Invalid Login Id or Password");
                    setBean(bean, request);
                }

            } catch (ApplicationException e) {
                log.severe("ApplicationException: " + e.getMessage());
                handleException(e, request, response);
                return;
            }
        }

        forwardView("/jsp/Login.jsp", request, response); // Redirect back to login on failure
        log.info("SecondServlet Method doPost Ended");
    }

    // Utility method to forward to a specific JSP view
    public static void forwardView(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    // Utility method to handle exceptions
    public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("exception", e);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/ErrorView.jsp"); // Redirect to a custom error page
        rd.forward(request, response);
    }

    // Utility method to set a bean as a request attribute
    public static void setBean(BaseBean bean, HttpServletRequest request) {
        request.setAttribute("bean", bean);
    }

	public static void setErrorMessage(String string, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

    // Redirect to another URL
    public static void redirect(String url, HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.sendRedirect(request.getContextPath() + url);
    }

    

    // Set a success message in the request scope
    public static void setSuccessMessage(String message, HttpServletRequest request) {
        request.setAttribute("successMessage", message);
    }

  
    

   
    }


