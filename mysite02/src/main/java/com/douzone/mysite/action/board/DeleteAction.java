package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Long no = Long.parseLong(request.getParameter("no"));
		Long userno = Long.parseLong(request.getParameter("userno"));
		
		BoardVo authconsVo = new BoardVo();
		authconsVo.setNo(no);
		authconsVo.setUserno(userno);
		
		new BoardRepository().listdelete(authconsVo);
		
		WebUtil.redirect(request.getContextPath() + "/board?a=list", request, response);
	}

}
