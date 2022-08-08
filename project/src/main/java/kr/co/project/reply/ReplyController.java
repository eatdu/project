package kr.co.project.reply;

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
public class ReplyController {
	
	@Autowired
	ReplyService service;
	
	@Autowired
	CommentService cService;
	
	@GetMapping("/reply/index.do")
	public String index(Model model, ReplyVO vo) {
		model.addAttribute("data", service.index(vo));
		return "reply/index";
	}
	
	@GetMapping("/reply/write.do")
	public String write() {
		// 작성페이지에 직접연결안되게 wirte, insert, edit, update
		return "reply/write"; 
	}
	
	@GetMapping("/reply/view.do")
	public String view(Model model, ReplyVO vo) {
		ReplyVO data = service.view(vo.getNo());
		model.addAttribute("data", data);
		return "reply/view";
	}

	@GetMapping("/reply/edit.do")
	public String edit(Model model, ReplyVO vo) {
		ReplyVO data = service.edit(vo.getNo());
		model.addAttribute("data", data);
		return "reply/edit";
	}
	
	@PostMapping("/reply/insert.do")
	public String insert(Model model, ReplyVO vo
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
		if(mv != null)vo.setMember_no(mv.getNo());
		if(service.insert(vo)) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "등록에 실패하였습니다.");
			return "common/alert";
		}
	}
	
	@PostMapping("/reply/update.do")
	public String update(ReplyVO vo, Model model) {
		if(service.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no="+vo.getNo());
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정실패");
			return "common/alert";
		}
	}
	
	@GetMapping("/reply/delete.do")
	public String delete(ReplyVO vo, Model model) {
		if(service.delete(vo.getNo())) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "삭제실패");
			return "common/alert";
		}
	}
	
	@GetMapping("/reply/reply.do")
	public String reply(ReplyVO vo, Model model) {
		ReplyVO data = service.edit(vo.getNo());
		model.addAttribute("data", data);
		return "reply/reply"; 
	}
	
	@PostMapping("/reply/reply.do")
	public String reply(Model model, ReplyVO vo
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
		if(mv != null)vo.setMember_no(mv.getNo());
		if(service.reply(vo)) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "등록에 실패하였습니다.");
			return "common/alert";
		}
	}
}
