package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import common.DBConnector;

public class guestbookDAO extends DBConnector {
	
	public guestbookDAO() {
		super();
	}

	public List<guestbook> getGuestbook_list(String id) {
		List<guestbook> list = new ArrayList<>();
		
		String sql = "select guestbook.*, imgFile from guestbook join member on member.id=guestbook.id where guestbook.owner_id=? order by no desc";
	
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				guestbook dto = new guestbook();
				dto.setNo(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setOwner_id(rs.getString(3));
				
				String date = rs.getString(4).substring(0, 16);
				dto.setCreated(date);
				dto.setContent(rs.getString(5));
				dto.setImgFile(rs.getString(6));
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int writebook(guestbook dto) {
		String INSERT_BOOK_SQL = "insert into guestbook(id, owner_id, content) values (?,?,?)";
		
		int result = 0;
		
		try {
			psmt = con.prepareStatement(INSERT_BOOK_SQL);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getOwner_id());
			psmt.setString(3, dto.getContent());
			
			result = psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deletebook(String no) {
		String DELETE_BOOK_SQL = "delete from guestbook where no = ?";
		
		int result = 0;
		
		try {
			psmt = con.prepareStatement(DELETE_BOOK_SQL);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int checkDate(String id) {
	      LocalDateTime currentTime = LocalDateTime.now();
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	      String created = currentTime.format(formatter);
	      
	      int result = 0; 
	      String CHECK_DATE_GUESTBOOK_SQL = "select count(*) as count from guestbook where created like '%"+created+"%' and owner_id = ?";
	      try {
	         psmt = con.prepareStatement(CHECK_DATE_GUESTBOOK_SQL);
	         psmt.setString(1, id);
	         rs = psmt.executeQuery();
	         
	         rs.next();
	         result = rs.getInt(1);
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      
	      return result;
	   }
}
