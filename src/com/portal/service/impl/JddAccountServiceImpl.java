package com.portal.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.bean.JddAccount;
import com.portal.common.CommonUtil;
import com.portal.common.StringUtil;
import com.portal.mapper.JddAccountMapper;
import com.portal.service.JddAccountService;

/**
 * 用户信息service
 * 
 * @author Qin.SiLiang
 * 
 */
@Service
public class JddAccountServiceImpl implements JddAccountService {

	@Autowired
	private JddAccountMapper JddAccountMapper;

	@Override
	public Integer deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JddAccount> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JddAccount queryById(String id) {
		JddAccount JddAccount = JddAccountMapper.selectByPrimaryKey(id);
		return JddAccount;
	}

	@Override
	public JddAccount queryByUserId(String userId) {
		// TODO Auto-generated method stub
		return JddAccountMapper.selectByUserId(userId);
	}

	@Override
	public PageInfo<JddAccount> queryPageListByWhere(Integer page, Integer rows, JddAccount record,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddAccount> selectAll = JddAccountMapper.selectByWhere(null);
		PageInfo<JddAccount> pageInfo = new PageInfo<JddAccount>(selectAll);
		return pageInfo;
	}

	@Override
	public PageInfo<JddAccount> queryPageNum(Integer page, Integer rows, JddAccount record,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		if (record != null) {
			record.setYearNo(CommonUtil.dateFormat(new Date(), "yyyy"));
		}

		List<JddAccount> selectAll = JddAccountMapper.orderByNum(record);
		PageInfo<JddAccount> pageInfo = new PageInfo<JddAccount>(selectAll);
		for (JddAccount jddAccount : pageInfo.getList()) {
			jddAccount.setPageNum(pageInfo.getPageNum() - 1);
		}
		return pageInfo;
	}

	@Override
	public List<JddAccount> queryListByWhere(JddAccount record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JddAccount queryOne(JddAccount record) {
		List<JddAccount> list = JddAccountMapper.selectByWhere(record);
		if (StringUtil.isNotNull(list)) {
			return list.get(0);
		}
		// JddAccount JddAccount = JddAccountMapper.selectByWhere(record).get(0);
		return null;
	}

	@Override
	public Integer save(JddAccount t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer saveSelective(JddAccount t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(JddAccount t) {
		// TODO Auto-generated method stub
		return JddAccountMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public Integer updateSelective(JddAccount t) {
		// TODO Auto-generated method stub
		return null;
	}

}
