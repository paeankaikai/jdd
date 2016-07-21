package com.portal.controller.home;

import java.util.HashMap;
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
import com.portal.bean.JddPresent;
import com.portal.service.BaseRecordService;
import com.portal.service.GeelyPortService;
import com.portal.service.JddAccountService;
import com.portal.service.JddPresentService;
import com.portal.service.MessageService;
import com.portal.service.RuleService;


@Controller
@RequestMapping("/home/weChatInfo/")
public class weChatInfoAction {


	@Autowired
	public GeelyPortService geelyPortService;



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
	@RequestMapping("weChatInfo.do")
	public ModelAndView weChatInfo(ModelAndView model) {
		//什么也不干，只是一个跳转
		model.addObject("page", "weChatInfo");
		model.setViewName("/home/weChatInfo");
		return model;
	}

	
	/**
	 * 微送公示详情
	 */
	@RequestMapping("weChatInfoPage.do")
	@ResponseBody
	public Map<String, Object> weChatInfoPage(HttpServletRequest request, HttpServletResponse response,ModelAndView model ,
			@RequestParam(value="page", defaultValue="1") String page,
			@RequestParam(value="rows", defaultValue="10") String rows) {
		Map<String, Object> data = new HashMap<String, Object>();
		PageInfo<JddPresent> pageRows = jddPresentService.queryPageListByWhere(Integer.parseInt(page) , Integer.parseInt(rows) , null, null);
		data.put("total", pageRows.getTotal());
		data.put("rows", pageRows.getList());
		return data;
	}
	
}
