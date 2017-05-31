<%@ page import="beans.Users" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.HomeworkStudentStatus" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="static beans.StudentHomework.HomeworkStatus.FINISHED" %>
<%@ page import="static beans.StudentHomework.HomeworkStatus.SAVED" %>
<%@ page import="WebDB.StudentDAO" %>
<%@ page import="cn.vove7.mydiv.StudentHomeworkListDiv" %>
<%@ page import="static beans.StudentHomework.HomeworkStatus.UNFINISHED" %>
<%@ page import="static beans.StudentHomework.HomeworkStatus.*" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/20
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <link type="text/css" rel="stylesheet" href="../myCss/homeworkDivStyle.css">
   <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css">

   <%
      Users teacher = (Users) session.getAttribute("user");
      if (teacher == null || !teacher.getUser_type().equals("TEACHER")) {
         return;
      }
      TeacherDAO teacherDAO = new TeacherDAO(teacher);

   %>
   <%
      String homeworkId = request.getParameter("homeworkId");
      String homeworkTitle = new StudentDAO().getHomeworkTitle(homeworkId);
   %>
   <title>学生作业详情</title>
   <%
      String moduleString = "学生作业详情--" + homeworkTitle;
   %>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->

<div id="fh5co-main" class="right">
   <!--右侧布局-->
   <p>已提交同学</p>
   <hr class="blue">
   <%
      ArrayList<HomeworkStudentStatus> homeworkFinishedAndSavedStudentList = teacherDAO.getFinishedAndSavedStudentList(homeworkId);
      String finishedStu = StudentHomeworkListDiv.getDivValue(homeworkFinishedAndSavedStudentList, FINISHED, false);
      out.print(finishedStu);
   %>

   <p>暂时保存同学</p>
   <hr class="blue">
   <%
      String savedStu = StudentHomeworkListDiv.getDivValue(homeworkFinishedAndSavedStudentList, SAVED, false);
      out.print(savedStu);
   %>

   <p>已批阅</p>
   <hr class="blue">
   <%
      String correctedStu = StudentHomeworkListDiv.getDivValue(homeworkFinishedAndSavedStudentList, CORRECTED, false);
      out.print(correctedStu);
   %>

   <p>未完成同学</p>
   <hr class="blue">
   <%
      ArrayList<HomeworkStudentStatus> homeworkUnfinishedStudentList = teacherDAO.getHomeworkUnfinishedStudentList(homeworkId);
      String unfinishedStu = StudentHomeworkListDiv.getDivValue(homeworkUnfinishedStudentList, UNFINISHED, true);
      out.println(unfinishedStu);
   %>

</div>
</body>
</html>