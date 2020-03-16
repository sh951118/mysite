package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Long no = Long.parseLong(request.getParameter("no"));
		
		BoardVo authconsVo = new BoardVo();
		authconsVo.setTitle(title);
		authconsVo.setContents(contents);
		authconsVo.setNo(no);
		
		new BoardRepository().modiupdate(authconsVo);
		
		WebUtil.forward("/WEB-INF/views/board/allsuccess.jsp", request, response);
	}

}
