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
 */
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = ((HttpServletRequest) req).getSession();

        String contextPath=request.getContextPath();//工程
        String uri = request.getRequestURI();//当前uri

        String[] noFilterPage = new String[]{//防过滤页面
                "/login.jsp",

                "/login.action"
        };
        for (String page : noFilterPage) {
            if (uri.equals(contextPath+page)) {
                chain.doFilter(req, resp);
                return;
            }
        }

        System.out.println("Login Filter:" + uri);

        if (session.getAttribute("user") == null) {
            response.sendRedirect(contextPath+"/login.jsp");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
