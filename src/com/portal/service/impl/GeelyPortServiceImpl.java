package com.portal.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.GeelyUserInfo;
import com.portal.bean.UserRights;
import com.portal.bean.webservice.IntfConfDto;
import com.portal.bean.webservice.IntfNameSpace;
import com.portal.common.CommonUtil;
import com.portal.common.ConstantsUtil;
import com.portal.common.Global;
import com.portal.common.SessionConstants;
import com.portal.service.GeelyPortService;
import com.portal.web.client.LoginAuthentication;
import com.portal.web.client.LoginAuthenticationSoap;

@Service
public class GeelyPortServiceImpl implements GeelyPortService {
	@Autowired
	private Global global;

	@Override
	public GeelyUserInfo doLogin(GeelyUserInfo logUser) {
		// TODO Auto-generated method stub
		try {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();

			HashMap<String, Object> map = (HashMap<String, Object>) global.getObject("INTFCONF");
			IntfConfDto IntfConfDto = (IntfConfDto) map.get("VARIFYUSER");

			TreeMap<String, Object> rootMap = new TreeMap<String, Object>();
			if (CommonUtil.isNotEmpty(IntfConfDto.getRootNode())) {
				rootMap.put("001" + IntfConfDto.getRootNode(), "");
			}

			if (CommonUtil.isNotEmpty(IntfConfDto.getSubNodeLst())) {
				for (int i = 0; i < IntfConfDto.getSubNodeLst().size(); i++) {
					if ("g:user".equals(IntfConfDto.getSubNodeLst().get(i))) {
						rootMap.put("101" + IntfConfDto.getSubNodeLst().get(i), logUser.getUserId());
					} else if ("g:pswd".equals(IntfConfDto.getSubNodeLst().get(i))) {
						rootMap.put("102" + IntfConfDto.getSubNodeLst().get(i),
								logUser.getPassword());
					}
				}
			}

			TreeMap<String, Object> nameSpaceMap = new TreeMap<String, Object>();

			if (CommonUtil.isNotEmpty(IntfConfDto.getNameSpaceLst())) {
				for (IntfNameSpace nameSpace : IntfConfDto.getNameSpaceLst()) {
					nameSpaceMap.put(nameSpace.getKey(), nameSpace.getValue());
				}
			}

			resultMap = CommonUtil.readIntfXml(CommonUtil.doRequest(
					IntfConfDto.getWsdlUrl(), IntfConfDto.getSoapAction(),
					CommonUtil.createSoapXml(rootMap, nameSpaceMap)));

			if (resultMap != null) {
				Integer status = Integer.parseInt(resultMap.get("status").toString());
				logUser.setLoginStatus(status);

				if (ConstantsUtil.LOGIN_STATUS_PSN.equals(status)) {
					logUser.setMsg(resultMap.get("detail").toString());
				} else if (ConstantsUtil.LOGIN_STATUS_MANG.equals(status)) {
					logUser.setMsg(resultMap.get("detail").toString());
				} else if (ConstantsUtil.LOGIN_STATUS_ADM.equals(status)) {
					logUser.setMsg(resultMap.get("detail").toString());
				} else {
					logUser.setMsg(resultMap.get("detail").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logUser.setLoginStatus(ConstantsUtil.LOGIN_STATUS_INTF_ERROR);
			logUser.setMsg("用户登录接口异常，请联系相关人员");
		}

		return logUser;
	}

	@Override
	@SuppressWarnings("unchecked")
	public GeelyUserInfo getUserInfo(String userId) {
		GeelyUserInfo userInfo = new GeelyUserInfo();
		try {
			// HashMap<String, Object> resultMap = new HashMap<String,
			// Object>();
			HashMap<String, Object> resultMap = null;

			HashMap<String, Object> map = (HashMap<String, Object>) global.getObject("INTFCONF");
			IntfConfDto IntfConfDto = (IntfConfDto) map.get("GETUSERINFO");

			TreeMap<String, Object> rootMap = new TreeMap<String, Object>();
			if (CommonUtil.isNotEmpty(IntfConfDto.getRootNode())) {
				rootMap.put("001" + IntfConfDto.getRootNode(), "");
			}

			if (CommonUtil.isNotEmpty(IntfConfDto.getSubNodeLst())) {
				for (int i = 0; i < IntfConfDto.getSubNodeLst().size(); i++) {
					if ("g:Emplid".equals(IntfConfDto.getSubNodeLst().get(i))) {
						rootMap.put("101" + IntfConfDto.getSubNodeLst().get(i), userId);
					} else if ("g:Business_unit".equals(IntfConfDto.getSubNodeLst().get(i))) {
						rootMap.put("102" + IntfConfDto.getSubNodeLst().get(i), "");
					} else if ("g:Month".equals(IntfConfDto.getSubNodeLst().get(i))) {
						rootMap.put("103" + IntfConfDto.getSubNodeLst().get(i), "");
					}
				}
			}

			TreeMap<String, Object> nameSpaceMap = new TreeMap<String, Object>();

			if (CommonUtil.isNotEmpty(IntfConfDto.getNameSpaceLst())) {
				for (IntfNameSpace nameSpace : IntfConfDto.getNameSpaceLst()) {
					nameSpaceMap.put(nameSpace.getKey(), nameSpace.getValue());
				}
			}

			resultMap = CommonUtil.readIntfXml(CommonUtil.doRequest(
					IntfConfDto.getWsdlUrl(), IntfConfDto.getSoapAction(),
					CommonUtil.createSoapXml(rootMap, nameSpaceMap)));

			if (resultMap != null) {
				List<HashMap<String, Object>> retLst = (List<HashMap<String, Object>>) resultMap
						.get("mapLst");
				HashMap<String, Object> infoMap = retLst.get(0);
				if (infoMap.size() > 0) {
					userInfo.setUserId(userId);
					userInfo.setName(infoMap.get("NAME").toString());
					userInfo.setSex(infoMap.get("SEX").toString());
					userInfo.setBirthdate(infoMap.get("BIRTHDATE").toString());
					userInfo.setCompanyCode(infoMap.get("BUSINESS_UNIT").toString());
					userInfo.setCompanyName(infoMap.get("G_COMP_DESCR").toString());
					userInfo.setCompanyNameDesc(infoMap.get("G_BU_DESCR").toString());
					userInfo.setEmail(infoMap.get("EMAIL_ADDR").toString());
					userInfo.setPhone(infoMap.get("PHONE").toString());
					userInfo.setAreaId(infoMap.get("LOCATION").toString());
					userInfo.setAreaName(infoMap.get("ADDRESS2").toString());
					userInfo.setOutAreaId(infoMap.get("G_OVERSEAS_LOC").toString());
					userInfo.setOutAreaName(infoMap.get("ADDRESS1").toString());
					userInfo.setJoinDate(infoMap.get("HIRE_DT").toString());
					userInfo.setLeaveDate(infoMap.get("TERMINATION_DT").toString());
					userInfo.setPosition(infoMap.get("DESCR").toString());
					//bumen 
					userInfo.setUserDept(infoMap.get("DEPT_DESCR").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return userInfo;
		}

		return userInfo;
	}

	private File getFileFromBytes(byte[] b, String outputFile) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	@Override
	public UserRights getUserRightsInfo(String userId) {
		UserRights userRights = new UserRights();
		try {
			List<String> resultLst = new ArrayList<>();

			HashMap<String, Object> map = (HashMap<String, Object>) global.getObject("INTFCONF");
			IntfConfDto IntfConfDto = (IntfConfDto) map.get("GETUSERRIGHTS");

			TreeMap<String, Object> rootMap = new TreeMap<String, Object>();
			if (CommonUtil.isNotEmpty(IntfConfDto.getRootNode())) {
				rootMap.put("001" + IntfConfDto.getRootNode(), "");
			}

			if (CommonUtil.isNotEmpty(IntfConfDto.getSubNodeLst())) {
				for (int i = 0; i < IntfConfDto.getSubNodeLst().size(); i++) {
					if ("m:emplid".equals(IntfConfDto.getSubNodeLst().get(i))) {
						rootMap.put("101" + IntfConfDto.getSubNodeLst().get(i), userId);
					}
				}
			}

			TreeMap<String, Object> nameSpaceMap = new TreeMap<String, Object>();

			if (CommonUtil.isNotEmpty(IntfConfDto.getNameSpaceLst())) {
				for (IntfNameSpace nameSpace : IntfConfDto.getNameSpaceLst()) {
					nameSpaceMap.put(nameSpace.getKey(), nameSpace.getValue());
				}
			}

			resultLst = CommonUtil.readIntfXmlRights(CommonUtil.doRequest(
					IntfConfDto.getWsdlUrl(), IntfConfDto.getSoapAction(),
					CommonUtil.createSoapXml(rootMap, nameSpaceMap)));

			if (resultLst.contains("G_EMPLOYEE")) {
				userRights.setLevel(SessionConstants.LEVEL_1);
			}
			if (resultLst.contains("G_JDD_SUB_ADMIN")) {
				userRights.setLevel(SessionConstants.LEVEL_2);
			}
			if (resultLst.contains("G_JDD_PT_ADMIN")) {
				userRights.setLevel(SessionConstants.LEVEL_3);
			}
			if (resultLst.contains("G_JDD_SH_ADMIN")) {
				userRights.setLevel(SessionConstants.LEVEL_4);
			}
			if (resultLst.contains("G_JDD_QYDXPT_ADMIN")) {
				userRights.setLevel(SessionConstants.LEVEL_5);
			}
			if (resultLst.contains("G_JDD_SUPER_ADMIN")) {
				userRights.setLevel(SessionConstants.LEVEL_6);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return userRights;
		}

		return userRights;
	}

	@Override
	public List<GeelyUserInfo> getUserInfoByDate(String context, boolean isQueryLeave) {
		// TODO Auto-generated method stub
		List<GeelyUserInfo> usersLst = new ArrayList<>();
		try {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();

			HashMap<String, Object> map = (HashMap<String, Object>) global.getObject("INTFCONF");
			IntfConfDto IntfConfDto = (IntfConfDto) map.get("GETUSERINFO");

			TreeMap<String, Object> rootMap = new TreeMap<String, Object>();
			if (CommonUtil.isNotEmpty(IntfConfDto.getRootNode())) {
				rootMap.put("001" + IntfConfDto.getRootNode(), "");
			}

			if (CommonUtil.isNotEmpty(IntfConfDto.getSubNodeLst())) {
				for (int i = 0; i < IntfConfDto.getSubNodeLst().size(); i++) {
					if ("g:Emplid".equals(IntfConfDto.getSubNodeLst().get(i))) {
						rootMap.put("101" + IntfConfDto.getSubNodeLst().get(i), "");
					} else if ("g:Business_unit".equals(IntfConfDto.getSubNodeLst().get(i))) {
						rootMap.put("102" + IntfConfDto.getSubNodeLst().get(i), context);
					} else if ("g:Month".equals(IntfConfDto.getSubNodeLst().get(i))) {
						rootMap.put("103" + IntfConfDto.getSubNodeLst().get(i), "");
					}
				}
			}

			TreeMap<String, Object> nameSpaceMap = new TreeMap<String, Object>();

			if (CommonUtil.isNotEmpty(IntfConfDto.getNameSpaceLst())) {
				for (IntfNameSpace nameSpace : IntfConfDto.getNameSpaceLst()) {
					nameSpaceMap.put(nameSpace.getKey(), nameSpace.getValue());
				}
			}

			resultMap = CommonUtil.readIntfXml(CommonUtil.doRequest(
					IntfConfDto.getWsdlUrl(), IntfConfDto.getSoapAction(),
					CommonUtil.createSoapXml(rootMap, nameSpaceMap)));

			if (resultMap != null) {
				List<HashMap<String, Object>> retLst = (List<HashMap<String, Object>>) resultMap.get("mapLst");

				for (HashMap<String, Object> tmpMap : retLst) {

					if (!isQueryLeave) {
						if (tmpMap.get("TERMINATION_DT").toString().trim().equals("")) {
							GeelyUserInfo userInfo = new GeelyUserInfo();
							userInfo.setUserId(tmpMap.get("EMPLID").toString());
							userInfo.setName(tmpMap.get("NAME").toString());
							userInfo.setCompanyName(tmpMap.get("G_COMP_DESCR").toString());
							usersLst.add(userInfo);
						}
					} else {
						GeelyUserInfo userInfo = new GeelyUserInfo();
						userInfo.setUserId(tmpMap.get("EMPLID").toString());
						userInfo.setName(tmpMap.get("NAME").toString());
						userInfo.setCompanyName(tmpMap.get("G_COMP_DESCR").toString());
						usersLst.add(userInfo);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return usersLst;
	}

	/**
	 * 调用OA接口得到用户id
	 * 
	 * @throws java.text.ParseException
	 * 
	 * */
	public String doLoginForOA(String geelyBuss) {
		String userID = "";
		if (geelyBuss == null)
			return "";
		// 调用Web Service接口
		LoginAuthenticationSoap loginAuthenSoap = new LoginAuthentication()
				.getLoginAuthenticationSoap();
		// String geelyBuss = request.getParameter("GEELYAUTHBUSS");
		String xmlWeb = "";
		// 把获取的值进行MD5解密处理
		String geelyBuss2 = loginAuthenSoap.md5Decrypt(geelyBuss);
		// 获取计时器,验证id
		String timeStamp = geelyBuss2.split(",")[0];
		String tokenId = geelyBuss2.split(",")[1];

		if ((null != timeStamp) && (null != tokenId)) {
			try {
				SimpleDateFormat formate = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String timeStamp2 = CommonUtil.getDate();
				Date dat1 = formate.parse(timeStamp);
				Date dat2 = formate.parse(timeStamp2);
				long timeMin = dat1.getTime() - dat2.getTime();
				long mins = timeMin / 1000;// 获取时间差秒

				// 从OA传输的时间点和portal本地时间点差不能超过五分钟,如果在五分钟内,返回给OA
				if ((-300 <= mins) && (mins <= 0)) {
					// 对tokenId,时间,key加密处理
					String tokenId2 = loginAuthenSoap.md5Encrypt(tokenId);
					String timeStamp3 = loginAuthenSoap.md5Encrypt(timeStamp2);
					String appkey = loginAuthenSoap
							.md5Encrypt("29501208-44B1-42ED-A7C2-91F9753081D0");
					// 解析用户信息,校验后返回给OA
					xmlWeb = loginAuthenSoap.tokenAuthtication(tokenId2,
							timeStamp3, appkey);

					Document document = DocumentHelper.parseText(xmlWeb);
					// 获取根节点元素对象
					Element node = document.getRootElement();
					Element result = node.element("Result");

					if (result.getTextTrim().equals("True")) {
						// 获取UserInfo节点对象
						Element userInfo = node.element("UserInfo");
						// 获取AppAccount节点对象
						Element appAccount = userInfo.element("AppAccount");
						userID = appAccount.getTextTrim();
					}
				} else {
					System.out.println("账号信息超时");
					;
					return null;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userID;
	}

}
