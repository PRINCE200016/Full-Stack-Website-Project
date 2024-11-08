package com.itrainu.Ctl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itrainu.Bean.UserBean;
import com.itrainu.Module.UserModule;

@WebServlet(name = "UserListCtl", urlPatterns = {"/ctl/UserListCtl"})
public class UserListCtl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserBean bean = new UserBean();
        UserModule module = new UserModule();
        try {
            List<UserBean> list = module.search(bean);
            if (list != null && !list.isEmpty()) {
                System.out.println("List size in servlet: " + list.size());
                request.setAttribute("Userlist", list);
            } else {
                System.out.println("No data found.");
                request.setAttribute("Userlist", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Userlist.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
