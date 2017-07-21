<%@ page language="java" import="java.util.*,dao.*,model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'password.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    
    <link rel="stylesheet" type="text/css" href="css/RegisterStyle.css" />
	<script src="js/jquery-1.8.0.js"></script>
    
    
<style type="text/css">
	.code{
	height:30px;
	width:60px;
	text-decoration:none;
	font-family:Arial,宋体;
	font-style:italic;
	color:green;
	border:0;
	letter-spacing:3px;
	font-weight:bolder;
	padding:auto; 
	}
</style>
<script language="javascript" type="text/javascript">
     $(function(){
     $("#pwd").blur(function(){
     var vv=$("#pwd").val();
       if(vv!=""){
		flag=true;
	}
	else{
		$("#msg_user").html("原密码不能为空");
		$("#msg_user").css({color:"red"});
	}
    })
     
     
     
     createCode();
     //第一次输入密码格式正确
        $("#psw").blur(function(){
        var psw=$("#psw").val();
       
	if(psw.length<6||psw.length>20){
		$("#msg_psw").html("密码应为6到20位之间!");
		$("#msg_psw").css({color:"#F20D0D"});
	}else{
		$("#msg_psw").html("密码正确");
		$("#msg_psw").css({color:"#39EA0D"});
		
	}        
        });
        
        
        //判断两次密码是否相同
      $("#repsw").blur(function(){
            var psw=$("#psw").val();
            var repsw=$(this).val();
            if(psw==""){
            	$("#msg_repsw").html("请先输入密码!");
            	$("#msg_repsw").css({color:"#F20D0D"});
            }
            else if(psw!=repsw){
            $("#msg_repsw").html("两次密码不一致!");
            $("#msg_repsw").css({color:"#F20D0D"});
            }else{
               $("#msg_repsw").html("密码一致");
		       $("#msg_repsw").css({color:"#39EA0D"});
            }
        })
     })

</script>


        
  </head>
  
  <body>
    <div class="h">
		<div class="h1">
			<a href="index.jsp"  class="l1"></a>
			<p class="h2">修改密码</p>
		</div>
	</div>
	<%
		User user=(User)request.getSession().getAttribute("user");
		int uid=user.getUserid();
	%>
	<form action="UserServlet?flag=password&uid=<%out.print(uid); %>" method="post" onsubmit="checkForm()" >
		<div class="m">
			<div class="p">
				<ul>
					<li><label for="user">原密码：</label><p class="p1"><input type="password" id="user" name="pwd" onBlur="checkUser_reg()" placeholder="请输入原始密码"></p>
					<span id="msg_user"></span>
					</li>
		
					<li><label for="psw">新密码：</label><p class="p2"><input type="password" id="psw" name="pass" placeholder="请输入密码"></p>
					<span id="msg_psw">* 6-20位字符，建议由字母，数字和符号两种以上组合</span>
					</li>
		
					<li><label for="repsw">请确认密码：</label><p class="p2"><input type="password" id="repsw" name="pass" placeholder="请输入密码"></p>
					<span id="msg_repsw">* 请再次输入密码</span>
					</li>
		
					
					<li><br><label> &nbsp;</label>
					<input type="submit" value="提交" class="bt" id="bt"/></li>
               		<li><div align="left"><label>&nbsp;</label></div></li>
				</ul>
			</div>
		</div>
	</form>
  </body>
</html>
