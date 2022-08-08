package kr.co.project.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewsController {
	
	@Autowired
	NewsService service;
	
	@GetMapping("/news/index.do")
	public String index(Model model, NewsVO vo) {
		model.addAttribute("data", service.index(vo));
		return "news/index";
	}
	
	@GetMapping("/news/write.do")
	public String write() {
		return "news/write"; 
	}
	
	@PostMapping("/news/insert.do")
	public String insert(Model model, NewsVO vo) {
		if(service.insert(vo)) {
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "등록에 실패하였습니다.");
			return "common/alert";
		}
	}
	
	@GetMapping("/news/view.do")
	public String view(Model model, NewsVO vo) {
		NewsVO data = service.view(vo.getNo());
		model.addAttribute("data", data);
		return "news/view";
	}
	
	@GetMapping("/news/edit.do")
	public String edit(Model model, NewsVO vo) {
		NewsVO data = service.edit(vo.getNo());
		model.addAttribute("data", data);
		return "news/edit";
	}
	
	@PostMapping("/news/update.do")
	public String update(NewsVO vo, Model model) {
		if(service.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no="+vo.getNo());
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정실패");
			return "common/alert";
		}
	}
	
	@GetMapping("/news/delete.do")
	public String delete(NewsVO vo, Model model) {
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
