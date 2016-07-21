package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.bean.JddPresent;
import com.portal.bean.JddPresentQuery;
import com.portal.bean.JddPresentTotalInfo;
import com.portal.mapper.JddPresentMapper;
import com.portal.service.JddPresentService;

@Service
public class JddPresentServiceImpl implements JddPresentService {
	@Autowired
	private JddPresentMapper jddPresentMapper;

	@Override
	public Integer deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JddPresent> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JddPresent queryById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JddPresent> queryListByWhere(JddPresentQuery record) {
		return jddPresentMapper.selectByWhere(record);
	}
	
	@Override
	public PageInfo<JddPresent> queryPageListByWhere(Integer page, Integer rows, JddPresentQuery record, List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy !=null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddPresent> selectAll =jddPresentMapper.selectByWhere(record);
		PageInfo<JddPresent> pageInfo = new PageInfo<JddPresent>(selectAll);
		return pageInfo;
	}

	@Override
	public JddPresent queryOne(JddPresent record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(JddPresent t) {
		// TODO Auto-generated method stub
		return jddPresentMapper.insert(t);
	}

	@Override
	public Integer saveSelective(JddPresent t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(JddPresent t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateSelective(JddPresent t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JddPresentTotalInfo queryTotalInfo(String userId) {
		// TODO Auto-generated method stub
		return jddPresentMapper.queryTotalInfo(userId);
	}

}
