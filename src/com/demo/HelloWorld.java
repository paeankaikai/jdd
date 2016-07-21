package com.demo;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.portal.bean.UserInfo;

@WebService
public interface HelloWorld {
	String sayHi(@WebParam(name = "text") String text);

	String sayHiToUser(UserInfo user);

	String[] SayHiToUserList(List<UserInfo> userList);
}