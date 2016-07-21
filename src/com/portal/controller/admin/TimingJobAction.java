package com.portal.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddAccount;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddPayment;
import com.portal.bean.JddRelease;
import com.portal.bean.JddReleaseDetail;
import com.portal.bean.SysParameter;
import com.portal.bean.UserInfo;
import com.portal.bean.UserInfoQuery;
import com.portal.common.CommonUtil;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.mapper.CommonMapper;
import com.portal.service.BaseRecordService;
import com.portal.service.GeelyPortService;
import com.portal.service.JddAccountService;
import com.portal.service.JddPaymentService;
import com.portal.service.JddReleaseDetailService;
import com.portal.service.JddReleaseService;
import com.portal.service.ParameterService;
import com.portal.service.UserInfoService;

@Controller
public class TimingJobAction {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private JddAccountService jddAccountService;
	
	@Autowired
	private GeelyPortService webService;
	
	@Autowired
	BaseRecordService baseRecordService;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private JddPaymentService jddPaymentService;
	
	@Autowired
	private JddReleaseService jddReleaseService;

	@Autowired
	private JddReleaseDetailService jddReleaseDetailService;
	
	
	private Calendar cal = Calendar.getInstance();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	final static Log log = LogFactory.getLog(TimingJobAction.class);
	
	/**
	 * 定时任务
	 */
	public void startUpAssess() {
		log.info("###### system auto start ####### ");
		Integer currentMonth = cal.get(Calendar.MONTH) + 1;
		Integer currentDay = cal.get(Calendar.DAY_OF_MONTH);
		String crDay = currentMonth + "-" + currentDay;
		SysParameter paramInfo = parameterService.selectByParamName(SessionConstants.PARAMETER_YEARCLEARDATE);
		if(crDay.equals(paramInfo.getParamValue())){
			//年度清零
			log.info("###### system doYearClear start ####### ");
			doYearClear();
			log.info("###### system doYearClear end ####### ");

		}
		
		//离职信息同步
		log.info("###### system synLeaveInfo start ####### ");
		synLeaveInfo();
		log.info("###### system synLeaveInfo end ####### ");
		
		//离职清零
		log.info("###### system doLeaveClear start ####### ");
		doLeaveClear();
		log.info("###### system doLeaveClear end ####### ");
		
		//公司
		paramInfo = parameterService.selectByParamName(SessionConstants.PARAMETER_CELEBRATEDATE);
		if(crDay.equals(paramInfo.getParamValue())){
			//司庆发放
			log.info("###### system doCelebrateSend start ####### ");
			doCelebrateSend();
			log.info("###### system doCelebrateSend end ####### ");

		}
		
		//转正发放
		log.info("###### system doBeEmployeeSend start ####### ");
		doBeEmployeeSend();
		log.info("###### system doBeEmployeeSend end ####### ");
		
		//入职周年发放
		log.info("###### system doAnniversarySend start ####### ");
		doAnniversarySend();
		log.info("###### system doAnniversarySend end ####### ");
		
		log.info("###### system auto end ####### ");
		
	}

	/**
	 * 入职周年发放
	 */
	private void doAnniversarySend() {
		//获取所有需要转正发放的用户
		SysParameter paramInfo = parameterService.selectByParamName(SessionConstants.PARAMETER_ANNIVERSARYSENDNUM);
		if(paramInfo.getStatus() == 1){
			return;
		}
		Integer sendNum = Integer.valueOf(paramInfo.getParamValue());
		String context = null;
		List<GeelyUserInfo> userInfoList = null;
		
		for (int i = 0; i < 100; i++) {
//			if(cal.get(Calendar.YEAR) == 2015){
			if(cal.get(Calendar.YEAR) == 1986){
				break;
			}
			cal.add(Calendar.YEAR, -1);
			context = "A" + sdf.format(cal.getTime());
			userInfoList = webService.getUserInfoByDate(context, false);
//			List<GeelyUserInfo> userInfoList = new ArrayList<GeelyUserInfo>();
//			GeelyUserInfo aa1 = new GeelyUserInfo();
//			aa1.setUserId("0045725");
//			aa1.setCompanyName("易云公司");
//			userInfoList.add(aa1);
//			GeelyUserInfo aa2 = new GeelyUserInfo();
//			aa1.setUserId("0052311");
//			aa1.setCompanyName("易云公司");
//			userInfoList.add(aa2);
			
			if(CommonUtil.isNotEmpty(userInfoList)){
				for(GeelyUserInfo userInfo : userInfoList){
					if(userInfoService.isExistUser(userInfo.getUserId()) > 0){
						JddRelease release = new JddRelease();
						release.setGuid(StringUtil.getGUID());
						release.setCreateBy("SYSTEM");
						release.setCreateName("系统");
						release.setCreateTime(new Date());
						release.setCheckBy("SYSTEM");
						release.setCheckName("系统");
						release.setCheckTime(new Date());
						release.setStstus(2);
						release.setContent("系统自动生成");
						release.setSource("所属公司");
						if(jddReleaseService.insert(release) > 0){
							JddReleaseDetail detail = new JddReleaseDetail();
							detail.setGuid(StringUtil.getGUID());
							detail.setReleaseId(release.getGuid());
							detail.setUserId(userInfo.getUserId());
							detail.setUserName(userInfo.getName());
							detail.setJddCount(sendNum);
							detail.setReason("入职周年发放");
							detail.setRuleChild(SessionConstants.RULE_ANNIVERSARY_GUID);
							detail.setSource("所属公司");
							detail.setSourceComp(userInfo.getCompanyName());
							jddReleaseDetailService.insert(detail);

							//添加收入记录并修改账户信息
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("as_userid", userInfo.getUserId());
							map.put("as_guid", StringUtil.getGUID());
							map.put("as_relationid", detail.getGuid());
							map.put("an_jddnum", sendNum);
							map.put("as_rulechid", SessionConstants.RULE_ANNIVERSARY_GUID);
							map.put("as_reason", "入职周年发放");
							map.put("an_result", 0);
							map.put("as_remess", null);
							commonMapper.jddbaserecord(map);
						}
					}
				}
			}
			
		}
		
	}

	/**
	 * 转正发放
	 */
	private void doBeEmployeeSend() {
		//获取所有需要转正发放的用户
		SysParameter paramInfo = parameterService.selectByParamName(SessionConstants.PARAMETER_BEEMPLOYEESENDNUM);
		if(paramInfo.getStatus() == 1){
			return;
		}
		Integer sendNum = Integer.valueOf(paramInfo.getParamValue());

		cal.add(Calendar.MONTH, -3);
		String context = "A" + sdf.format(cal.getTime());
		cal.add(Calendar.MONTH, 3);
		List<GeelyUserInfo> userInfoList = webService.getUserInfoByDate(context, false);
//		List<String> userIdList = new ArrayList<>();
//		userIdList.add("0045725");
//		userIdList.add("0070606");

		if(CommonUtil.isNotEmpty(userInfoList)){
			UserInfo userInfo = new UserInfo();
			for(GeelyUserInfo info : userInfoList){
				userInfo.setUserId(info.getUserId());
				userInfo.setStatus(12);
				if(userInfoService.updateByUserId(userInfo) > 0){
					JddRelease release = new JddRelease();
					release.setGuid(StringUtil.getGUID());
					release.setCreateBy("SYSTEM");
					release.setCreateName("系统");
					release.setCreateTime(new Date());
					release.setCheckBy("SYSTEM");
					release.setCheckName("系统");
					release.setCheckTime(new Date());
					release.setStstus(2);
					release.setContent("系统自动生成");
					release.setSource("所属公司");
					if(jddReleaseService.insert(release) > 0){
						JddReleaseDetail detail = new JddReleaseDetail();
						detail.setGuid(StringUtil.getGUID());
						detail.setReleaseId(release.getGuid());
						detail.setUserId(userInfo.getUserId());
						detail.setUserName(userInfo.getUserName());
						detail.setJddCount(sendNum);
						detail.setReason("转正发放");
						detail.setRuleChild(SessionConstants.RULE_TOEMPOLYEE_GUID);
						detail.setSource("所属公司");
						detail.setSourceComp(userInfo.getUserCom());
						jddReleaseDetailService.insert(detail);

						//添加收入记录并修改账户信息
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("as_userid", userInfo.getUserId());
						map.put("as_guid", StringUtil.getGUID());
						map.put("as_relationid", detail.getGuid());
						map.put("an_jddnum", sendNum);
						map.put("as_rulechid", SessionConstants.RULE_TOEMPOLYEE_GUID);
						map.put("as_reason", "转正发放");
						map.put("an_result", 0);
						map.put("as_remess", null);
						commonMapper.jddbaserecord(map);
					}


				}

			}
		}
	}

	/**
	 * 司庆发放
	 */
	private void doCelebrateSend() {
		//获取所有需要清零的用户
		SysParameter paramInfo = parameterService.selectByParamName(SessionConstants.PARAMETER_CELEBRATESENDNUM);
		if(paramInfo.getStatus() == 1){
			return;
		}
		Integer sendNum = Integer.valueOf(paramInfo.getParamValue());
		UserInfoQuery query = new UserInfoQuery();

		query.setCelebrateSendFlag(1);
		List<UserInfo> UserInfoList = userInfoService.selectByWhere(query);
		
		for(UserInfo userInfo : UserInfoList){
			//添加发放记录和发放明细
			JddRelease release = new JddRelease();
			release.setGuid(StringUtil.getGUID());
				release.setCreateBy("SYSTEM");
				release.setCreateName("系统");
				release.setCreateTime(new Date());
				release.setCheckBy("SYSTEM");
				release.setCheckName("系统");
				release.setCheckTime(new Date());
				release.setStstus(2);
				release.setContent("系统自动生成");
				release.setSource("所属公司");
				if(jddReleaseService.insert(release) > 0){
					JddReleaseDetail detail = new JddReleaseDetail();
					detail.setGuid(StringUtil.getGUID());
					detail.setReleaseId(release.getGuid());
					detail.setUserId(userInfo.getUserId());
					detail.setUserName(userInfo.getUserName());
					detail.setJddCount(sendNum);
					detail.setReason("司庆赠送");
					detail.setRuleChild(SessionConstants.RULE_CELEBRATE_GUID);
					detail.setSource("所属公司");
					detail.setSourceComp(userInfo.getUserCom());
					jddReleaseDetailService.insert(detail);
					
					//添加收入记录并修改账户信息
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("as_userid", userInfo.getUserId());
					map.put("as_guid", StringUtil.getGUID());
					map.put("as_relationid", detail.getGuid());
					map.put("an_jddnum", sendNum);
					map.put("as_rulechid", SessionConstants.RULE_CELEBRATE_GUID);
					map.put("as_reason", "司庆赠送");
					map.put("an_result", 0);
					map.put("as_remess", null);
					commonMapper.jddbaserecord(map);
				}
		}
		
	}

	/**
	 * 离职人员信息同步
	 */
	private void synLeaveInfo() {
		String context = sdf.format(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, -3);
		
		for (int i = 0; i < 3; i++) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			context = "I" + sdf.format(cal.getTime());
//			context = "I20140312";
			List<GeelyUserInfo> userInfoList = webService.getUserInfoByDate(context, true);
//			List<String> UserIdList = new ArrayList<>();
//			UserIdList.add("0045725");
//			UserIdList.add("0070606");
			
			if(CommonUtil.isNotEmpty(userInfoList)){
				UserInfo userInfo = new UserInfo(); 
				for(GeelyUserInfo info : userInfoList){
					userInfo.setUserId(info.getUserId());
					userInfo.setStatus(22);
					userInfoService.updateByUserId(userInfo);
				}
			}
			
		}
		
	}

	/**
	 * 年度清零
	 */
	private void doYearClear() {
		//获取所有需要清零的用户
		UserInfoQuery query = new UserInfoQuery();
		query.setStatus(13);
		query.setYearClearedFlag(1);
		List<UserInfo> UserInfoList = userInfoService.selectByWhere(query);
		
		Integer count = 0;
		
		String relationId ="";
		
		if(CommonUtil.isNotEmpty(UserInfoList)){
			for(UserInfo userInfo : UserInfoList){
				count = userInfo.getLyNum() - userInfo.getLyCostNum();
				//更新账户				
				JddAccount jddAccount = new JddAccount();
				jddAccount.setGuid(userInfo.getAccountGuid());
				jddAccount.setLyCostNum(userInfo.getLyNum());
				if(jddAccountService.update(jddAccount) > 0){
					//添加支出记录
					relationId = StringUtil.getGUID();
					JddPayment jddPayment = new JddPayment();
					jddPayment.setGuid(relationId);
					jddPayment.setUserId(userInfo.getUserId());
					jddPayment.setUserName(userInfo.getUserName());
					jddPayment.setCount(count);
					jddPayment.setReason("年度清零");
					jddPayment.setRuleChild(SessionConstants.RULE_LEAVE_CHILD_GUID);
					jddPayment.setCreateTime(new Date());
					jddPaymentService.saveSelective(jddPayment);
					
					//添加收支记录
					JddBaserecord jddBaserecord = new JddBaserecord();
					jddBaserecord.setGuid(StringUtil.getGUID());
					jddBaserecord.setRelationId(relationId);
					jddBaserecord.setRuleType(1);
					jddBaserecord.setCount(count);
					jddBaserecord.setReason("年度清零");
					jddBaserecord.setCreateBy(userInfo.getUserId());
					jddBaserecord.setCreateName("系统");
					jddBaserecord.setCreateTime(new Date());
					jddBaserecord.setRuleGrad(SessionConstants.RULE_SYS_GRAD_GUID);
					jddBaserecord.setRuleFather(SessionConstants.RULE_SYS_FATHER_GUID);
					jddBaserecord.setRuleChild(SessionConstants.RULE_YEAR_CHILD_GUID);
					jddBaserecord.setSource("");
					jddBaserecord.setIsDeleted(0);
					
					baseRecordService.insert(jddBaserecord);
				}
				
			}
				
		}
		
	}
	
	/**
	 * 离职清零
	 */
	private void doLeaveClear() {
		//获取所有需要清零的离职用户
		UserInfoQuery query = new UserInfoQuery();
		query.setStatus(22);
		query.setLeaveClearedFlag(1);
		List<UserInfo> UserInfoList = userInfoService.selectByWhere(query);
		
		Integer lyCount = 0;
		Integer cyCount = 0;
		String relationId = "";
		
		if(CommonUtil.isNotEmpty(UserInfoList)){
			for(UserInfo userInfo : UserInfoList){
				lyCount = userInfo.getLyNum() - userInfo.getLyCostNum();
				cyCount = userInfo.getCyNum() - userInfo.getCyCostNum();
				//更新账户
				JddAccount jddAccount = new JddAccount();
				jddAccount.setGuid(userInfo.getAccountGuid());
				jddAccount.setLyCostNum(userInfo.getLyNum());
				jddAccount.setCyCostNum(userInfo.getCyNum());
				if(jddAccountService.update(jddAccount) > 0){
					//添加支出记录
					relationId = StringUtil.getGUID();
					JddPayment jddPayment = new JddPayment();
					jddPayment.setGuid(relationId);
					jddPayment.setUserId(userInfo.getUserId());
					jddPayment.setUserName(userInfo.getUserName());
					jddPayment.setCount(lyCount + cyCount);
					jddPayment.setReason("离职清零");
					jddPayment.setRuleChild(SessionConstants.RULE_LEAVE_CHILD_GUID);
					jddPayment.setCreateTime(new Date());
					jddPaymentService.saveSelective(jddPayment);
					
					//添加收支记录
					JddBaserecord jddBaserecord = new JddBaserecord();
					jddBaserecord.setGuid(StringUtil.getGUID());
					jddBaserecord.setRelationId("");
					jddBaserecord.setRuleType(1);
					jddBaserecord.setCount(lyCount + cyCount);
					jddBaserecord.setReason("离职清零");
					jddBaserecord.setCreateBy(userInfo.getUserId());
					jddBaserecord.setCreateName("系统");
					jddBaserecord.setCreateTime(new Date());
					jddBaserecord.setRuleGrad(SessionConstants.RULE_SYS_GRAD_GUID);
					jddBaserecord.setRuleFather(SessionConstants.RULE_SYS_FATHER_GUID);
					jddBaserecord.setRuleChild(SessionConstants.RULE_LEAVE_CHILD_GUID);
					jddBaserecord.setSource("");
					jddBaserecord.setIsDeleted(0);
					
					baseRecordService.insert(jddBaserecord);
				}
				

				
			}
				
		}
		
	}



}