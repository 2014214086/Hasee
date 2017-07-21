package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Menu;
import util.DBUtil;

public class MenuDao {
	public List<Menu> getMenu(int id){
		 List<Menu> list=new ArrayList<Menu>();
		 Connection con=null;
		 PreparedStatement pst=null;
		 ResultSet rs=null;
		 Menu mu=null;
		 try{
			con=DBUtil.getCon();
			String sql="select * from menu where cid=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1,id);
			rs=pst.executeQuery();
			while(rs.next()){
				mu=new Menu();
				mu.setCid(rs.getInt("cid"));
				mu.setPicture(rs.getString("picture"));
				mu.setText(rs.getString("text"));
				list.add(mu);
			}
		 }catch(Exception e){
			e.printStackTrace(); 
		 }finally{
			 DBUtil.closeAll(con, rs, pst);
		 }
		 return list;	
		}
}
