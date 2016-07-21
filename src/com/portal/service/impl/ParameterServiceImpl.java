package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.bean.SysParameter;
import com.portal.common.StringUtil;
import com.portal.mapper.SysParameterMapper;
import com.portal.service.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService {
	@Autowired
	private SysParameterMapper sysParameterMapper;

	@Override
	public List<SysParameter> queryParameterByClassType(String classType) {
		
		return sysParameterMapper.selectParameterByClassType(classType);

	}
	
	@Override
	public SysParameter selectByParamName(String paramName) {
		
		return sysParameterMapper.selectByParamName(paramName);
	}

	@Override
	public int updateByParamName(SysParameter record) {
		// TODO Auto-generated method stub
		return sysParameterMapper.updateByParamName(record);
	}
	
	
	public SysParameter selectByPrimaryKey(String guid){
		return sysParameterMapper.selectByPrimaryKey(guid);
	}

}
