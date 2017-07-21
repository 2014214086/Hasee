<%@ page language="java" import="java.util.*,model.*,dao.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>神船商城私网-提供神船笔记本、台式机、平板电脑、电脑外设等产品的伪装购买服务</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/mystyle.css" >
	<link rel="stylesheet" href="css/bottom-style.css" >
    <script type="text/javascript" src="js/jquery-1.8.0.js"></script></head>

  </head>
  
  <body>

   <div class="top">
   	<p><span>关注我们</span>
   	<img src="images/sina.png">
	<a><img src="images/baidu.png"></a>
	<a><img src="images/wechat.png"></a>
	<span style="float:right; margin-right:100px">
	
	<c:if test="${not empty sessionScope.user}">
	 欢迎登录：${sessionScope.user.username}
 	<a href="login.jsp">[退出]</a>
 	<a href="password.jsp">修改密码</a>|
 	<a href="order.jsp.jsp">查看订单</a>|
  	</c:if>
  	<c:if test="${empty sessionScope.user}">
	<a href="login.jsp">登录</a>|
	<a href="register.html">注册</a>|
	</c:if>
	<a href="http://www.hasee.com/Chinese/sell/network.aspx?cid=105001003004001">销售网点</a>|
	<a href="http://www.hasee.com/Chinese/service/network.aspx?cid=105001003003002">服务网点&nbsp;</a>
   </span>
   </p>
   </div>
   <div class="top-mid">
	<span style="float:left;"><a href="index.jsp"><img src="images/logo.png" style="margin-left: 155px; margin-top:20px;"></a></span>
	<span style="float:left;"><img src="images/order.png" style="height:60px;margin-top:20px; margin-left:20px; border-left:1px solid black;"></span>
	</div>
  
   
   <div class="box">
   <div style="width:20%; margin-left:10%; border-left:2px solid red;"> 全部商品</div>
   <div style="width:1268px; margin-left:10%; height:50px; background-color:#44C1F3; text-align:center; color:white; line-height:50px;">
    <div style="width:556px; height:50px; float:left;">
		宝贝   
	</div>
	<div style="width:175px; height:50px; float:left;">
		单价(元)       
	</div>
	<div style="width:175px; height:50px; float:left;">
		数量 
	</div>
	<div style="width:175px; height:50px; float:left;">
		金额(元)       
	</div>
	<div style="width:175px; height:50px; float:left;">
		地址       
	</div>
   </div>
   
   <% OrderDao dao=new OrderDao();
   	  User user=(User)request.getSession().getAttribute("user");
   	  int uid=-1;
      if(user!=null){
      uid=user.getUserid();
      }
      out.print(uid);
      request.setAttribute("cartList",dao.showOrder(uid));
      int a=0;
      double sum=0;
   %> 
   
   <div style="width:1268px; margin-left:118px; text-align:center; margin-top:0px; line-height:150px;">
   		<c:if test="${empty cartList}">
         <a href="index.jsp">暂无订单</a>
    	 </c:if>
     	<c:if test="${not empty cartList}">
         <ul class="goods_list">
		       <c:forEach var="goods" items="${cartList}">
		      <li>
		         <div style="width:1266px;  line-height:120px; text-align:center;">
		          <div style="width:560px; height:120px; border-left:1px solid #ccc; float:left;">
		          	<div style="float:left; wdith:320px;">
		          	<img src="gimg/${goods.picture}" style="width:120px;heitht:120px; margin-left:100px; margin-right:100px;">
		          	</div>
		          	<div style="float:left; wdith:236px; text-align:center">${goods.goodsname}</div>
		          	</div>
		          <div style="width:175px; height:120px; border-left:1px solid #ccc; float:left;">
		          	${goods.price}
		          </div>
		          <div style="width:175px; height:120px; border-left:1px solid #ccc; float:left;">
		          	${goods.quantity}
		          </div>
		          <div style="width:175px; height:120px; border-left:1px solid #ccc; float:left;">
		          	<% 
		          	List<Goods> l=(List<Goods>)request.getAttribute("cartList"); 
		          	Goods g=l.get(a);
		          	a++;
		          	double x=g.getPrice()*g.getQuantity();
		          	out.print(x);
		          	sum+=x;
		          	%>
		          </div>
		          <div style="width:175px; height:120px; border-left:1px solid #ccc; border-right:1px solid #ccc; float:left;">
		          	<a href="#">
		          	<c:if test="${not empty goods.addr}">
		          	${goods.addr}
		          	</c:if>
		          	<c:if test="${empty goods.addr}">
		          	添加地址(暂未实装)
		          	</c:if>
		          	</a>
		          </div>
		          </div>
		      </li>                  
		       </c:forEach>
		       <li>
		       <div style="width:1266px; line-height:50px; color:white; text-align:center;">
		       	  <div style="width:1266px; background-color:#DDDDDD; height:50px;float:left;">
		       	  <span style="font-size:25px; color:red;">总价：<%out.print(sum); %>元</span>
		       	  </div>
		        </div>
		       </li>
		  </ul>
	  
     </c:if>
     
   </div>
       
   </div>
    
   
   
<div class="bottom2">
    <ul class="u1">
       <li>
           
               <img src="images/bottom/b1-1.png">
               <p>官方品质保障</p>
           
       
       </li>
           
       <li>
           
               <img src="images/bottom/b1-2.png">
               <p>售后维修保障</p>
          
       </li>
       <li>
           
              <img src="images/bottom/b1-3.png">
               <p>新品抢先体验</p>
           
       </li>
       
        <li>
           
               <img src="images/bottom/b1-4.png">
               <p>个性化定制服务</p>
          
       </li>
       
        <li>
           
               <img src="images/bottom/b1-5.png">
               <p>7天退货 15天退换</p>
           </ul> 
       <li>&nbsp; <br></li>
    </ul>
<br>
<hr>
</div>
<div class="bottom3">
    <ul class="u2">
      <li>
        <img src="images/bottom/b2-1.png">
                    <font>帮助中心 </font>
                    <ul class="u3">
                    <li><a href="#">购物指南</a></li>
                    
                    <li><a href="#">支付方式</a></li>
                    
                    <li><a href="#">送货方式</a></li>
                    
                    </ul>
      </li>
         
      <li>
         <img src="images/bottom/b2-2.png">
                    <font>服务与支持</font>
                    <ul class="u3">
                    <li><a href="#">售后政策</a></li>
                    
                    <li><a href="#">服务网点</a></li>
                    
                    <li><a href="#">驱动下载</a></li>
                    
                    </ul>
                    
      </li>
      
      <li>
         <img src="images/bottom/b2-3.png">
                    <font>神船分舵</font>
                    <ul class="u3">
                    <li><a href="#">神船电脑京东官方旗舰店</a></li>
                    
                    <li><a href="#">神船电脑天猫旗舰店</a></li>
                   
                    <li><a href="#">神船手机天猫旗舰店</a></li>
                    
                    </ul>
      </li>
      
      <li>
         <img src="images/bottom/b2-4.png">
                    <font>关于我们</font>
                    <ul class="u3">
                    <li><a href="#">公司简介</a></li>
                    
                    <li><a href="#">加入我们</a></li>
                    
                    <li><a href="#">联系我们</a></li>
                    
                    </ul>
      </li>
      
      <li>
         <img src="images/bottom/b2-5.png">
                    <font>关注我们</font>
                    <ul class="u3">
                    <li><a href="#">新浪微博</a></li>
                    
                    <li><a href="#">官方微信</a></li>
                    
                    <li><a href="#">神州笔记本吧</a></li>
                    
                    </ul>
      </li>
      <li class="l1">
      <p><font class="font1">咨询热线：</font><font class="font2">400-886-2668 0512-36823277</font></p>
      <p><font class="font1">退换申请：</font><font class="font2">0512-36823277</font></p>
      <p><font class="font1">维修申请：</font><font class="font2">400-106-9999</font></p>
      <p><font class="font1">上午9:00-12:30 & 下午14:00-18:00（节假日除外）</font></p>
      </li>
    
    </ul>
</div>
 
  </body>
</html>
