<%@ page import="com.itrainu.Ctl.DynamicView" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/Login.css">
</head>
<body>
    <div class="container">
        <h1>Register</h1>
        <form action="RegisterServlet" method="post">
            <label for="username">name</label>
            <input type="text" id="username" name="username" required><br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit">Register</button>
        </form>
        <p><a href="<%=request.getContextPath()%>/index.jsp">Back to Home</a></p>
        <%-- Display error message if registration fails --%>
        <% String error = request.getParameter("error"); if (error != null && error.equals("1")) { %>
            <p style="color: red;">Registration failed. Please try again.</p>
        <% } %>
    </div>
</body>
</html>
