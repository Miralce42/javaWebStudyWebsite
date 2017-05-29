<%@ page import="beans.Users" %>
<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.Homework" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="cn.vove7.mydiv.BrowserHomeworkDiv" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/16
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/doHomeworkStyle.css"/>
   <style type="text/css">
      .is-correct {
         margin-right: 10%;
         margin-bottom: 80px;
      }
      .operation-answer{
         padding:10px;
      }
   </style>
   <%
      String homeworkId = request.getParameter("homeworkId");
      String homeworkTitle;
      Users student = (Users) session.getAttribute("user");

      homeworkTitle = new StudentDAO().getHomeworkTitle(homeworkId);

      Homework thisHomework = new TeacherDAO(student).getHomeworkDetail(homeworkId);
      BrowserHomeworkDiv browserHomeworkDiv = new BrowserHomeworkDiv(student, thisHomework);
   %>
   <title><%=homeworkTitle%>
   </title>
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main">
   <!--右侧布局-->
   <div class="container-field">
      <div align="center">
         <h2><%=homeworkTitle%>
         </h2>
      </div>
      <%
         if (thisHomework.getChoiceHomeworkList().size() > 0) {
            out.println("<h4>选择题</h4>");
         }
      %>
      <div id="choicesField">
         <%=browserHomeworkDiv.getChoiceDetailDiv()%>

      </div>
      <%
         if (thisHomework.getCompletionHomeworkList().size() > 0) {
            out.println("<h4>填空题</h4>");
         }
      %>
      <div class="completionsField">
         <%=browserHomeworkDiv.getCompletionDetailDiv()%>
      </div>
      <%
         if (thisHomework.getOperationHomeworkList().size() > 0) {
            out.println("<h4>操作题</h4>");
         }
      %>
      <div class="operationsField">
         <%=browserHomeworkDiv.getOperationDetailDiv()%>

      </div>

   </div>
</body>
</html>
