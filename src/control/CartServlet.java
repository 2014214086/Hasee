package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;

import model.Cart;
import model.Goods;
import model.User;

public class CartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         String ff=request.getParameter("flag");
         if("addCart".equals(ff)){
        	 //���빺�ﳵ
        	 addCart(request,response);
         }
         if("showCart".equals(ff)){
        	 //��ʾ����
        	 showCart(request,response);
         }
         if("delete".equals(ff)){
        	 deleteCart(request,response);
         }
	}

	
	private void showCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.�����û�userid(��session��ȡ) 
		 User user=(User)request.getSession().getAttribute("user");
		 if(user!=null){
			 int uid=user.getUserid();
			    //2.ͨ��uid��ѯcart��
				  CartDao dao=new CartDao();
				 List<Goods> list= dao.SelectCart(uid);
				//3.ҳ��ת��
				 request.setAttribute("carList", list);
				 request.getRequestDispatcher("showCart.jsp").forward(request, response); 	 
		 }else{
			 request.setAttribute("carList", null);
			 request.getRequestDispatcher("showCart.jsp").forward(request, response); 	  
		 } 
		 
	}

	private void deleteCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int uid=Integer.parseInt(request.getParameter("flag1"));
		int gid=Integer.parseInt(request.getParameter("flag2"));
		CartDao d=new CartDao();
		d.deleteCart(uid,gid);
		request.getRequestDispatcher("showCart.jsp").forward(request, response);
	}

	private void addCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.�ж��û��Ƿ��¼
		 User user=(User)request.getSession().getAttribute("user");
		  if(user==null){
			  request.getRequestDispatcher("login.jsp").forward(request, response);
		  }else{
			  String gid=request.getParameter("gid");
			   int gid_=Integer.parseInt(gid);
			 //2.����dao�㣬��ӹ��ﳵ 
			  CartDao dao=new CartDao();
			  Cart cart=new Cart();
			  cart.setGid(gid_); 
			  cart.setUid(user.getUserid());
			  cart.setQuantity(1);
			 boolean result= dao.addToCart(cart);
			 //3.���ݽ����Ӧ��ͬ��Ϣ
			 if(result){
				request.setAttribute("msg", "��ӳɹ���");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			 }
		  }
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
