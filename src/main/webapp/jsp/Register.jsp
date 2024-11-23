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
        <form action="<%=request.getContextPath()%>/register" method="post">
            <label for="username">Name:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            <label for="department">Department:</label> <!-- Example additional field -->
            <input type="text" id="department" name="department" required><br>
            <button type="submit">Register</button>
        </form>
        <p><a href="<%=request.getContextPath()%>/index.jsp">Back to Home</a></p>
        <%-- Display error message if registration fails --%>
        <% String error = request.getParameter("error"); if (error != null && error.equals("1")) { %>
            <p style="color: red;">Registration failed. Please try again.</p>
        <% } %>
        <% String missingFields = request.getParameter("error"); if (missingFields != null && missingFields.equals("missingFields")) { %>
            <p style="color: red;">Please fill out all required fields.</p>
        <% } %>
        <%-- Display success message if registration succeeds --%>
        <% String registration = request.getParameter("registration"); if (registration != null && registration.equals("success")) { %>
            <p style="color: green;">Registration successful! Please <a href="<%=request.getContextPath()%>/jsp/Login.jsp">log in</a>.</p>
        <% } %>
    </div>
</body>
</html>
