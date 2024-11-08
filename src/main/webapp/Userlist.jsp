<%@ page import="java.sql.*, mypackage.DatabaseConnection" %>
<%@ page import = "mypackage.SecondServlet" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Userlist</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/Userlist.css">
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/Img/Userlistbackground.jpg">
</head>
<body>
    <jsp:include page="/jsp/Header.jsp" />
    <h2>Employee Data</h2>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Department</th>
            <th>Password</th>
            <th>Mobile No</th>
            <th>DOB</th>
            <th>Gender</th>
            <th>Last Name</th>
            <th>Lock</th>
            <th>Unsuccessful Login</th>
            <th>Role ID</th>
            <th>Created By</th>
        </tr>
        <%
            String url = "jdbc:mysql://localhost:3306/itrainu";
            String username = "root";
            String password = "Rajawat2000";
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, username, password);
                stmt = conn.createStatement();
                String query = "SELECT * FROM employee";
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("Name");
                    String department = rs.getString("department");
                    String passwordCol = rs.getString("PASSWORD");
                    String mobileNo = rs.getString("MOBILE_NO");
                    String dob = rs.getString("DOB");
                    String gender = rs.getString("GENDER");
                    String lastName = rs.getString("LASTNAME");
                    String lock = rs.getString("USER_LOCK");
                    String unsuccessfulLogin = rs.getString("UNSUCCESFULL_LOGIN");
                    String roleId = rs.getString("ROLE_ID");
                    String createdBy = rs.getString("CREATED_BY");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td><%= department %></td>
            <td><%= passwordCol %></td>
            <td><%= mobileNo %></td>
            <td><%= dob %></td>
            <td><%= gender %></td>
            <td><%= lastName %></td>
            <td><%= lock %></td>
            <td><%= unsuccessfulLogin %></td>
            <td><%= roleId %></td>
            <td><%= createdBy %></td>
        </tr>
        <%
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
                if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
                if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
            }
        %>
    </table>
    <jsp:include page="/jsp/Footer.jsp" />
</body>
</html>
