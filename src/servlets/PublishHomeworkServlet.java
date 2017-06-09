package servlets;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WebDB.TeacherDAO;
import beans.*;

/**
 * Created by Vove on 2017/5/22.
 * 教师发布、重编辑作业
 */
public class PublishHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users teacher = (Users) request.getSession().getAttribute("user");
        TeacherDAO teacherDAO = new TeacherDAO(teacher);
        HomeworkReleaseWay releaseWay = HomeworkReleaseWay.valueOf(request.getParameter("action")
                .toUpperCase());//发布方式

        //获取作业信息
        ArrayList<ChoiceHomework> choiceHomeworkList = new ArrayList<>();
        ArrayList<CompletionHomework> completionHomeworkList = new ArrayList<>();
        ArrayList<OperationHomework> operationHomeworkList = new ArrayList<>();

        String homeworkTitle = request.getParameter("homeworkTitle");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");

        //获取选择题
        String choiceTitle;
        for (int i = 1; (choiceTitle = request.getParameter("choice-title_" + i)) != null; i++) {
            String choice_A = request.getParameter("choice_" + i + "_A");
            String choice_B = request.getParameter("choice_" + i + "_B");
            String choice_C = request.getParameter("choice_" + i + "_C");
            String choice_D = request.getParameter("choice_" + i + "_D");
            String ref_key = request.getParameter("ref_key_" + i);
            String score = request.getParameter("score_" + i);

            ChoiceHomework choiceHomework = new ChoiceHomework(choiceTitle, choice_A,
                    choice_B, choice_C, choice_D, ref_key, score);
            choiceHomeworkList.add(choiceHomework);
        }

        //获取填空题
        String completionContent;
        for (int i = 1; (completionContent = request.getParameter("question_content_" + i)) != null; i++) {
            CompletionHomework completionHomework = new CompletionHomework(
                    null, completionContent,
                    request.getParameter("completion_refKey_" + i),
                    request.getParameter("comp_score_" + i));
            completionHomeworkList.add(completionHomework);
        }
        //获取操作题
        String operationContent;
        for (int i = 1; (operationContent = request.getParameter("operation_content_" + i)) != null; i++) {
            OperationHomework operationHomework = new OperationHomework(operationContent,
                    request.getParameter("operation_score_" + i));
            operationHomeworkList.add(operationHomework);
        }
        Homework homework = new Homework(homeworkTitle, beginTime, endTime,
                choiceHomeworkList, completionHomeworkList, operationHomeworkList);

        String pageTitle="";//执行消息页面title
        String pageContent="";
        HttpSession session = request.getSession();

        switch (releaseWay){//重编辑，重开放,获取id
            case REOPEN:
            case REEDITING:{
                homework.setHomeworkId(request.getParameter("homeworkId"));
                int i = 1;
                //设置选择id
                for (ChoiceHomework choiceHomework : choiceHomeworkList) {
                    String choiceId = request.getParameter("choice_id_" + (i++));
                    choiceHomework.setId(choiceId);
                }
                i = 1;
                //设置填空id
                for (CompletionHomework completionHomework : completionHomeworkList) {
                    String completionId = request.getParameter("completion_id_" + (i++));
                    completionHomework.setId(completionId);
                }
                //设置操作id
                i = 1;//
                for (OperationHomework operationHomework : operationHomeworkList) {
                    String operationId = request.getParameter("operation_id_" + i);
                    operationHomework.setId(operationId);
                }
            }break;
        }
        switch (releaseWay) {//重编辑作业，获取id信息
            case REEDITING: {
                if (teacherDAO.reeditHomework(homework)) {//update
                    pageTitle = "编辑成功";
                } else {
                    pageTitle = "编辑失败";
                }
            }break;
            case FIRST_PUBLISH: {//发布新作业
                if (teacherDAO.publishHomework(homework)) {
                    pageTitle = "发布成功";
                } else {
                    pageTitle = "发布失败";
                }
            }break;
            case REOPEN:{//打开删除作业
                if(teacherDAO.reopenHomework(homework)){
                    pageTitle="开启成功";
                }else {
                    pageTitle="开启失败";
                }
            }break;
            default:
                break;
        }
        pageContent = pageTitle+",<a href=backDesk/homeworkManager.jsp>返回作业管理</a>";
        session.setAttribute("title", pageTitle);
        session.setAttribute("pageContent", pageContent);
        response.sendRedirect("../executeMessage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("homeworkManager.jsp");
    }

    private enum HomeworkReleaseWay {
        FIRST_PUBLISH,//初次发布
        REOPEN,//删除后重发布
        REEDITING//重编辑
    }
}