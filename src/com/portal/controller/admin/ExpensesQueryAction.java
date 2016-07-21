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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.portal.bean.GeelyUserInfo;
import com.portal.bean.JddPayment;
import com.portal.bean.JddPaymentQuery;
import com.portal.bean.JddRule;
import com.portal.common.CommonUtil;
import com.portal.common.ExcelTemplate;
import com.portal.common.SessionConstants;
import com.portal.service.JddPaymentService;
import com.portal.service.RuleService;

@Controller
@RequestMapping("admin/expenses_q")
public class ExpensesQueryAction {

	@Autowired
	private JddPaymentService jddPaymentService;
	
	@Autowired
	private RuleService ruleService;

	/**
	 * 跳转支出查询页面
	 * @return 请求页面
	 */
	@RequestMapping(value = "/showQuery", method = RequestMethod.GET)
	public ModelAndView showSetting() {

		ModelAndView mav = new ModelAndView("admin/expensesQuery");
		
		//获取所有支出三级类别
		List<JddRule> ruleChilds = ruleService.findChildRuleByType(SessionConstants.PARAMETER_RULE_TYPE_EXPENSES);
		mav.addObject("ruleChilds", ruleChilds);
		return mav;

	}
	
	/**
	 * 支出查询
	 * @param request
	 * @param response
	 * @return 查询结果
	 */
	@RequestMapping("/doExpensesSearch")
	@ResponseBody
	public Object doExpensesSearch(HttpServletRequest request, HttpServletResponse response) {	
		JsonObject jsonObject = new JsonObject();
		String ruleChild = request.getParameter("ruleChild")==null?"":request.getParameter("ruleChild");
		String startDate = request.getParameter("startDate")==null?"":request.getParameter("startDate");
		String endDate = request.getParameter("endDate")==null?"":request.getParameter("endDate");
		String isGroupBy = request.getParameter("isGroupBy")==null?"":request.getParameter("isGroupBy");
		Integer pageNum = 1;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.valueOf(request.getParameter("pageNum"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JddPaymentQuery jddPaymentQuery = new JddPaymentQuery();
		
		GeelyUserInfo userInfo =(GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);
		int level=userInfo.getUserRights().getLevel();	
		//权限控制，如果为普通管理员，就只能看自己公司的
		if(level==SessionConstants.LEVEL_2){
			jddPaymentQuery.setUserUnit(userInfo.getCompanyCode());
		}

		
		if(!ruleChild.equals("0")){
			jddPaymentQuery.setRuleChild(ruleChild);
		};
		
		if(!startDate.equals("")){
			try {
				jddPaymentQuery.setStartDate(sdf.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonObject.addProperty("success", false);
				return jsonObject.toString();
			}
		}
		
		if(!endDate.equals("")){
			try {
				jddPaymentQuery.setEndDate(sdf.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonObject.addProperty("success", false);
				return jsonObject.toString();
			}
		}
		PageInfo<JddPayment> pageInfo = null;
		
		if(isGroupBy.equals("true")){
			pageInfo = jddPaymentService.queryPageListByWhereGroupBy(pageNum, SessionConstants.PAGE_SHOW_NUM, jddPaymentQuery, null);
		}else{
			List<String> orderBy = new ArrayList<String>();
			orderBy.add("CREATE_TIME desc");
			pageInfo = jddPaymentService.queryPageListByWhere(pageNum, SessionConstants.PAGE_SHOW_NUM, jddPaymentQuery, orderBy);
		}
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("success", true);
		data.put("resultPageInfo", pageInfo);

		return data;
	}
	
	/**
	 * 支出查询记录导出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doExpensesExport")
	public void doExpensesExport(HttpServletRequest request,
			HttpServletResponse response) throws IOException {	
		String ruleChild = request.getParameter("ruleChild")==null?"":request.getParameter("ruleChild");
		String startDate = request.getParameter("startDate")==null?"":request.getParameter("startDate");
		String endDate = request.getParameter("endDate")==null?"":request.getParameter("endDate");
		String isGroupBy = request.getParameter("isGroupBy")==null?"":request.getParameter("isGroupBy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_show = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		JddPaymentQuery jddPaymentQuery = new JddPaymentQuery();
		
		
		GeelyUserInfo userInfo =(GeelyUserInfo) request.getSession().getAttribute(SessionConstants.USERINFO);

		int level=userInfo.getUserRights().getLevel();	
		//权限控制，如果为普通管理员，就只能看自己公司的
		if(level==SessionConstants.LEVEL_2){
			jddPaymentQuery.setUserUnit(userInfo.getCompanyCode());
		}

		
		
		
		if(!ruleChild.equals("0")){
			jddPaymentQuery.setRuleChild(ruleChild);
		};
		
		if(!startDate.equals("")){
			try {
				jddPaymentQuery.setStartDate(sdf.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		if(!endDate.equals("")){
			try {
				jddPaymentQuery.setEndDate(sdf.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		if(isGroupBy.equals("true")){
			List<JddPayment> jddPaymentList = jddPaymentService.selectByWhereGroupBy(jddPaymentQuery);
			
			OutputStream ouputStream = response.getOutputStream();

			ExcelTemplate et=ExcelTemplate.getInstance().readTemplateByClasspath("/excel/expenses_g.xls");
			
			if(CommonUtil.isNotEmpty(jddPaymentList)){
				for(int i = 0; i < jddPaymentList.size(); i++){
					et.createNewRow();
					et.createCell(i+1);
					et.createCell(jddPaymentList.get(i).getChildName());
					et.createCell(jddPaymentList.get(i).getCount());
				}
			}
			
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
			String createTime = dateformat.format(new Date());
			
			Map<String,String> datas = new HashMap<String,String>();
			datas.put("title","支出信息");
			datas.put("date",dateformat.format(new Date()));
			et.replaceFinalData(datas);
			
			String filedisplay = "支出记录"+createTime;
			filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ filedisplay + ".xls");
			et.wirteToStream(ouputStream);
			ouputStream.flush();
			ouputStream.close();
			
		}else{
			List<JddPayment> jddPaymentList = jddPaymentService.selectByWhere(jddPaymentQuery);
			
			OutputStream ouputStream = response.getOutputStream();

			ExcelTemplate et=ExcelTemplate.getInstance().readTemplateByClasspath("/excel/expenses.xls");
			
			if(CommonUtil.isNotEmpty(jddPaymentList)){
				for(int i = 0; i < jddPaymentList.size(); i++){
					et.createNewRow();
					et.createCell(i+1);
					et.createCell(jddPaymentList.get(i).getChildName());
					et.createCell(jddPaymentList.get(i).getCount());
					et.createCell(sdf_show.format(jddPaymentList.get(i).getCreateTime()));
				}
			}
			
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
			String createTime = dateformat.format(new Date());
			
			Map<String,String> datas = new HashMap<String,String>();
			datas.put("title","支出信息");
			datas.put("date",dateformat.format(new Date()));
			et.replaceFinalData(datas);
			
			String filedisplay = "支出记录"+createTime;
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
