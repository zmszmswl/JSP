package co.micol.prj.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BoardListServ")
public class BoardListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터
		String id = request.getParameter("id");
		
		
		// DB처리
		BoardDAO dao =new BoardDAO();
		request.setAttribute("list", new BoardDAO().selectAll());
		//request.setAttribute("list", new BoardDAO().selectAll(id));
		 request.getRequestDispatcher("WEB-INF/jsp/board/boardList.jsp").forward(request, response);
	
	}
}
