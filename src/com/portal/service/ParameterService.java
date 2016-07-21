package com.portal.service;

import java.util.List;

import com.portal.bean.SysParameter;

public interface ParameterService {
	
	public List<SysParameter> queryParameterByClassType(String classType);

	public SysParameter selectByParamName(String paramName);
	
	
	public int updateByParamName(SysParameter record);
	
	
	/**
	 * 根据id来查，用于费用来源等
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月16日 下午4:09:04
	 * @param record
	 * @return
	 */
	public SysParameter selectByPrimaryKey(String guid);

}
