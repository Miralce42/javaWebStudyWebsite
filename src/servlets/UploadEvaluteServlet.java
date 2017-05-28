package servlets;

import WebDB.StudentDAO;
import beans.TeachingEvaluation;
import beans.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mac on 2017/5/26.
 */
public class UploadEvaluteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users student=(Users) request.getSession().getAttribute("user");
        if (student == null || !student.getUser_type().equals("STUDENT"))
            return;
        String star1=request.getParameter("text1");
        String star2=request.getParameter("text2");
        String star3=request.getParameter("text3");
        String star4=request.getParameter("text4");
        String content=request.getParameter("content");
        TeachingEvaluation teachingEvaluation=new TeachingEvaluation(student.getUsername(),star1,star2,star3,star4,content);
        StudentDAO studentDAO=new StudentDAO();
        String pageTitle;
        String pageContent;
        HttpSession session=request.getSession();
        if(studentDAO.addTeachingEvaluation(teachingEvaluation)){
            pageTitle="评论成功！";
            pageContent="评论成功,<a href=frontDesk/evaluate.jsp>返回课程评价</a>";
        }else{
            pageTitle="评论失败";
            pageContent="评论失败,<a href=frontDesk/evaluate.jsp>返回课程评价</a>";
        }
        session.setAttribute("title",pageTitle);
        session.setAttribute("pageContent",pageContent);
        response.sendRedirect("../executeMessage.jsp");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("evaluate.jsp");
    }
}
