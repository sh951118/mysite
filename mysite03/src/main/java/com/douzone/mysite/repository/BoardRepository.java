package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public BoardVo findByNo( Long no ) {
		return sqlSession.selectOne( "board.findByNo", no );
	}
	
	public int updatehit(Long no) {
		return sqlSession.update("board.updatehit", no);
	}

	public BoardVo titleandcontents(Long no) {
		return sqlSession.selectOne("board.titleandcontents", no);
	}
	
	public int insert(BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}

	public BoardVo findByNoAndUserNo(Long no, Long userno) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put( "no", no );
		map.put( "userno", userno );
		
		return sqlSession.selectOne( "board.findByNoAndUserNo", map );
	}
	
	public int updateOno(Integer gno, Integer ono) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put( "groupNo", gno );
		map.put( "orderNo", ono );
		
		return sqlSession.update( "board.updateOrederNo", map );
	}
	
	public int getTotalCount(String keyword) {
		return sqlSession.selectOne( "board.totalCount", keyword );
	}
	
	public List<BoardVo> findAllByPageAndKeword(String keyword, int Page, int Size) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "keyword", keyword );
		map.put( "startIndex", (Page-1)*Size );
		map.put( "size", Size );
		
		return sqlSession.selectList( "board.findAllByPageAndKeword", map );
	}

	public int update( BoardVo BoardVo ) {
		return sqlSession.update( "board.update", BoardVo );
	}

	public int delete( Long no, Long userno ) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put( "no", no );
		map.put( "userno", userno );
		
		return sqlSession.delete( "board.delete", map );
	}
}
