package com.portal.controller.home;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddAccount;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddBaserecordQuery;
import com.portal.bean.JddRule;
import com.portal.bean.RuleType;
import com.portal.common.CommonUtil;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.core.cache.CacheUtils;
import com.portal.service.BaseRecordService;
import com.portal.service.GeelyPortService;
import com.portal.service.JddAccountService;
import com.portal.service.JddPresentService;
import com.portal.service.MessageService;
import com.portal.service.RuleService;


@Controller
@RequestMapping("/home/myJddRecord/")
public class myJddRecordAction {


	@Autowired
	public GeelyPortService geelyPortService;

;

	@Autowired
	public JddAccountService userJddAccountService;
	
	@Autowired
	public MessageService messageService;
	
	@Autowired
	public JddPresentService jddPresentService;
	
	@Autowired
	public  BaseRecordService baseRecordService;
	
	@Autowired
	public  RuleService ruleService;
	
	@Autowired
	public JddAccountService jddAccountService;
	

	/**
	 * 我的吉点点记录跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("myJddRecord.do")
	public ModelAndView myJddRecord(ModelAndView model) {
		//什么也不干，只是一个跳转
		model.addObject("page", "myJddRecord");
		model.setViewName("/home/myJddRecord");
		return model;
	}

	/**
	 * 我的吉点点内容查询
	 * @param page			查询页号
	 * @param row			查询行数
	 * @param ruleType		收入/支出
	 * @param ruleGrad		一级类型
	 * @param ruleFather	二级类型
	 * @param beginDate		开始时间
	 * @param endDate		结束时间
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("myJddRecordPage.do")
	@ResponseBody
	public Map<String, Object> myJddRecordInfo(HttpServletRequest request, HttpServletResponse response, ModelAndView model , 
			@RequestParam(value="page", defaultValue="1") String page,
			@RequestParam(value="rows", defaultValue="10") String rows,
			String ruleType,String ruleFather,String ruleGrad, String beginDate , String endDate) throws ParseException {
		if(StringUtil.isEmpty(ruleType)||ruleType.equals("-1")){
			ruleType=null;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		JddBaserecordQuery record =  new JddBaserecordQuery();
		//修复 根据时间、类型查询  ------cyk
		try {
			if(CommonUtil.isNotEmpty(ruleType))
				record.setRuleType(Integer.parseInt(ruleType));
			if(CommonUtil.isNotEmpty(ruleFather))
				record.setRuleFather(ruleFather);
			if(CommonUtil.isNotEmpty(ruleGrad))
				record.setRuleGrad(ruleGrad);
			if(CommonUtil.isNotEmpty(beginDate)){
			//	String beginTime = beginDate.substring(0, beginDate.length() - 1) + " 00:00:00";
				record.setStartDate(CommonUtil.dateFormat(beginDate, "yyyy-MM-dd"));
			}
			if(CommonUtil.isNotEmpty(endDate)){
			//	String endTime = endDate.substring(0, endDate.length() - 1) + " 23:59:59";
				record.setEndDate(CommonUtil.dateFormat(endDate, "yyyy-MM-dd"));
			}
			//获得当前登录人(目前先写死)--添加排序信息并执行查询
			record.setCreateBy(userInfo.getUserId());
			PageInfo<JddBaserecord> pageRows = baseRecordService.queryPageListByWhere(Integer.parseInt(page) ,Integer.parseInt(rows) , record , null);
			data.put("result", "success");
			data.put("total", pageRows.getTotal());
			data.put("rows", pageRows.getList());
		} catch (Exception e) {
			data.put("result", "error");
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 我的吉点点内容查询
	 * @param page			查询页号
	 * @param row			查询行数
	 * @param ruleType		收入/支出
	 * @param ruleGrad		一级类型
	 * @param ruleFather	二级类型
	 * @param beginDate		开始时间
	 * @param endDate		结束时间
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("myJddRecordInfoTop.do")
	@ResponseBody
	public Map<String, Object> myJddRecordInfoTop(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
		Map<String, Object> data = new HashMap<String, Object>();
		//根据当前用户查询-----cyk
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		try {
			JddAccount record = new JddAccount();
			record.setUserId(userInfo.getUserId());
			//今年-往年收入总额
			int cyNum = jddAccountService.queryOne(record).getCyNum();
			int lyNum = jddAccountService.queryOne(record).getLyNum();
			//今年-往年消费总额
			int cyCostNum = jddAccountService.queryOne(record).getCyCostNum();
			int lyCostNum = jddAccountService.queryOne(record).getLyCostNum();
			
			data.put("totalInComNum",cyNum+lyNum); 
			data.put("totalOutComNum", cyCostNum+lyCostNum);
			data.put("myJddNum", ((cyNum+lyNum)-(cyCostNum+lyCostNum)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 收支类型下拉框
	 */
	@ResponseBody
	@RequestMapping("ruleType.do")
	public List<RuleType> ruleType(HttpServletRequest request, HttpServletResponse response) {
		List<RuleType> list = new ArrayList<RuleType>();
		RuleType all = new RuleType();
		all.setTypeName("全部");
		all.setTypeCode(-1);
		list.add(all);
		RuleType inCom = new RuleType();
		inCom.setTypeCode(0);
		inCom.setTypeName("收入");
		list.add(inCom);
		RuleType outCom = new RuleType();
		outCom.setTypeCode(1);
		outCom.setTypeName("支出");
		list.add(outCom);
		return list;
	}
	
	/**
	 * 一级类型下拉框
	 * @param ruleType	0为收入 规则 1为支出规则
	 */
	@ResponseBody
	@RequestMapping("ruleGrad.do")
	public List<JddRule> ruleGrad(HttpServletRequest request, HttpServletResponse response,Integer ruleType) {
		List<JddRule> list = new ArrayList<JddRule>();
		if ( ruleType==null||ruleType == 0) {
			list = ruleService.getIncomeRuleGrad();
		}else if(ruleType == 1){
			list = ruleService.getOutcomeRuleGrad();
		}
		return list;
	}
	
	/**
	 * 二级类型下拉框
	 */
	@ResponseBody
	@RequestMapping("ruleFather.do")
	public List<JddRule> ruleFather(HttpServletRequest request, HttpServletResponse response ,String ruleFather) {
		List<JddRule> list = ruleService.getChildren(ruleFather);
		return list;
	}

}
