package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Goods;

import util.DBUtil;

public class CartDao {
	
	 public boolean addToCart(Cart cart){
		   Connection con=null;
		   PreparedStatement st=null;
		   ResultSet rs=null;
		   boolean result=false;//执行结果
		   try{
		   //1.连接数据库
		    con=DBUtil.getCon();
		    String sql="select * from cart where gid=? and uid=?";
		    st= con.prepareStatement(sql);
		    st.setInt(1,cart.getGid());
		    st.setInt(2,cart.getUid());
		    rs=st.executeQuery();
		    if(rs.next())
		    {
		    	int n=rs.getInt("quantity");
		    	int cartid=rs.getInt("cartid");
		    	n+=1;
		    	st= con.prepareStatement("update cart set quantity=? where cartid=?");
		    	st.setInt(1,n);
		    	st.setInt(2,cartid);
		    	st.execute();
		    	result=true;
		    }
		   //2.sql语句
		    else{
		    sql="insert into cart(uid,gid,quantity) values(?,?,?)";
		   //3. 创建statement对象
		    st=con.prepareStatement(sql);
		    st.setInt(1,cart.getUid());
		    st.setInt(2,cart.getGid());
		    st.setInt(3,1);
		   //4.执行sql
		    st.execute();
		    result=true;
		    }
		  }catch(Exception e){
			  System.out.println(e.getMessage());
		  }finally{//释放资源
			  DBUtil.closeAll(con, null, st);
		  }
		   return result;
	   }
	 
	 public boolean deleteCart(int uid,int gid){
		   Connection con=null;
		   PreparedStatement st=null;
		   ResultSet rs=null;
		   boolean result=false;//执行结果
		   try{
		   //1.连接数据库
		    con=DBUtil.getCon();
		    String sql="delete from cart where gid=? and uid=?";
		    st= con.prepareStatement(sql);
		    st.setInt(1,gid);
		    st.setInt(2,uid);
		    st.execute();
		    result=true;
		  }catch(Exception e){
			  System.out.println(e.getMessage());
		  }finally{//释放资源
			  DBUtil.closeAll(con, null, st);
		  }
		   return result;
	   }
	 
	 public List<Goods> SelectCart(int uid){
		   	List<Goods> list=new ArrayList<Goods>();
		   	Connection con=null;
		   	PreparedStatement st=null;
		   	ResultSet rs=null;
		   	try{
		   	  con=DBUtil.getCon();
		      String sql="select * from cart inner join goods where cart.gid=goods.gid and cart.uid=?";
			  st=con.prepareStatement(sql);
			  st.setInt(1,uid);
			  rs=st.executeQuery();
			  while(rs.next())
			  {
				  Goods g=new Goods();
				  g.setCatalogid(rs.getInt("cid"));
				  g.setGoodsname(rs.getString("gname"));
				  g.setPicture(rs.getString("picture"));
				  g.setPrice(rs.getDouble("price"));
				  g.setGoodsid(rs.getInt("gid"));
				  g.setQuantity(rs.getInt("quantity"));
				  list.add(g);
			  }
		   }
		   catch(Exception e)
		   {
			   System.out.println(e.getMessage());
		   }finally{
			   DBUtil.closeAll(con, null, st);
		   }
		return list;
	 }
		   
}
