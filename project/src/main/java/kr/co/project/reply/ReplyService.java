package kr.co.project.reply;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ReplyService {

	// 목록
	public Map<String, Object> index(ReplyVO vo); 
	// 상세
	public ReplyVO view(int no);
	// 수정폼
	public ReplyVO edit(int no);
	// 수정처리
	public boolean update(ReplyVO vo);
	// 삭제처리
	public boolean delete(int no);
	// 등록처리
	public boolean insert(ReplyVO vo);
	// 답변등록
	public boolean reply(ReplyVO vo);
}
