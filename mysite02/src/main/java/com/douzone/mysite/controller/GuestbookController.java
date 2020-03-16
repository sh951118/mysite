package com.douzone.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.util.WebUtil;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String actionName = request.getParameter("a");
//		ActionFactory af = new GuestActionFactory();
//		Action action = af.getAction(actionName);
//		action.execute(request, response);

		if ("deleteform".equals(actionName)) {
			WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);

		} else if ("delete".equals(actionName)) {
			String password = request.getParameter("password");
			String no = request.getParameter("no");

			GuestbookVo vo = new GuestbookVo();
			vo.setNo(Long.parseLong(no));
			vo.setPassword(password);

			new GuestbookRepository().delete(vo);
			response.sendRedirect(request.getContextPath() + "/guestbook");
			
		} else if ("insert".equals(actionName)) {
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String contents = request.getParameter("content");

			if (!name.isEmpty() || !password.isEmpty() || !contents.isEmpty()) {

				GuestbookVo vo = new GuestbookVo();
				vo.setName(name);
				vo.setPassword(password);
				vo.setContents(contents);

				new GuestbookRepository().insert(vo);
			}
			response.sendRedirect(request.getContextPath() + "/guestbook");

		} else {
			// default 요청 처리 (list)
			List<GuestbookVo> list = new GuestbookRepository().getList();
			request.setAttribute("list", list);
			WebUtil.forward("/WEB-INF/views/guestbook/list.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}