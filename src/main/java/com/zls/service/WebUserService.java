package com.zls.service;

import com.zls.dao.model.WebUser;

public interface WebUserService {

	/**
	 * 根据用户名判断是否存在WebUser
	 * @param username
	 * @return
	 */
	WebUser queryByUsername(String username);
	
	/**
	 * 增加一个用户
	 */
	Boolean addWebUser(WebUser webUser);
}
