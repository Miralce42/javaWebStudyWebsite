package beans;

/**
 * Created by Vove on 2017/5/20.
 *
 *
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
      UNFINISHED,//未完成
      FINISHED,//完成
      SAVED//保存
   }
}
