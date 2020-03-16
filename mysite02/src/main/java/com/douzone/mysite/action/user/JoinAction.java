package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo uservo = new UserVo();
		uservo.setName(name);
		uservo.setEmail(email);
		uservo.setPassword(password);
		uservo.setGender(gender);
		
		if("".equals(name) || "".equals(email) || "".equals(password)) {
			request.setAttribute("result", "fail");
			WebUtil.forward("/WEB-INF/views/user/joinform.jsp", request, response);
			return;
		}
		
		new UserRepository().insert(uservo);
		
		WebUtil.redirect(request.getContextPath() + "/user?a=joinsuccess", request, response);
	}

}
