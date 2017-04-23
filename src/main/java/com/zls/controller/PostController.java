package com.zls.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zls.dao.model.WebUser;
import com.zls.util.HttpClientUtil;

/**
 * POST 测试
 * @author 承项
 * @date 2015年9月9日下午4:54:52
 */
@Controller
@RequestMapping("/test")
public class PostController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Resource
  private HttpClientUtil httpClientUtil;
  
  @RequestMapping(value="/post.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
  @ResponseBody
  public Map<String, Object> testPost(HttpServletRequest request, HttpServletResponse response){
    logger.info("post.do start");
    Map<String, Object> result = new HashMap<String, Object>();
    
    WebUser webUser = null;
    String postUrl = "http://localhost:8080/test/post2.do";
    webUser = httpClientUtil.doPost(postUrl, null, WebUser.class);
    
    logger.info("post.do 返回结果为{}.", webUser);
    
    result.put("errcode", 40001);
    result.put("errmsg", "invalid credential, access_token is invalid or not latest hint: [FxQeda0115vr19]");
    
    logger.info("post.do end");
    return result;
  }
  
  @RequestMapping(value="/post2.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
  @ResponseBody
  public WebUser testPost2(HttpServletRequest request, HttpServletResponse response){
    logger.info("post2.do start");
    WebUser webUser = new WebUser();
    
    webUser.setId(10000l);
    webUser.setUsercode("u10000");
    webUser.setUsername("ldx");
    logger.info("post2.do end");
    return webUser;
  }
}
