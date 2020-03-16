package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo uservo = new UserVo();
		uservo.setName(name);
		uservo.setEmail(email);
		uservo.setPassword(password);
		uservo.setGender(gender);
		uservo.setNo(userVo.getNo());
		
		if("".equals(password)) {
			request.setAttribute("result", "fail");
			WebUtil.forward("/WEB-INF/views/user/updateform.jsp", request, response);
			return;
		}
		
		new UserRepository().update(uservo);
		
		userVo.setName(name);
		WebUtil.forward("/WEB-INF/views/user/updatesuccess.jsp", request, response);

	}

}
