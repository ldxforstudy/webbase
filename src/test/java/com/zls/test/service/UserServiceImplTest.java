package com.zls.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.zls.dao.model.WebUser;
import com.zls.service.WebUserService;
import com.zls.test.base.TestBase;

public class UserServiceImplTest extends TestBase{
	
	@Resource
	private WebUserService webUserService;
	
	@Test
	public void testQueryByUsername(){
		String username = "username1";
		WebUser user = null;
		user = webUserService.queryByUsername(username);
		Assert.assertNotNull("webUser is null", user);
	}
	
	@Test
	public void testAddUser(){
		WebUser webUser = null;
		for(int i=0; i<10; i++){
			webUser = new WebUser();
			webUser.setUsername("username"+i);
			webUser.setPassword("password"+i);
			webUser.setUsercode("C"+System.currentTimeMillis());
//			System.out.println(webUserService.addWebUser(webUser));
		}
	}
}
