package co.micol.prj.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.dept.DeptDAO;
import co.micol.prj.dept.DeptVO;


@WebServlet("/BoardInsertServ")
public class BoardInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 등록페이지 요청
		 request.getRequestDispatcher("WEB-INF/jsp/board/boardInsert.jsp").forward(request, response);
	
	}
	// DB 등록 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 VO 담고
		String id = request.getParameter("id");
		String title= request.getParameter("title");
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
		int cnt = boardDAO.boardInsert(vo);
		// 결과 출력
		response.getWriter()
				.append(cnt + "건이 등록됨");
	}
}
