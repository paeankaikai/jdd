package com.demo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.portal.bean.UserInfo;

@WebService(endpointInterface = "com.demo.HelloWorld", serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	Map<Integer, UserInfo> users = new LinkedHashMap<Integer, UserInfo>();

	public String sayHi(String text) {
		return "Hello " + text;
	}

	public String sayHiToUser(UserInfo user) {
		users.put(users.size() + 1, user);
		return "Hello " + user.getUserName();
	}

	public String[] SayHiToUserList(List<UserInfo> userList) {
		String[] result = new String[userList.size()];
		int i = 0;
		for (UserInfo u : userList) {
			result[i] = "Hello " + u.getUserName();
			i++;
		}
		return result;
	}

}
