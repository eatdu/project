package kr.co.project.board;

import java.sql.Timestamp;
import java.util.List;

import kr.co.project.comment.CommentVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {

	private int no;
	private String title;
	private String content;
	private Timestamp regdate;
	private int viewcount;
	private int member_no;
	private String filename_org;
	private String filename_real;
	private String member_name;
	private int comment_count;
	
	
	private int page;
	private String stype;
	private String sword;
	private int startIdx;
	private int pageRow;
	
	private int endPage;
	private int startPage;
	
	private String msg;
	private String url;
	
	
	
	private List<CommentVO> commentList;
	
	public BoardVO() { // 1페이지당 10개의 행이 들어감
//		this.page = 1;
//		this.pageRow = 10;
		this(1, 10);
	}
	
//	public BoardVO(BoardVO vo) {
//		this.endPage = (int)(Math.ceil(vo.getPage()/10.0)) * vo.getPageRow();
//		this.startPage = endPage - 9;
//	}
	
	public BoardVO(int page, int pageRow) {
		this.page = page;
		this.pageRow = pageRow;
	}
}
