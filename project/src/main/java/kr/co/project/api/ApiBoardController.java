package kr.co.project.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import util.BaseController;

@CrossOrigin(origins = {"*"})
@RestController
public class ApiBoardController extends BaseController {

	@Autowired
	ApiBoardMapper mapper;
	
//	@GetMapping("/api/test")
//	public ResponseObj test() {
//		BoardVO vo = new BoardVO();
//		vo.setTitle("제목");
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		list.add(vo);
//		vo = new BoardVO();
//		vo.setTitle("제목2");
//		list.add(vo);
//		ResponseObj obj = new ResponseObj();
//		obj.setTotalCount(100);
//		obj.setTotalPage(10);
//		obj.setPage(1);
//		obj.setItmes(list);
//		return obj;
//	}
//	
//	@GetMapping("/api/board/list")
//	public ResponseObj list(Param param) {
//		int totalCount = mapper.count(param);
//		pageProcess(param, totalCount);
//		
//		ResponseObj obj = new ResponseObj();
//		obj.setTotalCount(totalCount);
//		obj.setPage(param.getPage());
//		obj.setSword(param.getSword());
//		obj.setTotalPage(totalPage);
//		obj.setItmes(mapper.list(param));
//		obj.setStartPage(startPage);
//		obj.setEndPage(endPage);
//		obj.setPrev(prev);
//		obj.setNext(next);
//		return obj;
//	}
}
