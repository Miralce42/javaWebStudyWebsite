package WebDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.StudentHomework;
import beans.Users;

/**
 * Created by Vove on 2017/5/20.
 */
public class TeacherDAO {
   private DB_Manager db_manager=new DB_Manager();
   private Users teacher;

   public TeacherDAO(Users teacher) {
      this.teacher = teacher;
   }
   public ArrayList<StudentHomework> getStudentHomework(){
      String sql="SELECT * FROM javawebcourseresources.homework";

      ArrayList<StudentHomework> homeworkList=new ArrayList<StudentHomework>();

      ResultSet resultSet=db_manager.executeQuery(sql,new String[]{teacher.getUsername()});
      try {
         while (resultSet.next()){
            String id=resultSet.getString("id");
            String title=resultSet.getString("title");
            String createTime=resultSet.getString("create_time");
            String closingTime=resultSet.getString("closing_time");
            StudentHomework studentHomework=new StudentHomework(id,title,createTime,closingTime);
            homeworkList.add(studentHomework);
         }
         return homeworkList;
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }

   }

}
