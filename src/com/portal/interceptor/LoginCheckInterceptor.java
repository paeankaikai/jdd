package com.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.portal.bean.GeelyUserInfo;
import com.portal.common.SessionConstants;
import com.portal.core.cache.CacheUtils;

/**
 * 拦截器
 * 
 * Description: <BR>
 * 
 * TODO <BR>
 * 
 * @author paean
 * 
 * @date 2016年5月20日 下午4:10:39
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	// @Autowired
	// private RedisTemplate redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String actionUri = request.getRequestURI().toString();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		if (!actionUri.startsWith("/login") && !actionUri.startsWith("/index") && !actionUri.startsWith("/interface")) {
			if (null == userInfo) {
				response.sendRedirect("/index");
				return false;
			}
		}

		// 进一步控制权限
		if (actionUri.startsWith("/admin")) {
			if (userInfo.getUserRights().getLevel() == SessionConstants.LEVEL_1) {
				System.out.println("您没有权限进入后台,给我老实点");
				response.sendRedirect("/home/default/home.do");
				return false;
			}
		}

		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
	}

}
