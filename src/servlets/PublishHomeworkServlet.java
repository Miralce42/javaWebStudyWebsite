package servlets;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import WebDB.TeacherDAO;
import beans.ChoiceHomework;
import beans.CompletionHomework;
import beans.Homework;
import beans.Users;

/**
 * Created by Vove on 2017/5/22.
 *
 */
public class PublishHomeworkServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Users teacher = (Users) request.getSession().getAttribute("user");
      TeacherDAO teacherDAO = new TeacherDAO(teacher);

      ArrayList<ChoiceHomework> choiceHomeworkList = new ArrayList<>();
      ArrayList<CompletionHomework> completionHomeworkList = new ArrayList<>();

      String homeworkTitle=request.getParameter("homeworkTitle");
      String beginTime=request.getParameter("beginTime");
      String endTime=request.getParameter("endTime");

      //获取选择题
      String choiceTitle;
      for (int i = 1; (choiceTitle = request.getParameter("choice-title_" + i)) != null; i++) {
         String choice_A = request.getParameter("choice_" + i + "_A");
         String choice_B = request.getParameter("choice_" + i + "_B");
         String choice_C = request.getParameter("choice_" + i + "_C");
         String choice_D = request.getParameter("choice_" + i + "_D");
         String ref_key = request.getParameter("ref_key_" + i);
         String score= request.getParameter("score_" + i);

         ChoiceHomework choiceHomework = new ChoiceHomework(choiceTitle, choice_A, choice_B, choice_C, choice_D, ref_key,score);
         choiceHomeworkList.add(choiceHomework);
      }

      //获取填空题
      String completionContent;
      for(int i=1;(completionContent=request.getParameter("question_content_"+i))!=null;i++){
         CompletionHomework completionHomework=new CompletionHomework(completionContent,request.getParameter("comp_score_"+i));
         completionHomeworkList.add(completionHomework);
      }

      Homework newHomework=new Homework(homeworkTitle,beginTime,endTime,choiceHomeworkList,completionHomeworkList);

      String pageTitle;//执行消息页面title
      String pageContent;
      HttpSession session= request.getSession();
      if(teacherDAO.publishHomework(newHomework)){
         pageTitle="发布成功";

         pageContent = "发布成功,<a href=backDesk/homeworkManager.jsp>返回作业管理</a>";
      }else {
         pageTitle="发布失败";
         pageContent= "发布失败,<a href=backDesk/homeworkManager.jsp>返回作业管理</a>";
      }
      session.setAttribute("title",pageTitle);
      session.setAttribute("pageContent",pageContent);
      response.sendRedirect("../executeMessage.jsp");
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.sendRedirect("homeworkManager.jsp");
   }
}
