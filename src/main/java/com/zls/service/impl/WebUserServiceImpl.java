package com.zls.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zls.dao.mapper.WebUserMapper;
import com.zls.dao.model.WebUser;
import com.zls.service.WebUserService;

/**
 * WebUserService实现
 * 
 * @author dingxiangliu
 * 
 *         时间: 2015年8月29日 工程名: zwpldx-study
 */
@Service
public class WebUserServiceImpl implements WebUserService {

	public static final Logger LOGGER = LoggerFactory.getLogger("userLogger");

//	@Resource
//	private WebUserMapper webUserMapper;

	@Override
	public WebUser queryByUsername(String username) {
		WebUser webUser = null;
		LOGGER.debug("queryByUsername start, [username={}]", username);
//		webUser = webUserMapper.selectByUsername(username);
		LOGGER.debug("queryByUsername end, webUser is {}",
				webUser == null ? "null" : "not null");
		return webUser;
	}

	@Override
	public Boolean addWebUser(WebUser webUser) {
		Boolean result = Boolean.FALSE;
		LOGGER.debug("addWebUser start [webUser.username={}]",
				webUser.getUsername() == null ? "null" : webUser.getUsername());
//		if (webUserMapper.insertSelective(webUser) > 0) {
//			result = Boolean.TRUE;
//		}
		LOGGER.debug("addWebUser end [result={}]", result);
		return result;
	}
}
