package com.portal.common;

public class Page {
	//当前页
	 private String pageNum = "1";
	 //每页记录条数
	 private String numPerPage = "10";
	 //总页数
	 private int count;
	 //asc   desc
	 private String sortOrder = "";	 
	 //根据什么排序
	 private String sortName = "";
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(String numPerPage) {
		this.numPerPage = numPerPage;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	 
	 
}
