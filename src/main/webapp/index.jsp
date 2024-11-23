<%@ page import="com.itrainu.Ctl.DynamicView" %>
<%@ page import="com.itrainu.Ctl.UserListCtl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index page</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css">
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/Img/—Pngtree—students successfully passed exam good_5163644.png">
    
</head>
<body class="body">
    <jsp:include page="/jsp/Header.jsp" />
    <br><br>
    <div id="thought">“I never dreamed about success. I worked for it.”</div>
    <div class="logo" align="center">
        <img src="<%=request.getContextPath()%>/Img/—Pngtree—students successfully passed exam good_5163644.png" alt="logo" align="middle" width="300" height="300" border="0" />
    </div>
    <br>
    <br>
    <hr>
   
    
    <div class="links"align="Center">
        <a href="LoginCtl">Login</a>
        <a href="<%=DynamicView.REGISTER%>">Register</a>
    </div>
    
    <hr>
   
    <div class="footer">
        <jsp:include page="/jsp/Footer.jsp" />
    </div>
</body>
</html>
