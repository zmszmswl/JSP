package co.micol.prj.member.service;

import java.util.List;

import co.micol.prj.member.vo.MemberVO;

public interface MemberService {
	List<MemberVO> memberSelectList(); // 멤버전체조회 R
	MemberVO emeberSelectOne(MemberVO vo); // 멤버조회 R  // id를 vo에 담아서 보내면 해당되는 데이터들 모두를 vo객체에 담아서옴 
	int memberInsert(MemberVO vo); // 멤버 삽입 C
	int memberUpdate(MemberVO vo); // 멤버 수정 U
	int memberDelete(MemberVO vo); // 멤버 삭제 D
	
	boolean isMemberIdCheck(String id); // 아이디 중복체크 // 존재하면 false가 돌아옴 isEmpty isBlank // true면 사용가능한 아이디입니다.
	MemberVO memberLogin(MemberVO vo); // 로그인 처리
	
	
	
}
