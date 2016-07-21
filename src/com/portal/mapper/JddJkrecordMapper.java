package com.portal.mapper;

import com.portal.bean.JddJkrecord;
import com.portal.bean.JddJkrecordQuery;

import java.util.List;

public interface JddJkrecordMapper {
 
    int deleteByPrimaryKey(String guid);

    int insert(JddJkrecord record);

    int insertSelective(JddJkrecord record);

 
    int updateByPrimaryKeySelective(JddJkrecord record);

    int updateByPrimaryKey(JddJkrecord record);

	List<JddJkrecord> selectByWhere(JddJkrecordQuery query);

	JddJkrecord queryExchangeCountInfo();
}