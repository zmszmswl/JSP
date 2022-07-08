package co.micol.prj.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BoardUpdateServ")
public class BoardUpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    // 수정페이지로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 파라미터
		String id = request.getParameter("id");
		// 단건조회
		BoardDAO boardDAO = new BoardDAO();
		request.setAttribute("list", boardDAO.selectOne(id));
		request.getRequestDispatcher("/WEB-INF/jsp/board/boardUpdate.jsp")
				.forward(request,response);

	}

	// DB 수정 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 파라미터 VO 담고
	
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String rdt = request.getParameter("rdt");
		String hit = request.getParameter("hit");
				
		BoardVO vo = new BoardVO();
		vo.setId(id);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setRdt(rdt);
		vo.setHit(hit);
				
		// DB 처리
		BoardDAO boardDAO = new BoardDAO();
		int cnt = boardDAO.update(vo);
		// 결과 출력
		response.getWriter()
				.append(cnt + "건이 등록됨");
		
	}

}
