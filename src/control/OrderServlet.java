package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Goods;
import model.User;
import dao.CartDao;
import dao.OrderDao;

public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         String ff=request.getParameter("flag");
         if("make".equals(ff)){
        	 //���빺�ﳵ
        	 makeOrder(request,response);
         }
         
	}

	
	private void makeOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.�����û�userid(��session��ȡ) 
		 User user=(User)request.getSession().getAttribute("user");
		 if(user!=null){
			 int uid=user.getUserid();
			 double money=Double.parseDouble(request.getParameter("money"));
			    //2.ͨ��uid��ѯcart��
				  OrderDao dao=new OrderDao();
				 dao.makeOrder(uid,money);
				 request.getRequestDispatcher("order.jsp").forward(request, response); 	 
		 }else{
			 request.getRequestDispatcher("showCart.jsp").forward(request, response); 	  
		 } 
		 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}
}
