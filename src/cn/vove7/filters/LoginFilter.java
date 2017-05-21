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

/**
 * Created by Vove on 2017/4/13.
 * <p>
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

      String[] filterPage = new String[]{//过滤页面
              "/frontDesk/studentHomework.jsp",
              "/backDesk/homeworkManager.jsp"
      };
      boolean isFilter=false;
      for (String page : filterPage) {
         if (uri.equals(contextPath + page)) {
            isFilter=true;
            break;
         }
      }
      if(!isFilter){
         chain.doFilter(req, resp);
         return;
      }

      if (session.getAttribute("user") == null) {
         System.out.println("过滤");
         response.sendRedirect(contextPath + "/login.jsp");
      }
      chain.doFilter(req, resp);
   }

   public void init(FilterConfig config) throws ServletException {

   }

}
