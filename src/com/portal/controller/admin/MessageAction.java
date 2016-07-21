package com.portal.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddMessage;
import com.portal.common.CommonUtil;
import com.portal.common.Page;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.service.MessageService;

@Controller
@RequestMapping("admin/message")
public class MessageAction  {

	@Autowired
	private MessageService messageService;

	/**
	 * 
	 * @param page
	 * @param record
	 *            用于封装参数(封装到对象中)
	 * @return
	 */
	@RequestMapping(value = "/messageList", method = RequestMethod.GET)
	public ModelAndView messageList(ModelAndView model, Page page, JddMessage record) {

		PageInfo<JddMessage> pageInfo = messageService.queryPageListByWhere(1, 5, record);
		// pageInfo对象中有list结果，count总数，页数等信息
		List<JddMessage> list = pageInfo.getList();
		model.addObject("messages", list);
		model.setViewName("admin/messageList");
		/*
		 * 
		 * JddMessageExample example = new JddMessageExample(); if (page !=
		 * null) super.setExamplePage(example, page);
		 * 
		 * Criteria criteria = example.createCriteria();
		 * criteria.andIsdeletedEqualTo(0);
		 * 
		 * PageRecord pageRecord = messageService.queryMessage(example);
		 */
		// mav.addObject("messages", pageRecord.getRecordList());
		return model;

	}

	/**
	 * 跳转到添加信息页面 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月27日 下午2:19:38
	 * @return
	 */
	@RequestMapping("/toAddMessage")
	public String toAddmessage() {
		return "addMessage";
	}

	
	
	/**
	 * 保存与更新信息
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月29日 上午9:40:41
	 * @param request
	 * @param jddMessage
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveMessage")
	@ResponseBody
	public String saveMessage(HttpServletRequest request, JddMessage jddMessage, HttpSession session) {
		try {
			String start = StringUtil.isEmpty(request.getParameter("start")) ? null : request
					.getParameter("start");
			String end = StringUtil.isEmpty(request.getParameter("end")) ? null : request.getParameter("end");
			GeelyUserInfo userInfo = (GeelyUserInfo) session.getAttribute(SessionConstants.USERINFO);
			jddMessage.setCreateBy(userInfo.getUserId());
			jddMessage.setCreateTime(new Date());
			// 修改
			if (!StringUtil.isEmpty(jddMessage.getGuid())) {
				if (start != null) {
					jddMessage.setStartDate(CommonUtil.dateFormat(start, "yyyy-MM-dd"));
				}
				if (end != null) {
					jddMessage.setEndDate(CommonUtil.dateFormat(end, "yyyy-MM-dd"));
				}
				messageService.update(jddMessage);
			}
			// 新增
			else {
				jddMessage.setGuid(StringUtil.getGUID());
				if (start != null) {
					jddMessage.setStartDate(CommonUtil.dateFormat(start, "yyyy-MM-dd"));
				}
				if (end != null) {
					jddMessage.setEndDate(CommonUtil.dateFormat(end, "yyyy-MM-dd"));
				}
				messageService.save(jddMessage);
				return jddMessage.getGuid();
			}		
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	@RequestMapping("deleMessage")
	@ResponseBody
	public String deleMessage(String guid) {
		try {
			if (!StringUtil.isEmpty(guid)) {
				messageService.deleteById(guid);
			}
			else {
				throw new Exception("没有获取到guid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";

	}

}
