package beans;

/**
 * Created by Vove on 2017/5/20.
 * 学生作业
 */
public class StudentHomework {
   private String id;
   private String title;
   private String createTime;
   private String closingTime;
   private HomeworkStatus homeworkStatus;

   public StudentHomework(String id, String title, String createTime, String closingTime) {
      this.id = id;
      this.title = title;
      this.createTime = createTime;
      this.closingTime = closingTime;
   }

   public StudentHomework(String id, String title, String createTime, String closingTime, HomeworkStatus homeworkStatus) {
      this.id = id;
      this.title = title;
      this.createTime = createTime;
      this.closingTime = closingTime;
      this.homeworkStatus = homeworkStatus;
   }

   public String statusToValue_tea() {//教师获取作业状态
      switch (homeworkStatus) {
         case UNCLOSED:
            return "重新编辑";
         case CLOSED:
            return "已结束";
         default:
            return "";
      }
   }

   public String statusToValue_stu() {//学生过去作业状态
      switch (homeworkStatus) {
         case SAVED:
            return "继续编辑";
         case FINISHED:
         case CORRECTED:
            return "查看";
         case UNFINISHED:
            return "做作业";
         default:
            return "";
      }
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getCreateTime() {
      return createTime;
   }

   public void setCreateTime(String createTime) {
      this.createTime = createTime;
   }

   public String getClosingTime() {
      return closingTime;
   }

   public void setClosingTime(String closingTime) {
      this.closingTime = closingTime;
   }


   public HomeworkStatus getHomeworkStatus() {
      return homeworkStatus;
   }

   public void setHomeworkStatus(HomeworkStatus homeworkStatus) {
      this.homeworkStatus = homeworkStatus;
   }

   public enum HomeworkStatus {
      CORRECTED,//已批
      UNFINISHED,//未完成
      FINISHED,//完成
      SAVED,//保存
      CLOSED,//关闭
      UNCLOSED,//未关闭
   }
}
