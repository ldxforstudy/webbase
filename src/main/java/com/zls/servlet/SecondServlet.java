package com.zls.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondServlet() {
        super();
        System.out.println("---- SecondServlet init ----");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---- SecondServlet doGet start ----");
		String desc = request.getAttribute("desc")==null?"":request.getAttribute("desc").toString();
		System.out.println("---- SecondServlet [desc="+desc+"]----");
		System.out.println("---- SecondServlet doGet end ----");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---- SecondServlet doPost start ----");
		System.out.println("---- SecondServlet doPost end ----");
	}

}
