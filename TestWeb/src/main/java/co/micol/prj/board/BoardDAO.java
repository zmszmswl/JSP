package co.micol.prj.board;

import java.util.ArrayList;

import co.micol.prj.comm.DAO;
import co.micol.prj.emp.EmpVO;

public class BoardDAO extends DAO {
	
	// 전체 조회
	public ArrayList<BoardVO> selectAll(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			// 1. 연결
			getConnect();
			// 2. sql 구문 준비
			String sql= "select * from board order by hit desc";
			psmt = conn.prepareStatement(sql);
			
			// 실행
			rs = psmt.executeQuery();
			// 조회결과 list에 담기
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setId(rs.getString("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRdt(rs.getString("rdt"));
				vo.setHit(rs.getString("hit"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	// 단건 조회
	public BoardVO selectOne(String id) {
		BoardVO vo = new BoardVO();
		try {
			getConnect();
			String sql = "select * from board where id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) { // 단건은 while문 대신 if문 
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));;
				vo.setWriter(rs.getString("writer"));
				vo.setRdt(rs.getString("rdt"));
				vo.setHit(rs.getString("hit"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}
	
	// 등록
	public int boardInsert(BoardVO vo) {
		int cnt=0;
		try {
			getConnect();
			String sql = " insert into board "
						+ " values( ?, ?, ?, ?, ?, ?)";
			// sql 구문 준비
			psmt = conn.prepareStatement(sql);
			
			// sql 구문 실행
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getContent());
			psmt.setString(4, vo.getWriter());
			psmt.setString(5, vo.getRdt());
			psmt.setString(6, vo.getHit());
			cnt=psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}
	
	// 수정
	public int update(BoardVO vo) {
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
	public int delete(String id) {
		int cnt = 0;
		try {
			getConnect();
			String sql = "delete from board where id =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			cnt=psmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			disconnect();
		}
		return cnt;
	}	
}
