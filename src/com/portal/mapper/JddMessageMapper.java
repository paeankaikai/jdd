package com.portal.mapper;

import java.util.List;

import com.portal.bean.JddMessage;

public interface JddMessageMapper {
 
    int deleteByPrimaryKey(String guid);

    int insert(JddMessage record);

    int insertSelective(JddMessage record);
    
    JddMessage selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(JddMessage record);

    int updateByPrimaryKey(JddMessage record);
    
    List<JddMessage> selectByWhere(JddMessage jddMessage);
    
}