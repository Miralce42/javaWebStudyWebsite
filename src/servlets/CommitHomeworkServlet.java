package servlets;

import WebDB.StudentDAO;
import beans.Answer;
import beans.HomeworkAnswer;
import beans.StudentHomework;
import beans.StudentHomework.HomeworkStatus;
import beans.Users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Vove on 2017/5/25.
 * 提交作业
 */
public class CommitHomeworkServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Users student=(Users)request.getSession().getAttribute("user");
      String action=request.getParameter("action");
      String homeworkId=request.getParameter("homeworkId");

      HomeworkStatus homeworkStatus=action.equals("save")? HomeworkStatus.SAVED: HomeworkStatus.FINISHED;
      ArrayList<Answer> choiceAnswers=new ArrayList<>();
      ArrayList<Answer> completionAnswers=new ArrayList<>();
      String choiceId;
      //获取选择
      for (int i = 1; (choiceId = request.getParameter("choiceId_" + i)) != null; i++) {
         Answer choiceAnswer=new Answer(choiceId,
                 request.getParameter("choice_"+i));
         choiceAnswers.add(choiceAnswer);
      }
      //获取填空
      String completionId;
      for (int i = 1; (completionId = request.getParameter("completionId_" + i)) != null; i++) {
         Answer completionAnswer=new Answer(completionId,
                 request.getParameter("completion_answer_"+i));
         completionAnswers.add(completionAnswer);
      }
      HomeworkAnswer homeworkAnswer=new HomeworkAnswer(student.getUsername()
              ,homeworkId,homeworkStatus,
              choiceAnswers,completionAnswers);

      StudentDAO studentDAO=new StudentDAO(student);

      String pageTitle;//执行消息页面title
      String pageContent;
      HttpSession session= request.getSession();
      if(studentDAO.commitHomework(homeworkAnswer)){
         if(homeworkStatus==HomeworkStatus.FINISHED){
            pageTitle="提交成功";
            pageContent = pageTitle+",<a href=frontDesk/browserHomework.jsp?homeworkId="+homeworkId+">查看作业</a>";
         }else {
            pageTitle="保存成功";
            pageContent = pageTitle+",<a href=frontDesk/doHomework.jsp?homeworkId="+homeworkId+"&homeworkTitle=作业>继续编辑</a>";
         }
      }else {
         if(homeworkStatus==HomeworkStatus.FINISHED){
            pageTitle="提交失败";
            pageContent = pageTitle+",请联系管理人员</a>";
         }else {
            pageTitle="保存失败";
            pageContent = pageTitle+",请联系管理人员";
         }
      }
      session.setAttribute("title",pageTitle);
      session.setAttribute("pageContent",pageContent);
      response.sendRedirect("../executeMessage.jsp");
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.sendRedirect("../frontDesk/index.jsp");
   }
}
