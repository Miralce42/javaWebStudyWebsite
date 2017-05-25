<%@ page import="beans.Users" %>
<%@ page import="WebDB.TeacherDAO" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/20
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String moduleString = "重新编辑作业";

        Users teacher = (Users) session.getAttribute("user");
        if (teacher == null || "STUDENT".equals(teacher.getUser_type())) {
            return;
        }
        TeacherDAO teacherDAO = new TeacherDAO(teacher);
        String homeworkId = request.getParameter("homeworkId");
        //后台管理页面标题,自行修改
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