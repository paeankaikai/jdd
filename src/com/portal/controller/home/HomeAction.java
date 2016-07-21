package com.portal.controller.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddAccount;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddBaserecordQuery;
import com.portal.bean.JddMessage;
import com.portal.bean.JddPresent;
import com.portal.common.CommonUtil;
import com.portal.common.SessionConstants;
import com.portal.core.cache.CacheUtils;
import com.portal.service.BaseRecordService;
import com.portal.service.GeelyPortService;
import com.portal.service.JddAccountService;
import com.portal.service.JddPresentService;
import com.portal.service.MessageService;

@Controller
@RequestMapping("/home/default/")
public class HomeAction {

	/*
	 * @Autowired private RedisTemplate<String, UserInfo> redisTemplate;
	 */

	@Autowired
	public GeelyPortService geelyPortService;

	@Autowired
	public JddAccountService jddAccountService;

	@Autowired
	public BaseRecordService baseRecordService;

	@Autowired
	public MessageService messageService;

	@Autowired
	public JddPresentService jddPresentService;

	/**
	 * 默认主页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("home.do")
	public ModelAndView home(ModelAndView model ,HttpServletRequest request) {
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		int level=userInfo.getUserRights().getLevel();
		model.addObject("level", level);
		model.setViewName("/home/home");

		return model;
	}

	/**
	 * 我的吉点点(吉点点余额--年度余额)
	 */
	@RequestMapping("myJDD.do")
	@ResponseBody
	public Map<String, Object> myJDD(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		Map<String, Object> map = new HashMap<String, Object>();
		//今年
		JddAccount record = new JddAccount();
		record.setUserId(userInfo.getUserId());
		record.setYearNo(CommonUtil.dateFormat(new Date(), "yyyy"));
		JddAccount userJddAccount = jddAccountService.queryOne(record);
		map.put("info", userJddAccount);
		return map;
	}

	/**
	 * 本年度收支信息
	 */
	@RequestMapping("inOutInfo.do")
	@ResponseBody
	public Map<String, Object> inOutInfo(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
		Map<String, Object> data = new HashMap<String, Object>();
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		JddAccount recordInfo = new JddAccount();
		recordInfo.setUserId(userInfo.getUserId());
		JddAccount userJddAccount = jddAccountService.queryOne(recordInfo);
		// 我的收入
		// 关爱认可
		JddBaserecordQuery recordInCom = new JddBaserecordQuery();
		recordInCom.setRuleType(0);
		recordInCom.setCreateBy(userInfo.getUserId());
		recordInCom.setRuleGrad("1");
		int in_gark = baseRecordService.selectTotalNum(recordInCom);
		// 成长认可
		recordInCom.setRuleGrad("22");
		int in_czrk = baseRecordService.selectTotalNum(recordInCom);
		// 行为认可
		recordInCom.setRuleGrad("8");
		int in_xwrk = baseRecordService.selectTotalNum(recordInCom);
		// 员工赠送
		recordInCom.setRuleGrad("47");
		int in_ygzs = baseRecordService.selectTotalNum(recordInCom);
		//其他收入
		recordInCom.setRuleGrad("60");
		int in_qita = baseRecordService.selectTotalNum(recordInCom);	
		
		// 收入信息放入map中
		data.put("in_gark", in_gark);
		data.put("in_xwrk", in_xwrk);
		data.put("in_czrk", in_czrk);
		data.put("in_ygzs", in_ygzs);
		data.put("in_other", in_qita);
		data.put("in_total", userJddAccount.getCyNum());
		// 我的支出
		// 商城消费
		JddBaserecordQuery recordOutCom = new JddBaserecordQuery();
		recordOutCom.setRuleType(1);
		recordOutCom.setCreateBy(userInfo.getUserId());
		recordOutCom.setRuleFather("45");
		int out_scxf = baseRecordService.selectTotalNum(recordOutCom);
		// 赠送他人
		recordOutCom.setRuleFather("");
		recordOutCom.setRuleChild("52");
		int out_zstr = baseRecordService.selectTotalNum(recordOutCom);
		// 过期清零
		recordOutCom.setRuleGrad("58");
		int out_cqql = baseRecordService.selectTotalNum(recordOutCom);
		// 收入信息放入map中
		data.put("out_scxf", out_scxf);
		data.put("out_zstr", out_zstr);
		data.put("out_cqql", out_cqql);
		data.put("out_other", userJddAccount.getCyCostNum() - (out_scxf + out_zstr + out_cqql));
		data.put("out_total", userJddAccount.getCyCostNum());
		return data;
	}

	/**
	 * 系统消息
	 */
	@RequestMapping("message.do")
	@ResponseBody
	public Map<String, Object> message(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
		Map<String, Object> map = new HashMap<String, Object>();
		JddMessage record = new JddMessage();
		List<JddMessage> rows = messageService.queryListByWhere(record);
		map.put("rows", rows);
		return map;
	}

	/**
	 * 吉点点达人榜
	 */
	@RequestMapping("JDDRanking.do")
	@ResponseBody
	public Map<String, Object> JDDRanking(HttpServletRequest request, HttpServletResponse response,
			ModelAndView model) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 执行查询
		PageInfo<JddAccount> page = jddAccountService.queryPageNum(1, 5, new JddAccount(), null);
		map.put("rows", page.getList());
		return map;
	}

	/**
	 * 我的吉点点记录
	 */
	@RequestMapping("myJDDInfo.do")
	@ResponseBody
	public Map<String, Object> myJDDInfo(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> orderBy = new ArrayList<String>();
		orderBy.add("CREATE_TIME desc");
		// 执行查询
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		JddBaserecordQuery record = new JddBaserecordQuery();
		record.setCreateBy(userInfo.getUserId());
		PageInfo<JddBaserecord> page = baseRecordService.queryPageListByWhere(1, 6, record, orderBy);
		map.put("rows", page.getList());
		return map;
	}

	/**
	 * 微送公示
	 */
	@RequestMapping("weChatInfo.do")
	@ResponseBody
	public Map<String, Object> weChatInfo(HttpServletRequest request, HttpServletResponse response,
			ModelAndView model) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<JddPresent> page = jddPresentService.queryPageListByWhere(1, 5, null, null);
		map.put("rows", page.getList());
		return map;
	}

	@RequestMapping("encyclopedia")
	public String encyclopedia() {
		return "home/encyclopedia";
	}

}
