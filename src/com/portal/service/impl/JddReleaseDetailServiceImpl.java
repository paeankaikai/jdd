package com.portal.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddReleaseDetail;
import com.portal.bean.JddReleaseDetailExample;
import com.portal.bean.JddReleaseDetailExample.Criteria;
import com.portal.bean.JddReleaseDetailQuery;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.core.cache.CacheUtils;
import com.portal.mapper.JddReleaseDetailMapper;
import com.portal.service.JddReleaseDetailService;

@Service
@Transactional
public class JddReleaseDetailServiceImpl implements JddReleaseDetailService {

	@Autowired
	private JddReleaseDetailMapper jddReleaseDetailMapper;

	@Override
	public int insert(JddReleaseDetail jddReleaseDetail) {
		jddReleaseDetail.setGuid(StringUtil.getGUID());
		return jddReleaseDetailMapper.insert(jddReleaseDetail);
	}

	@Override
	public List<JddReleaseDetail> selectByReleaseGuid(String releaseGuid) {
		JddReleaseDetailExample example = new JddReleaseDetailExample();
		Criteria criteria = example.createCriteria();
		criteria.andReleaseIdEqualTo(releaseGuid);
		return jddReleaseDetailMapper.selectByExample(example);
	}

	@Override
	public List<JddReleaseDetail> selectByReleaseGuid2(String releaseGuid) {
		JddReleaseDetailExample example = new JddReleaseDetailExample();
		Criteria criteria = example.createCriteria();
		criteria.andReleaseIdEqualTo(releaseGuid);
		return jddReleaseDetailMapper.selectByExample2(example);
	}

	@Override
	public int saveDetail(JddReleaseDetail detail) {
		return jddReleaseDetailMapper.insert(detail);
	}

	@Override
	public List<JddReleaseDetail> saveListDetail(List<Map<String, Object>> newlist, String releaseGuid) {
		List<JddReleaseDetail> details = new ArrayList<JddReleaseDetail>();
		if (StringUtil.isNotNull(newlist)) {
			Iterator<Map<String, Object>> iterator = newlist.iterator();
			while (iterator.hasNext()) {
				Map<String, Object> map = iterator.next();
				if (map.get("right").equals(0)) {
					JddReleaseDetail detail = (JddReleaseDetail) map.get("detail");
					detail.setGuid(StringUtil.getGUID());
					detail.setReleaseId(releaseGuid);
					if (saveDetail(detail) == 0) {
						throw new RuntimeException("保存失败");
					}
					detail.setRuleChild(map.get("child").toString());
					detail.setSource(map.get("source").toString());
					details.add(detail);
				}
			}

		}
		return details;
	}

	@Override
	public PageInfo<JddReleaseDetail> queryPageListByWhere(Integer page,
			Integer rows, JddReleaseDetailQuery query,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddReleaseDetail> selectAll = jddReleaseDetailMapper.selectByWhere(query);
		PageInfo<JddReleaseDetail> pageInfo = new PageInfo<JddReleaseDetail>(selectAll);
		return pageInfo;
	}

	@Override
	public PageInfo<JddReleaseDetail> queryPageListByWhereGroupBy(Integer page,
			Integer rows, JddReleaseDetailQuery query,
			List<String> orderBy) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		if (orderBy != null) {
			for (String orderString : orderBy) {
				PageHelper.orderBy(orderString);
			}
		}
		List<JddReleaseDetail> selectAll = jddReleaseDetailMapper.selectByWhereGroupBy(query);
		PageInfo<JddReleaseDetail> pageInfo = new PageInfo<JddReleaseDetail>(selectAll);
		return pageInfo;
	}

	@Override
	public List<JddReleaseDetail> selectByWhereGroupBy(
			JddReleaseDetailQuery query) {
		// TODO Auto-generated method stub
		return jddReleaseDetailMapper.selectByWhereGroupBy(query);
	}

	@Override
	public List<JddReleaseDetail> selectByWhere(JddReleaseDetailQuery query) {
		// TODO Auto-generated method stub
		return jddReleaseDetailMapper.selectByWhere(query);
	}

}
