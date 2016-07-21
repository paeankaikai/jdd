package com.portal.service;

import java.util.List;

import com.portal.bean.JddRule;
import com.portal.common.TreeNode;

public interface RuleService {
	
	/**
	 * 查找收入所有一级节点
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月2日 下午6:33:23
	 * @return
	 */
	public List<JddRule> getIncomeRuleGrad();
	
	
	/**
	 * 查找支出所有一级节点
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月2日 下午6:33:23
	 * @return
	 */
	public List<JddRule> getOutcomeRuleGrad();
	
	
	
	
	/**
	  * 根据parentId来查找子节点（二级节点）
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月2日 下午6:33:23
	 * @return
	 */
	public List<JddRule> getChildren(String parentGuid);
	
	
	/**
	 * 获取收入tree树结构
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月3日 上午10:49:47
	 * @return
	 */
	public TreeNode getTree(int type);
	

	public int insertRule(JddRule jddRule);
	
	
	public int deleteRuleByGUID(String Guid,int levels);
	
	
	public int modifyRule(JddRule jddRule);
	
	
	
	public JddRule findRuleByGUID(String guid);

	
	/**
	 * 查询三级目录
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月8日 上午9:38:59
	 * @param type
	 * @return
	 */
	public List<JddRule> findChildRuleByType(int type);
	
	
	/**
	 * 查询二级目录
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月8日 上午9:38:59
	 * @param type
	 * @return
	 */
	public List<JddRule> findFatherRuleByType(int type);
	
	
	
	/**
	 * 查询一级目录
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月8日 上午9:38:59
	 * @param type
	 * @return
	 */
	public List<JddRule> findGradRuleByType(int type);
	
	
	
	
}
