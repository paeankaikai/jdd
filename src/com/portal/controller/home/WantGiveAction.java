package com.portal.controller.home;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddAccount;
import com.portal.bean.JddPresent;
import com.portal.bean.JddPresentQuery;
import com.portal.bean.JddPresentTotalInfo;
import com.portal.bean.MicroSendRuleInfo;
import com.portal.bean.SysParameter;
import com.portal.common.CommonUtil;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.mapper.CommonMapper;
import com.portal.service.JddAccountService;
import com.portal.service.JddPresentService;
import com.portal.service.ParameterService;
import com.portal.service.RuleService;
import com.portal.service.UserInfoService;

@Controller
@RequestMapping("home/want_g")
public class WantGiveAction  {

	@Autowired
	private JddPresentService jddPresentService;
	
	@Autowired
	private JddAccountService jddAccountService;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private UserInfoService infoService;
	

	/**
	 * 跳转我要送页面
	 * @return 请求页面
	 */
	@RequestMapping(value = "/showWantGive", method = RequestMethod.GET)
	public ModelAndView showSetting(HttpServletRequest request) {
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		if(userInfo == null){
			ModelAndView mav = new ModelAndView("login");
			return mav;
		}
				
		//获取统计数据
		JddPresentTotalInfo totalInfo = jddPresentService.queryTotalInfo(userInfo.getUserId());		
		
		ModelAndView mav = new ModelAndView("home/wantGive");
		mav.addObject("totalInfo", totalInfo);
		
		return mav;

	}
	
	/**
	 * 微送记录查询
	 * @param request
	 * @param response
	 * @return 查询结果
	 */
	@RequestMapping("/doMicroSendSearch")
	@ResponseBody
	public Object doMicroSendSearch(HttpServletRequest request, HttpServletResponse response) {	
		JsonObject jsonObject = new JsonObject();
		String recordType = request.getParameter("recordType")==null?"":request.getParameter("recordType");
		String userName = request.getParameter("userName")==null?"":request.getParameter("userName");
		String startDate = request.getParameter("startDate")==null?"":request.getParameter("startDate");
		String endDate = request.getParameter("endDate")==null?"":request.getParameter("endDate");
		Integer pageNum = 1;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.valueOf(request.getParameter("pageNum"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		
		if(userInfo == null){
			ModelAndView mav = new ModelAndView("login");
			return mav;
		}
		
		JddPresentQuery jddPresentQuery = new JddPresentQuery();
		
		if(recordType.equals("1")){
			jddPresentQuery.setToUserName(userName);
			jddPresentQuery.setFromUserId(userInfo.getUserId());
		}
		
		if(recordType.equals("2")){
			jddPresentQuery.setFromUserName(userName);
			jddPresentQuery.setToUserId(userInfo.getUserId());
		}
		
		if(!startDate.equals("")){
			try {
				jddPresentQuery.setStartDate(sdf.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonObject.addProperty("success", false);
				return jsonObject.toString();
			}
		}
		
		if(!endDate.equals("")){
			try {
				jddPresentQuery.setEndDate(sdf.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonObject.addProperty("success", false);
				return jsonObject.toString();
			}
		}
		
		PageInfo<JddPresent> pageInfo = null;
		
		List<String> orderBy = new ArrayList<String>();
		orderBy.add("CREATE_TIME desc");
		pageInfo = jddPresentService.queryPageListByWhere(pageNum, SessionConstants.PAGE_SHOW_NUM, jddPresentQuery, orderBy);

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("success", true);
		data.put("resultPageInfo", pageInfo);

		return data;
	}
	
	@RequestMapping("/getDefaultInfo")
	@ResponseBody
	public Object getDefaultInfo(HttpServletRequest request) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		
		if(userInfo == null){
			data.put("success", false);
			return data;
		}
		
		//获取限制信息
		MicroSendRuleInfo microSendRuleInfo = new MicroSendRuleInfo();
		SysParameter countLimitInfo = parameterService.selectByParamName(SessionConstants.MICROSEND_PARAMETER_MICROSENDCOUNT);
		microSendRuleInfo.setMicroSendCount(countLimitInfo.getParamValue());
		microSendRuleInfo.setMicroSendCountEnable(countLimitInfo.getStatus());
		SysParameter numLimitInfo = parameterService.selectByParamName(SessionConstants.MICROSEND_PARAMETER_MICROSENDNUM);
		microSendRuleInfo.setMicroSendNum(numLimitInfo.getParamValue());
		microSendRuleInfo.setMicroSendNumEnable(numLimitInfo.getStatus());
		data.put("microSendRuleInfo", microSendRuleInfo);
		
		//获取当月发送记录
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date()); 
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date startDate = cal.getTime();
		cal.roll(Calendar.DAY_OF_MONTH, -1);  
		Date endDate = cal.getTime();

		JddPresentQuery jddPresentQuery = new JddPresentQuery();
		jddPresentQuery.setFromUserId(userInfo.getUserId());
		jddPresentQuery.setStartDate(startDate);
		jddPresentQuery.setEndDate(endDate);
		  
		List<JddPresent> presentList = jddPresentService.queryListByWhere(jddPresentQuery);
		
		Integer giveCount = 0;
		Integer giveNum = 0;
		if(CommonUtil.isNotEmpty(presentList)){
			giveCount = presentList.size();
			for(JddPresent present : presentList){
				giveNum += present.getCount();
			}
		}
		
		data.put("giveCount", giveCount);
		data.put("giveNum", giveNum);
		
		data.put("success", true);
		return data;
	}

	@RequestMapping("/doUserValidator")
	@ResponseBody
	public Object doUserValidator(HttpServletRequest request, @RequestParam("toUserId") String toUserId) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		if(userInfo == null){
			data.put("success", false);
			data.put("errorType", 0);
			return data;
		}
		
		if(userInfo.getUserId().equals(toUserId)){
			data.put("success", false);
			data.put("errorType", 1);
			return data;
		}
		
		JddAccount jddAccount = jddAccountService.queryByUserId(toUserId);
		if(jddAccount == null){
			data.put("success", false);
			data.put("errorType", 3);
			return data;
		}
		
		data.put("success", true);
		data.put("userName", jddAccount.getUserName());
		data.put("userCom", jddAccount.getUserCom());
		return data;
	}
	
	@RequestMapping("/doAccountValidator")
	@ResponseBody
	public Object doAccountValidator(HttpServletRequest request) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		if(userInfo == null){
			data.put("success", false);
			return data;
		}
		
		//获取账户余额吉点点
		JddAccount jddAccount = jddAccountService.queryByUserId(userInfo.getUserId());
		if(jddAccount == null){
			data.put("success", false);
			return data;
		}else{
			data.put("hasJddNum", jddAccount.getCyNum() - jddAccount.getCyCostNum());
		}
		
		data.put("success", true);
		return data;
	}
	
	@RequestMapping("/doJddGive")
	@ResponseBody
	public Object doJddGive(HttpServletRequest request,HttpServletResponse response) {
		String toUserId = request.getParameter("toUserId")==null?"":request.getParameter("toUserId");
		String toUserName = request.getParameter("toUserName")==null?"":request.getParameter("toUserName");
		String toUserCom = request.getParameter("toUserCom")==null?"":request.getParameter("toUserCom");
		String toReason = request.getParameter("toReason")==null?"":request.getParameter("toReason");
		
		Integer toGiveNum = 0;
		if (request.getParameter("toGiveNum") != null) {
			toGiveNum = Integer.valueOf(request.getParameter("toGiveNum"));
		}
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		if(userInfo == null){
			data.put("success", false);
			return data;
		}
		
		//添加微送记录
		JddPresent presentInfo = new JddPresent();
		
		String guid = StringUtil.getGUID();
		presentInfo.setGuid(guid);
		presentInfo.setFromUserId(userInfo.getUserId());
		presentInfo.setFromUserName(userInfo.getName());
		presentInfo.setFromUserCom(userInfo.getCompanyName());
		presentInfo.setToUserId(toUserId);
		presentInfo.setToUserName(toUserName);
		
		presentInfo.setToUserCom(infoService.selectByUserId(toUserId).getUserCom());
		presentInfo.setFromUserCom(userInfo.getCompanyNameDesc());
		
		presentInfo.setCount(toGiveNum);
		presentInfo.setReason(toReason);
		presentInfo.setCreateTime(new Date());
		
		jddPresentService.save(presentInfo);
		
		
//		//获取微送支出规则
//		JddRule outRule = ruleService.findRuleByGUID(SessionConstants.RULE_MICRO_OUT_GUID);
//		
//		//获取微送收入规则
//		JddRule inRule = ruleService.findRuleByGUID(SessionConstants.RULE_MICRO_IN_GUID);
		
		//添加收入记录并修改账户信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("as_userid", userInfo.getUserId());
		map.put("as_guid", StringUtil.getGUID());
		map.put("as_relationid", guid);
		map.put("an_jddnum", toGiveNum);
		map.put("as_rulechid", SessionConstants.RULE_MICRO_OUT_GUID);
		map.put("as_reason", toReason);
		map.put("an_result", 0);
		map.put("as_remess", null);
		commonMapper.jddbaserecord(map);
		
		//添加支出记录并修改账户信息
		map.put("as_userid", toUserId);
		map.put("as_guid", StringUtil.getGUID());
		map.put("as_rulechid", SessionConstants.RULE_MICRO_IN_GUID);
		commonMapper.jddbaserecord(map);
		
		data.put("success", true);
		return data;
	}
	

}
