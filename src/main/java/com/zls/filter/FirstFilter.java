package com.zls.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * FirstFilter
 * @author 承项
 *
 */
public class FirstFilter implements Filter{

	private String desc;	//描述
	@Override
	public void destroy() {
		System.out.println("---- FirstFilter destroy ----");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		System.out.println("---- FirstFilter doFilter start ----");
		arg0.setAttribute("desc", this.desc);
		arg2.doFilter(arg0, arg1);
		System.out.println("---- FirstFilter doFilter end ----");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("---- FirstFilter init ----");
		String desc = arg0.getInitParameter("desc");
		System.out.println("---- FirstFilter init [desc="+desc+"]----");
		this.desc = desc;
	}

}
