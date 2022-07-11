package co.micol.prj.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.prj.comm.DataSource;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<MemberVO> memberSelectList() {
		// 전체맴버 목록
		List<MemberVO> list = new ArrayList<MemberVO>(); // 결과담을 객체 = list
		MemberVO vo;
		String sql = "SELECT * FROM MEMBER";  // 관례적으로 SQL 문은 대문자로 작성한다 
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); // sql 을 수행시켜서 rs에
			while(rs.next()) {  // rs가 Eof가 될때까지 존재하면 true 아니면 false
				vo = new MemberVO();
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberAuthor(rs.getString("member_author"));
				list.add(vo);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	@Override
	public MemberVO emeberSelectOne(MemberVO vo) {
		// 한명 조회
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? ";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setMemberId(rs.getString("member_id"));
// 				vo.setMemberPassword(rs.getString("member_password")); 암호는 보여주지않는다. 평문 -암호화-> 암호화된문장 -복호화-> 평문
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {	
			close();
		}
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// 등록
		String sql = "insert into member values (? , ? , ? , ?) ";
		int cnt = 0;
		try {
			conn=dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPassword());
			psmt.setString(3, vo.getMemberName());
			psmt.setString(4, vo.getMemberAuthor());
			cnt = psmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {		
			close();
		}
		return cnt;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// 회원 정보 변경                           // 권한, 패스워드
		int n = 0;
		String sql = "UPDATE MEMBER SET MEMBER_PASSWORD =?, "
					+ "MEMBER_AUTHOR =? WHERE MEMBER_ID =? ";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberPassword());
			psmt.setString(2, vo.getMemberAuthor());
			psmt.setString(3, vo.getMemberId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// 회원정보 삭제
		int n = 0;
		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID =? ";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public boolean isMemberIdCheck(String id) {
		// 회원아이디 중복체크
		boolean b = false;
		String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID =?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(!rs.next()) { // 존재하지 않으면 true, 존재하면  false를 돌려준다
				b = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return b;
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		// 회원 로그인
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID =? AND MEMBER_PASSWORD =? ";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPassword());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
