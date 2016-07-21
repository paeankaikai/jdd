package com.portal.controller.admin;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.portal.bean.MicroSendRuleInfo;
import com.portal.bean.SysParameter;
import com.portal.common.SessionConstants;
import com.portal.service.ParameterService;

@Controller
@RequestMapping("admin/grant")
public class GrantSettingAction  {

	@Autowired
	private ParameterService parameterService;

	/**
	 * ��ת��������ҳ��
	 * @return ����ҳ��
	 */
	@RequestMapping(value = "/showSetting", method = RequestMethod.GET)
	public ModelAndView showSetting() {

		ModelAndView mav = new ModelAndView("admin/grantSetting");

		MicroSendRuleInfo microSendRuleInfo = new MicroSendRuleInfo();
		
		SysParameter countLimitInfo = parameterService.selectByParamName(SessionConstants.MICROSEND_PARAMETER_MICROSENDCOUNT);
		microSendRuleInfo.setMicroSendCount(countLimitInfo.getParamValue());
		microSendRuleInfo.setMicroSendCountEnable(countLimitInfo.getStatus());
		
		SysParameter numLimitInfo = parameterService.selectByParamName(SessionConstants.MICROSEND_PARAMETER_MICROSENDNUM);
		microSendRuleInfo.setMicroSendNum(numLimitInfo.getParamValue());
		microSendRuleInfo.setMicroSendNumEnable(numLimitInfo.getStatus());
		
		mav.addObject("microSendRuleInfo", microSendRuleInfo);
		return mav;

	}
	
	/**
	 * ΢������
	 * @param request    ����
	 * @param enable     �Ƿ�����
	 * @param value      ��ֵ
	 * @param setType    ��������0���Ŵ�������/1������������
	 * @return ���ý��״̬��Ϣ
	 */
	@RequestMapping("/countLimitSet")
	@ResponseBody
	public String countLimitSet(HttpServletRequest request, @RequestParam("enable") Integer enable, 
			@RequestParam("value") String value, @RequestParam("setType") Integer setType) {
		JsonObject jsonObject = new JsonObject();
		
		SysParameter sysParameter = new SysParameter();
		sysParameter.setParamValue(value);
		sysParameter.setStatus(enable);
		if(setType == 0){
			sysParameter.setParamName(SessionConstants.MICROSEND_PARAMETER_MICROSENDCOUNT);
		}
		if(setType == 1){
			sysParameter.setParamName(SessionConstants.MICROSEND_PARAMETER_MICROSENDNUM);
		}
		
		if(parameterService.updateByParamName(sysParameter) > 0){
			jsonObject.addProperty("success", true);
		}else{
			jsonObject.addProperty("success", false);
		}
		

		return jsonObject.toString(); 
		
	}
	
}
