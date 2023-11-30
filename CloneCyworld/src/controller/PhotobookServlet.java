package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.photobook;
import model.photobookDAO;


public class PhotobookServlet extends HttpServlet {
   

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      resp.setContentType("text/html; charset=UTF-8");
      
      String realFolder = req.getServletContext().getRealPath("/Uploads");
   
      System.out.println(realFolder);
      resp.setContentType("text/html; charset=UTF-8");
      int maxSize = 5 * 1024 * 1024;
      String encType = "UTF-8";
      
      MultipartRequest mr = new MultipartRequest(req, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
      
      HttpSession session = req.getSession();
      String loginUserId = (String) session.getAttribute("loginUserId");
   
      String owner_id = mr.getParameter("id");
      String title = mr.getParameter("title");
      String content = mr.getParameter("content");

      Enumeration files = mr.getFileNames();
      String imgName = (String) files.nextElement();
      String fileName = mr.getFilesystemName(imgName);
      
      LocalDateTime currentTime = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
      String created = currentTime.format(formatter);
      
      
      photobook photobook = new photobook();
      photobook.setId(owner_id);
      photobook.setCreated(created);
      photobook.setTitle(title);
      photobook.setContent(content);
      photobook.setFileName(fileName);
      
      photobookDAO dao = new photobookDAO();
      int result = dao.InsertPhotoBook(photobook);
      if(result == 1) {
         System.out.println("PhotobookInsertServlet 성공");
      }else {
         System.out.println("PhotobookInsertServlet 실패");
         
      }
      resp.sendRedirect("photobook.jsp?userid="+owner_id);
   }
}