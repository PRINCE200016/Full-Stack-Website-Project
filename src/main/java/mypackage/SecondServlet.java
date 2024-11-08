package mypackage;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itrainu.Ctl.DynamicView;

@WebServlet("/secondservlet")
public class SecondServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SecondServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/webapp/Userlist.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String userList = request.getParameter("Userlist");
        doGet(request, response); // Or forward to another page if needed.
    }

    public static void forwardView(String page, HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(page); // Use the page parameter
        rd.forward(request, response);
    }
    
 
}

