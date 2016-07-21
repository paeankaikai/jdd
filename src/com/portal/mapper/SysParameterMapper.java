package com.portal.mapper;

import java.util.List;

import com.portal.bean.SysParameter;

public interface SysParameterMapper {
	
	SysParameter selectByParamName(String paramName);
   
    int deleteByPrimaryKey(String guid);

    int insert(SysParameter record);

    int insertSelective(SysParameter record);

    List<SysParameter> selectParameterByClassType(String paramName);
 
    int updateByPrimaryKeySelective(SysParameter record);

    int updateByPrimaryKey(SysParameter record);
    
    int updateByParamName(SysParameter record);
    
    
    SysParameter selectByPrimaryKey(String guid);
}