package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDAO;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVO;

@WebServlet("/gbc3")
public class GuestbookController extends HttpServlet {
	
	
	//필드
	private static final long serialVersionUID = 1L;
    
	//생성자 생략
	
	//메소드 gs
	
	//메소드 일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GuestbookController");
		
		//action
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) {
			
			System.out.println("리스트, 등록폼");
			
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			List<GuestbookVO> guestbookList = guestbookDAO.guestbookSelect();
			
			System.out.println(guestbookList);
			
			request.setAttribute("gList", guestbookList);
			
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");
			
			
		}else if("write".equals(action)) {
			
			System.out.println("등록");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			String regDate = request.getParameter("reg_date");
			
			GuestbookVO guestbookVO = new GuestbookVO(name, password, content, regDate);
			System.out.println(guestbookVO);
			
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			guestbookDAO.guestbookInsert(guestbookVO);
			
			WebUtil.redirect(request, response, "/guestbook3/gbc3?action=list");
			
		}else if("dform".equals(action)) {
			
			System.out.println("삭제폼");
			
			WebUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
			
			
		}else if("delete".equals(action)) {
			
			System.out.println("삭제");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestbookDAO guestbookDAO = new GuestbookDAO();
			guestbookDAO.guestbookDelete(no, password);
			
			WebUtil.redirect(request, response, "/guestbook3/gbc3?action=list");
			
		}

		
	}//doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
