package WebDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.StudentHomework;
import beans.StudentHomework.HomeworkStatus;
import beans.Users;

/**
 * Created by Vove on 2017/5/20.
 *
 */
public class StudentDAO {
   private DB_Manager db_manager=new DB_Manager();
   private Users student;

   public StudentDAO(Users student) {
      this.student = student;
   }

   public Users getStudent() {
      return student;
   }

   public ArrayList<StudentHomework> getUnfinishedHomework(){
      String sql="SELECT * FROM javawebcourseresources.homework where is_closing=0";

      ArrayList<StudentHomework> homeworkList=new ArrayList<StudentHomework>();
      ResultSet resultSet=db_manager.executeQuery(sql,null);
      try {
         while (resultSet.next()){
            String id=resultSet.getString("id");
            String title=resultSet.getString("title");
            String createTime=resultSet.getString("create_time");
            String closingTime=resultSet.getString("closing_time");

            StudentHomework studentHomework=new StudentHomework(id,title,createTime,closingTime);

            String sql_GetStatus="SELECT * FROM javawebcourseresources.homework_status where hw_id=? and user_id=?";
            ResultSet statusSet=db_manager.executeQuery(sql_GetStatus,new String[]{id,student.getUsername()});

            if(statusSet.next()){//存在保存/完成记录
               HomeworkStatus homeworkStatus=HomeworkStatus.valueOf(statusSet.getString("status"));
               studentHomework.setHomeworkStatus(homeworkStatus);
            }else {//未做无记录
               studentHomework.setHomeworkStatus(HomeworkStatus.UNFINISHED);
            }

            homeworkList.add(studentHomework);
         }
         return homeworkList;
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }

   }
}
