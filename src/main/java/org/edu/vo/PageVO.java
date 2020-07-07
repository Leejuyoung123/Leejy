package org.edu.vo;

public class PageVO {
	private int startBno;
	private int perPageNum;

	private Integer page; // jsp단에서 null로 값이 올떄 에러가 발생하지 않도록 Intege사용
	private int totalCount;
	private int endPage;
	private int startPage;
	private boolean prev;
	private boolean next;
	// 검색용 변수 2개 추가
	private String searchType;
	private String searchKeyword;
	//db get set 하기위해 만듬
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	private void calcPage() {
		// page 변수는 현재 jsp에서 보내준 페이지 번호
		int tempEnd = (int) (Math.ceil(page / 10.0) * 10);
		// jsp에서 클릭한 페이지 번호를 기준으로 끝 페이지를 계산한다.
		// ceil함수는 천장함수로 1.1=2 2.1=3으로 출력된다.\
		// 반대 되는 바닥함수로 floor(), 반올림함수로 round()가 있음
		this.startPage = tempEnd - 9;
		// 시작 페이지 계산 클릭한 page 번호 10일떄 까지 시작페이지는 1
		if (tempEnd * 10 > this.totalCount) {
			this.endPage = (int) Math.ceil(this.totalCount / 10.0);
			// 클릭한 페이지 번호로 계산된 게시물수가 실제게시물 개수 totalcount 클떄
		} else {
			this.endPage = tempEnd;
			// 클릭한 페이지 번호로 계산된 게시물수가 실제게시물 개수 totalcount 작을떄
		}
		this.prev = this.startPage != 1; // 시작페이지가 1보다 크면 무조건 이전페이지가 있다.
		this.next = this.endPage * 10 < this.totalCount;
		// 현재 클릭한 페이지 번호로 계산된 게시물 수가
		// 실제 게시물 개수보다 작다면 다음페이지가 있음 true
	}
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcPage();
		// 토탈카운트값을 구한 이후에 calcpage 메서드 호출
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
		
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public int getStartBno() {
		// DB쿼리에서 사용 ... 
		//시작 데이터 번호 = (jsp 에서 클릭한 페이지 번호-1 *pageNum 페이지당 
		//보여지는 개수)
		startBno = (this.page - 1) * perPageNum;
		return startBno;
		
	}

	public void setStartBno(int startBno) {
		this.startBno = startBno;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

}
