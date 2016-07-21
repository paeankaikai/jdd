package com.portal.mapper;

import com.portal.bean.LoginVerify;

public interface LoginVerifyMapper {
    int insert(LoginVerify record);

    int insertSelective(LoginVerify record);

	LoginVerify queryByGuid(String guid);
}