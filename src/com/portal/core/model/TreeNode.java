package com.portal.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.portal.common.CommonUtil;




/**
 * 树形结构数据
 * @author Tuanfan.Zhang
 *
 * @param <T>
 */
public class TreeNode<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id="-1";

	private String name="根";

	/**
	 * 0 代表根
	 */
	private int level=0;

	/**
	 * 子树
	 */
	private List<TreeNode<T>> childs;

	/**
	 * 是否显示
	 */
	private boolean isShow = true;

	/**
	 * 获取当前树对象
	 */
	private T t;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<TreeNode<T>> getChilds() {
		return childs;
	}

	public void setChilds(List<TreeNode<T>> childs) {
		this.childs = childs;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
	public void addChild(TreeNode<T> dto){
		if(CommonUtil.isEmpty(childs)){
			childs = new ArrayList<TreeNode<T>>();
		}
		childs.add(dto);
	}
	
}
