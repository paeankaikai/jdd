package com.portal.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddRule;
import com.portal.bean.SysParameter;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.common.TreeNode;
import com.portal.core.cache.CacheUtils;
import com.portal.service.RuleService;

@Controller
@RequestMapping("admin/rule")
public class RuleAction {

	@Autowired
	private RuleService ruleService;

	@RequestMapping("ruleList")
	public ModelAndView ruleList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/ruleList");
		
		//获取收入规则树结构
		int ruleType=StringUtil.isEmpty(request.getParameter("type"))?0:Integer.parseInt(request.getParameter("type"));
		TreeNode Root=null;
		//收入
		if(ruleType==0){
			Root = ruleService.getTree(0);
			mav.addObject("tree", Root.getNodes().toString());
			mav.addObject("type", 0);
		}
		//支出
		else{
			//TODO
			Root = ruleService.getTree(1);
			mav.addObject("tree", Root.getNodes().toString());
			mav.addObject("type", 1);
		}
		
		return mav;

	}

	@RequestMapping(value = "toAddRule", method = RequestMethod.GET)
	public ModelAndView toAddRule(HttpServletRequest request, String parentGuid, String levels,String type) {
		levels = Integer.parseInt(levels) + 1 + "";

		if (StringUtil.isEmpty(parentGuid)) {
			parentGuid = "0";
		}
		ModelAndView mav = new ModelAndView("admin/doRule");
		mav.addObject("levels", levels.trim());
		mav.addObject("parentGuid", parentGuid.trim());
		mav.addObject("addOrEdit", 1);
		//是收入还是支出
		mav.addObject("type", type);

		if (levels.equals("3")) {
			// 从缓存中获取
			List<SysParameter> sources = CacheUtils.get(SessionConstants.PARAMETER_SOURCE);
			mav.addObject(SessionConstants.PARAMETER_SOURCE, sources);
		}

		return mav;

	}

	@RequestMapping(value = "toEditRule", method = RequestMethod.GET)
	public ModelAndView toEditRule(HttpServletRequest request, String guid) {
		
		JddRule jddRule = ruleService.findRuleByGUID(guid);

		ModelAndView mav = new ModelAndView("admin/doRule");
		// 从缓存中获取
		List<SysParameter> sources = CacheUtils.get(SessionConstants.PARAMETER_SOURCE);
		mav.addObject(SessionConstants.PARAMETER_SOURCE, sources);
		mav.addObject("jddRule", jddRule);
		mav.addObject("addOrEdit", 2);
		mav.addObject("type", jddRule.getType());

		return mav;

	}

	@RequestMapping(value = "addRule", method = RequestMethod.POST)
	@ResponseBody
	public String addRule(HttpServletRequest request, JddRule jddRule) {
		try {
			GeelyUserInfo userInfo =(GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
			jddRule.setGuid(UUID.randomUUID().toString());
			jddRule.setIsDeleted(0);
			jddRule.setCreateBy(userInfo.getUserId());
			jddRule.setCreateName(userInfo.getName());
			jddRule.setCreateTime(new Date());
			ruleService.insertRule(jddRule);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";

	}
	
	
	
	@RequestMapping(value = "editRule", method = RequestMethod.POST)
	@ResponseBody
	public String editRule(HttpServletRequest request, JddRule jddRule) {
		try {
			GeelyUserInfo userInfo =(GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
			jddRule.setModifyBy(userInfo.getUserId());
			jddRule.setModifyName(userInfo.getName());
			jddRule.setModifyTime(new Date());
			ruleService.modifyRule(jddRule);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";

	}
	
	

	@RequestMapping(value = "deleRule")
	@ResponseBody
	public String deleRule(HttpServletRequest request, String tguid,String levels) {
		try {	
			ruleService.deleteRuleByGUID(tguid,Integer.parseInt(levels.trim()));
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";

	}

}
