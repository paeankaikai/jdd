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
import com.portal.bean.JddJkrecord;
import com.portal.bean.JddJkrecordQuery;
import com.portal.common.CommonUtil;
import com.portal.common.ExcelTemplate;
import com.portal.common.SessionConstants;
import com.portal.service.JddJkRecordService;

@Controller
@RequestMapping("admin/exchange_q")
public class ExchangeQueryAction {

	@Autowired
	private JddJkRecordService jddJkRecordService;

	/**
	 * 跳转兑换记录查询页面
	 * @return 请求页面
	 */
	@RequestMapping(value = "/showQuery", method = RequestMethod.GET)
	public ModelAndView showSetting() {

		ModelAndView mav = new ModelAndView("admin/exchangeQuery");
		
		//获取所有兑换吉课金币数和吉点点数
		JddJkrecord countInfo = jddJkRecordService.queryExchangeCountInfo();

		mav.addObject("countInfo", countInfo);
		return mav;

	}
	
	/**
	 * 兑换记录查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doExchangeSearch")
	@ResponseBody
	public Object doExchangeSearch(HttpServletRequest request, HttpServletResponse response) {	
		JsonObject jsonObject = new JsonObject();
		String startDate = request.getParameter("startDate")==null?"":request.getParameter("startDate");
		String endDate = request.getParameter("endDate")==null?"":request.getParameter("endDate");
		Integer pageNum = 1;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.valueOf(request.getParameter("pageNum"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JddJkrecordQuery jddJkrecordQuery = new JddJkrecordQuery();
		
		if(!startDate.equals("")){
			try {
				jddJkrecordQuery.setStartDate(sdf.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonObject.addProperty("success", false);
				return jsonObject.toString();
			}
		}
		
		if(!endDate.equals("")){
			try {
				jddJkrecordQuery.setEndDate(sdf.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jsonObject.addProperty("success", false);
				return jsonObject.toString();
			}
		}
		
		PageInfo<JddJkrecord> pageInfo = null;

		List<String> orderBy = new ArrayList<String>();
		orderBy.add("CREATE_TIME desc");
		pageInfo = jddJkRecordService.queryPageListByWhere(pageNum, SessionConstants.PAGE_SHOW_NUM, jddJkrecordQuery, orderBy);

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("success", true);
		data.put("resultPageInfo", pageInfo);

		return data;
	}
	
	/**
	 * 兑换记录查询结果导出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doExchangeExport")
	public void doExchangeExport(HttpServletRequest request,
			HttpServletResponse response) throws IOException {	
		String startDate = request.getParameter("startDate")==null?"":request.getParameter("startDate");
		String endDate = request.getParameter("endDate")==null?"":request.getParameter("endDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_show = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		JddJkrecordQuery jddJkrecordQuery = new JddJkrecordQuery();

		if(!startDate.equals("")){
			try {
				jddJkrecordQuery.setStartDate(sdf.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		if(!endDate.equals("")){
			try {
				jddJkrecordQuery.setEndDate(sdf.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}

		List<JddJkrecord> recordList = jddJkRecordService.selectByWhere(jddJkrecordQuery);

		OutputStream ouputStream = response.getOutputStream();

		ExcelTemplate et=ExcelTemplate.getInstance().readTemplateByClasspath("/excel/exchange.xls");

		if(CommonUtil.isNotEmpty(recordList)){
			for(int i = 0; i < recordList.size(); i++){
				et.createNewRow();
				et.createCell(i+1);
				et.createCell(recordList.get(i).getCreateName());
				et.createCell(recordList.get(i).getJkCount());
				et.createCell(recordList.get(i).getCount());
				et.createCell(sdf_show.format(recordList.get(i).getCreateTime()));
			}
		}

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
		String createTime = dateformat.format(new Date());

		Map<String,String> datas = new HashMap<String,String>();
		datas.put("title","兑换信息");
		datas.put("date",dateformat.format(new Date()));
		et.replaceFinalData(datas);

		String filedisplay = "兑换记录"+createTime;
		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="
				+ filedisplay + ".xls");
		et.wirteToStream(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
