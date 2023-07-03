package utility;

import java.security.Timestamp;

public class PrintPlanner implements Comparable<PrintPlanner>{
   private int pk; // 프로젝트 no / 멤버 no
   private String selPjName; // 선택한 프로젝트 이름
   private String select; // 프로젝트 이름 / 멤버 이름
   private String title; // 프로젝트의 태스크명 / 멤버의 태스크명
   private String update; // 태스크 시작일자
   private String deadLine; // 태스크 종료일자
   
   
   public PrintPlanner(int pk, String select, String title, String update, String deadLine) {
      super();
      this.pk = pk;
      this.select = select;
      this.title = title;
      this.update = update;
      this.deadLine = deadLine;
   }

   public PrintPlanner(int pk, String selPjName, String select, String title, String update, String deadLine) {
      super();
      this.pk = pk;
      this.selPjName = selPjName;
      this.select = select;
      this.title = title;
      this.update = update;
      this.deadLine = deadLine;
   }
   
   public String getSelPjName() {
      return selPjName;
   }

   public void setSelPjName(String selPjName) {
      this.selPjName = selPjName;
   }

   public int getPk() {
      return pk;
   }
   public void setPk(int pk) {
      this.pk = pk;
   }
   public String getSelect() {
      return select;
   }
   public void setSelect(String select) {
      this.select = select;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getUpdate() {
      return update;
   }
   public void setUpdate(String update) {
      this.update = update;
   }
   public String getDeadLine() {
      return deadLine;
   }
   public void setDeadLine(String deadLine) {
      this.deadLine = deadLine;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((deadLine == null) ? 0 : deadLine.hashCode());
      result = prime * result + pk;
      result = prime * result + ((select == null) ? 0 : select.hashCode());
      result = prime * result + ((title == null) ? 0 : title.hashCode());
      result = prime * result + ((update == null) ? 0 : update.hashCode());
      return result;
   }
   
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      PrintPlanner other = (PrintPlanner) obj;
      if (deadLine == null) {
         if (other.deadLine != null)
            return false;
      } else if (!deadLine.equals(other.deadLine))
         return false;
      if (pk != other.pk)
         return false;
      if (select == null) {
         if (other.select != null)
            return false;
      } else if (!select.equals(other.select))
         return false;
      if (title == null) {
         if (other.title != null)
            return false;
      } else if (!title.equals(other.title))
         return false;
      if (update == null) {
         if (other.update != null)
            return false;
      } else if (!update.equals(other.update))
         return false;
      return true;
   }
   
   @Override
   public String toString() {
      if(selPjName == null) {
         return "전체 프로젝트: " + select + ", 프로젝트 pk: " + pk + ", 태스크명: " + title + ", 시작일자: " + update
               + ", 마감일자: " + deadLine + "]";
      } else {
         return "프로젝트 별: " + selPjName + ", 멤버 pk: " + pk +"멤버이름: " + select + ", 태스크명: " + title + ", 시작일자: " + update
               + ", 마감일자: " + deadLine + "]";
      }
   }
   
   @Override
   public int compareTo(PrintPlanner p) {
      if(p.pk < pk) {
         return 1;
      } else if (p.pk > pk) {
         return -1;
      } return 0;
   }
}