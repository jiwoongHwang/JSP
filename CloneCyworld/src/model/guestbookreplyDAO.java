package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import common.DBConnector;

public class guestbookreplyDAO extends DBConnector {
   
   
   public int InsertBookReply(guestbookreply bookReply) {
      List<guestbookreply> userBookReply = new ArrayList<guestbookreply>();
      String INSERT_BOOKREPLY_SQL = "insert into guestbookReply (b_no, id, created, content) values (?,?,?,?)";
      LocalDateTime currentTime = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
      String formattedTime = currentTime.format(formatter);
      
      int result=0;
      try {
         psmt = con.prepareStatement(INSERT_BOOKREPLY_SQL);
         psmt.setInt(1, bookReply.getB_no());
         psmt.setString(2, bookReply.getId());
         psmt.setString(3, formattedTime);
         psmt.setString(4, bookReply.getContent());
         
         result = psmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;
   }
   
   

   
   public List<guestbookreply> GetUserbookReplyList(int b_no) {
      List<guestbookreply> userReplyList = new ArrayList<guestbookreply>();
      String GET_USER_BOOKREPLY_LIST_SQL = "select guestbookreply.r_no, guestbookreply.id, guestbookreply.created, guestbookreply.content from guestbookreply join guestbook on guestbookreply.b_no = guestbook.no where guestbookreply.b_no = ? order by guestbookreply.created desc";
      
      try {
         psmt = con.prepareStatement(GET_USER_BOOKREPLY_LIST_SQL);
         psmt.setInt(1, b_no);
         rs = psmt.executeQuery();
         
         while (rs.next()) {
        	guestbookreply guestbookReply = new guestbookreply();
        	guestbookReply.setR_no(rs.getInt(1));
            guestbookReply.setId(rs.getString(2));
            guestbookReply.setCreated(rs.getString(3));
            guestbookReply.setContent(rs.getString(4));
            
            userReplyList.add(guestbookReply);
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return userReplyList;
   }
   
   public int DeleteGuestbookReply(int r_no) {
	   String DELETE_BOOKREPLY_SQL = "delete from guestbookreply where r_no = ?";
	   
	   int result = 0;
	   
	   try {
		   psmt = con.prepareStatement(DELETE_BOOKREPLY_SQL);
		   psmt.setInt(1, r_no);
		   psmt.executeUpdate();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return result;
   }
   
   
}