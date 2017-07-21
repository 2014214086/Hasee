package util;

public class Page {
	
	private  int pageSize;//页面大小，用户设置好的
	private int rowCount;//总记录数    从数据库查询
	private int pageCount;//总页数
	private int beginRow;//从第几条开始查询
	private int currentPage;//当前页是第几页, 用户设置
	
	private boolean hasprev;//是否有上一页      currentPage==1 false
	private boolean hasnext;//是否有下一页        currentPage == pageCount false

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {//第一处：pageCount=rowCount/pageSize  或者 rowCount/pageSize+1
		if(rowCount%pageSize==0)
		   pageCount=rowCount/pageSize;
		else
		   pageCount=rowCount/pageSize+1;
		
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBeginRow() {//第二处:beginRow=(currentPage-1)*pageSize
		beginRow=(currentPage-1)*pageSize;
		return beginRow;
	}
	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public boolean isHasprev() {//第三处 ：currentPage==1  false
		if(currentPage==1)
		     hasprev=false;
		else hasprev=true;
		
		return hasprev;
	}
	public void setHasprev(boolean hasprev) {
		this.hasprev = hasprev;
	}
	public boolean isHasnext() {//第四处:currentPage ==pageCount  false
		if(currentPage ==pageCount)
			hasnext=false;
		else hasnext=true;
		
		return hasnext;
	}
	public void setHasnext(boolean hasnext) {
		this.hasnext = hasnext;
	}
	

}
