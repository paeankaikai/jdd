package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddBaserecordQuery;
import com.portal.mapper.JddBaserecordMapper;
import com.portal.service.BaseRecordService;

@Service
public class BaseRecordServiceImpl implements BaseRecordService {
	@Autowired
	private JddBaserecordMapper jddBaserecordMapper;

	@Override
	public List<JddBaserecord> selectByWhere(
			JddBaserecordQuery query) {
		// TODO Auto-generated method stub
		return jddBaserecordMapper.selectByWhere(query);
	}

	@Override
	public List<JddBaserecord> selectByWhereGroupBy(
			JddBaserecordQuery query) {
		// TODO Auto-generated method stub
		return jddBaserecordMapper.selectByWhereGroupBy(query);
	}

	@Override
	public PageInfo<JddBaserecord> queryPageListByWhere(Integer page,
			Integer rows, JddBaserecordQuery query,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddBaserecord> selectAll = jddBaserecordMapper.selectByWhere(query);
		PageInfo<JddBaserecord> pageInfo = new PageInfo<JddBaserecord>(selectAll);
		return pageInfo;
	}
	
	@Override
	public PageInfo<JddBaserecord> queryPageAndNum(Integer page,Integer rows, JddBaserecordQuery query,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddBaserecord> selectAll = jddBaserecordMapper.selectByWhere(query);
		PageInfo<JddBaserecord> pageInfo = new PageInfo<JddBaserecord>(selectAll);
		for (JddBaserecord jddBaserecord : pageInfo.getList()) {
			jddBaserecord.setPageNum(pageInfo.getPageNum()-1);
		}
		return pageInfo;
	}

	@Override
	public PageInfo<JddBaserecord> queryPageListByWhereGroupBy(Integer page,
			Integer rows, JddBaserecordQuery query,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddBaserecord> selectAll = jddBaserecordMapper.selectByWhereGroupBy(query);
		PageInfo<JddBaserecord> pageInfo = new PageInfo<JddBaserecord>(selectAll);
		return pageInfo;
	}

	@Override
	public int selectTotalNum(JddBaserecordQuery record) {
		int tatalNum =  jddBaserecordMapper.selectTotalNum(record);
		return tatalNum;
	}

	@Override
	public List<String> queryAllRuleChilds(Integer ruleType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> queryAllcostSources(Integer ruleType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(JddBaserecord record) {
		// TODO Auto-generated method stub
		return jddBaserecordMapper.insert(record);
	}

}
