package kr.co.project.member;

import javax.servlet.http.HttpSession;

public interface MemberService {

	// DB에 정보를 입력하는 xml
	int insert(MemberVO vo);
	// DB에서 email정보를 확인하는 xml
	int emailDupCheck(String email);
	// DB에 조회한 email과 pwd가 같은지 확인하는 xml
	boolean loginCheck(MemberVO vo, HttpSession sess);
	// DB에 조회한 name과 hp가 같은지 확인하는 xml
	MemberVO findEmail(MemberVO vo);
	// DB에 조회한 email과 name과 hp가 같은지 확인하느 xml
	MemberVO findPwd(MemberVO vo);
	
}
