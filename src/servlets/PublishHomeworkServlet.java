package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebDB.TeacherDAO;
import beans.ChoiceHomework;
import beans.Users;

/**
 * Created by Vove on 2017/5/22.
 */
public class PublishHomeworkServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Users teacher = (Users) request.getSession().getAttribute("user");
      if (teacher == null || teacher.getUser_type().equals("TEACHER"))
         return;
      TeacherDAO teacherDAO = new TeacherDAO(teacher);

      ArrayList<ChoiceHomework> choiceHomeworkList = new ArrayList<>();
      ArrayList<ChoiceHomework> completionHomeworkList = new ArrayList<>();

      String homeworkTitle;
      for (int i = 1; (homeworkTitle = request.getParameter("choice-title_" + i)) != null; i++) {
         String choice_A = request.getParameter("choice_" + i + "_A");
         String choice_B = request.getParameter("choice_" + i + "_B");
         String choice_C = request.getParameter("choice_" + i + "_C");
         String choice_D = request.getParameter("choice_" + i + "_D");
         char ref_key = request.getParameter("ref_key_" + i).charAt(0);
         ChoiceHomework choiceHomework = new ChoiceHomework(homeworkTitle, choice_A, choice_B, choice_C, choice_D, ref_key);
         choiceHomeworkList.add(choiceHomework);

      }


      teacherDAO.publishHomework(choiceHomeworkList, completionHomeworkList);


   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.print("doget");
   }
}
