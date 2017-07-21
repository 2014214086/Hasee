<%@ page language="java" import="java.util.*,model.*,dao.*,control.*" pageEncoding="utf-8"%>
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
	<style>
      ul{
         margin:0;
         padding:0;
         list-style:none;
      }
      .box{
            width:1586px;
            margin:0 auto;
            position:relative;
            overflow:hidden;
            
        }
      .pic{
         width:300%;
         height:450px;
         position:absolute;
       }  
      .pic li{
         float:left;
      }
      .pic li img{
          width:1586px;
          height:450px;
          vertical-align:middle; /*去掉图片空白 */
       }
      .circle{
          width:100px;
          height:10px;
          position:absolute;
          left:50%;
          bottom:5px;
          margin-left:-30px;
       }
      .circle li{
         float:left;
         width:10px;
         height:10px;
         border-radius:5px;
         background:rgba(0,0,0,0.6);
         margin-right:10px;
         cursor:pointer;/*光标为小手 */
      } 
      
      
      .menuitem{
      	float:left;
      	width:150px;
      	height:200px; 
      	border:2px solid #eee; 
      	margin-left:20px;
      	margin-top:20px;
      	margin-right:20px;
      }
      
   </style>
   <script type="text/javascript" src="js/jquery-1.8.0.js"></script></head>
<script>
  $(function(){
   $(".circle li").eq(0).css({background:"orange"});
    
     var i=0;// 当前图片下标     
     //自定义函数 ：轮播图片
     function lunbo(){
      if(i>=3){
        i=0;
      }
       $(".pic").animate({left:-1586 *i},500);
       $(".circle li").eq(i).css({background:"orange"});
        $(".circle li").eq(i).siblings().css({background:"rgba(0,0,0,0.6)"});
       i++;
     }
    
      var tt=setInterval(lunbo,3000);
      
      $(".box").mouseenter(function(){//光标进入
        clearInterval(tt);
      }).mouseleave(function(){// 光标移开
        tt=setInterval(lunbo,3000);
      }); 
      
     // 点击小圆点改变大图
     $(".circle li").click(function(){
         var currentIndex= $(this).index();
         $(this).css({background:"orange"});
         $(this).siblings().css({background:"rgba(0,0,0,0.6)"});  
         
         i=currentIndex;
         $(".pic").animate({left:-1586*i},500);
         
     });
     
  });

</script>  
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
	<span>
	<div class="search">
	<form action="ProductServlet" method="get">
	 <input type="hidden" name="flag" value="search">
	 <input type="text" name="search" " class="search" placeholder="搜索你想要的产品">
	 <input type="submit" value="" style="width:35px; border:1px solid #ccc; border-left:0; height:35px; background-image:url(images/search.png); background-size:35px 35px; ">
	</form>
	</div>
	</span>
	<span>
	<a href="showCart.jsp">
	<div class="shopcar"> <img style="margin-top:5px;" src="images/shopcar.png"><span style="margin-top:10px;">购物车</span></div>
	</a>
	</span>
	<span style="margin-left:50px;"><img src="images/phone.png"></span>   	
   </div>
   <div class="menu">
   <div class="menulist">
   	<div class="item_First">
   	<span class="classification">全部商品分类</span>
   		<div class="d2">
	   		<ul>
	   		
	   		<% CatalogDao dao=new CatalogDao();
     		request.setAttribute("catalogList",dao.getCatalogs());
 			 int a=1;
 			 %> 
   			<c:forEach var="catalog" items="${catalogList}" >
   			<li><div class="item_second">${catalog.catalogname}<div class="item_third">
     		<%MenuDao md=new MenuDao();
     		  request.setAttribute("menuList",md.getMenu(a));
     		  a++;%>
     		<c:forEach var="menu" items="${menuList}" >
     		<div class="menuitem">
     		<img src="menuimg/${menu.picture}.png" style="width:150px;heitht:150px;">
     		<span>${menu.text}</span>
     		
     		</div>
     		</c:forEach>
     		
     		</div></div></li>
   			</c:forEach>

	   		</ul>
   		</div>
   		
   	</div>
   	<div class="item_First_2"><span><a href=index.jsp>首页</a></span></div>
   	<div class="item_First_2"><span><a href=index.jsp>新品推荐</a></span></div>
   	<div class="item_First_2"><span><a href=index.jsp>PCpad</a></span></div>
   	<div class="item_First_2"><span><a href=index.jsp>神船手机</a></span></div>
   	<div class="item_First_2"><span><a href=index.jsp>今日秒杀</a></span></div>
   	<div class="item_First_2"><span><a href=index.jsp>服务支持</a></span></div>
   </div>
   </div>
   
   
   <div class="box">
         <div style="width:80%; margin-left:10%;">
   		<c:if test="${empty goodsList}">
                	 未搜索到符合要求的产品
    	 </c:if>
     	<c:if test="${not empty goodsList}">
         <ul class="goods_list">
		       <c:forEach var="goods" items="${goodsList}">
		      <li>
		         <div style="width:280px;
					height:370px;
					margin-left:5px;
					margin-right:5px;
					border:1px solid #ccc;
					float:left;
					margin-top:10px;
					text-align:center;">
		          <img style="width:280px; height:280px;" src="gimg/${goods.picture}">
		          <div style="height:30px; line-height:30px;">商品名：${goods.goodsname}</div>
		         
		          <div style="width:139px; height:29px; line-height:29px; border-top:1px solid #ccc; border-right:1px solid #ccc; float:left;">价格：${goods.price}</div>
		          <div style="width:139px; height:29px; line-height:29px; border-top:1px solid #ccc; border-left:1px solid #ccc; float:left;">库存：${goods.amount}</div>
		          
		          <a href="CartServlet?gid=${goods.goodsid }&flag=addCart">
		          <div style="width:280px; height:30px; line-height:30px; border-top:1px solid #ccc; float:left; background-color:#D61818; color:white;">加入购物车</div>
		          </a>
		          </div>
		      </li>                  
		       </c:forEach>
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
       </li>
    </ul>
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
