package model;

public class photobook {
   private Integer p_no;
   private String id, created, title, content, fileName;
   
   
   public Integer getP_no() {
      return p_no;
   }
   
   public void setP_no(Integer p_no) {
      this.p_no = p_no;
   }
   
   public String getId() {
      return id;
   }
   
   public void setId(String id) {
      this.id = id;
   }
   
   public String getCreated() {
      return created;
   }
   
   public void setCreated(String created) {
      this.created = created;
   }
   
   public String getTitle() {
      return title;
   }
   
   public void setTitle(String title) {
      this.title = title;
   }
   
   public String getContent() {
      return content;
   }
   
   public void setContent(String content) {
      this.content = content;
   }
   
   public String getFileName() {
      return fileName;
   }
   
   public void setFileName(String fileName) {
      this.fileName = fileName;
   }
   
}