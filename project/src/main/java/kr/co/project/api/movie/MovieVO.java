package kr.co.project.api.movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieVO {

	private String title;
	private String link;
	private String image;
	
	private int page;
	private int pageRow;
	private String sword;
}
