package cn.vove7.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Users;

/**
 * Created by Vove on 2017/4/13.
 *
 * 登陆过滤器
 */
public class LoginFilter implements Filter {
   public void destroy() {
   }

   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) resp;
      HttpSession session = ((HttpServletRequest) req).getSession();
      String contextPath = request.getContextPath();//工程
      String uri = request.getRequestURI();//当前uri
      System.out.println("Login Filter:" + uri);
      Users user = (Users) session.getAttribute("user");

      boolean isFilter = false;
      String[] filterPage = new String[]{//需要过滤未登录页面，学生
              "/frontDesk/studentHomework.jsp",
              "/frontDesk/evaluate.jsp",
              "/frontDesk/doHomework.jsp",
              "/frontDesk/topicDetail.jsp",
              "/frontDesk/createTopic.jsp",
              "/frontDesk/myInfo.jsp",
              "/frontDesk/browserHomework.jsp"
      };
      String[] filterStudentPage = new String[]{//后台过滤页面,限制教师访问
              "/backDesk/homeworkManager.jsp",
              "/backDesk/studentsHomeworkList.jsp",
              "/backDesk/publishHomework.jsp",
              "/backDesk/Re-editHomework.jsp"
      };

      for (String page : filterPage) {
         if (uri.equals(contextPath + page)) {
            isFilter = true;
            break;
         }
      }
      if (!isFilter) {
         for (String page : filterStudentPage) {//判断后台页面登陆
            if (uri.equals(contextPath + page)) {
               isFilter = true;
               break;
            }
         }
      }
      if (!isFilter) {
         chain.doFilter(req, resp);
         return;
      }
      //判断过滤
      if (user == null) {
         System.out.println("登陆过滤");
         response.sendRedirect(contextPath + "/login.jsp");
         return;
      }
      //已登录

      boolean isTeaFilter = false;
      for (String page : filterStudentPage) {
         if (uri.equals(contextPath + page)) {
            isTeaFilter = true;
            break;
         }
      }
      if (!isTeaFilter) {
         chain.doFilter(req, resp);
         return;
      }

      if (!"TEACHER".equals(user.getUser_type())) {
         System.out.println("教师过滤");
         response.sendRedirect(contextPath + "/frontDesk/index.jsp");//学生主页
      } else {
         chain.doFilter(req, resp);//进入访问页面
      }
   }

   public void init(FilterConfig config) throws ServletException {

   }

}
