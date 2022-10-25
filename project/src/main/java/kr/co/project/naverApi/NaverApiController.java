package kr.co.project.naverApi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NaverApiController {
	
	@GetMapping("/movie/data")
	public String movie(Model model) {
		Movie movie = new Movie();
		model.addAttribute("movie",movie);
		return "movie/data";
	}
}
