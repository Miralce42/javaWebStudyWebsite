package beans;

/**
 * Created by Vove on 2017/5/21.
 * 学生作业状态  展示学生状态列表
 */
public class HomeworkStudentStatus {
   private String userId;
   private String homeworkId;
   private String name;
   private String major;
   private String classname;
   private StudentHomework.HomeworkStatus homeworkStatus;

   public HomeworkStudentStatus(String userId, String homeworkId, String name, String major, String classname, StudentHomework.HomeworkStatus homeworkStatus) {
      this.userId = userId;
      this.homeworkId = homeworkId;
      this.name = name;
      this.major = major;
      this.classname = classname;
      this.homeworkStatus = homeworkStatus;
   }

   public String getStatusDivContent() {
      String buttonStatusValue;
      switch (homeworkStatus) {
         case FINISHED:
         case SAVED:
            buttonStatusValue = "<a href=correctHomework.jsp?homeworkId=" + homeworkId + "&studentId="+userId+" class='floatButton'>批改</a>\n";
            break;
         case UNFINISHED:
         default:
            buttonStatusValue = "";
      }
      return  " <div class=\"col-md-4\">\n" +
              "     <div class=\"panel\">\n" +
              "         <div class=\"panel-heading\">\n" +
              "             <div class=\"page-title\">" + name  +"<div class='title-right'>"+ major+ classname +"</div>"+
              "             </div>" +
              "         </div>\n" +
              "         <div class=\"panel-buttonValue\">" + buttonStatusValue+
              "         </div>\n" +
              "     </div>\n" +
              " </div>\n";
   }


   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getHomeworkId() {
      return homeworkId;
   }

   public void setHomeworkId(String homeworkId) {
      this.homeworkId = homeworkId;
   }

   public StudentHomework.HomeworkStatus getHomeworkStatus() {
      return homeworkStatus;
   }

   public void setHomeworkStatus(StudentHomework.HomeworkStatus homeworkStatus) {
      this.homeworkStatus = homeworkStatus;
   }
}
