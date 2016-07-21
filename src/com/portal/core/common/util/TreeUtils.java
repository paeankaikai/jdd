package com.portal.core.common.util;

import java.util.List;

import com.portal.common.CommonUtil;
import com.portal.core.model.TreeNode;

/**
 * 树形工具类
 * 
 * @author Tuanfan.Zhang
 * @see com.gl.core.model.TreeNode
 * @param <T>
 */
public class TreeUtils{

	public static <T> List<TreeNode<T>> getChilds(TreeNode<T> t, String treeId) {
		if (t == null) {
			return null;
		}

		if (treeId.equals(t.getId())) {
			return t.getChilds();
		}
		List<TreeNode<T>> childs = t.getChilds();
		if (CommonUtil.isEmpty(childs)) {
			return null;
		}
		for (TreeNode<T> treeNode : childs) {
			List<TreeNode<T>> child = getChilds(treeNode, treeId);
			if (CommonUtil.isNotEmpty(child)) {
				return child;
			}
			getChilds(treeNode, treeId);
		}
		return null;
	}
}
