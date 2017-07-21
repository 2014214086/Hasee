package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

import model.User;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         String ff= request.getParameter("flag");
         if("checkUserName".equals(ff)){
        	 checkUserName(request,response);
         }
          
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决请求中文乱码
		request.setCharacterEncoding("utf-8");
		//获取flag值确定用户操作
		String f=request.getParameter("flag");
		if("register".equals(f)){
			//注册
			register(request,response);
		}
		if("login".equals(f)){
			//登录
			login(request,response);
		}
		if("password".equals(f)){
			//登录
			pass(request,response);
		}
	}

	public void pass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取请求参数
		String pwd=request.getParameter("pwd");
		String psw=request.getParameter("pass");
		 User u=new User();
		 u.setUserid(Integer.parseInt(request.getParameter("uid")));
		 u.setPassword(pwd);
		  UserDao dao=new UserDao();
		  boolean result=dao.password(u,psw);
			 request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}	
	
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取请求参数
		String user=request.getParameter("uname");
		String psw=request.getParameter("pass");
		
		//2. 实例化一个用户对象
		 User u=new User();
		 u.setUsername(user);
		 u.setPassword(psw);
		 
		//3.调用dao层
		  UserDao dao=new UserDao();
		  boolean result=dao.register(u);
		   //3.根据结果响应不同信息
		  User tmp=dao.login(u.getUsername(),u.getPassword());
			 if(user!=null){
				 //登录成功，存放在session中
				 request.getSession().setAttribute("user", tmp);
				 request.getRequestDispatcher("index.jsp").forward(request, response); 
			 }
		//4. 响应信息
		  
		  if(result){
			 //请求转发：
			 request.getRequestDispatcher("login.jsp").forward(request, response);
		  }else{
			 request.getRequestDispatcher("register.html").forward(request, response);  
		  }
		
	}	
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //1.获取请求参数
		 String uname=request.getParameter("txtUserName");
		 String psw=request.getParameter("txtPwd");
	   // 2.调用dao层
		 UserDao dao=new UserDao();
		 User user=dao.login(uname,psw);
	   //3.根据结果响应不同信息
		 if(user!=null){
			 //登录成功，存放在session中
			 request.getSession().setAttribute("user", user);
			 request.getRequestDispatcher("index.jsp").forward(request, response); 
		 }else{
			 request.getRequestDispatcher("login.jsp").forward(request, response); 
		 }
		
	}
	// 验证用户名是否重复
	public void checkUserName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决get请求乱码
		String user=request.getParameter("uname");
		user=new String(user.getBytes("iso-8859-1"),"utf-8");
		// 调用dao层
		UserDao dao=new UserDao();
		boolean result=dao.checkUserName(user);
		PrintWriter  out=response.getWriter();
		if(result){//没查到，可用
			out.print(0);
		}else{
			out.print(1);
		}
	}
	
}
