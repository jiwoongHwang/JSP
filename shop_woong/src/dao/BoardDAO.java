package dao;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;
import dto.BoardDTO;

public class BoardDAO extends JDBConnect {
	
	public BoardDAO() {
		super();
	}
	public int selectCount(String searchField, String searchWord) {
		int result = 0;
		String query = "select count(*) from board";
		
		if(searchWord != null) {
			query += " where " + searchField + " like '%" + searchWord + "%'";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			result = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 개수 세기 오류");
		}
		
		return result;
	}
	
	public List<BoardDTO> selectList(int start, String searchField, String searchWord) {
		List<BoardDTO> dto = new ArrayList<BoardDTO>();
		int totalNum = selectCount(searchField,searchWord) - start + 1;
		String query = "select * from board";
		if(searchWord != null) {
			query += " where " + searchField + " like '%" + searchWord + "%'";
		}
		query += " order by num desc";
		
		int limit = 0;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.absolute(start-1);
			
			while(rs.next()) {
				BoardDTO bto = new BoardDTO();
				bto.setNum(rs.getInt("num"));
				bto.setVirNum(totalNum);
				bto.setTitle(rs.getString("title"));
				bto.setContent(rs.getString("content"));
				bto.setId(rs.getString("Id"));
				bto.setPostdate(rs.getString("postdate"));
				bto.setVisitcount(rs.getInt("visitcount"));
				dto.add(bto);
				totalNum--;
				limit++;
				if(limit==20) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 가져오기 오류");
		}
		return dto;
	}
	
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "insert into board(title, content, id, postdate, visitcount) values(?, ?, ?, ?, 0)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			psmt.setString(4, dto.getPostdate());
			result = psmt.executeUpdate();

//			LocalDate currentDate = LocalDate.now();
//			Date postDate = Date.valueOf(currentDate);
//	        psmt.setDate(4, postDate);
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("게시물 글쓰기 오류");
		}
		return result;
	}
	
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		String query = "select board.*, member.name from board "
				+ "inner join member on board.id = member.id where num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			System.out.println("게시물 상세보기 성공");
			
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setPostdate(rs.getString("postdate"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 상세보기 오류");
		}
				
		return dto;
	}
	
	public void updateVisitCount(String num) {
		String query = "update board set visitcount=visitcount+1 where num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeUpdate();
			System.out.println("게시물 조회수 증가 성공");
			
		} catch(Exception e) {
			System.out.println("게시물 조회수 증가 오류");
			e.printStackTrace();
		}
	}
	
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		String query = "update board set title=?, content=? where num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getNum());
			result = psmt.executeUpdate();
			System.out.println("게시물 수정 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("게시물 수정 오류");
		}
		return result;
	}
	
	public int deletePost(BoardDTO dto) {
		int result = 0;
		String query = "delete from board where num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, dto.getNum());
			result = psmt.executeUpdate();
			System.out.println("게시물 삭제 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("게시물 삭제 실패");
		}
		
		return result;
	}
	
}	
	
