package kr.co.project.comment;

import java.util.Map;

public interface CommentService {
	
	// 댓글목록
	Map<String, Object> index(CommentVO vo);
	// 댓글등록
	int insert(CommentVO vo);
	// 댓글삭제
	int delete(CommentVO vo);
}