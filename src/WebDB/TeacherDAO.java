package WebDB;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ChoiceHomework;
import beans.CompletionHomework;
import beans.Homework;
import beans.HomeworkStudentStatus;
import beans.StudentHomework;
import beans.Users;

/**
 * Created by Vove on 2017/5/20.
 */
public class TeacherDAO {
   private DB_Manager db_manager = new DB_Manager();
   private Users teacher;

   public TeacherDAO() {
   }

   ;

   public TeacherDAO(Users teacher) {
      this.teacher = teacher;
   }

   public ArrayList<StudentHomework> getHomeworkList() {
      String sql = "SELECT * FROM javawebcourseresources.homework ORDER BY closing_time DESC ";

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

   public int updateStudentInfo(Users student) {
      String ssql = "update javawebcourseresources.users" +
              " set name=?,sex=?,phone=?,major=?,class=? " +
              "where user_id=?";
      int states = db_manager.executeUpdate(ssql, new String[]{student.getName(), student.getSex(), student.getPhone(), student.getMajor(), student.getClassNum(), student.getUsername()});
      return states;
   }

   public int deleteStudentInfo(Users student) {
      String ssql = "delete from javawebcourseresources.users where user_id=?";
      int states = db_manager.executeUpdate(ssql, new String[]{student.getUsername()});
      return states;
   }

   public int addStudentInfo(Users student) {
      String ssql = "insert into javawebcourseresources.users values(?,'student',?,?,?,?,?,?)";
      int states = db_manager.executeUpdate(ssql, new String[]{student.getUsername(), student.getName(), student.getUsername(), student.getSex(), student.getPhone(), student.getMajor(), student.getClassNum()});
      return states;
   }


   public boolean publishHomework(Homework newHomework) {
      try {
         db_manager.beginAffair();//开始事务

         String sql = "insert into homework(title,create_time,closing_time,is_closing)" +
                 " values(?,?,?,0)";
         db_manager.executeUpdate(sql, new String[]{newHomework.getHomeworkTitle()
                 , newHomework.getBeginTime()
                 , newHomework.getEndTime()
         });//插入作业记录

         String homeworkId = getHomeworkId();//根据刚插入数据获取id

         int questionIndex = 1;
         for (ChoiceHomework choiceHomework : newHomework.getChoiceHomeworkList()) {//插入选择题记录
            String insertChoiceSql = "insert into hw_question_choice(hw_id,question_index,question_content,reference_answer) " +
                    "values(?,?,?,?)";
            if (db_manager.executeUpdate(insertChoiceSql, new String[]{homeworkId
                    , Integer.toString(questionIndex++)//*
                    , choiceHomework.getQuestion()
                    , choiceHomework.getRef_ky()
            }) != 1) {
               db_manager.rollbackAffair();//因db_manager逻辑，发生exception
               return false;
            }

            //插入选项记录
            String choiceId = getChoiceId();
            String insertChoice = "insert into choiceofquestion values(?,?,?)";//A
            if (db_manager.executeUpdate(insertChoice, new String[]{choiceId, "A", choiceHomework.getChoice_A()}) != 1
                    || db_manager.executeUpdate(insertChoice, new String[]{choiceId, "B", choiceHomework.getChoice_B()}) != 1
                    || db_manager.executeUpdate(insertChoice, new String[]{choiceId, "C", choiceHomework.getChoice_C()}) != 1
                    || db_manager.executeUpdate(insertChoice, new String[]{choiceId, "D", choiceHomework.getChoice_D()}) != 1) {
               db_manager.rollbackAffair();
               return false;
            }
         }
         for (CompletionHomework completionHomework : newHomework.getCompletionHomeworkList()) {//插入填空记录
            String insertCompletionSql = "insert into hw_question_completion(hw_id,question_content,score) values(?,?,?)";
            if (db_manager.executeUpdate(insertCompletionSql, new String[]{homeworkId
                    , completionHomework.getCompletionContent()
                    , completionHomework.getScore()}) != 1) {
               db_manager.rollbackAffair();
               return false;
            }
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

   public Homework getHomeworkDetail(String homeworkId) {
      Homework homework = new Homework();
      homework.setHomeworkId(homeworkId);
      ArrayList<ChoiceHomework> choiceHomeworkList = new ArrayList<>();
      ArrayList<CompletionHomework> completionHomeworkList = new ArrayList<>();
      homework.setChoiceHomeworkList(choiceHomeworkList);
      homework.setCompletionHomeworkList(completionHomeworkList);

      String homeworkSql = "SELECT title,create_time,closing_time FROM homework where id=?";
      ResultSet homeworkResultSet = db_manager.executeQuery(homeworkSql, new String[]{homeworkId});
      try {
         if (homeworkResultSet.next()) {
            homework.setHomeworkTitle(homeworkResultSet.getString("title"))
                    .setBeginTime(homeworkResultSet.getString("create_time"))
                    .setEndTime(homeworkResultSet.getString("closing_time"));
            String getChiocesSql = "SELECT * FROM javawebcourseresources.hw_question_choice where hw_id=? order by question_index";


            //获取选择题集
            ResultSet choiceSet = db_manager.executeQuery(getChiocesSql, new String[]{homeworkId});
            while (choiceSet.next()) {//先存放choice,防止结果集丢失
               ChoiceHomework choiceHomework = new ChoiceHomework();
               choiceHomework.setId(choiceSet.getString("id"))
                       .setQuestion(choiceSet.getString("question_content"))
                       .setRef_ky(choiceSet.getString("reference_answer"))
                       .setScore(choiceSet.getString("score"));
               choiceHomeworkList.add(choiceHomework);
            }
            //获取选项
            for (ChoiceHomework choiceHomework : choiceHomeworkList) {
               String getChoiceSql = "SELECT * FROM choiceofquestion where choice_id=? and choice_key=?";
               ResultSet choiceASet = db_manager.executeQuery(getChoiceSql, new String[]{choiceHomework.getId(), "A"});
               choiceASet.next();
               choiceHomework.setChoice_A(choiceASet.getString("content"));
               ResultSet choiceBSet = db_manager.executeQuery(getChoiceSql, new String[]{choiceHomework.getId(), "B"});
               choiceBSet.next();
               choiceHomework.setChoice_B(choiceBSet.getString("content"));
               ResultSet choiceCSet = db_manager.executeQuery(getChoiceSql, new String[]{choiceHomework.getId(), "C"});
               choiceCSet.next();
               choiceHomework.setChoice_C(choiceCSet.getString("content"));
               ResultSet choiceDSet = db_manager.executeQuery(getChoiceSql, new String[]{choiceHomework.getId(), "D"});
               choiceDSet.next();
               choiceHomework.setChoice_D(choiceDSet.getString("content"));
            }
            //获取填空集
            String getCompletionSql = "SELECT * FROM hw_question_completion where hw_id=?";
            ResultSet completionSet = db_manager.executeQuery(getCompletionSql, new String[]{homeworkId});
            while (completionSet.next()) {
               CompletionHomework completionHomework = new CompletionHomework(
                       completionSet.getString("id"),
                       completionSet.getString("question_content"),
                       completionSet.getString("score"));
               completionHomeworkList.add(completionHomework);
            }

            return homework;
         } else {
            return null;
         }
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }
   }
}

