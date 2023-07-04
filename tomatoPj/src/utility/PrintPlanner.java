package utility;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class PrintPlanner implements Comparable<PrintPlanner> {
   private int pk; // 프로젝트 no / 멤버 no
   private String selPjName; // 선택한 프로젝트 이름(프로젝트별)
   private String select; // 프로젝트 이름 / 멤버 이름
   private String title; // 프로젝트의 태스크명 / 멤버의 태스크명
   private String update; // 태스크 시작일자
   private String deadLine; // 태스크 종료일자
   private int colorPk = 0;
   private LocalDate up;
   private LocalDate dead;
   private CalendarData cd = new CalendarData();
   private List<PrintPlanner> printList;
   private boolean isAll;

   public PrintPlanner(boolean isAll,int colorPk, List<PrintPlanner> list) {
      super();
      this.isAll = isAll;
      this.colorPk = colorPk;
      this.printList = list;
   }

   public PrintPlanner(boolean isAll, int pk, String select, String title, String update, String deadLine) {
      super();
      this.isAll = isAll;
      this.pk = pk;
      this.select = select;
      this.title = title;
      this.up = cd.getLocalDate(update);
      this.dead = cd.getLocalDate(deadLine);
   }

   public PrintPlanner(boolean isAll,int pk, String selPjName, String select, String title, String update, String deadLine) {
      super();
      this.isAll = isAll;
      this.pk = pk;
      this.selPjName = selPjName;
      this.select = select;
      this.title = title;
      this.up = cd.getLocalDate(update);
      this.dead = cd.getLocalDate(deadLine);
   }

   
   public int getPk() {
      return pk;
   }

   public void setPk(int pk) {
      this.pk = pk;
   }

   public String getSelPjName() {
      return selPjName;
   }

   public void setSelPjName(String selPjName) {
      this.selPjName = selPjName;
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

   public int getColorPk() {
      return colorPk;
   }

   public void setColorPk(int colorPk) {
      this.colorPk = colorPk;
   }

   public LocalDate getUp() {
      return up;
   }

   public void setUp(LocalDate up) {
      this.up = up;
   }

   public LocalDate getDead() {
      return dead;
   }

   public void setDead(LocalDate dead) {
      this.dead = dead;
   }

   public CalendarData getCd() {
      return cd;
   }

   public void setCd(CalendarData cd) {
      this.cd = cd;
   }

   public List<PrintPlanner> getPrintList() {
      return printList;
   }

   public void setPrintList(List<PrintPlanner> printList) {
      this.printList = printList;
   }

   public boolean isAll() {
      return isAll;
   }

   public void setAll(boolean isAll) {
      this.isAll = isAll;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((cd == null) ? 0 : cd.hashCode());
      result = prime * result + colorPk;
      result = prime * result + ((dead == null) ? 0 : dead.hashCode());
      result = prime * result + ((deadLine == null) ? 0 : deadLine.hashCode());
      result = prime * result + (isAll ? 1231 : 1237);
      result = prime * result + pk;
      result = prime * result + ((printList == null) ? 0 : printList.hashCode());
      result = prime * result + ((selPjName == null) ? 0 : selPjName.hashCode());
      result = prime * result + ((select == null) ? 0 : select.hashCode());
      result = prime * result + ((title == null) ? 0 : title.hashCode());
      result = prime * result + ((up == null) ? 0 : up.hashCode());
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
      if (cd == null) {
         if (other.cd != null)
            return false;
      } else if (!cd.equals(other.cd))
         return false;
      if (colorPk != other.colorPk)
         return false;
      if (dead == null) {
         if (other.dead != null)
            return false;
      } else if (!dead.equals(other.dead))
         return false;
      if (deadLine == null) {
         if (other.deadLine != null)
            return false;
      } else if (!deadLine.equals(other.deadLine))
         return false;
      if (isAll != other.isAll)
         return false;
      if (pk != other.pk)
         return false;
      if (printList == null) {
         if (other.printList != null)
            return false;
      } else if (!printList.equals(other.printList))
         return false;
      if (selPjName == null) {
         if (other.selPjName != null)
            return false;
      } else if (!selPjName.equals(other.selPjName))
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
      if (up == null) {
         if (other.up != null)
            return false;
      } else if (!up.equals(other.up))
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
      if(isAll) {
         if(colorPk == 0) {
            return "[전체 프로젝트]\n"
                  + "[컬러 :" + colorPk + ", 프로젝트명: " + select + ", 태스크명: " + title
                  + ", 시작일자: " + up + ", 마감일자: " + dead + "]";
                  
         } else {
            return "[전체 프로젝트]\n"
                  + "[컬러 :" + colorPk + "리스트사이즈: " + printList.size() + "]";
         }
      } else {
         if(colorPk == 0) {
            return "[프로젝트 별] : " + selPjName + "\n"
                  + "[컬러 :" + colorPk + ", 프로젝트명: " + select + ", 태스크명: " + title
                  + ", 시작일자: " + up + ", 마감일자: " + dead + "]";
                  
         } else {
            return "[프로젝트 별] : " + selPjName + "\n"
                  + "[컬러 :" + colorPk + "리스트사이즈: " + printList.size() + "]";
         }
      }
   }

   @Override
   public int compareTo(PrintPlanner p) {
      if (p.pk < pk) {
         return 1;
      } else if (p.pk > pk) {
         return -1;
      }
      return 0;
   }

   public int compareToColorPk(PrintPlanner p) {
      if (p.colorPk < colorPk) {
         return 1;
      } else if (p.colorPk > colorPk) {
         return -1;
      }
      return 0;
   }
}