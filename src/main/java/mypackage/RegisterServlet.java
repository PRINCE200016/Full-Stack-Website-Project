package mypackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itrainu.Doa.UserDao;
import com.itrainu.Doa.UserDaoImpl;
import com.itrainu.Module.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String department = request.getParameter("department"); // Example additional field

        // Print values to debug
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department); // Example additional field

        if (username == null || password == null || email == null || department == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/Register.jsp?error=missingFields");
            return;
        }

        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setDepartment(department); // Example additional field
        // Initialize other fields as necessary
        user.setMobileNo("default_mobile");
        user.setDob(new java.util.Date());
        user.setGender("default_gender");
        user.setLastName("default_lastname");
        user.setLock("default_lock");
        user.setUnsuccessfulLogin(0);
        user.setRoleId(1);
        user.setCreatedBy("admin");
        user.setConfirmPassword(password); // Example default matching password

        UserDao userDao = new UserDaoImpl();
        if (userDao.addUser(user)) {
            response.sendRedirect(request.getContextPath() + "/jsp/Register.jsp?registration=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/jsp/Register.jsp?error=1");
        }
    }
}
