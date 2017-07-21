package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

import model.Goods;

public class GoodsDao {

	public List<Goods> getGoodsBystr(String str) {
		List<Goods> list=new ArrayList<Goods>();
		 Goods goods=null;
		 Connection con=null;
		 PreparedStatement pst=null;
		 ResultSet rs=null;
		 try{
			con=DBUtil.getCon();
			// Ä£ºý²éÑ¯ like  
			String sql;
			sql="select * from goods where gname like ? ";
			pst=con.prepareStatement(sql);
			pst.setString(1,"%"+str+"%");
			rs=pst.executeQuery();
			while(rs.next()){
				goods=new Goods();
				goods.setCatalogid(rs.getInt("cid"));
				goods.setGoodsname(rs.getString("gname"));
				goods.setGoodsid(rs.getInt("gid"));
				goods.setAmount(rs.getInt("amount"));
				goods.setPicture(rs.getString("picture"));
				goods.setPrice(rs.getFloat("price"));
				list.add(goods);
			}
		 }catch(Exception e){
			e.printStackTrace(); 
			System.out.println("exception");
		 }finally{
			 DBUtil.closeAll(con, rs, pst);
		 }
		 return list;
	}
   
}
