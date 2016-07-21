package com.portal.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver {

	private static Logger logger = Logger.getLogger(ExceptionHandler.class.getName());

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// 记录异常信息
		
		logger.debug("系统发生异常", ex);
		ex.printStackTrace();
		
		
		StringBuffer buff = new StringBuffer("<table style='width:100%'><tr><td width='50%'></td><td width='30%'>方法</td><td>错误</td></tr>");
		StackTraceElement[] sts = ex.getStackTrace();
		for (StackTraceElement st : sts) {
			if(st.getClassName().startsWith("com.jl")){
				buff.append("<tr style='color:#ff0000;'>");
			}else{
				buff.append("<tr>");
			}
			buff.append("<td>" + st.getClassName() + "</td><td>" + st.getMethodName() + "</td><td>" + st.getLineNumber() + "</td></tr>");
		}
		buff.append("</table>");
		return new ModelAndView("error", "error", buff.toString());
	}

}
