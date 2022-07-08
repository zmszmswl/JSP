package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;
import co.micol.prj.member.vo.MemberVO;

public class MemberLogin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 처리
		HttpSession session = request.getSession(); // 서버가 만들어 놓은 세션을 가져온다.
		MemberService memberDao = new MemberServiceImpl(); // 인터페이스는 기본적으로 자기를 인스턴스로 생성하지 못한다 . 따라서 구현체를 통해서..
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPassword(request.getParameter("memberPassword"));
		
		vo = memberDao.memberLogin(vo); // 연산자의 가장 기본원칙 오른쪽값을 수행해서 왼쪽에 저장하라
		if(vo.getMemberName() !=null ) { // 비어있지않고 존재한다면
			session.setAttribute("id", vo.getMemberId());
			session.setAttribute("author", vo.getMemberAuthor()); // 세션에 담는다
			session.setAttribute("name", vo.getMemberName());
			request.setAttribute("message", vo.getMemberName() + "님 환영합니다."); // 정상적으로 담겼다면 ?결과를 돌려보낼때 ~~~님 환영합니다 뜸
		}else {
			request.setAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
		}
		return "member/memberLogin";
	}

}
