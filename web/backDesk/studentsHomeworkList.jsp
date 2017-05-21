<%@ page import="beans.Users" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/20
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <%
        String homeworkId=request.getParameter("homeworkId");
    %>
    <%
        String moduleString="学生作业详情";
    %>

</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<%
    Users teacher=(Users)session.getAttribute("user");
    if(teacher!=null&&!teacher.getUser_type().equals("TEACHER")){
       return;
    }


%>
<div id="fh5co-main" class="right">
    <!--右侧布局-->
    <p>已完成同学</p>
    <hr>

    <p>未完成同学</p>
    <hr>
</div>
</body>
</html>