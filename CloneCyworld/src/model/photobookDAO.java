package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import common.DBConnector;

public class photobookDAO extends DBConnector {
   
   public photobookDAO() {
      super();
   }
   
   
   public int InsertPhotoBook(photobook p_book) {
      String INSERT_PHOTOBOOK_SQL = "insert into photobook (id, created, title, content, fileName) values "
                           + "(?, ?, ?, ?, ?)";
      LocalDateTime currentTime = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
      String formattedTime = currentTime.format(formatter);
      
      
      int result = 0;
      
      try {
         psmt = con.prepareStatement(INSERT_PHOTOBOOK_SQL);
         psmt.setString(1, p_book.getId());
         psmt.setString(2, p_book.getCreated());
         psmt.setString(3, p_book.getTitle());
         psmt.setString(4, p_book.getContent());
         psmt.setString(5, p_book.getFileName());
         
         result = psmt.executeUpdate(); 
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      return result;
   }
   
   public List<photobook> GetPhotoBookList(String id)  {
      String GET_PHOTOBOOK_SQL = "select * from photobook where id = ? ";
      List<photobook> p_bookList = new ArrayList<photobook>();
      
      try {
         psmt = con.prepareStatement(GET_PHOTOBOOK_SQL);
         psmt.setString(1, id);
         rs = psmt.executeQuery();
         
         while(rs.next()) {
            photobook photobook = new photobook();
            photobook.setP_no(rs.getInt(1));
            photobook.setId(rs.getString(2));
            photobook.setCreated(rs.getString(3));
            photobook.setTitle(rs.getString(4));
            photobook.setContent(rs.getString(5));
            photobook.setFileName(rs.getString(6));
            
            p_bookList.add(photobook);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      
      return p_bookList;
   }
   
   //입력한 사진첩의 파일글 번호와 일치하는 파일글 삭제하기
   public void DeletePhotoBook(Integer p_no) { 
      String DELETE_PHOTOBOOK_SQL = "delete from photobook where p_no=?";
      try {
         psmt = con.prepareStatement(DELETE_PHOTOBOOK_SQL);
         psmt.setInt(1, p_no);
         psmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }
}