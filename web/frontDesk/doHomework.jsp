<%@ page import="beans.Users" %>
<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.Homework" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="cn.vove7.mydiv.DoHomeworkDiv" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/16
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>做作业</title>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="js/commitHomework.js"></script>
    <link type="text/css" rel="stylesheet" href="../myCss/doHomeworkStyle.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/publishHomework.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css"/>
    <%
        String homeworkId = request.getParameter("homeworkId");
        String homeworkTitle = request.getParameter("homeworkTitle");
        Users student = (Users) session.getAttribute("user");
        StudentDAO studentDAO = new StudentDAO(student);
        Homework thisHomework = new TeacherDAO(student).getHomeworkDetail(homeworkId);//使用TeacherDAO获取作业详情
        DoHomeworkDiv doHomeworkDiv=new DoHomeworkDiv(student,thisHomework);
    %>
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
        <form name="form" method="post" action="commitHomework.servlet">
            <h2>选择题</h2>
            <div id="choicesField">
                <%=doHomeworkDiv.getChoicesValue()%>
            </div>
            <h2>填空题</h2>
            <div id="completionField">
                <%=doHomeworkDiv.getCompletionsValue()%>
            </div>
           <div align="center" style="margin-top: 20px">
              <a class="floatButton" onclick="saveHomework()">暂时保存</a>
              <a class="floatButton" onclick="commitHomework()">提交作业</a>
           </div>
           <input type="hidden" id="action" name="action">
           <input type="hidden" name="homeworkId" value="<%=homeworkId%>">
        </form>
    </div>
</div>
<script>
   setNum(<%=thisHomework.getChoiceHomeworkList().size()+1%>,<%=thisHomework.getCompletionHomeworkList().size()+1%>)
</script>
</body>
</html>
