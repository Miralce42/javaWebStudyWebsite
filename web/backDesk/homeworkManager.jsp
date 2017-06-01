<%@ page import="beans.Users" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.StudentHomework" %>
<%@ page import="cn.vove7.mydiv.ManagerHomeworkDiv" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/20
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <title>作业管理</title>
   <link type="text/css" rel="stylesheet" href="../myCss/homeworkDivStyle.css">
   <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css">
   <%
      String moduleString = "作业管理";
   %>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
   <!--右侧布局-->
   <div class="float-right">
      <a class="floatButton" href="homeworkStatistics.jsp">作业统计</a>
      <a class="floatButton" href="publishHomework.jsp">发布新作业</a>
   </div>
   <div style="margin-top: 50px">
      <%
         Users teacher = (Users) session.getAttribute("user");
         TeacherDAO teacherDAO = new TeacherDAO(teacher);

         //获取所有作业
         ArrayList<StudentHomework> homeworkList = teacherDAO.getHomeworkList();

         StringBuilder unclosedBuilder = new StringBuilder();//未关闭
         StringBuilder closedBuilder = new StringBuilder();//已关闭
         StringBuilder deletedBuilder = new StringBuilder();//已删除
         //展示未关闭作业

         ManagerHomeworkDiv.buildTeaBumps(homeworkList, unclosedBuilder, closedBuilder,deletedBuilder);

      %>

      <p>未结束</p>
      <hr class="blue">
      <%=unclosedBuilder.toString()%>


      <p>已结束</p>
      <hr class="blue">
      <%=closedBuilder.toString()%>

      <p>回收站</p>
      <hr class="blue">
      <%=deletedBuilder.toString()%>


   </div>

</div>
</body>
</html>