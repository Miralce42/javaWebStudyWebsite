package WebDB;

import beans.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static beans.StudentHomework.HomeworkStatus.CORRECTED;

/**
 * Created by Vove on 2017/5/20.
 *
 */
public class TeacherDAO {
    private DB_Manager db_manager = new DB_Manager();
    private Users teacher;

    public TeacherDAO() {
    }


    public TeacherDAO(Users teacher) {
        this.teacher = teacher;
    }

    public ArrayList<StudentHomework> getHomeworkList() {//获取作业（未关闭-已关闭-已删除）
        //未结束作业
        String unfinishSql = "SELECT * FROM homework where is_delete=0 and now()<end_time and now()>begin_time ORDER BY end_time DESC ";

        ArrayList<StudentHomework> homeworkList = new ArrayList<>();
        ResultSet unfinishedSet = db_manager.executeQuery(unfinishSql, null);
        try {
            while (unfinishedSet != null && unfinishedSet.next()) {
                String id = unfinishedSet.getString("id");
                String title = unfinishedSet.getString("title");
                String createTime = unfinishedSet.getString("begin_time");
                String closingTime = unfinishedSet.getString("end_time");

                StudentHomework studentHomework = new StudentHomework(id, title, createTime, closingTime);
                studentHomework.setHomeworkStatus(StudentHomework.HomeworkStatus.UNCLOSED);//设置标志
                homeworkList.add(studentHomework);
            }
            //获取结束作业
            String finishSql = "SELECT * FROM homework where is_delete=0 and now()>end_time ORDER BY end_time DESC";
            ResultSet finishedSet = db_manager.executeQuery(finishSql, null);
            while (finishedSet.next()) {
                String id = finishedSet.getString("id");
                String title = finishedSet.getString("title");
                String createTime = finishedSet.getString("begin_time");
                String closingTime = finishedSet.getString("end_time");

                StudentHomework studentHomework = new StudentHomework(id, title, createTime, closingTime);
                studentHomework.setHomeworkStatus(StudentHomework.HomeworkStatus.CLOSED);//设置标志
                homeworkList.add(studentHomework);
            }
            //获取删除作业
            String deletedSql = "SELECT * FROM homework where is_delete=1 ORDER BY end_time DESC ";
            ResultSet deletedSet = db_manager.executeQuery(deletedSql, null);
            while (deletedSet != null && deletedSet.next()) {
                String id = deletedSet.getString("id");
                String title = deletedSet.getString("title");
                String createTime = deletedSet.getString("begin_time");
                String closingTime = deletedSet.getString("end_time");

                StudentHomework studentHomework = new StudentHomework(id, title, createTime, closingTime);
                studentHomework.setHomeworkStatus(StudentHomework.HomeworkStatus.DELETED);//设置标志
                homeworkList.add(studentHomework);
            }

            return homeworkList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //获取保存和完成状态的学生
    public ArrayList<HomeworkStudentStatus> getFinishedAndSavedStudentList(String homeworkId) {
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

    //获取未完成学生
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

            String sql = "insert into homework(title,begin_time,end_time,is_delete)" +
                    " values(?,?,?,0)";
            if (db_manager.executeUpdate(sql, new String[]{newHomework.getHomeworkTitle()
                    , newHomework.getBeginTime()
                    , newHomework.getEndTime()
            }) != 1) {//插入作业记录
                db_manager.rollbackAffair();
                return false;
            }
            String homeworkId = getHomeworkId();//根据刚插入数据获取id

            int questionIndex = 1;
            for (ChoiceHomework choiceHomework : newHomework.getChoiceHomeworkList()) {//插入选择题记录
                String insertChoiceSql = "insert into hw_question_choice(hw_id,question_index,question_content,refer_key,score) values(?,?,?,?,?)";
                if (db_manager.executeUpdate(insertChoiceSql, new String[]{
                        homeworkId,
                        Integer.toString(questionIndex++),//*
                        choiceHomework.getQuestion(),
                        choiceHomework.getRef_ky(),
                        choiceHomework.getScore()
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
            //插入填空记录
            for (CompletionHomework completionHomework : newHomework.getCompletionHomeworkList()) {
                String insertCompletionSql = "insert into hw_question_completion(hw_id,question_content,refer_key,score) values(?,?,?,?)";
                if (db_manager.executeUpdate(insertCompletionSql, new String[]{homeworkId,
                        completionHomework.getCompletionContent(),
                        completionHomework.getRefKey(),
                        completionHomework.getScore()}) != 1) {
                    db_manager.rollbackAffair();
                    return false;
                }
            }
            //插入操作题
            for (OperationHomework operationHomework : newHomework.getOperationHomeworkList()) {
                String insertOperationSql = "insert into hw_question_operation(hw_id,question_content,score) values(?,?,?)";
                if (db_manager.executeUpdate(insertOperationSql, new String[]{
                        homeworkId, operationHomework.getQuestionContent(),
                        operationHomework.getScore()}) != 1) {
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

    public boolean reeditHomework(Homework homework) {
        try {
            db_manager.beginAffair();
            //update作业状态
            String updateHomeworkSql = "update homework set title=?,begin_time=?,end_time=? where id=?";
            if (db_manager.executeUpdate(updateHomeworkSql, new String[]{
                    homework.getHomeworkTitle(),
                    homework.getBeginTime(),
                    homework.getEndTime(),
                    homework.getHomeworkId()}) != 1) {
                db_manager.rollbackAffair();
                return false;
            }
            //update选择题
            String updateChoiceSql = "update hw_question_choice set question_content=?,refer_key=?,score=? where id=?";
            for (ChoiceHomework choiceHomework : homework.getChoiceHomeworkList()) {
                if (db_manager.executeUpdate(updateChoiceSql, new String[]{
                        choiceHomework.getQuestion(),
                        choiceHomework.getRef_ky(),
                        choiceHomework.getScore(),
                        choiceHomework.getId()
                }) != 1) {
                    db_manager.rollbackAffair();
                    return false;
                }
                //update选项
                String updateOptionSql = "update choiceofquestion set content=? where choice_id=? and choice_key=?";
                if (db_manager.executeUpdate(updateOptionSql, new String[]{
                        choiceHomework.getChoice_A(), choiceHomework.getId(), "A"}) != 1 ||
                        db_manager.executeUpdate(updateOptionSql, new String[]{
                                choiceHomework.getChoice_B(), choiceHomework.getId(), "B"}) != 1 ||
                        db_manager.executeUpdate(updateOptionSql, new String[]{
                                choiceHomework.getChoice_C(), choiceHomework.getId(), "C"}) != 1 ||
                        db_manager.executeUpdate(updateOptionSql, new String[]{
                                choiceHomework.getChoice_D(), choiceHomework.getId(), "D"}) != 1) {
                    db_manager.rollbackAffair();
                    return false;
                }
            }
            //update填空
            String updateCompletionSql = "update hw_question_completion set question_content=?,refer_key=?,score=? where id=?";
            for (CompletionHomework completionHomework : homework.getCompletionHomeworkList()) {
                if (db_manager.executeUpdate(updateCompletionSql, new String[]{
                        completionHomework.getCompletionContent(),
                        completionHomework.getRefKey(),
                        completionHomework.getScore(),
                        completionHomework.getId()
                }) != 1) {
                    db_manager.rollbackAffair();
                    return false;
                }
            }
            //update操作题
            String updateOperationSql = "update hw_question_operation set question_content=?,score=? where id=?";
            for (OperationHomework operationHomework : homework.getOperationHomeworkList()) {
                if (db_manager.executeUpdate(updateOperationSql, new String[]{
                        operationHomework.getQuestionContent(),
                        operationHomework.getScore(),
                        operationHomework.getId()
                }) != 1) {
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
    public boolean reopenHomework(Homework homework) {
        //删除原作业，新建作业。
        String deleteSql = "delete from homework where id=?";
        //发布新作业

        return db_manager.executeUpdate(deleteSql, new String[]{homework.getHomeworkId()}) == 1 && publishHomework(homework);
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

    public Homework getHomeworkDetail(String homeworkId) {//获取作业详细
        Homework homework = new Homework();
        homework.setHomeworkId(homeworkId);
        ArrayList<ChoiceHomework> choiceHomeworkList = new ArrayList<>();
        ArrayList<CompletionHomework> completionHomeworkList = new ArrayList<>();
        ArrayList<OperationHomework> operationHomeworkList = new ArrayList<>();
        homework.setChoiceHomeworkList(choiceHomeworkList);
        homework.setCompletionHomeworkList(completionHomeworkList);
        homework.setOperationHomeworkList(operationHomeworkList);

        String homeworkSql = "SELECT title,begin_time,end_time FROM homework where id=?";
        ResultSet homeworkResultSet = db_manager.executeQuery(homeworkSql, new String[]{homeworkId});
        try {
            if (homeworkResultSet.next()) {
                homework.setHomeworkTitle(homeworkResultSet.getString("title"))
                        .setBeginTime(homeworkResultSet.getString("begin_time"))
                        .setEndTime(homeworkResultSet.getString("end_time"));
                String getChiocesSql = "SELECT * FROM javawebcourseresources.hw_question_choice where hw_id=? order by question_index";


                //获取选择题集
                ResultSet choiceSet = db_manager.executeQuery(getChiocesSql, new String[]{homeworkId});
                while (choiceSet.next()) {//先存放choice,防止结果集丢失
                    ChoiceHomework choiceHomework = new ChoiceHomework();
                    choiceHomework.setId(choiceSet.getString("id"))
                            .setQuestion(choiceSet.getString("question_content"))
                            .setRef_ky(choiceSet.getString("refer_key"))
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
                            completionSet.getString("refer_key"),
                            completionSet.getString("score"));
                    completionHomeworkList.add(completionHomework);
                }
                //获取操作集
                String getOperationSql = "SELECT * FROM hw_question_operation where hw_id=?";
                ResultSet operationSet = db_manager.executeQuery(getOperationSql, new String[]{homeworkId});
                while (operationSet.next()) {
                    OperationHomework operationHomework = new OperationHomework(
                            operationSet.getString("id"),
                            operationSet.getString("question_content"),
                            operationSet.getString("score")
                    );
                    operationHomeworkList.add(operationHomework);
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

    public double[] getAvgStar() {
        String ssql = "SELECT AVG(star1) s1,AVG(star2) s2,AVG(star3) s3,AVG(star4) s4 FROM teaching_evaluation";
        ResultSet resultSet = db_manager.executeQuery(ssql, null);
        try {
            resultSet.next();
            double[] b = new double[4];
            b[0] = resultSet.getDouble("s1");
            b[1] = resultSet.getDouble("s2");
            b[2] = resultSet.getDouble("s3");
            b[3] = resultSet.getDouble("s4");
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<TeachingEvaluation> getStudentEvaluation() {
        String sql = "SELECT name,major, evaluate_date,evaluation_content from teaching_evaluation,users where users.user_id=teaching_evaluation.user_id;";
        ArrayList<TeachingEvaluation> StudentEvaluationSaved = new ArrayList<TeachingEvaluation>();
        ResultSet resultSet = db_manager.executeQuery(sql, new String[]{});
        try {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String major = resultSet.getString("major");
                String evaluation_content = resultSet.getString("evaluation_content");
                String evaluate_date = resultSet.getString("evaluate_date");

                TeachingEvaluation teachingEvaluation = new TeachingEvaluation(name, evaluation_content, major, evaluate_date);
                StudentEvaluationSaved.add(teachingEvaluation);

            }
            return StudentEvaluationSaved;
        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }

    }
    public boolean updateStuHomeworkStatus(String userId, String homeworkId, StudentHomework.HomeworkStatus status){
        String updateStatusSql = "update homework_status set status=? where user_id=? and hw_id=?";
        return db_manager.executeUpdate(updateStatusSql, new String[]{String.valueOf(status), userId, homeworkId})==1;
    }

    public boolean correctOperation(String userId, String homeworkId, ArrayList<AnswerSheet> operations) {
        Users student = new Users();
        student.setUsername(userId);
        StudentDAO studentDAO = new StudentDAO(student);
        try {
            db_manager.beginAffair();
            if (!updateStuHomeworkStatus(userId,homeworkId,CORRECTED)) {
                db_manager.rollbackAffair();
                return false;
            }

            for (AnswerSheet answerSheet : operations) {
                if (!studentDAO.giveMark("answersheet_operation", answerSheet.getQuestionId(), answerSheet.getScore())) {
                    db_manager.rollbackAffair();
                    return false;
                }
            }
            calAggregateScore(userId,homeworkId);//更新总分

            db_manager.Commit();
            return true;
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

    public boolean deleteHomework(String homeworkId) {
        if (!verifyUser())//验证用户
            return false;
        String deleteSql = "UPDATE homework SET is_delete=1 WHERE id=?";
        return db_manager.executeUpdate(deleteSql, new String[]{homeworkId}) == 1;
    }

    private boolean verifyUser() {//验证用户
        String verifySql = "select count(*) from users where user_id=? and password=?";
        ResultSet resultSet = db_manager.executeQuery(verifySql, new String[]{
                teacher.getUsername(), teacher.getPassword()
        });
        try {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getStudentMajorName(String studentId) {
        String sql = "select major,name from users where user_id=?";
        ResultSet resultSet = db_manager.executeQuery(sql, new String[]{studentId});
        try {
            if (resultSet.next()) {
                String major = resultSet.getString("major");
                String name = resultSet.getString("name");
                return major + "&nbsp&nbsp&nbsp" + name;
            } else {
                return "获取信息失败";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "无法显示学生信息";
        }
    }

    public void calAggregateScore(String userId, String homeworkId) {//计算总分
        String sql = "update homework_status set score=(" +
                "ifnull((select sum(ifnull(score,0)) from answersheet_choice where hw_id=? and user_id=?),0)+" +
                "ifnull((select sum(ifnull(score,0)) from answersheet_completion where hw_id=? and user_id=?),0)+" +
                "ifnull((select sum(ifnull(score,0)) from answersheet_operation where hw_id=? and user_id=?),0)" +
                ")" +
                " where hw_id=? and user_id=?";
        db_manager.executeUpdate(sql, new String[]{
                homeworkId, userId,
                homeworkId, userId,
                homeworkId, userId,
                homeworkId, userId
        });
    }
}

