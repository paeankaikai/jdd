package com.portal.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.JddRule;
import com.portal.bean.JddRuleExample;
import com.portal.bean.JddRuleExample.Criteria;
import com.portal.bean.SysParameter;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.common.TreeNode;
import com.portal.core.cache.CacheUtils;
import com.portal.mapper.JddRuleMapper;
import com.portal.mapper.SysParameterMapper;
import com.portal.service.RuleService;

@Service
public class RuleServiceImpl implements RuleService {

	@Autowired
	private JddRuleMapper jddRuleMapper;

	@Autowired
	private SysParameterMapper sysParameterMapper;

	@Override
	public List<JddRule> getIncomeRuleGrad() {
		JddRuleExample example = new JddRuleExample();
		Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(0);
		criteria.andParentGuidEqualTo(String.valueOf(0));
		criteria.andIsDeletedEqualTo(0);
		List<JddRule> gradFather = jddRuleMapper.selectByExample(example);
		return gradFather;
	}

	@Override
	public List<JddRule> getOutcomeRuleGrad() {
		JddRuleExample example = new JddRuleExample();
		Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(1);
		criteria.andIsDeletedEqualTo(0);
		criteria.andParentGuidEqualTo(String.valueOf(0));
		return jddRuleMapper.selectByExample(example);
	}

	@Override
	public List<JddRule> getChildren(String parentGuid) {
		JddRuleExample example = new JddRuleExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentGuidEqualTo(parentGuid);
		criteria.andIsDeletedEqualTo(0);
		return jddRuleMapper.selectByExample(example);
	}

	/**
	 * 获取收入规则树结构 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月3日 上午10:02:53
	 * @return
	 */
	public TreeNode getTree(int type) {
		// 根目录
		TreeNode root = new TreeNode();
		root.setSelfId(0 + "");
		List<JddRule> list = new ArrayList<JddRule>();
		if (type == 0) {
			list = this.getIncomeRuleGrad();
		}
		if (type == 1) {
			list = this.getOutcomeRuleGrad();
		}

		// 第一级
		this.addChild(list, root, 1);

		List<TreeNode> firstList = root.getNodes();
		Iterator<TreeNode> iterator = firstList.iterator();
		while (iterator.hasNext()) {
			TreeNode first = iterator.next();
			List<JddRule> secondList = this.getChildren(first.getSelfId());
			addChild(secondList, first, 2);

			List<TreeNode> secondNode = first.getNodes();
			Iterator<TreeNode> iter = secondNode.iterator();
			while (iter.hasNext()) {
				TreeNode second = iter.next();
				List<JddRule> thirdList = this.getChildren(second.getSelfId());
				addChild(thirdList, second, 3);
			}
		}
		return root;
	}

	public void addChild(List<JddRule> list, TreeNode root, int level) {
		Iterator<JddRule> iterator = list.iterator();
		List<TreeNode> childList = new ArrayList<TreeNode>();
		while (iterator.hasNext()) {
			JddRule rule = iterator.next();
			TreeNode grad = new TreeNode();
			grad.setParent_Id(rule.getParentGuid());
			grad.setSelfId(rule.getGuid());

			if (StringUtil.isEmpty(rule.getSource())) {
				grad.setText(rule.getClassName());
			} else {
				List<SysParameter> sources = CacheUtils.get(SessionConstants.PARAMETER_SOURCE);
				String source = "";
				for (int i = 0; i < sources.size(); i++) {
					if (sources.get(i).getGuid().equals(rule.getSource())) {
						source = sources.get(i).getParamValue();
					}
				}
				grad.setText(rule.getClassName()
						+ (rule.getSource() == null ? "" : "------------来源:" + source));
			}

			grad.setJddRule(rule);
			grad.setParentNode(root);
			if (level == 3) {
				grad.setNodes(null);
			}

			childList.add(grad);
		}
		root.setNodes(childList);
	}

	@Override
	public int insertRule(JddRule jddRule) {
		return jddRuleMapper.insert(jddRule);
	}

	@Override
	public int deleteRuleByGUID(String guid, int levels) {
		if (levels == 1) {
			List<JddRule> fathers = this.getChildren(guid);
			Iterator<JddRule> iterator = fathers.iterator();
			while (iterator.hasNext()) {
				JddRule father = iterator.next();
				List<JddRule> childs = this.getChildren(father.getGuid());
				this.deleteList(childs);
			}
			this.deleteList(fathers);
		}
		if (levels == 2) {
			List<JddRule> childs = this.getChildren(guid);
			this.deleteList(childs);
		}
		 jddRuleMapper.deleteByPrimaryKey(guid);
		 return 1;
	}

	/**
	 * 批量删除rules Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月6日 下午9:29:07
	 * @param rules
	 * @return
	 */
	public int deleteList(List<JddRule> rules) {
		Iterator<JddRule> iterator = rules.iterator();
		while (iterator.hasNext()) {
			JddRule rule = iterator.next();
			jddRuleMapper.deleteByPrimaryKey(rule.getGuid());
		}

		return 1;

	}

	@Override
	public int modifyRule(JddRule jddRule) {
		return jddRuleMapper.updateByPrimaryKeySelective(jddRule);
	}

	public JddRule findRuleByGUID(String guid) {
		return jddRuleMapper.selectByPrimaryKey(guid);
	}

	public List<JddRule> findChildRuleByType(int type) {
		JddRuleExample example = new JddRuleExample();
		Criteria criteria = example.createCriteria();
		criteria.andLevelsEqualTo(3);
		criteria.andTypeEqualTo(type);
		return jddRuleMapper.selectByExample(example);
	}

	public List<JddRule> findFatherRuleByType(int type) {
		JddRuleExample example = new JddRuleExample();
		Criteria criteria = example.createCriteria();
		criteria.andLevelsEqualTo(2);
		criteria.andTypeEqualTo(type);
		return jddRuleMapper.selectByExample(example);
	}

	public List<JddRule> findGradRuleByType(int type) {
		JddRuleExample example = new JddRuleExample();
		Criteria criteria = example.createCriteria();
		criteria.andLevelsEqualTo(1);
		criteria.andTypeEqualTo(type);
		return jddRuleMapper.selectByExample(example);
	}

}
