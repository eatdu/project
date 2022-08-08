package kr.co.project.board;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.project.comment.CommentService;
import kr.co.project.comment.CommentVO;
import kr.co.project.member.MemberVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@Autowired
	CommentService cService;
	
	@GetMapping("/board/index.do")
	public String index(Model model, BoardVO vo) {
		model.addAttribute("data", service.index(vo));
		return "board/index";
	}
	
	@GetMapping("/board/write.do")
	public String write() {
		// 작성페이지에 직접연결안되게 wirte, insert, edit, update
		return "board/write"; 
	}
	
	@GetMapping("/board/view.do")
	public String view(Model model, BoardVO vo) {
		BoardVO data = service.view(vo.getNo());
		model.addAttribute("data", data);
		return "board/view";
	}

	@GetMapping("/board/edit.do")
	public String edit(Model model, BoardVO vo) {
		BoardVO data = service.edit(vo.getNo());
		model.addAttribute("data", data);
		return "board/edit";
	}
	
	@PostMapping("/board/insert.do")
	public String insert(Model model, BoardVO vo
			, @RequestParam MultipartFile filename
			,HttpServletRequest req) {
		// 첨부파일 처리
		if(!filename.isEmpty()) {
			// 파일명 초기화
			String org = filename.getOriginalFilename();
			String ext = org.substring(org.lastIndexOf(".")); // 확장자 ex) .png, .jpg
			String real = new Date().getTime()+ext;
			
			// 파일저장
			String path = req.getRealPath("/upload/");
			try {
			filename.transferTo(new File(path+real));
			} catch(Exception e) {}
			vo.setFilename_org(org);
			vo.setFilename_real(real);
		}
		// member_no 저장
		HttpSession sess = req.getSession();
		MemberVO mv = (MemberVO)sess.getAttribute("loginInfo");
		vo.setMember_no(mv.getNo());
		if(service.insert(vo)) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "등록에 실패하였습니다.");
			return "common/alert";
		}
	}
	
	@PostMapping("/board/update.do")
	public String update(BoardVO vo, Model model) {
		if(service.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no="+vo.getNo());
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정실패");
			return "common/alert";
		}
	}
	
	@GetMapping("/board/delete.do")
	public String delete(BoardVO vo, Model model) {
		if(service.delete(vo.getNo())) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "삭제실패");
			return "common/alert";
		}
	}
}
