<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" type="text/css" href="css/styles.css">
  </head>
  
  <body>
  <% request.getSession().setAttribute("user",null); %>

	<div class="header2">
		<div class="head_in">
			<a href="index.jsp" class="logo2"></a>
			<p class="head_welcome">欢迎登录</p>
		</div>
	</div>
		<div class="login">
		<div class="login_d">
			<h2>欢迎您，尊贵的神船会员</h2>
			
     	<form action="UserServlet?flag=login" method="post">
			<p class="login_p1"><input name="txtUserName" type="text" placeholder="请在这儿输入你的账号" />
			</p>
			<p class="login_p2"><input name="txtPwd" type="password" id="txtPwd" /> 
			</p>
            <input type="submit" value="登录" class="login_btn" />
			<p class="login_p3"><a href="register.html" >没有账号？立即注册<i>&gt;&gt;</i></a></p>
			</form>
		</div>
		</div>
  </body>
</html>
