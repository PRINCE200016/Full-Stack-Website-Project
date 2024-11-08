<%@ page import="com.itrainu.Ctl.DynamicView" %>
<%@ page import="com.itrainu.Ctl.UserListCtl" %>
<%@ page import="com.itrainu.Ctl.WelcomeCtl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome page</title>
      <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/Img/welcomeViewlogo.png">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/WelcomeView.css" >
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
</head>
<body class="body">

<jsp:include page="/jsp/Header.jsp" />
<h2>Welcome Student</h2>
 <div class="logo" align="center">
        <img src="<%=request.getContextPath()%>/Img/welcomeViewlogo.png" alt="logo" align="middle" width="300" height="300" border="0" />
    </div>
    <br><br>
    <h1 align="Center">
        <font size="10px" color="red">
          
                <a href="<%=DynamicView.USER_LIST_CTL%>">Online Data System</a>
            </font>
        </h1>
    </form>
    </div>
    </div>
    
    <jsp:include page="/jsp/Footer.jsp" />
</body>
</html>
