package dao;

import java.sql.*;


import util.DBUtil;

import model.User;

public class UserDao {
  // 登录（查看数据库有没有该用户）
	public User login(String uname,String psw){
		User user=null;
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
				con=DBUtil.getCon();
			    String sql="select * from user where username=? and password=?";
			    st=con.prepareStatement(sql);
			    st.setString(1,uname);
			    st.setString(2,psw);
			    rs=st.executeQuery();
			    if(rs.next()){
			    	user=new User();
			    	user.setUserid(rs.getInt("uid"));
			    	user.setUsername(uname);
			    	user.setPassword(psw);
			    }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			DBUtil.closeAll(con, rs, st);
		}
		return user;
	}
  // 注册（user表insert 信息）	
   public boolean register(User user){
	   Connection con=null;
	   PreparedStatement st=null;
	   boolean result=false;//执行结果
	   try{
	   //1.连接数据库
	    con=DBUtil.getCon();
	   //2.sql语句
	    String sql="insert into user(username,password) values(?,?)";
	   //3. 创建statement对象
	      st= con.prepareStatement(sql);
	    st.setString(1, user.getUsername());
	    st.setString(2,user.getPassword());
	   //4.执行sql
	    st.execute();
	    result=true;
	  }catch(Exception e){
		  System.out.println(e.getMessage());
	  }finally{//释放资源
		  DBUtil.closeAll(con, null, st);
	  }
	   return result;
   }
  //修改密码
   public boolean password(User user,String pass){
	   Connection con=null;
	   ResultSet rs=null;
	   PreparedStatement st=null;
	   boolean result=false;//执行结果
	   try{
	   //1.连接数据库
	    con=DBUtil.getCon();
	   //2.sql语句
	    String sql="select * from user where uid=?";
	    st= con.prepareStatement(sql);
	    st.setInt(1, user.getUserid());
	    rs=st.executeQuery();
	    if(rs.next())
	    {
	    	if(rs.getString("password").equals(user.getPassword()))
	    	{
	    		 sql="update user set password=? where uid=?";
	    		 st= con.prepareStatement(sql);
	    		 st.setString(1,pass);
	    		 st.setInt(2, user.getUserid());
	    		 st.execute();
	    		 result=true;
	    	}
	    }
	  }catch(Exception e){
		  System.out.println(e.getMessage());
	  }finally{//释放资源
		  DBUtil.closeAll(con, null, st);
	  }
	   return result;
   }
   
  // 管理订单地址	
public boolean checkUserName(String user) {
	boolean result=false;
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	try{
		  //1.连接数据库
		  con= DBUtil.getCon();
		  String sql="select * from user where username=?";
		 //2 创建prepareStatement对象
		  pst=con.prepareStatement(sql);
		  pst.setString(1,user);
		// 3.执行sql
		  rs=pst.executeQuery();
		//4 处理结果集
		  if(rs.next()){
			  result=true;
		  }
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		DBUtil.closeAll(con, rs, pst);
	}
	return result;
}
}
