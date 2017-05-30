package cn.vove7.filters;

import WebDB.StudentDAO;
import beans.StudentHomework;
import beans.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vove on 2017/5/28.
 * 学生做作业过滤
 * 已完成不可再答
 */
public class DohHomeworkFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        System.out.println("过滤doHomework");
//        System.out.println(req.getParameter("homeworkId"));
        HttpSession session = ((HttpServletRequest) req).getSession();
        String homeworkId = req.getParameter("homeworkId");
        if (homeworkId != null) {
            Users student = (Users) session.getAttribute("user");
            StudentHomework.HomeworkStatus homeworkStatus = new StudentDAO(student).getStudentHomeworkStatus(homeworkId);
            if (homeworkStatus != null &
                    homeworkStatus != StudentHomework.HomeworkStatus.FINISHED &&
                    homeworkStatus != StudentHomework.HomeworkStatus.CORRECTED){//未完成，放通
                chain.doFilter(req, resp);
                return;
            }
        }

        session.setAttribute("title", "出错");
        String pageContent = "无法访问此资源,返回<a href=frontDesk/studentHomework.jsp>作业列表</a>";
        session.setAttribute("pageContent", pageContent);
        ((HttpServletResponse) resp).sendRedirect("../executeMessage.jsp");

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
