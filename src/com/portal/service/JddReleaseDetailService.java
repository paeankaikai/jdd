package com.portal.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddReleaseDetail;
import com.portal.bean.JddReleaseDetailExample;
import com.portal.bean.JddReleaseDetailQuery;

public interface JddReleaseDetailService {
	
	public int insert(JddReleaseDetail jddReleaseDetail);
	
	/**
	 * 普通的查询
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月14日 下午1:40:21
	 * @param releaseGuid
	 * @return
	 */
	public List<JddReleaseDetail> selectByReleaseGuid(String releaseGuid );
	
	
	/**
	 * 查询结果将来源和规则，详细信息显示
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月14日 下午1:37:03
	 * @param releaseGuid
	 * @return
	 */
	public List<JddReleaseDetail> selectByReleaseGuid2(String releaseGuid );
	
	public int saveDetail(JddReleaseDetail detail);
	
	public  List<JddReleaseDetail>  saveListDetail(List<Map<String, Object>> newlist,String releaseGuid);

	public PageInfo<JddReleaseDetail> queryPageListByWhere(Integer page, Integer rows,JddReleaseDetailQuery jddReleaseDetailQuery, List<String> orderBy);

	public PageInfo<JddReleaseDetail> queryPageListByWhereGroupBy(Integer page, Integer rows,JddReleaseDetailQuery jddReleaseDetailQuery, List<String> orderBy);

	public List<JddReleaseDetail> selectByWhereGroupBy(JddReleaseDetailQuery query);

	public List<JddReleaseDetail> selectByWhere(JddReleaseDetailQuery query);
	
	

}
