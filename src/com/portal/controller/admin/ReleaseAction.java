package com.portal.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddRelease;
import com.portal.bean.JddReleaseDetail;
import com.portal.bean.JddRule;
import com.portal.bean.JkRelease;
import com.portal.bean.JkReleaseDetail;
import com.portal.bean.SysParameter;
import com.portal.bean.UserInfo;
import com.portal.common.Page;
import com.portal.common.ReadXlsUtil;
import com.portal.common.SessionConstants;
import com.portal.common.StringUtil;
import com.portal.core.cache.CacheUtils;
import com.portal.service.CommonService;
import com.portal.service.GeelyPortService;
import com.portal.service.JddReleaseDetailService;
import com.portal.service.JddReleaseService;
import com.portal.service.JkReleaseDetailService;
import com.portal.service.JkReleaseService;
import com.portal.service.ParameterService;
import com.portal.service.RuleService;
import com.portal.service.UserInfoService;

@Controller
@RequestMapping("/admin/release")
public class ReleaseAction {

	@Autowired
	public GeelyPortService geelyPortService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private RuleService ruleService;

	@Autowired
	private JddReleaseService jddReleaseService;

	@Autowired
	private JddReleaseDetailService jddReleaseDetailService;

	@Autowired
	private JkReleaseService jkReleaseService;

	@Autowired
	private JkReleaseDetailService jkReleaseDetailService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ParameterService parameterService;

	private Logger logger = Logger.getLogger(ReleaseAction.class);

	@RequestMapping("/toAddSingleJdd")
	public ModelAndView toAddSingleJdd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/singleJdd");
		List<SysParameter> sources = CacheUtils.get(SessionConstants.PARAMETER_SOURCE);
		List<JddRule> childlist = ruleService.findGradRuleByType(0);
		mav.addObject(SessionConstants.PARAMETER_SOURCE, sources);
		mav.addObject("rules", childlist);

		if (StringUtil.isEmpty(request.getParameter("msg"))) {
			mav.addObject("msg", true);
		}
		else {
			mav.addObject("msg", false);
		}

		return mav;
	}

	/**
	 * 单条导入，post请求，表单提交 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月10日 下午2:52:31
	 * @param request
	 * @param response
	 * @param detail
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/addSingleJdd", method = RequestMethod.POST)
	public ModelAndView addSingleJdd(HttpServletRequest request, JddReleaseDetail detail,String userName,
			RedirectAttributes attr) {

		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		ModelAndView mav = new ModelAndView("redirect:/admin/release/toAddSingleJdd");
		attr.addAttribute("msg", false);
		if (judgeUser(detail.getUserId(), detail.getUserName()) == 1) {
			return mav;
		}
		JddRelease release = new JddRelease();
		try {
			release.setCreateBy(userInfo.getUserId());
			release.setCreateName(userInfo.getName());
			release.setCreateTime(new Date());
			release.setStstus(0);
			release.setExcelPath(String.valueOf(0));
			release.setContent(request.getParameter("content"));
			release.setSource(request.getParameter("source"));
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 得到上传的文件
			MultipartFile filePath = multipartRequest.getFile("filePath");
			String path = request.getSession().getServletContext().getRealPath("/") + "../upload/file/";
			String fileName = filePath.getOriginalFilename();
			fileName = StringUtil.getRandom() + fileName.substring(fileName.indexOf("."));

			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				filePath.transferTo(targetFile);
			} catch (Exception e) {
				System.out.println("上传审批文件失败");
			}
			release.setFilePath(fileName);
			release.setUserCom(userInfo.getCompanyNameDesc());
			release.setGuid(StringUtil.getGUID());

			int msg = jddReleaseService.insert(release);
			if (msg == 0) {
				return mav;
			}
			//受支援公司
			if(SessionConstants.PARAMETER_SOURCE_HELP_GUID.equals(detail.getSource())){
				detail.setSourceComp(userInfo.getCompanyCode());
			}
			else{
				userInfo = geelyPortService.getUserInfo(detail.getUserId());
				detail.setSourceComp(userInfo.getCompanyCode());
			}	
			detail.setReleaseId(release.getGuid());
			UserInfo d=userInfoService.selectByUserId(detail.getUserId());
			detail.setUserCom(d.getUserCom());
			detail.setUserDept(d.getUserDept());
			msg = jddReleaseDetailService.insert(detail);
			if (msg == 0) {
				return mav;
			}

		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "添加失败");
			return mav;
		}
		// 保存的时候是guid,但显示的时候要是内容
		detail.setRuleChild(ruleService.findRuleByGUID(detail.getRuleChild()).getClassName());
		detail.setSource(parameterService.selectByPrimaryKey(detail.getSource()).getParamValue());

		mav = new ModelAndView("admin/jddView");
		mav.addObject("release", release);
		List<JddReleaseDetail> details = new ArrayList<JddReleaseDetail>();
		details.add(detail);
		mav.addObject("details", details);
		mav.addObject("admin", 0);
		return mav;
	}

	@RequestMapping("toReleaseManager")
	public ModelAndView toReleaseManager(HttpServletRequest request, String type, Page page) {
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		int level = userInfo.getUserRights().getLevel();
		String guid = null;
		// 0 jdd 1 jinbi
		int releaseType = StringUtil.isEmpty(type) ? 0 : Integer.parseInt(type);
		// 普通管理员只能查看自己提交的
		if (level == SessionConstants.LEVEL_2) {
			guid = userInfo.getUserId();
			releaseType=0;
		}
		if (level == SessionConstants.LEVEL_3) {
			releaseType=0;
		}
		if (level == SessionConstants.LEVEL_5) {
			releaseType=1;
		}

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String content = request.getParameter("content");
		ModelAndView mav = new ModelAndView("admin/releaseManager");

		try {
			if (releaseType == 0)
				mav.addObject("pageInfo", jddReleaseService.selectJddReleaseByExample(page,
						startDate, endDate, content, null, null, guid));
			else
				mav.addObject("pageInfo",
						jkReleaseService.selectJkReleaseByExample(page, startDate, endDate, null, null, guid));

		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("type", releaseType);
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.addObject("content", content);
		return mav;

	}

	@RequestMapping("jddView")
	public ModelAndView jddView(HttpServletRequest request, Page page) {
		String releaseGuid = request.getParameter("releaseGuid");
		ModelAndView mav = new ModelAndView("admin/jddView");
		JddRelease release = jddReleaseService.selectByPrimaryKey(releaseGuid);
		List<JddReleaseDetail> details = jddReleaseDetailService.selectByReleaseGuid2(release.getGuid());
		mav.addObject("release", release);
		mav.addObject("details", details);
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		int level = userInfo.getUserRights().getLevel();
		if (level == SessionConstants.LEVEL_4) {
			mav.addObject("admin", 1);
		}
		else {
			mav.addObject("admin", 0);
		}
		return mav;

	}

	@RequestMapping("jkView")
	public ModelAndView jkView(HttpServletRequest request, Page page, String admin) {
		String releaseGuid = request.getParameter("releaseGuid");
		ModelAndView mav = new ModelAndView("admin/jkView");
		JkRelease release = jkReleaseService.selectByPrimaryKey(releaseGuid);
		List<JkReleaseDetail> details = jkReleaseDetailService.selectByReleaseGuid(release
				.getGuid());
		mav.addObject("release", release);
		mav.addObject("details", details);
		if (!StringUtil.isEmpty(admin) && admin.equals("admin")) {
			mav.addObject("admin", 1);
		}
		else {
			mav.addObject("admin", 0);
		}
		return mav;

	}

	/**
	 * 跳转到jdd批量导入 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月10日 上午10:30:47
	 * @return
	 */

	@RequestMapping("/toInportJdd")
	public ModelAndView toInportJdd() {
		ModelAndView mav = new ModelAndView("admin/inportJdd");
		return mav;
	}

	/**
	 * 跳转到jk金币导入页面 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月12日 下午4:36:26
	 * @return
	 */
	@RequestMapping("/toInportJk")
	public ModelAndView toInportJk() {
		ModelAndView mav = new ModelAndView("admin/inportJk");
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("readExcel")
	@ResponseBody
	public List<Map<String, Object>> readExcel(HttpServletRequest request, Page page, String type)
			throws IOException {
		int releaseType = StringUtil.isEmpty(type) ? 0 : Integer.parseInt(type);

		// jdd读取
		if (releaseType == 0) {
			List<JddReleaseDetail> list = null;
			List<Map<String, Object>> newlist = null;
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 得到上传的文件
				MultipartFile mFile = multipartRequest.getFile("excelPath");
				InputStream inputStream = mFile.getInputStream();
				list = ReadXlsUtil.readXlsx(inputStream, 0);
				newlist = judgeExcel(list, request);
				CacheUtils.set(SessionConstants.JDDRELEASE_UPLOADEXCELDATA, newlist);
			} catch (Exception e) {
				e.printStackTrace();
				newlist = null;
				return newlist;
			}

			return newlist;
		}
		// jk金币读取
		else {
			List<JkReleaseDetail> list = null;
			List<Map<String, Object>> newlist = null;
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 得到上传的文件
				MultipartFile mFile = multipartRequest.getFile("excelPath");
				InputStream inputStream = mFile.getInputStream();
				list = ReadXlsUtil.readXlsx(inputStream, 1);
				newlist = judgeJkExcel(list);
				CacheUtils.set(SessionConstants.JKRELEASE_UPLOADEXCELDATA, newlist);
			} catch (Exception e) {
				e.printStackTrace();
				newlist = null;
				return newlist;
			}

			return newlist;

		}

	}

	@RequestMapping("uploadTemplate")
	public void uploadTemplate(HttpServletRequest request, HttpServletResponse res, int tempType) {

		try {
			InputStream input = null;
			OutputStream ouputStream = res.getOutputStream();
			res.reset(); // 必要地清除response中的缓存信息
			res.setContentType("application/octet-stream");
			String path = getClass().getResource("/").getPath();
			// win
			if (path.contains(":/")) {
				path = path.substring(1);
			}
			// linux
			else {
				path = path.substring(0);
			}

			if (tempType == 0) {
				path += "excel/吉点点导入模板.xlsx";
				res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("吉点点导入模版", "UTF-8")
						+ ".xlsx");
				input = new FileInputStream(new File(path));
			}
			if (tempType == 1) {
				path += "excel/金币导入模板.xlsx";
				res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("金币导入模版", "UTF-8")
						+ ".xlsx");
				input = new FileInputStream(new File(path));
			}

			int temp = 0;
			while ((temp = input.read()) != -1) {
				ouputStream.write(temp);
			}
			input.close();
			ouputStream.flush();
			ouputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("uploadFilePath")
	public void uploadFilePath(HttpServletRequest request, HttpServletResponse res, String filePath) {
		InputStream input = null;
		try {
			String path = request.getSession().getServletContext().getRealPath("/") + "../upload/file/" + filePath;
			if (StringUtil.isEmpty(filePath)) {
				logger.error("获取审批文件失败");
			}

			OutputStream ouputStream = res.getOutputStream();
			res.reset(); // 必要地清除response中的缓存信息
			res.setContentType("application/octet-stream");
			res.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ URLEncoder.encode("审批文件" + filePath.substring(filePath.indexOf(".")), "UTF-8"));
			input = new FileInputStream(new File(path));

			int temp = 0;
			while ((temp = input.read()) != -1) {
				ouputStream.write(temp);
			}
			input.close();
			ouputStream.flush();
			ouputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/inportJdd")
	public ModelAndView inportJdd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/jddView");
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 得到上传的文件
			MultipartFile filePath = multipartRequest.getFile("filePath");
			String path = request.getSession().getServletContext().getRealPath("/") + "../upload/file/";
			String fileName = filePath.getOriginalFilename();
			fileName = StringUtil.getRandom() + fileName.substring(fileName.indexOf("."));
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				filePath.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("上传审批文件失败");
			}
			JddRelease release = new JddRelease();
			release.setContent(request.getParameter("content"));
			release.setFilePath(fileName);
			release.setGuid(StringUtil.getGUID());
			release.setStstus(0);
			release.setCreateBy(userInfo.getUserId());
			release.setCreateName(userInfo.getName());
			release.setCreateTime(new Date());
			release.setExcelPath(String.valueOf(1));

			List<Map<String, Object>> newlist = CacheUtils.get(SessionConstants.JDDRELEASE_UPLOADEXCELDATA);
			List<JddReleaseDetail> details = jddReleaseDetailService.saveListDetail(newlist, release.getGuid());
			jddReleaseService.insert(release);
			mav.addObject("admin", 0);
			mav.addObject("release", release);
			mav.addObject("details", details);

		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("admin/inportJdd");
			mav.addObject("error", "导入失败！");
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/inportJk")
	public ModelAndView inportJk(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/jkView");
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 得到上传的文件
			MultipartFile filePath = multipartRequest.getFile("filePath");
			String path = request.getSession().getServletContext().getRealPath("/") + "../upload/file/";
			String fileName = filePath.getOriginalFilename();
			fileName = StringUtil.getRandom() + fileName.substring(fileName.indexOf("."));
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				filePath.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("上传审批文件失败");
			}
			JkRelease release = new JkRelease();
			release.setExcelPath("\\" + fileName);
			release.setGuid(StringUtil.getGUID());
			release.setStstus(0);
			release.setCreateBy(userInfo.getUserId());
			release.setCreateName(userInfo.getName());
			release.setCreateTime(new Date());
			jkReleaseService.insert(release);

			List<JkReleaseDetail> details = new ArrayList<JkReleaseDetail>();
			List<Map<String, Object>> newlist = CacheUtils
					.get(SessionConstants.JKRELEASE_UPLOADEXCELDATA);
			if (StringUtil.isNotNull(newlist)) {
				Iterator<Map<String, Object>> iterator = newlist.iterator();
				while (iterator.hasNext()) {
					Map<String, Object> map = iterator.next();
					if (map.get("right").equals(0)) {
						JkReleaseDetail detail = (JkReleaseDetail) map.get("detail");
						detail.setGuid(StringUtil.getGUID());
						detail.setReleaseId(release.getGuid());
						jkReleaseDetailService.insert(detail);
						details.add(detail);
					}
				}

			}
			mav.addObject("admin", 0);
			mav.addObject("release", release);
			mav.addObject("details", details);
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("admin/inportJk");
			mav.addObject("error", "导入失败！");
			return mav;
		}
		return mav;
	}

	/**
	 * 读取的excel数据进行分析判断，有问题的进行记录 Description: <BR>
	 * right 1 表示有错误
	 * 
	 * @author cyk
	 * @date 2016年6月11日 上午9:51:28
	 * @param list
	 * @param sources
	 * @return
	 */
	public List<Map<String, Object>> judgeExcel(List<JddReleaseDetail> list, HttpServletRequest request) {
		List<Map<String, Object>> newlist = new ArrayList<Map<String, Object>>();
		if (StringUtil.isNull(list)) {
			return null;
		}
		List<SysParameter> sources = CacheUtils.get(SessionConstants.PARAMETER_SOURCE);
		Iterator<JddReleaseDetail> iterator = list.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			JddReleaseDetail detail = iterator.next();
			if (detail == null) {
				continue;
			}
			int count = detail.getJddCount();
			if (count < 0) {
				map.put("right", 1);
				map.put("detail", detail);
				newlist.add(map);
				continue;
			}
			String userId = detail.getUserId();
			String userName = detail.getUserName();

			// 判断用户id与姓名是否对应
			if (judgeUser(userId, userName) == 1) {
				map.put("right", 1);
				map.put("detail", detail);
				newlist.add(map);
				continue;
			}
			UserInfo user = userInfoService.selectByUserId(userId);
			detail.setUserCom(user.getUserCom());
			detail.setUserDept(user.getUserDept());
			// 判断来源
			/*
			 * boolean falg = false; for (int i = 0; i < sources.size(); i++) {
			 * if (sources.get(i).getParamValue().equals(detail.getSource())) {
			 * detail.setSource(sources.get(i).getGuid()); falg = true; break; }
			 * } if (!falg) { map.put("right", 1); map.put("detail", detail);
			 * newlist.add(map); continue; }
			 */

			// 判断rule类型

			map.put("child", detail.getRuleChild());
			map.put("source", detail.getSource());
			// 获取三级目录
			List<JddRule> childlist = ruleService.findChildRuleByType(0);
			Iterator<JddRule> it = childlist.iterator();
			boolean falg = false;
			while (it.hasNext()) {
				JddRule jddRule = it.next();
				if (jddRule.getClassName().equals(detail.getRuleChild().trim())) {
					detail.setRuleChild(jddRule.getGuid());
					detail.setSource(jddRule.getSource());
					// 来源中的受支援公司
					if (jddRule.getSource().equals(SessionConstants.PARAMETER_SOURCE_HELP_GUID)) {
						GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(
								SessionConstants.USERINFO);
						detail.setSourceComp(userInfo.getCompanyCode());
					}
					else {
						UserInfo userInfo = userInfoService.selectByUserId(userId);
						detail.setSourceComp(userInfo.getUserUnit());
					}

					if (!StringUtil.isEmpty(jddRule.getSource().trim())) {
						for (int i = 0; i < sources.size(); i++) {
							if (jddRule.getSource().equals(sources.get(i).getGuid())) {
								map.put("source", sources.get(i).getParamValue());
								break;
							}
						}
					}

					falg = true;
					break;
				}
			}
			if (!falg) {
				map.put("right", 1);
				map.put("detail", detail);
				newlist.add(map);
				continue;
			}

			map.put("right", 0);
			map.put("detail", detail);
			newlist.add(map);
		}

		return newlist;
	}

	/**
	 * 判断用户是否正确（userId与userName对应 0正确 1 错误） Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月7日 下午7:34:21
	 * @param userId
	 * @param userName
	 * @return
	 */

	public int judgeUser(String userId, String userName) {
		UserInfo user = userInfoService.selectByUserId(userId);
		// 本地库没有就去接口获取
		if (user == null) {
			GeelyUserInfo userInfo = geelyPortService.getUserInfo(userId);
			// 保存用户

			if (userInfo.getName().equals(userName)) {
				return userInfoService.insertUserInfoFromGeely(userInfo);
			}
			else {
				return 1;
			}
		}
		if (user.getUserName().equals(userName)) {
			return 0;
		}
		else {
			return 1;
		}

	}

	/**
	 * jk金币excel分析 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月12日 下午6:29:02
	 * @param list
	 * @return
	 */
	public List<Map<String, Object>> judgeJkExcel(List<JkReleaseDetail> list) {
		List<Map<String, Object>> newlist = new ArrayList<Map<String, Object>>();
		Iterator<JkReleaseDetail> iterator = list.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			JkReleaseDetail detail = iterator.next();
			if (detail == null) {
				continue;
			}
			if (this.judgeUser(detail.getUserId(), detail.getUserName()) == 0) {
				map.put("right", 0);
			}
			else {
				map.put("right", 1);
			}
			map.put("detail", detail);
			newlist.add(map);

		}
		return newlist;
	}

	/**
	 * 审批页面 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月13日 下午2:22:29
	 * @param request
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping("/checkRelease")
	public ModelAndView checkRelease(HttpServletRequest request, String type, Page page) {
		GeelyUserInfo userInfo = (GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		int level = userInfo.getUserRights().getLevel();
		if (level != SessionConstants.LEVEL_4) {
			logger.error("您没有权限进行审批");
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("error", "您没有权限进行审批");
			return mav;
		}

		ModelAndView mav = new ModelAndView("admin/checkRelease");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String createName = request.getParameter("createName");
		int ststus = StringUtil.isEmpty(request.getParameter("ststus")) ? -1 : Integer.parseInt(request.getParameter(
				"ststus").trim());
		// 0 jdd 1 jinbi
		int releaseType = StringUtil.isEmpty(type) ? 0 : Integer.parseInt(type);
		try {
			if (releaseType == 0)
				mav.addObject("pageInfo", jddReleaseService.selectJddReleaseByExample(page,
						startDate, endDate, null, createName, ststus, null));
			else
				mav.addObject("pageInfo",
						jkReleaseService.selectJkReleaseByExample(page, startDate, endDate,
								createName, ststus, null));

		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("type", releaseType);
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.addObject("createName", createName);
		mav.addObject("ststus", ststus);
		return mav;
	}

	/**
	 * jdd审批提交操作 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月14日 下午5:10:11
	 * @param releaseGuid
	 * @param ststus
	 * @return
	 */
	@RequestMapping("/checkJddView")
	@ResponseBody
	public Integer checkJddView(String releaseGuid, int ststus, HttpServletRequest request) {
		List<JddReleaseDetail> details = jddReleaseDetailService.selectByReleaseGuid(releaseGuid);
		// 审批通过
		if (ststus == 2) {
			commonService.jddbaserecord(details, request);
			return 2;
		}
		// 驳回
		if (ststus == 1) {
			JddRelease release = new JddRelease();
			release.setStstus(1);
			release.setGuid(releaseGuid);
			return jddReleaseService.updateByPrimaryKeySelective(release);
		}
		return 0;
	}

	/**
	 * 金币审批提交操作 Description: <BR>
	 * 
	 * @author cyk
	 * @date 2016年6月14日 下午5:10:38
	 * @param releaseGuid
	 * @param ststus
	 * @return
	 */

	@RequestMapping("/checkJkView")
	@ResponseBody
	public Integer checkJkView(String releaseGuid, int ststus, HttpServletRequest request) {
		List<JkReleaseDetail> details = jkReleaseDetailService.selectByReleaseGuid(releaseGuid);
		// 审批通过
		if (ststus == 2) {
			return userInfoService.updateJkSum(details, request);
		}
		// 驳回
		if (ststus == 1) {
			JkRelease release = new JkRelease();
			release.setStstus(1);
			release.setGuid(releaseGuid);
			return jkReleaseService.updateStatus(release);
		}

		return 0;

	}

	@RequestMapping("/findUser")
	@ResponseBody
	public Object findUser(String userId, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isEmpty(userId)) {
			return null;
		}
		GeelyUserInfo userInfo = null;
		try {
			userInfo = geelyPortService.getUserInfo(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		map.put("name", userInfo.getName());
		return map;

	}

}
