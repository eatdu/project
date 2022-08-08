package kr.co.project.comment;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentVO {

	private int no;
	private String content;
	private int member_no;
	private int board_no;
	private Timestamp regdate;
	private String tablename;
	private String member_name;
	
	private int page;
	private int pageRow;
	private int startIdx;
	
//	private int endPage;
//	private int startPage;
	
	public CommentVO() {
		this.page = 1;
		this.pageRow = 10;
	}
	
//	public CommentVO(int page, int pageRow) {
//		this.page = page;
//		this.pageRow = pageRow;
//	}
}
