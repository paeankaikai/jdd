package com.portal.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddRelease;
import com.portal.bean.JddReleaseExample;
import com.portal.bean.JddReleaseExample.Criteria;
import com.portal.common.Page;
import com.portal.common.StringUtil;
import com.portal.mapper.JddReleaseMapper;
import com.portal.service.JddReleaseService;

@Service
public class JddReleaseServiceImpl implements JddReleaseService {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private JddReleaseMapper jddReleaseMapper;

	@Override
	public int insert(JddRelease jddRelease) {
		return jddReleaseMapper.insert(jddRelease);
	}

	@Override
	public int countJddRelease() {
		JddReleaseExample example=new JddReleaseExample();		
		return jddReleaseMapper.countByExample(example);
	}

	@Override
	public PageInfo<JddRelease> selectJddReleaseByExample(Page page, String startDate,
			String endDate, String content,String createName,Integer ststus,String UserGuid) {
		PageHelper.startPage(Integer.parseInt(page.getPageNum()),
				Integer.parseInt(page.getNumPerPage()));
		PageHelper.orderBy("create_time desc");
		JddReleaseExample example = new JddReleaseExample();
		Criteria criteria = example.createCriteria();
		try {
			if (!StringUtil.isEmpty(startDate)) {
				criteria.andCreateTimeGreaterThanOrEqualTo(sdf.parse(startDate+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(endDate)) {
				criteria.andCreateTimeLessThanOrEqualTo(sdf.parse(endDate+" 23:59:59"));
			}
			if (!StringUtil.isEmpty(content)) {
				criteria.andContentLike("%" + content + "%");
			}
			if(!StringUtil.isEmpty(createName)){
				criteria.andCreateNameLike("%" + createName + "%");
			}
			if(ststus!=null&&ststus>=0){
				criteria.andStstusEqualTo(ststus);
			}
			if(!StringUtil.isEmpty(UserGuid)){
				criteria.andCreateByEqualTo(UserGuid);
			}
			List<JddRelease> list = jddReleaseMapper.selectByExample(example);
			return new PageInfo<JddRelease>(list);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public JddRelease selectByPrimaryKey(String guid) {
		return jddReleaseMapper.selectByPrimaryKey(guid);
	}

	@Override
	public int updateByPrimaryKeySelective(JddRelease release) {		
		return jddReleaseMapper.updateByPrimaryKeySelective(release);
	}
}
