package WebDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import beans.*;
import beans.StudentHomework.HomeworkStatus;
import beans.TopicComments;
import beans.TeachingEvaluation;
import beans.Users;

/**
 * Created by Vove on 2017/5/20.
 *
 */
public class StudentDAO {
    private DB_Manager db_manager = new DB_Manager();
    private Users student;

    public StudentDAO() {
    }

    public StudentDAO(Users student) {
        this.student = student;
    }

    public Users getStudent() {
        return student;
    }

    public ArrayList<StudentHomework> getHomeworkList() {
        String sql = "SELECT * FROM javawebcourseresources.homework where is_delete=0 and now()<end_time and now()>begin_time ORDER BY end_time DESC";
        //未结束
        ArrayList<StudentHomework> homeworkList = new ArrayList<>();
        ResultSet resultSet = db_manager.executeQuery(sql, null);
        try {
            while (resultSet.next()) {
                String homeworkId = resultSet.getString("id");
                String title = resultSet.getString("title");
                String createTime = resultSet.getString("begin_time");
                String closingTime = resultSet.getString("end_time");

                StudentHomework studentHomework = new StudentHomework(homeworkId, title, createTime, closingTime);

                String sql_GetStatus = "SELECT * FROM javawebcourseresources.homework_status where hw_id=? and user_id=?";
                ResultSet statusSet = db_manager.executeQuery(sql_GetStatus, new String[]{homeworkId, student.getUsername()});

                if (statusSet.next()) {//存在保存/完成记录
                    HomeworkStatus homeworkStatus = HomeworkStatus.valueOf(statusSet.getString("status"));
                    studentHomework.setHomeworkStatus(homeworkStatus);
                } else {//未做无记录
                    studentHomework.setHomeworkStatus(HomeworkStatus.UNFINISHED);
                }

                homeworkList.add(studentHomework);
            }
            //获取已关闭
            String finishedSql = "SELECT * FROM javawebcourseresources.homework where is_delete=0 and now()>end_time ORDER BY end_time DESC";
            ResultSet finishedSet = db_manager.executeQuery(finishedSql, null);
            while (finishedSet.next()) {
                String homeworkId = finishedSet.getString("id");
                String title = finishedSet.getString("title")+"<div align=right>（已关闭）</div>";//显示已关闭
                String createTime = finishedSet.getString("begin_time");
                String closingTime = finishedSet.getString("end_time");
                StudentHomework studentHomework = new StudentHomework(homeworkId, title, createTime, closingTime);
                studentHomework.setHomeworkStatus(HomeworkStatus.FINISHED);//设为完成
                homeworkList.add(studentHomework);
            }

            return homeworkList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public int createTopic(InteractionTopic topic) {
        String ssql = "insert into javawebcourseresources.interactiontopic(" +
                "user_id,title,content,topic_type,is_deleted) " +
                "values(?,?,?,?,0)";
        String topicType = topic.getTopicType().toString();//字符转换
        //executeUpdate成功返回1
        return db_manager.executeUpdate(ssql, new String[]{topic.getUsername(), topic.getTitle(), topic.getContent(), topicType});
    }

    public String getName(String username) {
        String ssql = "select name from javawebcourseresources.users where user_id = ?";
        ResultSet rs = db_manager.executeQuery(ssql, new String[]{username});
        try {
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getAllTopic(ArrayList<InteractionTopic> Topics) {
        String ssql = "select * from javawebcourseresources.interactiontopic where is_deleted = 0 order by topic_id DESC";
        ResultSet rs = db_manager.executeQuery(ssql, null);
        try {
            while (rs.next()) {
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
        String ssql = "select * from javawebcourseresources.interactiontopic where  is_deleted=0 and topic_id=?";
        ResultSet rs = db_manager.executeQuery(ssql, new String[]{topicId});
        InteractionTopic topic = new InteractionTopic();
        try {
            while (rs.next()) {
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

   public boolean addTeachingEvaluation(TeachingEvaluation teachingEvaluation) {
      String ssql = "insert into javawebcourseresources.teaching_evaluation(user_id,star1,star2,star3,star4,evaluation_content,evaluate_date) value(?,?,?,?,?,?,now())";
      int rs = db_manager.executeUpdate(ssql, new String[]{teachingEvaluation.getUsername(),
              teachingEvaluation.getStar1()
              ,teachingEvaluation.getStar2()
              ,teachingEvaluation.getStar3()
              ,teachingEvaluation.getStar4()
              ,teachingEvaluation.getContent()});
      if (rs == 1) {
         return true;
      }
      return false;
   }

   public int createComment(TopicComments comment){
      String ssql = "insert into javawebcourseresources.topiccomments(" +
              "topic_id,user_id,content,is_deleted) " +
              "values(?,?,?,0)";
      return db_manager.executeUpdate(ssql,new String[]{comment.getTopicId(),comment.getUsername(),comment.getContent()});
   }

    public ArrayList<TopicComments> getAllComment(String topic_id) {
        String ssql = "select * from javawebcourseresources.topiccomments where is_deleted = 0 and topic_id=?";
        ArrayList<TopicComments> Comments = new ArrayList<>();
        ResultSet rs = db_manager.executeQuery(ssql, new String[]{topic_id});
        try {
            while (rs.next()) {
                TopicComments comment = new TopicComments();
                comment.setCommentId(rs.getString("id"));
                comment.setTopicId(rs.getString("topic_id"));
                comment.setUsername(rs.getString("user_id"));
                comment.setContent(rs.getString("content"));
                Timestamp date = rs.getTimestamp("comment_date");
                comment.setDate(date);
                Comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Comments;
    }

    public ArrayList<InteractionTopic> selectSearchTopic(String condition) {
        String likeCondition = "%" + condition + "%";
        String ssql = "select * from javawebcourseresources.interactiontopic" +
                " where is_deleted = 0 and (" +
                "title like ? or content like ? or " +
                "topic_type like ?) order by topic_id desc";
        ResultSet rs = db_manager.executeQuery(ssql, new String[]{likeCondition, likeCondition, likeCondition});
        ArrayList<InteractionTopic> Topics = new ArrayList<>();
        try {
            while (rs.next()) {
                InteractionTopic topic = new InteractionTopic();
                topic.setTopicId(rs.getString("topic_id"));
                topic.setUsername(rs.getString("user_id"));
                topic.setTitle(rs.getString("title"));
                topic.setContent(rs.getString("content"));
                topic.setTopicType(rs.getString("topic_type"));
                Timestamp date = rs.getTimestamp("create_date");
                topic.setDate(date);
                Topics.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Topics;
    }

    public int deleteTopic(String topicId) {
        String ssql = "update javawebcourseresources.interactiontopic set is_deleted=1 where topic_id=?";
        return db_manager.executeUpdate(ssql, new String[]{topicId});
    }

    public TopicComments getOneComment(String commentId) {
        String ssql = "select * from javawebcourseresources.topiccomments where  is_deleted=0 and id=?";
        ResultSet rs = db_manager.executeQuery(ssql, new String[]{commentId});
        TopicComments comment = new TopicComments();
        try {
            while (rs.next()) {
                comment.setCommentId(rs.getString("id"));
                comment.setUsername(rs.getString("user_id"));
                comment.setTopicId(rs.getString("topic_id"));
                comment.setContent(rs.getString("content"));
                Timestamp date = rs.getTimestamp("comment_date");
                comment.setDate(date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public int deleteComment(String commentId) {
        String ssql = "update javawebcourseresources.topiccomments set is_deleted=1 where id=?";
        return db_manager.executeUpdate(ssql, new String[]{commentId});
    }

    public int updateTopic(InteractionTopic topic) {
        String ssql = "update javawebcourseresources.interactiontopic set content=? where topic_id=?";
        return db_manager.executeUpdate(ssql, new String[]{topic.getContent(), topic.getTopicId()});
    }

    public String getChoiceAnswer(String choiceId) {//获取已保存选项
        String sql = "SELECT answer FROM answersheet_choice where question_id=? and user_id=?";
        ResultSet resultSet = db_manager.executeQuery(sql, new String[]{choiceId, student.getUsername()});
        return getAnswer(resultSet);
    }

    public String getCompletionAnswer(String completionId) {//获取已保存填空
        String sql = "SELECT answer FROM answersheet_completion where question_id=? and user_id=?";
        ResultSet resultSet = db_manager.executeQuery(sql, new String[]{completionId, student.getUsername()});
        return getAnswer(resultSet);
    }
    public String getOperationAnswer(String operationId){
        String sql="SELECT * FROM answersheet_operation where question_id=? and user_id=?";
        return getAnswer(db_manager.executeQuery(sql,new String[]{operationId,student.getUsername()}));
    }

    private String getAnswer(ResultSet resultSet) {
        try {
            if (resultSet.next()) {//已保存
                return resultSet.getString("answer");
            } else {//无记录
                return "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean commitHomework(HomeworkAnswerSheet homeworkAnswerSheet) {//保存、提交作业

        try {
            db_manager.beginAffair();//事务开始
            if (commitHomeworkStatus(homeworkAnswerSheet) && insertIntoHomeworkAnswer(homeworkAnswerSheet)) {//提交作业状态、放入答题卡
                db_manager.Commit();//提交
                return true;
            } else {
                db_manager.rollbackAffair();
                return false;
            }
        } catch (SQLException e) {
            try {
                db_manager.rollbackAffair();
            } catch (SQLException e1) {
                e1.printStackTrace();
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    private boolean isExistCommitRecord(String homeworkId, String userId) {
        String sql = "SELECT status FROM homework_status where hw_id=? and user_id=?";
        try {
            return db_manager.executeQuery(sql, new String[]{homeworkId, userId}).next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;//
        }
    }

    private boolean commitHomeworkStatus(HomeworkAnswerSheet homeworkAnswerSheet) {
        String status = String.valueOf(homeworkAnswerSheet.getHomeworkStatus());//状态
        String sql;
        //判断是否存在上传记录
        if (isExistCommitRecord(homeworkAnswerSheet.getHomeworkId(), homeworkAnswerSheet.getUserId())) {//update
            sql = "update homework_status set status=? where hw_id=? and user_id=?";
        } else {//insert
            sql = "insert into homework_status(status,hw_id,user_id) values(?,?,?)";
        }
        return db_manager.executeUpdate(sql, new String[]{
                status, homeworkAnswerSheet.getHomeworkId(), student.getUsername()}) == 1;
    }

    private boolean insertIntoHomeworkAnswer(HomeworkAnswerSheet homeworkAnswerSheet) {
        String userId= homeworkAnswerSheet.getUserId();
        //放入选择
        for (AnswerSheet choiceAnswerSheet : homeworkAnswerSheet.getChoiceAnswers()) {
            String insertChoiceAnswerSql;
            if(isExistAnswer("answersheet_choice", choiceAnswerSheet.getQuestionId(),userId)){
                insertChoiceAnswerSql="update answersheet_choice set answer=? where  question_id=? and user_id=? and hw_id=?";
            }else {
                insertChoiceAnswerSql = "insert into answersheet_choice(answer,question_id,user_id,hw_id) values(?,?,?,?)";
            }
            if (db_manager.executeUpdate(insertChoiceAnswerSql, new String[]{
                    choiceAnswerSheet.getAnswer(), choiceAnswerSheet.getQuestionId(),
                    userId, homeworkAnswerSheet.getHomeworkId()
            }) != 1) return false;
        }
        //填空
        for (AnswerSheet completionAnswerSheet : homeworkAnswerSheet.getCompletionAnswers()) {
            String insertCompleionAnswerSql;
            if(isExistAnswer("answersheet_completion", completionAnswerSheet.getQuestionId(),userId)){
                insertCompleionAnswerSql="update answersheet_completion set answer=? where question_id=? and user_id=? and hw_id=?";
            }else {
                insertCompleionAnswerSql = "insert into answersheet_completion(answer,question_id,user_id,hw_id) values(?,?,?,?)";
            }
            if (db_manager.executeUpdate(insertCompleionAnswerSql, new String[]{
                    completionAnswerSheet.getAnswer(), completionAnswerSheet.getQuestionId(),
                    userId, homeworkAnswerSheet.getHomeworkId()
            }) != 1) return false;
        }
        //操作题
        for(AnswerSheet operationAnswerSheet : homeworkAnswerSheet.getOperationAnswer()){
            String insertOperationSql;
            if(isExistAnswer("answersheet_operation", operationAnswerSheet.getQuestionId(),userId)){
                insertOperationSql="update answersheet_operation set answer=? where question_id=? and user_id=? and hw_id=?";
            }
            else {
                insertOperationSql="insert into answersheet_operation(answer,question_id,user_id,hw_id) values(?,?,?,?)";
            }
            if(db_manager.executeUpdate(insertOperationSql,new String[]{
                    operationAnswerSheet.getAnswer(), operationAnswerSheet.getQuestionId(),
                    userId, homeworkAnswerSheet.getHomeworkId()
            })!=1) return false;
        }
        return true;
    }
    private boolean isExistAnswer(String tabltName,String questionId,String userId){//提交作业检查记录
        String sql="SELECT answer FROM "+tabltName+" where question_id=? and user_id=?";
        try {
            return db_manager.executeQuery(sql,new String[]{questionId,userId}).next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoCorrectHomework(HomeworkAnswerSheet homeworkAnswerSheet) {//批改选择填空作业
        //从学a生nswersheet_XX与hw_question_XXX比较
        try {
            db_manager.beginAffair();//事务开始
//            String homeworkId=homeworkAnswerSheet.getHomeworkId();

            //批阅该学生选择题
            String getChoiceKeySql="SELECT refer_key,score FROM hw_question_choice where id=?";
            for (AnswerSheet choiceAnswer:homeworkAnswerSheet.getChoiceAnswers()){
                String[] result=getKey_Score(db_manager.executeQuery(getChoiceKeySql,new String[]{
                        choiceAnswer.getQuestionId()}));
                if(result==null){
                    db_manager.rollbackAffair();
                    return false;
                }
                double quetionScore=Double.parseDouble(result[1]);//题目分
                double getScore=choiceAnswer.getAnswer().equals(result[0])? quetionScore:0;//判断得分
                if(!giveMark("answersheet_choice",choiceAnswer.getQuestionId(),getScore)){//给分
                    db_manager.rollbackAffair();
                    return false;
                }
            }

            //填空每个空均分题分
            String getCompletionKeySql="SELECT refer_key,score FROM hw_question_completion where id=?";
            for (AnswerSheet completionAnswer:homeworkAnswerSheet.getCompletionAnswers()){
                String[] result=getKey_Score(db_manager.executeQuery(getCompletionKeySql,new String[]{
                    completionAnswer.getQuestionId()}));
                if(result==null){
                    db_manager.rollbackAffair();
                    return false;
                }
                String[] answerKey=result[0].split("#");//参考答案
                double perScore=Double.parseDouble(result[1])/(answerKey.length);//每个空的分
                String[] stuAnswer=completionAnswer.getAnswer().split("#");//学生答案
                int correctNum=0;//答对数
                for(int i=0;i<answerKey.length&&i<stuAnswer.length;i++){
                    if(stuAnswer[i].equals(answerKey[i]))
                        correctNum++;
                }
                double getScore=perScore*correctNum;//得分
                if(!giveMark("answersheet_completion",completionAnswer.getQuestionId(),getScore)){//给分
                    db_manager.rollbackAffair();
                    return false;
                }
            }

            db_manager.Commit();
            return true;
        } catch (SQLException e) {
            try {
                db_manager.rollbackAffair();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }
    private String[] getKey_Score(ResultSet resultSet){//获取参考答案&分数
        try {
            resultSet.next();
            return new String[]{
                    resultSet.getString("refer_key"),
                    resultSet.getString("score")
            };
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean giveMark(String tableName,String questionId,double score){
        String sql="update "+tableName+" set score=? where question_id=? and user_id=?";
        return db_manager.executeUpdate(sql, new String[]{
                Double.toString(score),
                questionId, student.getUsername()
        }) == 1;
    }
    public String getHomeworkTitle(String homeworkId){
        String sql="select title from homework where id=?";
        ResultSet resultSet=db_manager.executeQuery(sql,new String[]{homeworkId});
        try {
            if(resultSet.next()){
                return resultSet.getString("title");
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public HomeworkStatus getStudentHomeworkStatus(String homeworkId){
        String sql="select status from homework_status where user_id=? and hw_id=?";
        ResultSet resultSet=db_manager.executeQuery(sql,new String[]{student.getUsername(),homeworkId});
        try {
            if(resultSet.next()){
                return HomeworkStatus.valueOf(resultSet.getString("status"));
            }else {
                return HomeworkStatus.UNFINISHED;//无记录,未开始
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getChoiceAnswerScore(String choiceId){
        return getAnswerScore("answersheet_choice",choiceId);
    }
    public String getCompletionAnswerScore(String completionId){
        return getAnswerScore("answersheet_completion",completionId);
    }
    public String getOperationAnswerScore(String operationId){
        return getAnswerScore("answersheet_operation",operationId);
    }
    private String getAnswerScore(String tableName,String questionId){
        String sql="select score from "+tableName+" where user_id=? and question_id=?";
        ResultSet resultSet=db_manager.executeQuery(sql,new String[]{student.getUsername(),questionId});
        try {
            if(resultSet.next()){
                return resultSet.getString("score");
//                return resultSet.getDouble("score");
            }else {
                return null;//未批改
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getAggregateScore(String studentId,String homeworkId){//获取总分
        String sql="select score from homework_status where user_id=? and hw_id=?";
        ResultSet resultSet=new DB_Manager().executeQuery(sql,new String[]{
                studentId,homeworkId
        });
        try {
            if(resultSet.next()){
                String score= resultSet.getString("score");
                return score==null? "暂无得分":score;
            }else {
                return "暂无得分";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }


    }
}
