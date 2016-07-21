package com.portal.controller.admin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.bean.JddAccount;
import com.portal.bean.JddPayment;
import com.portal.bean.LoginVerify;
import com.portal.common.Global;
import com.portal.common.InfoTool;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.mapper.CommonMapper;
import com.portal.service.CommonService;
import com.portal.service.JddAccountService;
import com.portal.service.JddPaymentService;
import com.portal.service.UserInfoService;
import com.portal.web.client.LoginAuthentication;
import com.portal.web.client.LoginAuthenticationSoap;

@Controller
@RequestMapping("interface")
public class OutInterfaceAction  {
	
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private JddAccountService jddAccountService;
	
	@Autowired
	private JddPaymentService jddPaymentService;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private Global global;
	
	@RequestMapping("/callbackVerify")
	@ResponseBody
	public Object doExchangeSearch(HttpServletRequest request, @RequestParam("guid") String guid) {	
		LoginVerify verifyInfo  = commonService.getCallbackVerify(guid);
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		if(null == verifyInfo){
			data.put("success", "1");
		}else{
			data.put("success", "0");
			data.put("userId", verifyInfo.getUserId());
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", data);
		return result;
	}

	/**
	 * 兑换记录查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getGjjAccount")
	@ResponseBody
	public Object getGjjAccount(HttpServletRequest request) {
		String tokenId = request.getParameter("tokenId")==null?"":request.getParameter("tokenId");
		String hosts = request.getParameter("hosts")==null?"":request.getParameter("hosts");
		HashMap<String, Object> data = new HashMap<String, Object>();
		HashMap<String, Object> result = new HashMap<String, Object>();		
		
		if("".equals(tokenId)){
			data.put("success", "1");
			data.put("errorCode", "11");
			data.put("errorReason", "非法tokenId");
			result.put("result", data);
			return result;
		}
		
		if("".equals(hosts)){
			data.put("success", "1");
			data.put("errorCode", "12");
			data.put("errorReason", "非法hosts");
			result.put("result", data);
			return result;
		}
		
		
		LoginAuthenticationSoap loginAuthenSoap = new LoginAuthentication().getLoginAuthenticationSoap();
		String guid = loginAuthenSoap.md5Decrypt(tokenId);
		
		InfoTool infor = new InfoTool();
		String url = "http://" + hosts + "/callbackVerify.do?guid="+guid;
		String userId = "";
		try {
			Map<String, String> dateMap = (Map<String, String>)infor.GerJasonInfo(url);
			if(dateMap.get("success").equals("0")){
				userId = dateMap.get("userId");
				
				JddAccount query = new JddAccount();
				query.setUserId(userId);
				JddAccount userInfo =jddAccountService.queryOne(query);
				
				if(userInfo == null){
					data.put("success", "1");
					data.put("errorCode", "13");
					data.put("errorReason", "吉点点用户不存在");
					result.put("result", data);
					return result;
				}else{
					data.put("success", "0");
					data.put("userInfo", userInfo);
				}
			}else{
				data.put("success", "1");
				data.put("errorCode", "14");
				data.put("errorReason", "用户验证失败");
				result.put("result", data);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("success", "1");
			data.put("errorCode", "99");
			data.put("errorReason", "系统异常");
			result.put("result", data);
		}
		
		
		result.put("result", data);
		return result;
	}
	
	@RequestMapping("/doTransaction")
	@ResponseBody
	public Object doTransaction(HttpServletRequest request) {
		String orderNo = request.getParameter("orderNo")==null?"":request.getParameter("orderNo");
		String jddNum = request.getParameter("jddNum")==null?"":request.getParameter("jddNum");
		String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
		String reason = request.getParameter("reason")==null?"":request.getParameter("reason");
		String userId = request.getParameter("userId")==null?"":request.getParameter("userId");
		String userName = request.getParameter("userName")==null?"":request.getParameter("userName");
		String tokenId = request.getParameter("tokenId")==null?"":request.getParameter("tokenId");
		String systemId = request.getParameter("systemId")==null?"":request.getParameter("systemId");
		String hosts = request.getParameter("hosts")==null?"":request.getParameter("hosts");

		try {
			userName = URLDecoder.decode(userName, "UTF-8");
			reason = URLDecoder.decode(reason, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		if("".equals(orderNo)){
			data.put("success", "1");
			data.put("errorCode", "11");
			data.put("errorReason", "非法订单号");
			result.put("result", data);
			return result;
		}
		if("".equals(jddNum)){
			data.put("success", "1");
			data.put("errorCode", "12");
			data.put("errorReason", "非法吉点点数");
			result.put("result", data);
			return result;
		}
		if(!"0".equals(flag) && !"1".equals(flag)){
			data.put("success", "1");
			data.put("errorCode", "13");
			data.put("errorReason", "错误标识");
			result.put("result", data);
			return result;
		}
		if("".equals(userId)){
			data.put("success", "1");
			data.put("errorCode", "14");
			data.put("errorReason", "非法用户id");
			result.put("result", data);
			return result;
		}
		if("".equals(userName)){
			data.put("success", "1");
			data.put("errorCode", "15");
			data.put("errorReason", "非法用户名");
			result.put("result", data);
			return result;
		}
		if("".equals(tokenId)){
			data.put("success", "1");
			data.put("errorCode", "16");
			data.put("errorReason", "非法tokenId");
			result.put("result", data);
			return result;
		}
		if(!"S001".equals(systemId)){
			data.put("success", "1");
			data.put("errorCode", "17");
			data.put("errorReason", "非法系统id");
			result.put("result", data);
			return result;
		}
		
		if("".equals(hosts)){
			data.put("success", "1");
			data.put("errorCode", "18");
			data.put("errorReason", "非法hosts");
			result.put("result", data);
			return result;
		}
		
		try {
			LoginAuthenticationSoap loginAuthenSoap = new LoginAuthentication().getLoginAuthenticationSoap();
			String guid = loginAuthenSoap.md5Decrypt(tokenId);

			InfoTool infor = new InfoTool();
			String url = "http://" + hosts + "/callbackVerify.do?guid="+guid;
			String verifyuserId = "";
			
			Map<String, String> dateMap = (Map<String, String>)infor.GerJasonInfo(url);
			if(dateMap.get("success").equals("0")){
				verifyuserId = dateMap.get("userId");
				
				if(userId.equals(verifyuserId)){
					//添加支出记录
					String relationId = StringUtil.getGUID();
					JddPayment jddPayment = new JddPayment();
					jddPayment.setGuid(relationId);
					jddPayment.setUserId(userId);
					jddPayment.setUserName(userName);
					jddPayment.setLinkId(orderNo);
					jddPayment.setCount(Integer.parseInt(jddNum));
					jddPayment.setReason(reason);
					if(("1").equals(flag)){
						jddPayment.setRuleChild(SessionConstants.RULE_MALL_OUT_GUID);
					}else{
						jddPayment.setRuleChild(SessionConstants.RULE_MALL_IN_GUID);
					}
					
					jddPayment.setCreateTime(new Date());
					jddPayment.setLinkSystemId(systemId);
					if(("0").equals(flag)){
						jddPayment.setReturnTime(new Date());
					}
					
					if(jddPaymentService.saveSelective(jddPayment) > 0){
						//添加收入记录并修改账户信息
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("as_userid", userId);
						map.put("as_guid", StringUtil.getGUID());
						map.put("as_relationid", relationId);
						map.put("an_jddnum", jddNum);
						if(("1").equals(flag)){
							map.put("as_rulechid", SessionConstants.RULE_MALL_OUT_GUID);
						}else{
							map.put("as_rulechid", SessionConstants.RULE_MALL_IN_GUID);
						}
						map.put("as_reason", reason);
						map.put("an_result", 0);
						map.put("as_remess", null);
						
						commonMapper.jddbaserecord(map);
						
						data.put("success", "0");
					}else{
						data.put("success", "1");
						data.put("errorCode", "22");
						data.put("errorReason", "回调验证失败");
					}
					
				}else{
					data.put("success", "1");
					data.put("errorCode", "22");
					data.put("errorReason", "回调验证失败");
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("success", "1");
			data.put("errorCode", "99");
			data.put("errorReason", "系统异常");
		}

		result.put("result", data);

		return result;
	}

}
