package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import util.DBUtil;
import model.Catalog;
import model.Goods;

public class CatalogDao {
   //查询所有分类
	public List<Catalog> getCatalogs(){
	 List<Catalog> list=new ArrayList<Catalog>();
	 Connection con=null;
	 PreparedStatement pst=null;
	 ResultSet rs=null;
	 Catalog cg=null;
	 try{
		con=DBUtil.getCon();
		String sql="select * from catalog";
		pst=con.prepareStatement(sql);
		rs=pst.executeQuery();
		while(rs.next()){
			cg=new Catalog();
			cg.setCatalogid(rs.getInt("cid"));
			cg.setCatalogname(rs.getString("cname"));
			list.add(cg);
		}
	 }catch(Exception e){
		e.printStackTrace(); 
	 }finally{
		 DBUtil.closeAll(con, rs, pst);
	 }
	 return list;	
	}
// 通过cid查询产品列表
	public List<Goods> getGoodsBycid(int cid) {
		List<Goods> list=new ArrayList<Goods>();
		 Goods goods=null;
		 Connection con=null;
		 PreparedStatement pst=null;
		 ResultSet rs=null;
		 try{
			con=DBUtil.getCon();
			String sql="select * from flower where cid=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1, cid);
			rs=pst.executeQuery();
			while(rs.next()){
				goods=new Goods();
				goods.setCatalogid(cid);
				goods.setGoodsname(rs.getString("gname"));
				goods.setGoodsid(rs.getInt("gid"));
				goods.setPicture(rs.getString("picture"));
				goods.setPrice(rs.getFloat("price"));

				list.add(goods);
			}
		 }catch(Exception e){
			e.printStackTrace(); 
		 }finally{
			 DBUtil.closeAll(con, rs, pst);
		 }
		 return list;
	}
}
