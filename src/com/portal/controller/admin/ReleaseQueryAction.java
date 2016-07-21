package com.portal.controller.admin;


import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddReleaseDetail;
import com.portal.bean.JddReleaseDetailQuery;
import com.portal.bean.JddRule;
import com.portal.bean.SysParameter;
import com.portal.common.CommonUtil;
import com.portal.common.ExcelTemplate;
import com.portal.common.SessionConstants;
import com.portal.core.cache.CacheUtils;
import com.portal.service.JddReleaseDetailService;
import com.portal.service.ParameterService;
import com.portal.service.RuleService;

@Controller
@RequestMapping("admin/release_q")
public class ReleaseQueryAction  {

	@Autowired
	private JddReleaseDetailService jddReleaseDetailService;
	
	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private ParameterService parameterService;

	/**
	 * 跳转发放记录查询页面
	 * @return 请求页面
	 */
	@RequestMapping(value = "/showQuery", method = RequestMethod.GET)
	public ModelAndView showSetting() {

		ModelAndView mav = new ModelAndView("admin/releaseQuery");
		
		//获取所有支出三级类别
		List<JddRule> gradRules = ruleService.findGradRuleByType(SessionConstants.PARAMETER_RULE_TYPE_INCOME);

		//获取所有的费用来源
		List<SysParameter> costSources = CacheUtils.get(SessionConstants.PARAMETER_SOURCE);
		if(costSources == null){
			costSources=parameterService.queryParameterByClassType(SessionConstants.PARAMETER_SOURCE);
		}
		mav.addObject("gradRules", gradRules);
		
		mav.addObject("costSources", costSources);
		
		return mav;

	}
	
	/**
	 * 查询子规则
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryChildRules")
	@ResponseBody
	public Object queryChildRules(HttpServletRequest request, @RequestParam("parentGuid") String parentGuid) {
		List<JddRule> childRules = ruleService.getChildren(parentGuid);
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		if(CommonUtil.isNotEmpty(childRules)){
			data.put("success", true);
			data.put("childRules", childRules);
		}else{
			data.put("success", false);
		}


		return data;
	}
	
	/**
	 * 发放记录查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doReleaseSearch")
	@ResponseBody
	public Object doReleaseSearch(HttpServletRequest request, HttpServletResponse response) {	
		JsonObject jsonObject = new JsonObject();
		String gradRule = request.getParameter("gradRule")==null?"":request.getParameter("gradRule");
		String fatherRule = request.getParameter("fatherRule")==null?"":request.getParameter("fatherRule");
		String childRule = request.getParameter("childRule")==null?"":request.getParameter("childRule");
		String costSource = request.getParameter("costSource")==null?"":request.getParameter("costSource");
		String startDate = request.getParameter("startDate")==null?"":request.getParameter("startDate");
		String endDate = request.getParameter("endDate")==null?"":request.getParameter("endDate");
		String isGroupBy = request.getParameter("isGroupBy")==null?"":request.getParameter("isGroupBy");
		Integer pageNum = 1;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.valueOf(request.getParameter("pageNum"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JddReleaseDetailQuery jddReleaseDetailQuery = new JddReleaseDetailQuery();
		
		
		GeelyUserInfo userInfo =(GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);

		int level=userInfo.getUserRights().getLevel();	
		//权限控制，如果为普通管理员，就只能看自己公司的
		if(level==SessionConstants.LEVEL_2){
			jddReleaseDetailQuery.setUserUnit(userInfo.getCompanyCode());
		}

		if(!gradRule.equals("0")){
			jddReleaseDetailQuery.setRuleGrad(gradRule);
		};
		
		if(!fatherRule.equals("0")){
			jddReleaseDetailQuery.setRuleFather(fatherRule);
		};
		
		if(!childRule.equals("0")){
			jddReleaseDetailQuery.setRuleChild(childRule);
		};
		
		if(!costSource.equals("0")){
			jddReleaseDetailQuery.setSource(costSource);
		};
		jddReleaseDetailQuery.setStstus(2);
		
		if(!startDate.equals("")){
			try {
				jddReleaseDetailQuery.setStartDate(sdf.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonObject.addProperty("success", false);
				return jsonObject.toString();
			}
		}
		
		if(!endDate.equals("")){
			try {
				jddReleaseDetailQuery.setEndDate(sdf.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonObject.addProperty("success", false);
				return jsonObject.toString();
			}
		}
		PageInfo<JddReleaseDetail> pageInfo = null;
		
		if(isGroupBy.equals("true")){
			pageInfo = jddReleaseDetailService.queryPageListByWhereGroupBy(pageNum, SessionConstants.PAGE_SHOW_NUM, jddReleaseDetailQuery, null);
		}else{
			List<String> orderBy = new ArrayList<String>();
			orderBy.add("CREATE_TIME desc");
			pageInfo = jddReleaseDetailService.queryPageListByWhere(pageNum, SessionConstants.PAGE_SHOW_NUM, jddReleaseDetailQuery, orderBy);
		}
		
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("success", true);
		data.put("resultPageInfo", pageInfo);

		return data;
	}
	
	/**
	 * 发放记录查询结果导出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doReleaseExport")
	public void doReleaseExport(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String gradRule = request.getParameter("gradRule")==null?"":request.getParameter("gradRule");
		String fatherRule = request.getParameter("fatherRule")==null?"":request.getParameter("fatherRule");
		String childRule = request.getParameter("childRule")==null?"":request.getParameter("childRule");
		String costSource = request.getParameter("costSource")==null?"":request.getParameter("costSource");
		String startDate = request.getParameter("startDate")==null?"":request.getParameter("startDate");
		String endDate = request.getParameter("endDate")==null?"":request.getParameter("endDate");
		String isGroupBy = request.getParameter("isGroupBy")==null?"":request.getParameter("isGroupBy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_show = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		JddReleaseDetailQuery jddReleaseDetailQuery = new JddReleaseDetailQuery();
		
		
		
		GeelyUserInfo userInfo =(GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);

		int level=userInfo.getUserRights().getLevel();	
		//权限控制，如果为普通管理员，就只能看自己公司的
		if(level==SessionConstants.LEVEL_2){
			jddReleaseDetailQuery.setUserUnit(userInfo.getCompanyCode());
		}
	
		if(!gradRule.equals("0")){
			jddReleaseDetailQuery.setRuleGrad(gradRule);
		};
		
		if(!fatherRule.equals("0")){
			jddReleaseDetailQuery.setRuleFather(fatherRule);
		};

		if(!childRule.equals("0")){
			jddReleaseDetailQuery.setRuleChild(childRule);
		};
		
		if(!costSource.equals("0")){
			jddReleaseDetailQuery.setSource(costSource);
		};
		
		jddReleaseDetailQuery.setStstus(2);
		
		if(!startDate.equals("")){
			try {
				jddReleaseDetailQuery.setStartDate(sdf.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		if(!endDate.equals("")){
			try {
				jddReleaseDetailQuery.setEndDate(sdf.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		if(isGroupBy.equals("true")){
			List<JddReleaseDetail> detailList = jddReleaseDetailService.selectByWhereGroupBy(jddReleaseDetailQuery);
			
			OutputStream ouputStream = response.getOutputStream();

			ExcelTemplate et=ExcelTemplate.getInstance().readTemplateByClasspath("/excel/release_g.xls");
			
			if(CommonUtil.isNotEmpty(detailList)){
				for(int i = 0; i < detailList.size(); i++){
					et.createNewRow();
					et.createCell(i+1);
					et.createCell(detailList.get(i).getChildName());
					et.createCell(detailList.get(i).getSourceName());
					et.createCell(detailList.get(i).getJddCount());
				}
			}
			
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
			String createTime = dateformat.format(new Date());
			
			Map<String,String> datas = new HashMap<String,String>();
			datas.put("title","发放信息");
			datas.put("date",dateformat.format(new Date()));
			et.replaceFinalData(datas);
			
			String filedisplay = "发放记录"+createTime;
			filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ filedisplay + ".xls");
			et.wirteToStream(ouputStream);
			ouputStream.flush();
			ouputStream.close();
			
		}else{
			List<JddReleaseDetail> detailList = jddReleaseDetailService.selectByWhere(jddReleaseDetailQuery);
			
			OutputStream ouputStream = response.getOutputStream();

			ExcelTemplate et=ExcelTemplate.getInstance().readTemplateByClasspath("/excel/release.xls");
			
			if(CommonUtil.isNotEmpty(detailList)){
				for(int i = 0; i < detailList.size(); i++){
					et.createNewRow();
					et.createCell(i+1);
					et.createCell(detailList.get(i).getChildName());
					et.createCell(detailList.get(i).getSourceName());
					et.createCell(detailList.get(i).getJddCount());
					et.createCell(sdf_show.format(detailList.get(i).getCreateTime()));
				}
			}
			
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
			String createTime = dateformat.format(new Date());
			
			Map<String,String> datas = new HashMap<String,String>();
			datas.put("title","发放信息");
			datas.put("date",dateformat.format(new Date()));
			et.replaceFinalData(datas);
			
			String filedisplay = "发放记录"+createTime;
			filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ filedisplay + ".xls");
			et.wirteToStream(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		}
	}
}
