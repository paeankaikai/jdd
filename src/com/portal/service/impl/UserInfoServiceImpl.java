package com.portal.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddAccount;
import com.portal.bean.JkRelease;
import com.portal.bean.JkReleaseDetail;
import com.portal.bean.UserInfo;
import com.portal.bean.UserInfoExample;
import com.portal.bean.UserInfoExample.Criteria;
import com.portal.bean.UserInfoQuery;
import com.portal.common.CommonUtil;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.core.cache.CacheUtils;
import com.portal.mapper.JddAccountMapper;
import com.portal.mapper.JkReleaseMapper;
import com.portal.mapper.UserInfoMapper;
import com.portal.service.JkReleaseService;
import com.portal.service.UserInfoService;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private JkReleaseMapper jkReleaseMapper;

	@Autowired
	private JddAccountMapper jddAccountMapper;

	public UserInfo selectByUserId(String userId) {
		UserInfoExample example = new UserInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId.trim());
		List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
		if (StringUtil.isNotNull(userInfos)) {
			return userInfos.get(0);
		}
		return null;

	}

	@Override
	public int insertUserInfo(UserInfo userInfo) {
		return userInfoMapper.insert(userInfo);
	}

	@Override
	public int insertUserInfoFromGeely(GeelyUserInfo userInfo) {
		UserInfo user = new UserInfo();
		JddAccount account = new JddAccount();
		try {
			user.setGuid(UUID.randomUUID().toString());
			user.setUserId(userInfo.getUserId());
			user.setUserName(userInfo.getName());
			user.setUserCom(userInfo.getCompanyNameDesc());
			user.setUserUnit(userInfo.getCompanyCode());
			String position=userInfo.getPosition();
			user.setUserPosition(position.substring(position.indexOf("-")+1));
			user.setJkSum(0);
			user.setUsedJkSum(0);
			user.setStatus(getUserStatus(userInfo.getJoinDate(), userInfo.getLeaveDate()));
			user.setUserDept(userInfo.getUserDept());
			insertUserInfo(user);

			account.setGuid(StringUtil.getGUID());
			account.setUserId(userInfo.getUserId());
			account.setUserName(userInfo.getName());
			account.setCyCostNum(0);
			account.setCyNum(0);
			account.setLyNum(0);
			account.setLyCostNum(0);
			account.setYearNo(CommonUtil.dateFormat(new Date(), "yyyy"));
			jddAccountMapper.insert(account);
		} catch (ParseException e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	}

	public int updateJkSum(JkReleaseDetail detail) {
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

	@Override
	public int updateJkSum(List<JkReleaseDetail> details,HttpServletRequest request) {
		Iterator<JkReleaseDetail> iterator = details.iterator();
		String releaseGuid = null;
		while (iterator.hasNext()) {
			JkReleaseDetail detail = iterator.next();
			releaseGuid = detail.getReleaseId();
			UserInfo userInfo = selectByUserId(detail.getUserId());
			userInfo.setJkSum(userInfo.getJkSum() + detail.getJkCount());
			if (userInfoMapper.updateByPrimaryKeySelective(userInfo) == 0) {
				throw new RuntimeException("保存失败");
			}
		}
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		JkRelease release = new JkRelease();
		release.setStstus(2);
		release.setGuid(releaseGuid);
		release.setCheckBy(userInfo.getUserId());
		release.setCheckName(userInfo.getName());
		release.setCheckTime(new Date());
		jkReleaseMapper.updateByPrimaryKeySelective(release);
		return 2;
	}

	@Override
	public List<UserInfo> selectByWhere(UserInfoQuery query) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByWhere(query);
	}

	@Override
	public int updateByUserId(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.updateByUserIdSelective(userInfo);
	}

	@Override
	public int isExistUser(String userId) {
		// TODO Auto-generated method stub
		return userInfoMapper.isExistUser(userId);
	}

	@Override
	public List<UserInfo> selectByUserCom(String userCom) {
		UserInfoExample example = new UserInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserComEqualTo(userCom);
		return userInfoMapper.selectByExample(example);
	}

}
