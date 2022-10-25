package kr.co.project.api.movie;

import java.util.List;

import kr.co.project.api.BoardVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseObject {

	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	
//	private List<MovieVO> items;
	
	private int page;
	private String sword;
	private List<BoardVO> items;
}
