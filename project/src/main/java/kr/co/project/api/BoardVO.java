package kr.co.project.api;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardVO {
	
	private int no;
	private String title;
	private String member_name;
	private int comment_count;
	private String regdate;

}
