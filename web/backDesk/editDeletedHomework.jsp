<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="WebDB.StudentDAO" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/30
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <%
      //后台管理页面标题,自行修改
      String moduleString="编辑作业";
      String homeworkId=request.getParameter("homeworkId");
      out.println(new StudentDAO().getHomeworkTitle(homeworkId));
   %>
   <title><%=moduleString%></title>

</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
   <!--右侧布局-->





</div>
</body>
</html>