package com.portal.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddAccount;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddJkrecord;
import com.portal.bean.JddJkrecordQuery;
import com.portal.bean.JddRule;
import com.portal.bean.JddRuleExample;
import com.portal.bean.UserInfo;
import com.portal.bean.UserInfoExample.Criteria;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.core.cache.CacheUtils;
import com.portal.mapper.JddAccountMapper;
import com.portal.mapper.JddBaserecordMapper;
import com.portal.mapper.JddJkrecordMapper;
import com.portal.mapper.JddRuleMapper;
import com.portal.mapper.UserInfoMapper;
import com.portal.service.JddJkRecordService;

@Service
@Transactional
public class JddJkRecordServiceImpl implements JddJkRecordService {
	@Autowired
	private JddJkrecordMapper jddJkrecordMapper;

	@Autowired
	private JddBaserecordMapper jddBaserecordMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private JddAccountMapper jddAccountMapper;
	
	
	@Autowired
	private JddRuleMapper jddRuleMapper;
	
	


	@Override
	public List<JddJkrecord> selectByWhere(
			JddJkrecordQuery query) {
		// TODO Auto-generated method stub
		return jddJkrecordMapper.selectByWhere(query);
	}

	@Override
	public PageInfo<JddJkrecord> queryPageListByWhere(Integer page,
			Integer rows, JddJkrecordQuery query,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddJkrecord> selectAll = jddJkrecordMapper.selectByWhere(query);
		PageInfo<JddJkrecord> pageInfo = new PageInfo<JddJkrecord>(selectAll);
		return pageInfo;
	}

	@Override
	public JddJkrecord queryExchangeCountInfo() {
		// TODO Auto-generated method stub
		return jddJkrecordMapper.queryExchangeCountInfo();
	}

	@Override
	public PageInfo<JddJkrecord> queryPageNum(Integer page, Integer rows, JddJkrecordQuery query,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddJkrecord> selectAll = jddJkrecordMapper.selectByWhere(query);
		PageInfo<JddJkrecord> pageInfo = new PageInfo<JddJkrecord>(selectAll);
		for (JddJkrecord jddJkrecord : pageInfo.getList()) {
			jddJkrecord.setPageNum(pageInfo.getPageNum() - 1);
		}
		return pageInfo;
	}

	@Override
	public int insertExchange(JddJkrecord jkRecord) {
		/*GeelyUserInfo userInfo = CacheUtils.get(SessionConstants.USERINFO);

		UserInfo user = userInfoMapper.selectByUserId(userInfo.getUserId());

		jkRecord.setGuid(StringUtil.getGUID());
		jkRecord.setCreateBy(userInfo.getUserId());
		jkRecord.setCreateName(userInfo.getName());
		jkRecord.setCreateTime(new Date());
		jddJkrecordMapper.insert(jkRecord);

		JddBaserecord jddBaserecord = new JddBaserecord();
		jddBaserecord.setGuid(StringUtil.getGUID());
		jddBaserecord.setRelationId(jkRecord.getGuid());
		jddBaserecord.setRuleType(0);
		jddBaserecord.setCount(jkRecord.getCount());
		jddBaserecord.setCreateBy(userInfo.getUserId());
		jddBaserecord.setCreateName(userInfo.getName());
		jddBaserecord.setCreateTime(new Date());
		jddBaserecord.setRuleChild(SessionConstants.RULE_EXCHANE_IN_CHILD);
		jddBaserecord.setRuleFather(SessionConstants.RULE_EXCHANE_IN_FATHER);
		jddBaserecord.setRuleGrad(SessionConstants.RULE_EXCHANE_IN_GRAD);
		jddBaserecord.setReason("吉课金币兑换吉点点");
		jddBaserecord.setIsDeleted(0);
		jddBaserecordMapper.insert(jddBaserecord);

		user.setUsedJkSum(user.getUsedJkSum() + jkRecord.getJkCount());
		userInfoMapper.updateByPrimaryKey(user);
		
		JddAccount account=jddAccountMapper.selectByUserId(user.getUserId());
		account.setCyNum(account.getCyNum()+jkRecord.getCount());	
		jddAccountMapper.updateByPrimaryKeySelective(account);
*/
		return 1;
	}
	
	
}
