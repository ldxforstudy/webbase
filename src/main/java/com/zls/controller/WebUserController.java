package com.zls.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zls.common.OperationResult;
import com.zls.common.OperationResult.OperationResultCode;
import com.zls.service.WebUserService;

/**
 * WebUser Controller
 * @author dingxiangliu
 *
 * 时间:		2015年8月29日
 * 工程名:	zwpldx-study
 */
@RequestMapping("/webUser")
@Controller
public class WebUserController {
	
	@Resource
	private WebUserService webUserService;
	
	@RequestMapping("/queryByUsername.do")
	@ResponseBody
	public OperationResult<Boolean> queryWebUserByUsername(
			@RequestParam(value="username", required=true, defaultValue="")String username,
			HttpServletRequest request){
		OperationResult<Boolean> operationResult = new OperationResult<>();
		
		// 业务执行10s
		
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!StringUtils.isBlank(username)){
			if (username.equals("zwp")){
				operationResult.setCode(OperationResultCode.FAIL);
				operationResult.setData(false);
			}else {
				operationResult.setCode(OperationResultCode.SUCCESS);
				operationResult.setData(true);
			}
		}else {
			// 参数为空
			operationResult.setCode(OperationResultCode.FAIL);
			operationResult.setDescription("参数有误!");
		}
		return operationResult;
	}
}
