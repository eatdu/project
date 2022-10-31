package kr.co.project.api;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseObj {

	private int totalCount;
	private int totalPage;
	private int Page;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private String sword;
	private String stype;
	
	private List<BoardVO> items;
}
