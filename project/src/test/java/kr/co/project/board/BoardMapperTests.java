package kr.co.project.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/servlet-context.xml")
// Java Config
//@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;
	
	//@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void insert() { // 행삽입
		BoardVO vo = new BoardVO();
		vo.setTitle("제목입니다");
		vo.setContent("내용없음");
//		int r = 0;
//		for(int i=0; i<200; i++) {
//			r += mapper.insert(vo);
//		}
		log.info(vo);
	}
	
	//@Test
	public void delete(){ // idx로 해당행 삭제하기
		BoardVO vo = new BoardVO();
		for(int r=2; r<14; r++) {
			vo.setNo(mapper.delete(r)); 
		}
		log.info(vo);
	}
	
	//@Test
	public void update(){ // idx번호로 해당행 제목,내용 수정,파일수정하기
		BoardVO vo = new BoardVO();
		vo.setNo(3);
		vo.setTitle("수정제목");
		vo.setContent("수정한 내용");
		mapper.update(vo);
	}
	
	//@Test 
//	public void selectOne() { // idx 번호로 해당행 가져오기
//		BoardVO vo = new BoardVO();
//		vo.setNo(1);
//		mapper.view(vo);
//		log.info(vo);
//	}
	
	//@Test
//	public void list() { // 리스트 출력
//		BoardVO vo = new BoardVO();
////		Map map = new HashMap();
////		map.put("stype", "title");
////		map.put("sword", "1");
////		map.put("startIdx", 0);
////		map.put("pageRow", 10);
//		vo.setTitle("제목");
//		vo.setContent("1");
//		vo.setStartIdx(0);
//		vo.setPageRow(10);
//		List<BoardVO> list = mapper.list(vo);
//		list.forEach(vo ->log.info(vo));
//	}
//	
//	//@Test
//	public void count() { // 해당내용이 포함된 행 카운트
//		Map map = new HashMap();
//		map.put("stype", "all");
//		map.put("sword", "2");
//		int totalCount = mapper.count(map);
//		System.out.println("총카운트 : " + totalCount);
//	}
	
	//@Test
	public void updateViewcount() { // 조회수 +1 증가
		mapper.updateViewcount(14);
	}
	
	
	
	
}
