package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookRepository guestbookRepository;


	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = guestbookRepository.findAll();
		return list;
	}

	public boolean insert(GuestbookVo vo) {
		int count = guestbookRepository.insert(vo);
		return count == 1;
	}
	
	public boolean delete(GuestbookVo vo) {
		return 1 == guestbookRepository.delete( vo );
	}

	public GuestbookVo deletechek(Long no, String password) {
		GuestbookVo guestbookVo = guestbookRepository.deletechek(no, password);
		return guestbookVo;
	}
}
