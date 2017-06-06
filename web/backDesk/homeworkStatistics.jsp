<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.StudentGradeStatics" %>
<%@ page import="beans.Users" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="cn.vove7.mydiv.GradeStaticsTable" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/6/1
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css"/>
   <link type="text/css" rel="stylesheet" href="css/homeworkStaticsStyle.css"/>
   <script type="text/javascript" src="ajax/ajax_func.js"></script>
   <%
      String moduleString = "作业统计";
   %>
   <%
      Users teacher = (Users) session.getAttribute("user");

      TeacherDAO teacherDAO = new TeacherDAO(teacher);
      ArrayList<StudentGradeStatics> studentGradeStaticsList = teacherDAO.staticsStudentGradeList(null);
   %>
   <title><%=moduleString%>
   </title>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
   <!--右侧布局-->
   <div class="container-field">
      <form class="navbar-form">
         <div class="input-group">
            <input type="text" value="" class="form-control" id="condition" placeholder="模糊查询">
            <span class="input-group-btn">
               <button type="button" class="btn btn-primary" onclick="sendRequest()">查询</button>
            </span>
         </div>
      </form>
      <div class="panel">
         <div class="panel-heading">
            <h3 class="panel-title">学生成绩统计</h3>
         </div>
         <div class="panel-body">
            <table class="table">
               <thead>
               <tr>
                  <th>#</th>
                  <th>学号</th>
                  <th>姓名</th>
                  <th>专业班级</th>
                  <th>作业平均分</th>
               </tr>
               </thead>
               <tbody id="tbody">
               <%=GradeStaticsTable.getTableContent(studentGradeStaticsList)%>
               </tbody>
            </table>
         </div>
      </div>
   </div>
</div>
</body>
</html>