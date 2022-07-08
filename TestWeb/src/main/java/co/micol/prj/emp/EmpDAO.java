package co.micol.prj.emp;

import java.sql.ResultSet;
import java.util.ArrayList;

import co.micol.prj.comm.DAO;

public class EmpDAO extends DAO {
	// JOBs 전체조회
	public ArrayList<JobsVO> selectJobs(){
		ArrayList<JobsVO> list = new ArrayList<JobsVO>();
		try {
			getConnect();
			String sql = "select * from jobs order by job_id";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				JobsVO vo = new JobsVO();
				vo.setJobId(rs.getNString("job_id"));
				vo.setJobTitle(rs.getString("job_title"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4. 연결해제
			disconnect();  // 에러가 나던 나지않던 연결해제 해야하니까 finally에 둬야한다
		}
		return list;
		
	}
	
	// 단건조회 (list 필요없음)
	public EmpVO selectOne(String employeeId) { 
		EmpVO vo = new EmpVO();
		try {
			getConnect();
			String sql = "select * from employees where employee_id =? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, employeeId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setEmployeeId(rs.getString("employee_id"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setJobId(rs.getString("job_id"));
				vo.setDepartmentId(rs.getString("department_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			disconnect();
		}
		return vo;
	}
	
	// 전체조회
	public ArrayList<EmpVO> selectAll(String departmentId ){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		try {
			//1. 연결
			getConnect();
			//2. sql 구문 준비
			String sql = "select * from employees";
			if(departmentId != null && ! departmentId.isEmpty()) {
				sql += " where department_id= ? ";
			}
			sql += " order by employee_id ";
			psmt = conn.prepareStatement(sql);
			if(departmentId != null && ! departmentId.isEmpty()) {
				psmt.setString(1, departmentId);
			}
			
			// 실행
			ResultSet rs = psmt.executeQuery();
			// 조회결과 list에 담기
			while(rs.next()){
				EmpVO vo = new EmpVO();
				vo.setEmployeeId(rs.getString("employee_id"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setJobId(rs.getString("job_id"));
				vo.setDepartmentId(rs.getString("department_id"));
				list.add(vo);
				}	
			} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4. 연결해제
			disconnect();  // 에러가 나던 나지않던 연결해제 해야하니까 finally에 둬야한다
		}
		return list;
	}
	

	
	// 등록
	public int empInsert(EmpVO vo) {
		int cnt=0;
		try {
			getConnect();
			String sql = " insert into"
						+ " employees ( employee_id, last_name, email, hire_date, job_id, department_id)"
						+ " values( (select max(employee_id)+1 from employees) ,"
						+ " ?,?,?,?,?)" ;
			// sql 구문 준비
			psmt = conn.prepareStatement(sql);
			// sql 구문 실행
			psmt.setString(1, vo.getLastName());
			psmt.setString(2, vo.getEmail());
			psmt.setString(3, vo.getHireDate());
			psmt.setString(4, vo.getJobId());
			psmt.setString(5, vo.getDepartmentId());
			cnt=psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return cnt;
	}
	
	// 수정
	public int update(EmpVO vo) {
		int cnt = 0;
		try {
			getConnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}
	// 삭제
	
	public int delete(String employeeId) {
		int cnt = 0;
		try {
			getConnect();
			String sql = "delete from employees where employee_id =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, employeeId);
			cnt=psmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			disconnect();
		}
		return cnt;
	}	
}
