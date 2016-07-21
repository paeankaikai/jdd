package com.portal.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.portal.bean.JddRule;
 
public class TreeNode implements Serializable {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String parent_Id;  
    private String selfId;  
    private JddRule jddRule;  
    private String text;
    private TreeNode parentNode;  
    private List<TreeNode> nodes;  
  
    public TreeNode getParentNode() {
		return parentNode;
	}


	@Override
	public String toString() {
		return "{parent_Id:'" + parent_Id + "', selfId:'" + selfId +"', text:'" + text +"',nodes:"+nodes+",jddRule:"+jddRule.toString()+"}";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
		result = prime * result + ((jddRule == null) ? 0 : jddRule.hashCode());
		result = prime * result + ((parent_Id == null) ? 0 : parent_Id.hashCode());
		result = prime * result + ((parentNode == null) ? 0 : parentNode.hashCode());
		result = prime * result + ((selfId == null) ? 0 : selfId.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode other = (TreeNode) obj;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		if (jddRule == null) {
			if (other.jddRule != null)
				return false;
		} else if (!jddRule.equals(other.jddRule))
			return false;
		if (parent_Id == null) {
			if (other.parent_Id != null)
				return false;
		} else if (!parent_Id.equals(other.parent_Id))
			return false;
		if (parentNode == null) {
			if (other.parentNode != null)
				return false;
		} else if (!parentNode.equals(other.parentNode))
			return false;
		if (selfId == null) {
			if (other.selfId != null)
				return false;
		} else if (!selfId.equals(other.selfId))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
	}

	public List<TreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public JddRule getJddRule() {
		return jddRule;
	}

	public void setJddRule(JddRule jddRule) {
		this.jddRule = jddRule;
	}
    
    
    public TreeNode() {  
        initnodes();  
    }  
    
    public void initnodes() {
		if (nodes == null)
			nodes = new ArrayList<TreeNode>();
	} 
  
    public TreeNode(TreeNode parentNode) {  
        this.getParentNode();  
        initnodes();  
    }  
  
    public boolean isLeaf() {  
        if (nodes == null) {  
            return true;  
        } else {  
            if (nodes.isEmpty()) {  
                return true;  
            } else {  
                return false;  
            }  
        }  
    }  
  
    /* 插入一个child节点到当前节点中 */  
    public void addChildNode(TreeNode treeNode) {  
        initnodes();  
        nodes.add(treeNode);  
    }  
  
	
  
    public boolean isValidTree() {  
        return true;  
    }

	public String getSelfId() {
		return selfId;
	}

	public void setSelfId(String selfId) {
		this.selfId = selfId;
	}

	public String getParent_Id() {
		return parent_Id;
	}

	public void setParent_Id(String parent_Id) {
		this.parent_Id = parent_Id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}  
  
  
 
}  