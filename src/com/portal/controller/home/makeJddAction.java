package com.portal.controller.home;

import java.util.HashMap;
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
import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddJkrecord;
import com.portal.bean.JddJkrecordQuery;
import com.portal.bean.SysParameter;
import com.portal.bean.UserInfo;
import com.portal.common.SessionConstants;
import com.portal.core.cache.CacheUtils;
import com.portal.service.CommonService;
import com.portal.service.JddJkRecordService;
import com.portal.service.UserInfoService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/home/makeJdd/")
public class makeJddAction {

	@Autowired
	public JddJkRecordService jddJkRecordService;

	@Autowired
	private UserInfoService userInfoService;
	
	
	
	@Autowired
	private CommonService commonService;

	/**
	 * 兑换吉点点默认页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("makeJdd.do")
	public ModelAndView home(ModelAndView model,HttpServletRequest request) {
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		UserInfo user = userInfoService.selectByUserId(userInfo.getUserId());
		SysParameter parameter = CacheUtils.get(SessionConstants.CHANGENUM);
		//(SysParameter) request.getSession().getAttribute(SessionConstants.CHANGENUM);
		int count = user.getJkSum() - user.getUsedJkSum();
		model.addObject("usedjkcount", user.getUsedJkSum());
		model.addObject("jkcount", count);
		model.addObject("changeNum", Integer.parseInt(parameter.getParamValue()));
		model.addObject("page", "makeJdd");
		model.setViewName("/home/makeJdd");
		return model;
	}

	/**
	 * 吉点点兑换记录
	 */
	@RequestMapping("jddEXrecord.do")
	@ResponseBody
	public Map<String, Object> jddEXrecord(HttpServletRequest request, HttpServletResponse response,
			ModelAndView model,
			@RequestParam(value = "page", defaultValue = "1") String page,
			@RequestParam(value = "rows", defaultValue = "10") String rows) {
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		JddJkrecordQuery query=new JddJkrecordQuery();
		query.setUserId(userInfo.getUserId());
		Map<String, Object> data = new HashMap<String, Object>();
		PageInfo<JddJkrecord> pageRows = jddJkRecordService.queryPageNum(Integer.parseInt(page),
				Integer.parseInt(rows), query, null);
		data.put("total", pageRows.getTotal());
		data.put("rows", pageRows.getList());
		return data;
	}

	/**
	 * 吉点点刷新
	 */
	@RequestMapping("flashJk")
	@ResponseBody
	public JSONObject flashJk(HttpServletRequest request, HttpServletResponse response, int changeNum) {
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		UserInfo user = userInfoService.selectByUserId(userInfo.getUserId());
		int jkcount = user.getJkSum() - user.getUsedJkSum();
		int jddcount = jkcount / changeNum;
		JSONObject json = new JSONObject();
		json.put("jkcount", jkcount);
		json.put("jddcount", jddcount);
		return json;
	}

	/**
	 * 金币兑换jdd,成功后刷新首页
	 */
	@RequestMapping(value = "exchangeJdd", method = RequestMethod.POST)
	@ResponseBody
	public Integer exchangeJdd(HttpServletRequest request, HttpServletResponse response, JddJkrecord jkRecord) {
		SysParameter parameter = CacheUtils.get(SessionConstants.CHANGENUM);
		int changeNum = Integer.parseInt(parameter.getParamValue().toString());
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		jkRecord.setJkCount(jkRecord.getCount() * changeNum);
		//要更新四张表
		//return jddJkRecordService.insertExchange(jkRecord);
		return commonService.exchange(jkRecord,userInfo);
	}

}
