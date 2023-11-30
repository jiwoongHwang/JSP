package dao;

import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;
import dto.BookDTO;

public class BookDAO extends JDBConnect {
	
	public BookDAO() {
		super();
	}
	
	public List<BookDTO> selectList() {
		List<BookDTO> dto = new ArrayList<BookDTO>();
		
		String sql = "select * from bookinfo";
		
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BookDTO dto2 = new BookDTO();
				
				dto2.setBookid(rs.getString(1));
				dto2.setAuthor(rs.getString(2));
				dto2.setBooktitle(rs.getString(3));
				dto2.setBookcontent(rs.getString(4));
				dto2.setBookprice(rs.getInt(5));
				dto2.setBookdate(rs.getString(6));
				dto2.setMemberid(rs.getString(7));
				
				dto.add(dto2);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 가져오기 오류");
		}
		return dto;
	}
	
	public BookDTO selectView(String id) {
		BookDTO dto = new BookDTO();
		String sql = "select bookinfo.*, membership.memberid from bookinfo "
				+ "inner join membership on bookinfo.memberid = membership.memberid where bookid=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			System.out.println("게시물 상세보기 성공");
			
			if(rs.next()) {
				dto.setBookid(rs.getString("bookid"));
				dto.setAuthor(rs.getString("author"));
				dto.setBooktitle(rs.getString("booktitle"));
				dto.setBookcontent(rs.getString("bookcontent"));
				dto.setBookprice(rs.getInt("bookprice"));
				dto.setBookdate(rs.getString("bookdate"));
				dto.setMemberid(rs.getString("memberid"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 상세보기 오류");
		}
		return dto;
	}
	
	public int addLibrary(BookDTO dto) {
		int result = 0;
		
		try {
			String sql = "insert into bookinfo(bookid, author, booktitle, bookcontent, bookprice, bookdate, memberid) values(?, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getBookid());
			psmt.setString(2, dto.getAuthor());
			psmt.setString(3, dto.getBooktitle());
			psmt.setString(4, dto.getBookcontent());
			psmt.setInt(5, dto.getBookprice());
			psmt.setString(6, dto.getBookdate());
			psmt.setString(7, dto.getMemberid());
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("도서 추가 실패");
		}
		
		return result;
	}
}
