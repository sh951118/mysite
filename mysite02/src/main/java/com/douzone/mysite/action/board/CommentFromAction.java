package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class CommentFromAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		int gno = Integer.parseInt(request.getParameter("gno"));
		int ono = Integer.parseInt(request.getParameter("ono"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		
		
//		BoardVo av = (BoardVo)request.getAttribute("abab");
		BoardVo authconsVo = new BoardVo();
		authconsVo.setTitle(title);
		authconsVo.setContents(contents);
		authconsVo.setGno(gno);
		authconsVo.setOno(ono);
		authconsVo.setDepth(depth);
		
		request.setAttribute("vo", authconsVo);
		
		WebUtil.forward("/WEB-INF/views/board/comment.jsp", request, response);
	}

}
