<%@ page import="beans.Users" %>
<%@ page import="WebDB.StudentDAO" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/16
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <%
      String homeworkId=request.getParameter("homeworkId");
      String homeworkTitle=request.getParameter("homeworkTitle");
      Users student=(Users)session.getAttribute("user");
      if(homeworkTitle==null){
          homeworkTitle=new StudentDAO().getHomeworkTitle(homeworkId);
      }
    %>
    <title><%=homeworkTitle%></title>
  </head>
  <body>
  <%@include file="aside.jsp"%><!--左侧布局-->
  <div id="fh5co-main">
   <!--右侧布局-->
  </div>
  </body>
</html>
