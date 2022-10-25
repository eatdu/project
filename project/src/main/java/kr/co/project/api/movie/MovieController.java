package kr.co.project.api.movie;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.NaverApi;

@Controller
@RequestMapping("/api")
public class MovieController {

	@GetMapping("/movie/list")
	public String list(@RequestParam(name = "sword", required = false) String sword, Model model) {
		
		if (sword != null && !"".equals(sword)) {
			// 네이버API 호출
			String res = NaverApi.getResponse("movie", sword);
//			System.out.println(res);
			// 응답받은 결과를 파싱 (JSON -> Object : JSONObject)
			JSONObject obj = new JSONObject(res);
			System.out.println(obj); // 전체
			System.out.println(obj.getInt("total")); // 총개수
			
			// 검색결과(배열)
			JSONArray items =  obj.getJSONArray("items");
			System.out.println(items.length()); // 검색결과(배열) 길이
			
			List<MovieVO> list = new ArrayList<MovieVO>();
			for (int i=0; i<items.length(); i++) {
				// 배열안에는 객체존재
				JSONObject obj2 = items.getJSONObject(i);
				System.out.println(obj2.getString("title"));
				MovieVO vo = new MovieVO();
				vo.setTitle(obj2.getString("title"));
				vo.setLink(obj2.getString("link"));
				vo.setImage(obj2.getString("image"));
				list.add(vo);
			}
			model.addAttribute("movies",list);
		}
		return "api/movie/list";
	}
	
	@GetMapping("/movie/list2")
	public String list2(@RequestParam(name = "sword", required = false) String sword, Model model) {
		
		if (sword != null && !"".equals(sword)) {
			// 네이버API 호출
			String res = NaverApi.getResponse("movie", sword);
//			System.out.println(res);
			// 응답받은 결과를 파싱 (JSON -> Object : jackson)
			ObjectMapper om = new ObjectMapper();
			ResponseObject ro = new ResponseObject();
			try {
				// JSON에 속성이 있는데 Object에 필드가 없는 경우 에러가 발생
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//				ro = om.readValue(res, new TypeReference<ResponseObject>() {});
				// TypeReference는 맨바깥이 배열인 경우
				//TypeReference<ArrayList<MovieVO>>() {}
				ro = om.readValue(res, ResponseObject.class);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			model.addAttribute("movies",ro.getItems());
		}
		return "api/movie/list";
	}
	
	@GetMapping("/board/list2")
	public String list3(@RequestParam(name = "sword", required = false) String sword,
			@RequestParam(name = "page", required = false) Integer page, MovieVO vo, Model model) {
		String res = MyApi.getRsponse(page, sword);
		System.out.println(res);
		ObjectMapper om = new ObjectMapper();
		ResponseObject ro = new ResponseObject();
		try {
			// JSON에 속성이 있는데 Object에 필드가 없는 경우 에러가 발생
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ro = om.readValue(res, new TypeReference<ResponseObject>() {});
			// TypeReference는 맨바깥이 배열인 경우
			//TypeReference<ArrayList<MovieVO>>() {}
//			ro = om.readValue(res, ResponseObject.class);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("my",ro.getItems());
		return "api/board/list";
	}
}










