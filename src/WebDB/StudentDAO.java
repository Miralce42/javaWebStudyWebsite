package WebDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import beans.InteractionTopic;
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

   public StudentDAO(){}

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

   public int createTopic(InteractionTopic topic){
      String ssql = "insert into javawebcourseresources.interactiontopic(" +
              "user_id,title,content,topic_type,is_deleted) " +
              "values(?,?,?,?,0)";
      String topicType = topic.getTopicType().toString();//字符转换
      //executeUpdate成功返回1
      return db_manager.executeUpdate(ssql,new String[]{topic.getUsername(),topic.getTitle(),topic.getContent(),topicType});
   }

   public String getName(String username){
      String ssql = "select name from javawebcourseresources.users where user_id = ?";
      ResultSet rs = db_manager.executeQuery(ssql,new String[]{username});
      try {
         if(rs.next()){
            return rs.getString("name");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }

   public int getAllTopic(ArrayList<InteractionTopic> Topics) {
      String ssql = "select * from javawebcourseresources.interactiontopic where is_deleted = 0";
      ResultSet rs = db_manager.executeQuery(ssql,null);
      try {
         while (rs.next()){
            InteractionTopic topic = new InteractionTopic();
            topic.setTopicId(rs.getString("topic_id"));
            topic.setUsername(rs.getString("user_id"));
            topic.setTopicType(rs.getString("topic_type"));
            topic.setTitle(rs.getString("title"));
            topic.setContent(rs.getString("content"));
            Timestamp date = rs.getTimestamp("create_date");
            topic.setDate(date);
            Topics.add(topic);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return Topics.size();
   }

   public InteractionTopic getOneTopic(String topicId) {
      String ssql = "select * from javawebcourseresources.interactiontopic where topic_id=？and is_deleted = 0";
      ResultSet rs = db_manager.executeQuery(ssql,new String[]{topicId});
      InteractionTopic topic = new InteractionTopic();
      try {
         while (rs.next()){
            topic.setTopicId(rs.getString("topic_id"));
            topic.setUsername(rs.getString("user_id"));
            topic.setTopicType(rs.getString("topic_type"));
            topic.setTitle(rs.getString("title"));
            topic.setContent(rs.getString("content"));
            Timestamp date = rs.getTimestamp("create_date");
            topic.setDate(date);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return topic;
   }
}
