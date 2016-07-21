package com.portal.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.portal.bean.GeelyUserInfo;
import com.portal.bean.LoginVerify;
import com.portal.bean.UserRights;
import com.portal.common.Global;
import com.portal.common.InfoTool;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.core.cache.CacheUtils;
import com.portal.service.CommonService;
import com.portal.service.GeelyPortService;
import com.portal.service.MessageService;
import com.portal.service.ParameterService;
import com.portal.service.UserInfoService;
import com.portal.web.client.LoginAuthentication;
import com.portal.web.client.LoginAuthenticationSoap;
import com.portal.web.handler.ExceptionHandler;

@Controller
public class LoginAction extends ExceptionHandler {

	@Autowired
	private ParameterService parameterService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private Global global;

	@Autowired
	public GeelyPortService geelyPortService;

	public Logger logger = Logger.getLogger(LoginAction.class);

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {

		/* 加载系统参数表 */
		// 费用来源
		if (CacheUtils.get(SessionConstants.PARAMETER_SOURCE) == null) {
			CacheUtils.set(SessionConstants.PARAMETER_SOURCE,
					parameterService.queryParameterByClassType(SessionConstants.PARAMETER_SOURCE));
		}

		// 兑换比例
		if (CacheUtils.get(SessionConstants.CHANGENUM) == null) {
			CacheUtils.set(SessionConstants.CHANGENUM, parameterService.selectByParamName(SessionConstants.CHANGENUM));
		}
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, GeelyUserInfo userInfo, HttpSession session,
			HttpServletResponse response) {

		try {
			// 判断用户是否存在
			// userInfo = geelyPortService.doLogin(userInfo);
			int status = 3;// userInfo.getLoginStatus();

			// 6 用户登录接口异常，请联系相关人员

			// 验证成功，保存session
			if (status == 3 || status == 4 || status == 5) {

				// 获取用户信息
				userInfo = geelyPortService.getUserInfo(userInfo.getUserId());
				// 初始化用户
				commonService.jdduserinit(userInfo);
				// 获取用户权限
				UserRights userRight = geelyPortService.getUserRightsInfo(userInfo.getUserId());

				// --------------------------------------------------测试
				// UserRights userRight =new UserRights();
				// userInfo.setUserId("0070605");
				 userRight.setLevel(6);
				// -------------------------------------------------

				userInfo.setUserRights(userRight);

				session.setAttribute(SessionConstants.USERINFO, userInfo);

				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";

	}

	// 从吉利主页OA登录
	@RequestMapping(value = "/loginForMall", method = RequestMethod.GET)
	public ModelAndView loginFromMall(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String tokenId = request.getParameter("tokenId") == null ? "" : request.getParameter("tokenId");
		String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
		String hosts = request.getParameter("hosts") == null ? "" : request.getParameter("hosts");
		if ("".equals(tokenId) || "".equals(userId) || "".equals(hosts)) {
			response.sendRedirect("/index");
			return null;
		}

		try {
			LoginAuthenticationSoap loginAuthenSoap = new LoginAuthentication().getLoginAuthenticationSoap();
			String guid = loginAuthenSoap.md5Decrypt(tokenId);
			InfoTool infor = new InfoTool();
			String url = "http://" + hosts + "/callbackVerify.do?guid=" + guid;
			String verifyuserId = "";
			Map<String, String> dateMap = (Map<String, String>) infor.GerJasonInfo(url);
			if (dateMap.get("success").equals("0")) {
				verifyuserId = dateMap.get("userId");
				if (verifyuserId != null && verifyuserId.equals(userId)) {
					// 获取用户信息
					GeelyUserInfo userInfo = geelyPortService.getUserInfo(userId);
					// 初始化用户
					commonService.jdduserinit(userInfo);

					// 获取用户权限
					UserRights userRight = geelyPortService.getUserRightsInfo(userInfo.getUserId());

					userInfo.setUserRights(userRight);

					CacheUtils.set(SessionConstants.USERINFO, SessionConstants.REDIS_TIME, userInfo);

					session.setAttribute(SessionConstants.USERINFO, userInfo);

					ModelAndView mav = new ModelAndView("home/home");
					mav.addObject("userName", userInfo.getName());
					return mav;
				} else {
					response.sendRedirect("/index");
					return null;
				}

			} else {
				response.sendRedirect("/index");
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/index");
			return null;
		}

	}

	/**
	 * 单点登录吉淘淘
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/loginToJtt")
	public ModelAndView loginToJtt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		GeelyUserInfo userInfo = (GeelyUserInfo) session.getAttribute(SessionConstants.USERINFO);
		if (null == userInfo) {
			ModelAndView login = new ModelAndView();
			login.setViewName("login");
			return login;
		}

		String guid = StringUtil.getGUID();
		LoginVerify loginVerify = new LoginVerify();
		loginVerify.setGuid(guid);
		loginVerify.setUserId(userInfo.getUserId());

		commonService.callbackVerifyInsert(loginVerify);

		LoginAuthenticationSoap loginAuthenSoap = new LoginAuthentication().getLoginAuthenticationSoap();

		String tokenId = loginAuthenSoap.md5Encrypt(guid);

		String url = global.getValue("welfare.login.url") + "?tokenId="
				+ tokenId + "&userId=" + userInfo.getUserId() + "&hosts=" + global.getValue("jdd.callback.hosts");

		response.sendRedirect(url);

		return null;

	}

	/**
	 * 注销 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月16日 下午2:41:36
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CacheUtils.delete(SessionConstants.USERINFO);
		request.getSession().removeAttribute(SessionConstants.USERINFO);
		response.sendRedirect("/index");
	}

}
