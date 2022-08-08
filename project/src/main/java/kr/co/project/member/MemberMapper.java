package kr.co.project.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	// DB에 정보를 입력하는 xml
	int insert(MemberVO vo);
	// DB에서 email정보를 확인하는 xml
	int emailDupCheck(String email);
	// DB에 조회한 email과 pwd가 같은지 확인하는 xml
	MemberVO loginCheck(MemberVO vo);
	// DB에 조회한 name과 hp가 같은지 확인하는 xml
	MemberVO findEmail(MemberVO vo);
	// DB에 조회한 email과 name과 hp가 같은지 확인하느 xml
	MemberVO findPwd(MemberVO vo);
	
	int updateTempPwd(MemberVO vo);
	
}
