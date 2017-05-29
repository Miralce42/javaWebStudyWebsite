<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.Homework" %>
<%@ page import="beans.Users" %>
<%@ page import="beans.StudentHomework" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="cn.vove7.mydiv.BrowserHomeworkDiv" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/20
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/doHomeworkStyle.css"/>
   <script type="text/javascript" src="js/correctHomework.js"></script>
   <title>批改作业</title>
   <%
      String homeworkId=request.getParameter("homeworkId");
      String studentId=request.getParameter("studentId");
      String moduleString = "批改作业";
      Users student=new Users();
      student.setUsername(studentId);
      String homeworkTitle=new StudentDAO().getHomeworkTitle(homeworkId);

      Homework thisHomework = new TeacherDAO().getHomeworkDetail(homeworkId);
      BrowserHomeworkDiv browserHomeworkDiv = new BrowserHomeworkDiv(student,thisHomework );
   %>

</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
   <!--右侧布局-->
   <div class="container-field">
      <div align="center">
         <h2><%=homeworkTitle%>
         </h2>
      </div>
      <div class="student-info">
         学生：计算机151 来来来
      </div>

      <%
         if (thisHomework.getChoiceHomeworkList().size() > 0) {
            out.println("<h4>选择题</h4>");
         }
      %>
      <div id="choicesField">
         <%=browserHomeworkDiv.getChoiceDetailDiv(true)%>
      </div>
      <%
         if (thisHomework.getCompletionHomeworkList().size() > 0) {
            out.println("<h4>填空题</h4>");
         }
      %>
      <div class="completionsField">
         <%=browserHomeworkDiv.getCompletionDetailDiv(true)%>
      </div>

      <form name="form" action="correctHomework.servlet" method="post">
         <%
            if (thisHomework.getOperationHomeworkList().size() > 0) {
               out.println("<h4>操作题</h4>");
            }
         %>
         <div class="operationsField">
            <%=browserHomeworkDiv.getOperationDetailDiv(true)%>
         </div>

         <div align="center" style="margin-top: 20px">
            <a class="floatButton" onclick="checkScore(<%=thisHomework.getOperationHomeworkList().size()+1%>)">完成批改</a>
         </div>
         <input type="hidden" name="homeworkId" value="<%=homeworkId%>">
         <input type="hidden" name="studentId" value="<%=studentId%>">
      </form>
   </div>


</div>
</body>
</html>