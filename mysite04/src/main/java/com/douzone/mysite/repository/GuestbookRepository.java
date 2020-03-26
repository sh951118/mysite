package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int delete( GuestbookVo vo ) {
		int count = sqlSession.delete( "guestbook.delete", vo );
		return count;
	}

	public int insert(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insert", vo);
	}

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.findAll");
		return list;
	}

	public GuestbookVo deletechek(Long no, String password) {	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);
		
		return sqlSession.selectOne("guestbook.deletechek", map);
	}
}