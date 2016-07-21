package com.portal.mapper;

import java.util.List;

import com.portal.bean.JddBaserecord;
import com.portal.bean.JddBaserecordQuery;

public interface JddBaserecordMapper {   
    int deleteByPrimaryKey(String guid);

    int insert(JddBaserecord record);

    int insertSelective(JddBaserecord record);

    JddBaserecord selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(JddBaserecord record);

    int updateByPrimaryKey(JddBaserecord record);

	List<JddBaserecord> selectByWhere(JddBaserecordQuery query);

	List<JddBaserecord> selectByWhereGroupBy(JddBaserecordQuery query);

	int selectTotalNum(JddBaserecordQuery record);
	
}