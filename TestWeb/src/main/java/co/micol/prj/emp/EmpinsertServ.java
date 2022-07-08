package co.micol.prj.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.dept.DeptDAO;

// http://localhost/컨텍스트패스/
@WebServlet("/empInsert")
public class EmpinsertServ extends HttpServlet{

	// 등록페이지로 이동
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB조회
		// jobs, 부서, 사원리스트
		EmpDAO empDAO = new EmpDAO();
		request.setAttribute("jobs" , empDAO.selectJobs());
		
		DeptDAO deptDAO = new DeptDAO();
		request.setAttribute("depts" , deptDAO.selectAll());
		
		request.getRequestDispatcher("/WEB-INF/jsp/emp/empInsert.jsp")
			   .forward(request, response);
	}
	// 등록처리

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 VO 담고
		//String id = request.getParameter("employeeId");
		//String name = request.getParameter("lastName");
		String email = request.getParameter("email");
		String hiredate = request.getParameter("hireDate");
		String jobid = request.getParameter("jobId"); 
		String depid = request.getParameter("depid");
		
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(request.getParameter("employeeId"));
		vo.setLastName(request.getParameter("lastName"));
		vo.setEmail(email);
		vo.setHireDate(hiredate);
		vo.setJobId(jobid);
		vo.setDepartmentId(depid);
		
		// DB 처리
		EmpDAO empDAO = new EmpDAO();
		int cnt = empDAO.empInsert(vo);
		
		// 결과출력
		// response.getWriter().append(cnt + "건이 등록됨");
		
		// request.getRequestDispatcher("empList").forward(request, response);
		response.sendRedirect("empList"); // 등록 수정 삭제 경우에는 다른페이지로 옮길땐 sendRedirect로
		
				
		
		
	}

	
}
