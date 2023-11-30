package model;

public class guestbookreply {
   private Integer r_no, b_no;
   private String id, created, content;
   
   public Integer getR_no() {
      return r_no;
   }
   public void setR_no(Integer r_no) {
      this.r_no = r_no;
   }
   
   public Integer getB_no() {
      return b_no;
   }
   
   public void setB_no(Integer b_no) {
      this.b_no = b_no;
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
   
   public String getContent() {
      return content;
   }
   
   public void setContent(String content) {
      this.content = content;
   }
   
   
}