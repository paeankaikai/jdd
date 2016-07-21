package com.portal.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.portal.bean.GeelyUserInfo;
import com.portal.common.SessionConstants;
import com.portal.core.cache.CacheUtils;

@Controller
@RequestMapping("/admin/default")
public class DefaultAction {

	@RequestMapping("")
	public ModelAndView LoadDefault(HttpServletRequest request) {
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		int level = userInfo.getUserRights().getLevel();
		ModelAndView mav = new ModelAndView("admin/default");

		if (level == SessionConstants.LEVEL_3 || level == SessionConstants.LEVEL_2) {
			mav.addObject("href", "/admin/release_q/showQuery");
		}

		// 审核管理员
		if (level == SessionConstants.LEVEL_4) {
			mav.addObject("href", "/admin/release/checkRelease");
		}
		// 企业大学管理员
		if (level == SessionConstants.LEVEL_5) {
			mav.addObject("href", "/admin/release/toReleaseManager");
		}
		// 超级管理员
		if (level == SessionConstants.LEVEL_6) {
			mav.addObject("href", "/admin/message/messageList");
		}
		return mav;
	}

	@RequestMapping("/header")
	public ModelAndView LoadHeader(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("common/header");
		return mav;
	}

	@RequestMapping("/left")
	public ModelAndView LoadLeft(HttpServletRequest request) {
/*		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		int level = userInfo.getUserRights().getLevel();*/
		ModelAndView mav = new ModelAndView("admin/baseMenu");
		/*if (level == SessionConstants.LEVEL_6) {
			mav.setViewName("admin/baseMenu");
		}
		else {
			mav.setViewName("admin/releaseMenu");
		}*/
		return mav;
	}

	@RequestMapping("/right")
	public ModelAndView LoadRight(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/right");
		return mav;
	}

	@RequestMapping("/foot")
	public ModelAndView LoadFoot(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("common/foot");
		return mav;
	}

}
