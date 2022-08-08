package kr.co.project.board;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface BoardService {

	// 목록
	public Map<String, Object> index(BoardVO vo); 
	// 상세
	public BoardVO view(int no);
	// 수정폼
	public BoardVO edit(int no);
	// 수정처리
	public boolean update(BoardVO vo);
	// 삭제처리
	public boolean delete(int no);
	// 등록처리
	public boolean insert(BoardVO vo);
}
