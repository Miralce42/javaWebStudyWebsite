<%@ page import="beans.Users" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="cn.vove7.mydiv.ReeditHomeworkDiv" %>
<%@ page import="beans.Homework" %><%--
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

      TeacherDAO teacherDAO = new TeacherDAO(teacher);
      String homeworkId = request.getParameter("homeworkId");

   %>
   <%
      //生成布局
      Homework thisHomework = teacherDAO.getHomeworkDetail(homeworkId);
      if (thisHomework == null) {
         session.setAttribute("title", "作业不存在");
         session.setAttribute("pageContent", "作业不存在或内部错误，请联系管理人员");
         response.sendRedirect("../executeMessage.jsp");
         return;
      }
      ReeditHomeworkDiv reeditHomeworkDiv = new ReeditHomeworkDiv(thisHomework);
   %>
   <script type="text/javascript" src="../js/jquery.min.js"></script>

   <script type="text/javascript" src="../laydate/laydate.js"></script>
   <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
   <script type="text/javascript" src="js/publishHomework.js"></script>
   <link type="text/css" rel="stylesheet" href="../myCss/publishHomework.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css"/>
   <title><%=moduleString%>
   </title>


</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
   <!--右侧布局-->
   <div class="container-field">
      <form name="form" action="publishHomework.servlet" method="post">
         <input type="hidden" name="action" value="reedit">
         <input type="hidden" name="homeworkId" value="<%=homeworkId%>">
         <%=reeditHomeworkDiv.getHead()%>
         <h2>选择题</h2>
         <div id="choices_field">
            <%=reeditHomeworkDiv.getChoiceDiv()%>
         </div>
         <br>
         <br>
         <h2>填空题</h2>
         <div class="completions_field" id="completions_field">
            <%=reeditHomeworkDiv.getCompletionDiv()%>
         </div>
         <h2>操作题</h2>
         <div class="operations_field" id="completions_field">
            <%=reeditHomeworkDiv.getOperationDiv()%>
         </div>
      </form>
   </div>
</div>
</body>
</html>