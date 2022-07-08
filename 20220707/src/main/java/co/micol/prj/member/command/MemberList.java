package co.micol.prj.member.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;
import co.micol.prj.member.vo.MemberVO;

public class MemberList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원 목록 보기
		MemberService memberDao = new MemberServiceImpl();
		List<MemberVO> list = new ArrayList<MemberVO>(); // ArrayList<MemberVO> 인터페이스를 LIST 하위객체로 초기화
		list = memberDao.memberSelectList();
		request.setAttribute("list", list);
		return "member/memberList";
	}

}
