package com.douzone.mysite.vo;

public class BoardVo {

	private Long no;
	private String username;
	private String title;
	private String contents;
	private int hit;
	private String regdate;
	private int gno;
	private int ono;
	private int depth;
	private Long userno;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getGno() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}

	public int getOno() {
		return ono;
	}

	public void setOno(int ono) {
		this.ono = ono;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Long getUserno() {
		return userno;
	}

	public void setUserno(Long userno) {
		this.userno = userno;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", username=" + username + ", title=" + title + ", contents=" + contents + ", hit="
				+ hit + ", regdate=" + regdate + ", gno=" + gno + ", ono=" + ono + ", depth=" + depth + ", userno="
				+ userno + "]";
	}
}
