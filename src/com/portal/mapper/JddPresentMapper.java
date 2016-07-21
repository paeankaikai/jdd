package com.portal.mapper;

import java.util.List;

import com.portal.bean.JddPresent;
import com.portal.bean.JddPresentQuery;
import com.portal.bean.JddPresentTotalInfo;

public interface JddPresentMapper {
 
    int deleteByPrimaryKey(String guid);

    int insert(JddPresent record);

    int insertSelective(JddPresent record);
    
    List<JddPresent> selectByWhere(JddPresentQuery record);

    int updateByPrimaryKeySelective(JddPresent record);

    int updateByPrimaryKey(JddPresent record);

	JddPresentTotalInfo queryTotalInfo(String userId);
}