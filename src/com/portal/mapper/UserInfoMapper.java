package com.portal.mapper;

import com.portal.bean.UserInfo;
import com.portal.bean.UserInfoExample;
import com.portal.bean.UserInfoQuery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
 
    int deleteByPrimaryKey(String guid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String guid);
    
    UserInfo selectByUserId(String userId);
    
    List<UserInfo> selectByExample(UserInfoExample example);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);
    
    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

	List<UserInfo> selectByWhere(UserInfoQuery query);

	int updateByUserIdSelective(UserInfo userInfo);

	int isExistUser(String userId);
}