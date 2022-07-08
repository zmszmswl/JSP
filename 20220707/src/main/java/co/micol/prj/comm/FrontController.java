package co.micol.prj.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.MainCommand;
import co.micol.prj.member.command.MemberList;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;
import co.micol.prj.member.command.MemberLogout;
import co.micol.prj.member.command.AjaxMemberIdCheck;
import co.micol.prj.member.command.MemberJoin;
import co.micol.prj.member.command.MemberJoinForm;


@WebServlet("*.do") // 모든 .do요청은 내가 처리한다.
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<>();  // 요청과 실행문을 매핑하기 위해
	
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// 초기화 하는 메소드 (Mapping 하는 부분을 작성한다.)
		map.put("/main.do", new MainCommand()); // main.do를 넘겨서 처음 접속하는 페이지 MainCommand()가 옴 (index페이지를 만들겠다는 뜻 ) 키 / 벨류
		map.put("/memberLoginForm.do", new MemberLoginForm()); // 로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); // 로그인 처리
		map.put("/memberLogout.do", new MemberLogout()); // 로그아웃
		map.put("/memberList.do", new MemberList());	// 회원 목록 보기
		map.put("/memberJoinForm.do", new MemberJoinForm()); // 회원 가입 화면
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck());
		map.put("/memberJoin.do", new MemberJoin()); //회원가입 처리
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실행하는 메소드(서비스 해주는 것)
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지
		String uri = request.getRequestURI(); // 요청된 URI를 확인한다. (갖고와서 담는다)
		String contextPath = request.getContextPath(); // 요청 URL로 부터 contextPath를 확인한다. (갖고와서 담는다)
		String page = uri.substring(contextPath.length()); // 실제 요청한 서블릿 페이지를 찾는다.
		
		Command command = map.get(page); // 실제 수행할 Command를 찾음 new MainCommand();
		// Command command = new MainCommand();
		String viewPage = command.exec(request, response); // 요청 Command를 수행하고 결과를 받음
		
		// viewResolve  // 보여줄페이지 page 선택
		if(!viewPage.endsWith(".do") && !viewPage.equals(null)) { // 문자열 끝에 .do 가 붙어있고 null이 아니면 아래 실행
			if(viewPage.startsWith("ajax:")) {  // ajax를 처리하는 viewResolve 
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			viewPage = "WEB-INF/views/" + viewPage + ".jsp"; // 시스템에서 접근 가능한 폴더를 더해주고 
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); // 리스퀘트 개체에 뷰페이지 담고 직전에 사용한값을 갖고있음 request,response 객체 들고옴
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(viewPage); // 새로고침 .do로 권한 위임 처리 request,response 객체가 있어도 새로줌 fosward 권한이임
		}
			

		
	}

}
