package servlets;

import WebDB.StudentDAO;
import WebDB.TeacherDAO;
import beans.AnswerSheet;
import beans.HomeworkAnswerSheet;
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
 * 学生提交作业
 */
public class CommitHomeworkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users student = (Users) request.getSession().getAttribute("user");
        String action = request.getParameter("action");
        String homeworkId = request.getParameter("homeworkId");
        //提交设为FINISHED状态，保存SAVED状态
        HomeworkStatus homeworkStatus = action.equals("save") ? HomeworkStatus.SAVED : HomeworkStatus.FINISHED;

        ArrayList<AnswerSheet> choiceAnswerSheets = new ArrayList<>();
        ArrayList<AnswerSheet> completionAnswerSheets = new ArrayList<>();
        ArrayList<AnswerSheet> operationAnswerSheets = new ArrayList<>();
        String choiceId;
        //获取选择
        for (int i = 1; (choiceId = request.getParameter("choiceId_" + i)) != null; i++) {
            AnswerSheet choiceAnswerSheet = new AnswerSheet(choiceId,
                    request.getParameter("choice_" + i));
            choiceAnswerSheets.add(choiceAnswerSheet);
        }
        //获取填空
        String completionId;
        for (int i = 1; (completionId = request.getParameter("completionId_" + i)) != null; i++) {
            AnswerSheet completionAnswerSheet = new AnswerSheet(completionId,
                    request.getParameter("completion_answer_" + i));
            completionAnswerSheets.add(completionAnswerSheet);
        }
        String operationId;
        for (int i = 1; (operationId = request.getParameter("operation_id_" + i)) != null; i++) {
            String operationContent = request.getParameter("operation_content_" + i);
            operationAnswerSheets.add(new AnswerSheet(operationId, operationContent));
        }
        HomeworkAnswerSheet homeworkAnswerSheet = new HomeworkAnswerSheet(student.getUsername(),
                homeworkId, homeworkStatus,
                choiceAnswerSheets, completionAnswerSheets,
                operationAnswerSheets
        );

        StudentDAO studentDAO = new StudentDAO(student);

        String pageTitle;//执行消息页面title
        String pageContent;
        HttpSession session = request.getSession();
        if (studentDAO.commitHomework(homeworkAnswerSheet)) {

            // 保存提交成功
            studentDAO.autoCorrectHomework(homeworkAnswerSheet);//自动批改 （保存&提交）

            new TeacherDAO().calAggregateScore(student.getUsername(),homeworkId);//计算总分

            if (homeworkStatus == HomeworkStatus.FINISHED) {
                pageTitle = "提交成功";
                pageContent = pageTitle + ",<a href=frontDesk/browserHomework.jsp?homeworkId=" + homeworkId + ">查看作业</a>";
            } else {
                pageTitle = "保存成功";
                pageContent = pageTitle + ",<a href=frontDesk/doHomework.jsp?homeworkId=" + homeworkId + "&homeworkTitle=作业>继续编辑</a>";
            }
        } else {
            if (homeworkStatus == HomeworkStatus.FINISHED) {
                pageTitle = "提交失败";
                pageContent = pageTitle + ",请联系管理人员</a>";
            } else {
                pageTitle = "保存失败";
                pageContent = pageTitle + ",请联系管理人员";
            }
        }
        session.setAttribute("title", pageTitle);
        session.setAttribute("pageContent", pageContent);
        response.sendRedirect("../executeMessage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("../frontDesk/index.jsp");
    }
}
