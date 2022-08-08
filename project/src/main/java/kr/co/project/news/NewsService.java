package kr.co.project.news;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface NewsService {

	// 목록
	public Map index(NewsVO vo); 
	// 상세
	public NewsVO view(int no);
	// 수정폼
	public NewsVO edit(int no);
	// 수정처리
	public boolean update(NewsVO vo);
	// 삭제처리
	public boolean delete(int no);
	// 등록처리
	public boolean insert(NewsVO vo);
}
