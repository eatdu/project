package kr.co.project.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.SendMail;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper mapper;
	
	@Override
	public int insert(MemberVO vo) { // 회원정보를 입력하는 메소드
		return mapper.insert(vo);
	}

	@Override
	public int emailDupCheck(String email) { // 0 or 1을 리턴하여 이메일 체크하는 메소드
		return mapper.emailDupCheck(email);
	}

	@Override
	public boolean loginCheck(MemberVO vo, HttpSession sess) { // 조회한 email의 pwd가 같은
		boolean r = false;
		MemberVO loginInfo = mapper.loginCheck(vo);
		if(mapper.loginCheck(vo) != null) {
			r = true;
			// 로그인 성공시 세션에 저장
			sess.setAttribute("loginInfo", loginInfo);
		}
		return r;
	}
	
	@Override
	public MemberVO findEmail(MemberVO vo) {
		return mapper.findEmail(vo);
	}

	@Override
	public MemberVO findPwd(MemberVO vo) {
		// update
		MemberVO mv = mapper.findEmail(vo);
		if(mv != null) {
			// 임시비밀번호 생성
			// 영문두자리, 숫자두자리
			String temp = "";
			for (int i=0; i<2; i++) {
				temp += (char)(Math.random()*26+65);
			}
			for (int i=0; i<2; i++) {
				temp += (int)(Math.random()*9);
			}
			// 임시비밀번호 update
			vo.setPwd(temp); // 임시비밀번호를 DB에 저장
			mapper.updateTempPwd(vo);
			
			// email발송
			SendMail.sendMail("2do2023@naver.com", vo.getEmail(), "[더좋은]임시비밀번호", "임시비밀번호:"+temp);
			return mv;
		} else {
			return null;
		}
	}

}
