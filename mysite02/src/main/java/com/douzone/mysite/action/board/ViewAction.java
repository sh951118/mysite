package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		String gno = request.getParameter("gno");
		String ono = request.getParameter("ono");
		String depth = request.getParameter("depth");
		
		BoardVo vo = new BoardVo();
		
		vo.setNo(Long.parseLong(no));
		vo.setGno(Integer.parseInt(gno));
		vo.setOno(Integer.parseInt(ono));
		vo.setDepth(Integer.parseInt(depth));
		
		BoardVo authcons = new BoardRepository().titleandcontents(vo);
		
		new BoardRepository().titleandcontentsupdate(vo);

		request.setAttribute("authcons", authcons);
		
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
	}

}
