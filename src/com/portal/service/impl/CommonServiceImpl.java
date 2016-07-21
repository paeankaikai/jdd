package com.portal.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddJkrecord;
import com.portal.bean.JddRelease;
import com.portal.bean.JddReleaseDetail;
import com.portal.bean.LoginVerify;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.mapper.CommonMapper;
import com.portal.mapper.JddReleaseMapper;
import com.portal.mapper.LoginVerifyMapper;
import com.portal.service.CommonService;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonMapper commonMapper;

	@Autowired
	private JddReleaseMapper jddReleaseMapper;
	
	@Autowired
	LoginVerifyMapper loginVerifyMapper;

	@Override
	public Integer jddbaserecord(List<JddReleaseDetail> details,HttpServletRequest request) {

		Iterator<JddReleaseDetail> iterator = details.iterator();
		String releaseGuid = null;
		while (iterator.hasNext()) {
			JddReleaseDetail detail = iterator.next();
			releaseGuid = detail.getReleaseId();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("as_userid", detail.getUserId());
			map.put("as_guid", StringUtil.getGUID());
			map.put("as_relationid", detail.getGuid());
			map.put("an_jddnum", detail.getJddCount());
			map.put("as_rulechid", detail.getRuleChild());
			map.put("as_reason", detail.getReason());
			map.put("an_result", 0);
			map.put("as_remess", null);
			commonMapper.jddbaserecord(map);
			// 回滚
			if (Double.parseDouble(map.get("an_result").toString()) == 0) {
				throw new RuntimeException("保存失败");
			}
		}
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		if (!StringUtil.isEmpty(releaseGuid)) {
			JddRelease release = new JddRelease();
			release.setStstus(2);
			release.setGuid(releaseGuid);
			release.setCheckBy(userInfo.getUserId());
			release.setCheckName(userInfo.getName());
			release.setCheckTime(new Date());
			return jddReleaseMapper.updateByPrimaryKeySelective(release);
		}
		return 0;

	}

	@Override
	public Integer jdduserinit(GeelyUserInfo geelyUserInfo) throws ParseException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("as_userid", geelyUserInfo.getUserId());
		map.put("as_username", geelyUserInfo.getName());
		map.put("as_usercomp", geelyUserInfo.getCompanyCode());
		map.put("an_status", getUserStatus(geelyUserInfo.getJoinDate(), geelyUserInfo.getLeaveDate()));
		map.put("as_usercompdesc", geelyUserInfo.getCompanyNameDesc());
		String position = geelyUserInfo.getPosition();
		if (!StringUtil.isEmpty(position)) {
			position = position.substring(position.indexOf("-") + 1);
		}
		map.put("as_userposition", position);
		map.put("as_userdept", geelyUserInfo.getUserDept());
		map.put("an_result", 0);
		map.put("as_remess", "");
		commonMapper.jdduserinit(map);
		// 回滚
		if (Double.parseDouble(map.get("an_result").toString()) == 0) {
			throw new RuntimeException("初始化失败");
		}
		return 0;
	}

	/**
	 * 根据接口中的入职时间和离职时间来判断用户状态 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月16日 上午9:54:18
	 * @param joinDate
	 * @param leaveDate
	 * @return
	 * @throws ParseException
	 */
	public int getUserStatus(String joinDate, String leaveDate) throws ParseException {
		if (!StringUtil.isEmpty(leaveDate)) {
			return 22;
		}
		String geshi = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(geshi);
		Date join = sdf.parse(joinDate);

		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());
		rightNow.add(Calendar.MONTH, -3);
		// 11 未转正
		if (join.after(rightNow.getTime())) {
			return 11;
		}
		// 12 转正未满一年
		rightNow.add(Calendar.MONTH, -9);
		if (join.after(rightNow.getTime())) {
			return 12;
		}
		// 13 转正满一年
		else {
			return 13;
		}
	}

	public Integer exchange(JddJkrecord jkRecord,GeelyUserInfo userInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("as_userid", userInfo.getUserId());
		map.put("as_username", userInfo.getName());
		map.put("an_jknum", jkRecord.getJkCount());
		map.put("an_jddnum", jkRecord.getCount());
		map.put("as_reason", jkRecord.getReason());
		map.put("an_result", 0);
		map.put("as_remess", "");
		commonMapper.exchange(map);
		// 回滚
		if (Double.parseDouble(map.get("an_result").toString()) == 0) {
			throw new RuntimeException("初始化失败");
		}
		return 1;
	}


	@Override
	public Integer callbackVerifyInsert(LoginVerify record) {
		// TODO Auto-generated method stub
		return loginVerifyMapper.insert(record);
	}

	@Override
	public LoginVerify getCallbackVerify(String guid) {
		// TODO Auto-generated method stub
		return loginVerifyMapper.queryByGuid(guid);
	}

}
