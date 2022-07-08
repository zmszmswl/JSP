package co.micol.prj.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/EmpListServ", "/empList"})
public class EmpListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public EmpListServ() {
        super();    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터
		String id = request.getParameter("departmentId");
		
		// DB처리
		EmpDAO dao = new EmpDAO();
		request.setAttribute("list", dao.selectAll(id)); // 뷰 페이지로 데이터타입이 "list"인 리스트 객체를 넘겨줌
		
		// 페이지이동
		request.getRequestDispatcher("WEB-INF/jsp/emp/empList.jsp") // 뷰 페이지 : empList 출력담당! foward jsp 페이지로 가는경우
		       .forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
