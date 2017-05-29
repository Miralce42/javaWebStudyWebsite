package servlets;

import WebDB.TeacherDAO;
import beans.AnswerSheet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Vove on 2017/5/29.
 * 批改操作题
 */

public class CorrectHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String homeworkId=request.getParameter("homeworkId");
        String studentId=request.getParameter("studentId");

        ArrayList<AnswerSheet> operations=new ArrayList<>();//存放批改分数
        String questionId;
        for(int i=1;(questionId=request.getParameter("operation_id_"+i))!=null;i++){
            String score=request.getParameter("operation_score_"+questionId);
            AnswerSheet answerSheet=new AnswerSheet(questionId,Double.parseDouble(score));
            operations.add(answerSheet);
        }

        HttpSession session= request.getSession();
        String pageTitle;//执行消息页面title
        String pageContent;
        if(new TeacherDAO().correctOperation(studentId,homeworkId,operations)){
            pageTitle="批改完成";
            pageContent = pageTitle+",<a href=backDesk/studentsHomeworkList.jsp?homeworkId="+homeworkId+">返回学生作业列表</a>";
        }else {
            pageTitle="批改失败";
            pageContent = pageTitle+",<a href=backDesk/studentsHomeworkList.jsp?homeworkId="+homeworkId+">返回学生作业列表</a>";
        }
        session.setAttribute("title",pageTitle);
        session.setAttribute("pageContent",pageContent);
        response.sendRedirect("../executeMessage.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
