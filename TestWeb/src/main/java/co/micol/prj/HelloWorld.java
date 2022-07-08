package co.micol.prj;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 고유이름인 /aaa 로 컨테이너에 등록되어라. 자바로 만들어진 서블릿은 반드시 컨테이너 안에 맵핑되어야함 */
@WebServlet(urlPatterns = "/hello" , loadOnStartup=1)
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	public void destroy() {
		System.out.println("제거됨");

	}
	@Override
	public void init(ServletConfig config) throws ServletException {
			System.out.println("생성됨");
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서비스 실행");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
