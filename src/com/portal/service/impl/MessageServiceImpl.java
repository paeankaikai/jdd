package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.bean.JddMessage;
import com.portal.bean.JddMessageExample;
import com.portal.mapper.JddMessageMapper;
import com.portal.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private JddMessageMapper jddMessageMapper;

	@Override
	public Integer deleteById(String id) {
		return jddMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer deleteByIds(List<Object> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteByWhere(JddMessage record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JddMessage> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JddMessage queryById(String id) {
		JddMessage jddMessage = jddMessageMapper.selectByPrimaryKey(id);
		return jddMessage;
	}

	@Override
	public List<JddMessage> queryListByWhere(JddMessage record) {
		List<JddMessage> selectByWhere = jddMessageMapper.selectByWhere(record);
		return selectByWhere;
	}

	@Override
	public JddMessage queryOne(JddMessage record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<JddMessage> queryPageListByWhere(Integer page, Integer rows, JddMessage record) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		List<JddMessage> selectAll =jddMessageMapper.selectByWhere(null);
		PageInfo<JddMessage> pageInfo = new PageInfo<JddMessage>(selectAll);
		return pageInfo;
	}

	@Override
	public Integer save(JddMessage t) {
		return jddMessageMapper.insert(t);
	}

	@Override
	public Integer saveSelective(JddMessage t) {
		return jddMessageMapper.insertSelective(t);
	}

	@Override
	public Integer update(JddMessage t) {
		return jddMessageMapper.updateByPrimaryKey(t);
	}

	@Override
	public Integer updateSelective(JddMessage t) {		
		return jddMessageMapper.updateByPrimaryKeySelective(t);
	}

}
