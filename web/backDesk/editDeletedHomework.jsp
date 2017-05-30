<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.Homework" %>
<%@ page import="cn.vove7.mydiv.ReeditHomeworkDiv" %>
<%@ page import="beans.Users" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/30
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <script type="text/javascript" src="../js/jquery.min.js"></script>

   <script type="text/javascript" src="../laydate/laydate.js"></script>
   <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
   <script type="text/javascript" src="js/checkHomeworkInfo.js"></script>
   <script type="text/javascript" src="js/publishHomework.js"></script>
   <link type="text/css" rel="stylesheet" href="../myCss/publishHomework.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css"/>
   <%
      Users teacher = (Users) session.getAttribute("user");
      TeacherDAO teacherDAO = new TeacherDAO(teacher);
      String homeworkId=request.getParameter("homeworkId");
      out.println(new StudentDAO().getHomeworkTitle(homeworkId));
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
      String moduleString="重新开启"+"--"+thisHomework.getHomeworkTitle();
      ReeditHomeworkDiv reeditHomeworkDiv = new ReeditHomeworkDiv(thisHomework,true);
   %>
   <title><%=moduleString%></title>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
   <!--右侧布局-->

   <div class="container-field">
      <form name="form" action="publishHomework.servlet" method="post">
         <input type="hidden" name="action" value="REOPEN">
         <input type="hidden" name="homeworkId" value="<%=homeworkId%>">
         <div class="float-right">
            <input class="homework-title" name="homeworkTitle" type="text" placeholder="作业标题" value="<%=thisHomework.getHomeworkTitle()%>">
            <a class="floatButton" onclick="checkHomework()">开启</a>
         </div>
         <div align="right" style="padding-right: 12%">
            开始时间:<input CLASS="score" type="text" name="beginTime" id="beginTime" value="<%=thisHomework.getBeginTime()%>" title="选择开始时间"
                        onclick="laydate({istime:true,format: 'YYYY-MM-DD hh:mm:ss'})">
            结束时间:<input CLASS="score" type="text" name="endTime" id="endTime" placeholder="选择结束时间"
                        onclick="laydate({istime:true,format: 'YYYY-MM-DD hh:mm:ss'})" value="<%=thisHomework.getEndTime()%>">
         </div>

         <h2>选择题</h2>
         <div id="choices_field">
            <%=reeditHomeworkDiv.getChoiceDiv()%>
         </div>
         <a class="floatButton" onclick="addChoice()">添加选择题</a>
         <br>
         <br>
         <h2>填空题</h2>
         <div class="completions_field" id="completions_field">
            <%=reeditHomeworkDiv.getCompletionDiv()%>
         </div>
         <a class="floatButton" onclick="addCompletion()">添加填空题</a>
         <h2>操作题</h2>
         <div class="operations_field" id="operations_field">
            <%=reeditHomeworkDiv.getOperationDiv()%>
         </div>
         <a class="floatButton" onclick="addOperation()">添加操作题</a>
      </form>
   </div>
   <script>
      setCompletionBum(<%=thisHomework.getCompletionHomeworkList().size()+1%>);
      setChoiceBum(<%=thisHomework.getChoiceHomeworkList().size()+1%>);
      setOperationNum(<%=thisHomework.getOperationHomeworkList().size()+1%>);
   </script>
</div>
</body>
</html>