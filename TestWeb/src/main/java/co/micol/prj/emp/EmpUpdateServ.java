package co.micol.prj.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.dept.DeptDAO;

// http://localhost/컨텍스트패스/
@WebServlet("/empUpdate")
public class EmpUpdateServ extends HttpServlet{

	// 등록페이지로 이동
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB조회
		// jobs, 부서, 사원리스트
		EmpDAO empDAO = new EmpDAO();
		request.setAttribute("jobs" , empDAO.selectJobs());
		DeptDAO deptDAO = new DeptDAO();
		request.setAttribute("depts" , deptDAO.selectAll());
		
		// 수정할 사번을 받아서 단건조회 empUpdate=employeeId=100
		String employeeId = request.getParameter("employeeId");
		request.setAttribute("emp", empDAO.selectOne(employeeId));
		request.getRequestDispatcher("/WEB-INF/jsp/emp/empUpdate.jsp")
			   .forward(request, response);
	}
	// DB 수정 처리

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		
		
		//파라미터 VO 담고
		String id = request.getParameter("employeeId");
		String name = request.getParameter("lastName");
		String email = request.getParameter("email");
		String hiredate = request.getParameter("hireDate");
		String jobid = request.getParameter("jobId"); 
		String depid = request.getParameter("depid");
		
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(id);
		vo.setLastName(name);
		vo.setEmail(email);
		vo.setHireDate(hiredate);
		vo.setJobId(jobid);
		vo.setDepartmentId(depid);
	
		
		// DB 처리
		EmpDAO empDAO = new EmpDAO();
		int cnt = empDAO.update(vo);
		
		// 결과출력
		response.getWriter()
		.append(cnt + "건이 등록됨");
		
		
		
				
		
		
	}

	
}
