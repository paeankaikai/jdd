package com.portal.mapper;

import com.portal.bean.JddAccount;

import java.util.List;

public interface JddAccountMapper {


    int deleteByPrimaryKey(String guid);

    int insert(JddAccount record);

    int insertSelective(JddAccount record);

    List<JddAccount> selectByWhere(JddAccount record);
    
    List<JddAccount> orderByNum(JddAccount record);

    JddAccount selectByPrimaryKey(String guid);

 
    int updateByPrimaryKeySelective(JddAccount record);

    int updateByPrimaryKey(JddAccount record);

    //获取当年的记录
	JddAccount selectByUserId(String userId);

	
}