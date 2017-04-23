package com.zls.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        System.out.println("---- FirstServlet init ----");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---- FirstServlet doGet start ----");
		String desc = request.getAttribute("desc")==null?"":request.getAttribute("desc").toString();
		System.out.println("---- FirstServlet [desc="+desc+"]----");
		System.out.println("---- FirstServlet doGet end ----");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---- FirstServlet doPost start ----");
		System.out.println("---- FirstServlet doPost end ----");
	}

}
