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

   public ArrayList<StudentHomework> getStudentHomework(){
      String sql="select homework.*,homework_status.status from homework_status,homework " +
              "where is_closing=1 and homework_status.hw_id=homework.id and user_id=?";

      ArrayList<StudentHomework> homeworkList=new ArrayList<StudentHomework>();
      ResultSet resultSet=db_manager.executeQuery(sql,new String[]{student.getUsername()});
      try {
         while (resultSet.next()){
            String id=resultSet.getString("id");
            String title=resultSet.getString("title");
            String createTime=resultSet.getString("create_time");
            String closingTime=resultSet.getString("closing_time");
            HomeworkStatus homeworkStatus=HomeworkStatus.valueOf(resultSet.getString("status"));

            StudentHomework studentHomework=new StudentHomework(id,title,createTime,closingTime,homeworkStatus);
            homeworkList.add(studentHomework);
         }
         return homeworkList;
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }

   }
}
