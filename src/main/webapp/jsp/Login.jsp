<%@ page import="com.itrainu.Doa.UserDaoImpl" %>
<%@ page import="com.itrainu.Doa.UserDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/Login.css">
</head>
<body>
    <div class="container">
        <h1>Login</h1>

        <%
        // Fetching user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // DAO object to validate the user
        UserDao userdao = new UserDaoImpl();

        if (username != null && password != null) {
            // Validate the user
            if (userdao.isValidUser(username, password)) {
                session.setAttribute("username", username); // Set username in session
                response.sendRedirect(request.getContextPath() + "/jsp/WelcomeView.jsp"); // Redirect to Welcome page
                return; // Stop further processing
            } else {
                // Show error if invalid credentials
                out.println("<p style='color: red;'>Invalid username or password!</p>");
            }
        }
        %>

        <!-- Login Form -->
        <form method="post" action="<%=request.getRequestURI()%>">
            <input type="hidden" name="action" value="login">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>

        <p><a href="<%=request.getContextPath()%>/index.jsp">Back to Home</a></p>

        <!-- Error Messages -->
        <% 
        String error = request.getParameter("error");
        if (error != null && error.equals("1")) { 
        %>
            <p style="color: red;">Invalid name or password. Please try again.</p>
        <% 
        }

        String registrationSuccess = request.getParameter("registration");
        if (registrationSuccess != null && registrationSuccess.equals("success")) { 
        %>
            <p style="color: green;">Your registration is successful. Please login.</p>
        <% 
        } 
        %>
    </div>
</body>
</html>
