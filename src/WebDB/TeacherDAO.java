package WebDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ChoiceHomework;
import beans.CompletionHomework;
import beans.HomeworkStudentStatus;
import beans.StudentHomework;
import beans.Users;

/**
 * Created by Vove on 2017/5/20.
 *
 */
public class TeacherDAO {
   private DB_Manager db_manager = new DB_Manager();
   private Users teacher;

   public TeacherDAO(){};

   public TeacherDAO(Users teacher) {
      this.teacher = teacher;
   }

   public ArrayList<StudentHomework> getHomework() {
      String sql = "SELECT * FROM javawebcourseresources.homework ORDER BY id DESC ";

      ArrayList<StudentHomework> homeworkList = new ArrayList<StudentHomework>();

      ResultSet resultSet = db_manager.executeQuery(sql, null);

      try {
         while (resultSet != null && resultSet.next()) {
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String createTime = resultSet.getString("create_time");
            String closingTime = resultSet.getString("closing_time");
            boolean isClosing = resultSet.getInt("is_closing") == 1;

            StudentHomework studentHomework = new StudentHomework(id, title, createTime, closingTime);
            if (isClosing) {
               studentHomework.setHomeworkStatus(StudentHomework.HomeworkStatus.CLOSED);
            } else
               studentHomework.setHomeworkStatus(StudentHomework.HomeworkStatus.UNCLOSED);
            homeworkList.add(studentHomework);
         }
         return homeworkList;
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }
   }

   //保存和完成状态
   public ArrayList<HomeworkStudentStatus> getHomeworkFinishedAndSavedStudentList(String homeworkId) {
      String sql = "SELECT users.user_id,name,status,major,class FROM homework_status,users where hw_id=? " +
              "and user_type='STUDENT' and users.user_id=homework_status.user_id";
      ArrayList<HomeworkStudentStatus> homeworkFinishedAndSavedStudentList = new ArrayList<HomeworkStudentStatus>();
      ResultSet resultSet = db_manager.executeQuery(sql, new String[]{homeworkId});
      try {
         while (resultSet.next()) {
            String userId = resultSet.getString("user_id");
            String status = resultSet.getString("status");
            String name = resultSet.getString("name");
            String major = resultSet.getString("major");
            String classname = resultSet.getString("class");


            //存在记录，存在状态
            StudentHomework.HomeworkStatus homeworkStatus = StudentHomework.HomeworkStatus.valueOf(status);
            HomeworkStudentStatus homeworkStudentStatus = new HomeworkStudentStatus(userId, homeworkId, name, major, classname, homeworkStatus);
            homeworkFinishedAndSavedStudentList.add(homeworkStudentStatus);
         }
         return homeworkFinishedAndSavedStudentList;
      } catch (SQLException e) {
         e.printStackTrace();
         return homeworkFinishedAndSavedStudentList;
      }
   }

   public ArrayList<HomeworkStudentStatus> getHomeworkUnfinishedStudentList(String homeworkId) {
      String sql = "SELECT users.user_id,name,major,class FROM users " +
              "where user_type='STUDENT' and user_id not in (" +
              "select user_id from homework_status where hw_id=?)";
      ArrayList<HomeworkStudentStatus> homeworkFinishedAndSavedStudentList = new ArrayList<HomeworkStudentStatus>();
      ResultSet resultSet = db_manager.executeQuery(sql, new String[]{homeworkId});
      try {
         while (resultSet.next()) {
            String userId = resultSet.getString("user_id");
            String name = resultSet.getString("name");
            String major = resultSet.getString("major");
            String classname = resultSet.getString("class");

            HomeworkStudentStatus homeworkStudentStatus = new HomeworkStudentStatus(userId, homeworkId, name, major, classname, StudentHomework.HomeworkStatus.UNFINISHED);
            homeworkFinishedAndSavedStudentList.add(homeworkStudentStatus);
         }
         return homeworkFinishedAndSavedStudentList;
      } catch (SQLException e) {
         e.printStackTrace();
         return homeworkFinishedAndSavedStudentList;
      }
   }

   public int updateStudentInfo(Users student){
      String ssql = "update javawebcourseresources.users" +
              " set name=?,sex=?,phone=?,major=?,class=? " +
              "where user_id=?";
      int states = db_manager.executeUpdate(ssql,new String[]{student.getName(),student.getSex(),student.getPhone(),student.getMajor(),student.getClassNum(),student.getUsername()});
      return states;
   }

   public int deleteStudentInfo(Users student){
      String ssql = "delete from javawebcourseresources.users where user_id=?";
      int states = db_manager.executeUpdate(ssql,new String[]{student.getUsername()});
      return states;
   }

   public int addStudentInfo(Users student){
      String ssql = "insert into javawebcourseresources.users values(?,'student',?,?,?,?,?,?)";
      int states = db_manager.executeUpdate(ssql,new String[]{student.getUsername(),student.getName(),student.getUsername(),student.getSex(),student.getPhone(),student.getMajor(),student.getClassNum()});
      return states;
   }



   public boolean publishHomework(String homeworkTitle
           , ArrayList<ChoiceHomework> choices, ArrayList<CompletionHomework> completions) {
      try {
         db_manager.beginAffair();//开始事务

         String sql = "insert into homework(title,create_time,closing_time,is_closing)" +
                 " values(?,now(),now(),0)";
         db_manager.executeUpdate(sql, new String[]{homeworkTitle});//

         String homeworkId = getHomeworkId();//根据刚插入数据获取id

         int questionIndex = 1;
         for (ChoiceHomework choiceHomework : choices) {//插入选择题记录
            String insertChoiceSql = "insert into hw_question_choice(hw_id,question_index,question_content,reference_answer) " +
                    "values(?,?,?,?)";
            db_manager.executeUpdate(insertChoiceSql, new String[]{homeworkId
                    , Integer.toString(questionIndex++)//*
                    , choiceHomework.getQuestion()
                    , choiceHomework.getRef_ky()
            });

            //插入选项记录
            String choiceId = getChoiceId();
            String insertChoice = "insert into choiceofquestion values(?,?,?)";//A
            db_manager.executeUpdate(insertChoice, new String[]{choiceId, "A", choiceHomework.getChoice_A()});
            db_manager.executeUpdate(insertChoice, new String[]{choiceId, "B", choiceHomework.getChoice_B()});
            db_manager.executeUpdate(insertChoice, new String[]{choiceId, "C", choiceHomework.getChoice_C()});
            db_manager.executeUpdate(insertChoice, new String[]{choiceId, "D", choiceHomework.getChoice_D()});
         }
         for (CompletionHomework completionHomework : completions) {//插入填空记录
            String insertCompletionSql = "insert into hw_question_completion(hw_id,question_content) values(?,?)";
            db_manager.executeUpdate(insertCompletionSql, new String[]{homeworkId, completionHomework.getCompletionContent()});
         }
         db_manager.Commit();//提交
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         try {
            db_manager.rollbackAffair();
            return false;
         } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
         }
      }
   }

   private String getChoiceId() throws SQLException {
      String getHwIdSql = "SELECT max(id) FROM hw_question_choice";
      ResultSet resultSet = db_manager.executeQuery(getHwIdSql, null);
      resultSet.next();
      return resultSet.getString(1);
   }

   private String getHomeworkId() throws SQLException {
      String getHwIdSql = "SELECT max(id) FROM homework";
      ResultSet resultSet = db_manager.executeQuery(getHwIdSql, null);
      resultSet.next();
      return resultSet.getString(1);
   }

}

