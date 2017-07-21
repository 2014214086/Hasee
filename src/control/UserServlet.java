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
		//���������������
		request.setCharacterEncoding("utf-8");
		//��ȡflagֵȷ���û�����
		String f=request.getParameter("flag");
		if("register".equals(f)){
			//ע��
			register(request,response);
		}
		if("login".equals(f)){
			//��¼
			login(request,response);
		}
		if("password".equals(f)){
			//��¼
			pass(request,response);
		}
	}

	public void pass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.��ȡ�������
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
		// 1.��ȡ�������
		String user=request.getParameter("uname");
		String psw=request.getParameter("pass");
		
		//2. ʵ����һ���û�����
		 User u=new User();
		 u.setUsername(user);
		 u.setPassword(psw);
		 
		//3.����dao��
		  UserDao dao=new UserDao();
		  boolean result=dao.register(u);
		   //3.���ݽ����Ӧ��ͬ��Ϣ
		  User tmp=dao.login(u.getUsername(),u.getPassword());
			 if(user!=null){
				 //��¼�ɹ��������session��
				 request.getSession().setAttribute("user", tmp);
				 request.getRequestDispatcher("index.jsp").forward(request, response); 
			 }
		//4. ��Ӧ��Ϣ
		  
		  if(result){
			 //����ת����
			 request.getRequestDispatcher("login.jsp").forward(request, response);
		  }else{
			 request.getRequestDispatcher("register.html").forward(request, response);  
		  }
		
	}	
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //1.��ȡ�������
		 String uname=request.getParameter("txtUserName");
		 String psw=request.getParameter("txtPwd");
	   // 2.����dao��
		 UserDao dao=new UserDao();
		 User user=dao.login(uname,psw);
	   //3.���ݽ����Ӧ��ͬ��Ϣ
		 if(user!=null){
			 //��¼�ɹ��������session��
			 request.getSession().setAttribute("user", user);
			 request.getRequestDispatcher("index.jsp").forward(request, response); 
		 }else{
			 request.getRequestDispatcher("login.jsp").forward(request, response); 
		 }
		
	}
	// ��֤�û����Ƿ��ظ�
	public void checkUserName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���get��������
		String user=request.getParameter("uname");
		user=new String(user.getBytes("iso-8859-1"),"utf-8");
		// ����dao��
		UserDao dao=new UserDao();
		boolean result=dao.checkUserName(user);
		PrintWriter  out=response.getWriter();
		if(result){//û�鵽������
			out.print(0);
		}else{
			out.print(1);
		}
	}
	
}
