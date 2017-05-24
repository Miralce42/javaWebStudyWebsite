package cn.vove7.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vove on 2017/5/20.
 *
 * 编码过滤器
 */
public class EncodingFilter implements Filter {
   public void destroy() {
   }

   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
      resp.setContentType("text/html;charset=utf-8");
      req.setCharacterEncoding("utf-8");
      chain.doFilter(req,resp);
   }

   public void init(FilterConfig config) throws ServletException {

   }

}
