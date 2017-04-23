package com.zls.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zls.common.OperationResult;
import com.zls.common.OperationResult.OperationResultCode;
import com.zls.service.WebUserService;

/**
 * WebUser Controller
 * 
 * @author dingxiangliu
 *
 *         时间: 2015年8月29日 工程名: zwpldx-study
 */
@RequestMapping("/home")
@Controller
public class HomeController {

	@RequestMapping("/")
	@ResponseBody
	public String queryWebUserByUsername(HttpServletRequest request) {

		return "Hello world";
	}

	@RequestMapping("/number")
	@ResponseBody
	public Integer testLoad(HttpServletRequest request) {

		return 5;
	}

	@RequestMapping("/fileUpload")
	@ResponseBody
	public String fileUpload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file1") MultipartFile file,
			@RequestParam("newFileName")String fileName) {

		System.out.println("文件上传.");
		return "success";
	}
}
