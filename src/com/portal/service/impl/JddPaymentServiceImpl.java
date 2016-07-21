package com.portal.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.bean.JddPayment;
import com.portal.bean.JddPaymentQuery;
import com.portal.mapper.JddPaymentMapper;
import com.portal.service.JddPaymentService;

@Service
public class JddPaymentServiceImpl implements JddPaymentService {
	@Autowired
	private JddPaymentMapper jddPaymentMapper;

	@Override
	public Integer saveSelective(JddPayment t) {
		// TODO Auto-generated method stub
		return jddPaymentMapper.insertSelective(t);
	}
	
	@Override
	public List<JddPayment> selectByWhere(
			JddPaymentQuery query) {
		// TODO Auto-generated method stub
		return jddPaymentMapper.selectByWhere(query);
	}

	@Override
	public List<JddPayment> selectByWhereGroupBy(
			JddPaymentQuery query) {
		// TODO Auto-generated method stub
		return jddPaymentMapper.selectByWhereGroupBy(query);
	}
	
	@Override
	public PageInfo<JddPayment> queryPageListByWhere(Integer page,
			Integer rows, JddPaymentQuery query,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddPayment> selectAll = jddPaymentMapper.selectByWhere(query);
		PageInfo<JddPayment> pageInfo = new PageInfo<JddPayment>(selectAll);
		return pageInfo;
	}
	
	@Override
	public PageInfo<JddPayment> queryPageListByWhereGroupBy(Integer page,
			Integer rows, JddPaymentQuery query,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddPayment> selectAll = jddPaymentMapper.selectByWhereGroupBy(query);
		PageInfo<JddPayment> pageInfo = new PageInfo<JddPayment>(selectAll);
		return pageInfo;
	}

}
