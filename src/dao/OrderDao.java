package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Goods;
import util.DBUtil;

public class OrderDao {
	public boolean makeOrder(int uid,double money){
		   Connection con=null;
		   PreparedStatement st=null;
		   ResultSet rs=null;
		   boolean result=false;//执行结果
		   try{
		   //1.连接数据库
		    con=DBUtil.getCon();
		    String sql="select * from cart where uid=?";
		    st= con.prepareStatement(sql);
		    st.setInt(1,uid);
		    rs=st.executeQuery();
		    while(rs.next())
		    {
		    	st= con.prepareStatement("insert into orders(gid,uid,total_money) values(?,?,?)");
		     	st.setInt(1,rs.getInt("gid"));
		     	st.setInt(2,uid);
		     	st.setDouble(3,money);
		     	st.execute();
		    }
		    result=true;
		  }catch(Exception e){
			  System.out.println(e.getMessage());
		  }finally{//释放资源
			  DBUtil.closeAll(con, null, st);
		  }
		   return result;
	   }
	
	public List<Goods> showOrder(int uid){
		List<Goods> list=new ArrayList<Goods>();   
		Connection con=null;
		   PreparedStatement st=null;
		   ResultSet rs=null,rs2=null;
		   boolean result=false;//执行结果
		   try{
		   //1.连接数据库
		    con=DBUtil.getCon();
		    st=con.prepareStatement("select * from address where uid=?");
		    st.setInt(1,uid);
		    rs=st.executeQuery();
		    String addr=null;
		    while(rs.next()){
		    addr=rs.getString("address");}
		    String sql="select * from orders inner join goods where orders.gid=goods.gid and uid=?";
		    st=con.prepareStatement(sql);
		    st.setInt(1,uid);
		    rs=st.executeQuery();
		    while(rs.next())
		    {
		    	int gid=rs.getInt("gid");
		    	sql="select quantity from cart where gid=? and uid=?";
		    	st=con.prepareStatement(sql);
		    	st.setInt(1,gid);
		    	st.setInt(2,uid);
		    	rs2=st.executeQuery();
		    	while(rs2.next())
		    	{
		    		 Goods g=new Goods();
		    		  g.setCatalogid(gid);
		    		  g.setGoodsname(rs.getString("gname"));
		    		  g.setPicture(rs.getString("picture"));
		    		  g.setPrice(rs.getDouble("price"));
		    		  g.setGoodsid(rs.getInt("gid"));
		    		  g.setQuantity(rs2.getInt("quantity"));
		    		  g.setAddr(addr);
		    		  list.add(g);
		    	}
		    }
		    st.setInt(1,uid);
		    st.execute();
		    result=true;
		  }catch(Exception e){
			  System.out.println(e.getMessage());
		  }finally{//释放资源
			  DBUtil.closeAll(con, null, st);
		  }
		   return list;
	   }
}