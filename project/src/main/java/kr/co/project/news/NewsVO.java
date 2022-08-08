package kr.co.project.news;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewsVO {

	private int no;
	private String title;
	private String content;
	private Timestamp regdate;
	private int viewcount;
	private int member_no;
	private String filename_org;
	private String filename_real;
	
	private int page;
	private String stype;
	private String sword;
	private int startIdx;
	private int pageRow;
	private String tableName;
	
	private int endPage;
	private int startPage;
	
	private String msg;
	private String url;
	
	public NewsVO() { // 1페이지당 10개의 행이 들어감
//		this.page = 1;
//		this.pageRow = 10;
		this(1, 10);
	}
	
//	public BoardVO(BoardVO vo) {
//		this.endPage = (int)(Math.ceil(vo.getPage()/10.0)) * vo.getPageRow();
//		this.startPage = endPage - 9;
//	}
	
	public NewsVO(int page, int pageRow) {
		this.page = page;
		this.pageRow = pageRow;
	}
}
