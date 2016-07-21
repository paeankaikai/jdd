package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.JkReleaseDetail;
import com.portal.bean.JkReleaseDetailExample;
import com.portal.bean.JkReleaseDetailExample.Criteria;
import com.portal.mapper.JkReleaseDetailMapper;
import com.portal.service.JkReleaseDetailService;

@Service
public class JkReleaseDetailServiceImpl implements JkReleaseDetailService {

	@Autowired
	private JkReleaseDetailMapper jkReleaseDetailMapper;

	@Override
	public int insert(JkReleaseDetail detail) {
		return jkReleaseDetailMapper.insert(detail);
	}

	@Override
	public List<JkReleaseDetail> selectByReleaseGuid(String releaseGuid) {
		JkReleaseDetailExample example = new JkReleaseDetailExample();
		Criteria criteria=example.createCriteria();
		criteria.andReleaseIdEqualTo(releaseGuid.trim());
		return jkReleaseDetailMapper.selectByExample(example);
	}

}
