package com.douzone.mysite.vo;

public class PageVo {

	private static int pagecountno = 5;
	private int pagestartno;
	private int pagelastno;
	private int pageendno;
	
	public int getPagestartno() {
		return pagestartno;
	}
	public void setPagestartno(int pagestartno) {
		this.pagestartno = pagestartno;
	}
	public int getPagelastno() {
		return pagelastno;
	}
	public void setPagelastno(int pagelastno) {
		this.pagelastno = pagelastno;
	}
	public int getPageendno() {
		return pageendno;
	}
	public void setPageendno(int pageendno) {
		this.pageendno = pageendno;
	}
	
	public void PageNo(int countpage) {
		int pageno = 0;
		pageno = (int)Math.floor((countpage-1)/pagecountno);
		pagestartno = (pagecountno * pageno) + 1;
		pagelastno = pagestartno + (pagecountno-1);
	}
	
	public void LastPage() {
		BoardVo bv = new BoardVo();
		int total = (int)(long)bv.getNo().SIZE;
		
		if(total % pagecountno == 0) {
			pageendno = (int)Math.floor(total/pagecountno);
		}
		else {
			pageendno = (int)Math.floor(total/pagecountno)+1;
		}
	}
	
	@Override
	public String toString() {
		return "PageVo [pagestartno=" + pagestartno + ", pagelastno=" + pagelastno + ", pageendno=" + pageendno + "]";
	}
}
