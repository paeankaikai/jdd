package com.portal.core.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Tuanfan.Zhang
 *
 */
@SuppressWarnings("rawtypes")
public class TreeNodeContainer {
	private static Map<String, TreeNode> trees = new ConcurrentHashMap<String, TreeNode>();
	
	public static TreeNode getTree(String key){
		return trees.get(key);
	}
	
	public static void addTree(String key,TreeNode node){
		trees.put(key, node);
	}
}
