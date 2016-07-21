package com.portal.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.bean.JkRelease;
import com.portal.bean.JkReleaseExample;
import com.portal.bean.JkReleaseExample.Criteria;
import com.portal.common.Page;
import com.portal.common.StringUtil;
import com.portal.mapper.JkReleaseMapper;
import com.portal.service.JkReleaseService;

@Service
public class JkReleaseServiceImpl implements JkReleaseService {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private JkReleaseMapper jkReleaseMapper;

	@Override
	public PageInfo<JkRelease> selectJkReleaseByExample(Page page, String startDate,
			String endDate, String createName,Integer ststus,String userGuid) {
		PageHelper.startPage(Integer.parseInt(page.getPageNum()),Integer.parseInt(page.getNumPerPage()));
		PageHelper.orderBy("create_time desc");
		JkReleaseExample example = new JkReleaseExample();
		Criteria criteria = example.createCriteria();
		try {
			if (!StringUtil.isEmpty(startDate)) {
				criteria.andCreateTimeGreaterThanOrEqualTo(sdf.parse(startDate.trim() + " 00:00:00"));
			}
			if (!StringUtil.isEmpty(endDate)) {
				criteria.andCreateTimeLessThanOrEqualTo(sdf.parse(endDate.trim() + " 23:59:59"));
			}
			if (!StringUtil.isEmpty(createName)) {
				criteria.andCreateNameLike("%" + createName.trim() + "%");
			}
			if(ststus!=null&& ststus>=0){
				criteria.andStstusEqualTo(ststus);
			}
			if(!StringUtil.isEmpty(userGuid)){
				criteria.andCreateByEqualTo(userGuid);
			}
			List<JkRelease> list = jkReleaseMapper.selectByExample(example);
			return new PageInfo<JkRelease>(list);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(JkRelease release) {
		return jkReleaseMapper.insertSelective(release);
	}

	@Override
	public JkRelease selectByPrimaryKey(String guid) {		
		return jkReleaseMapper.selectByPrimaryKey(guid);
	}

	@Override
	public int updateStatus(JkRelease release) {
		return jkReleaseMapper.updateByPrimaryKeySelective(release);
	}

}
