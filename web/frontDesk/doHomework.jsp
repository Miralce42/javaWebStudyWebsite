<%@ page import="beans.Users" %>
<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.Homework" %>
<%@ page import="WebDB.TeacherDAO" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/16
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>做作业</title>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="js/publishHomework.js"></script>
    <link type="text/css" rel="stylesheet" href="../myCss/publishHomework.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/doHomeworkStyle.css"/>
    <%
        String homeworkId = request.getParameter("homeworkId");
        String homeworkTitle = request.getParameter("homeworkTitle");
        Users student = (Users) session.getAttribute("user");
        StudentDAO studentDAO = new StudentDAO(student);
        Homework thisHomework = new TeacherDAO(student).getHomeworkDetail(homeworkId);//使用TeacherDAO获取作业详情
    %>
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main">
    <!--右侧布局-->
    <div class="container-field">
        <div align="center">
            <h1><%=homeworkTitle%></h1>
        </div>
        <form method="post" action="commitHomework.servlet">
            <h2>选择题</h2>
            <div id="choicesField">
                <div class="choiceBump">
                    <p>选择1</p>
                    <div class="option">
                        <input name="choice_1" type="radio">选项A
                    </div>
                    <div class="option">
                        <input name="choice_1" type="radio">选项A
                    </div>
                    <div class="option">
                        <input name="choice_1" type="radio">选项A
                    </div>
                    <div class="option">
                        <input name="choice_1" type="radio">选项A
                    </div>
                </div>
            </div>
            <h2>填空题</h2>
            <div id="completionField">
                <div class="completionBump">
                    <p>填空11111111</p>
                </div>
                回答：<input class="completion_answer" id="completion_answer_1"
                          name="completion_answer_1" type="text" placeholder=" ">
            </div>
        </form>
    </div>
</div>
</body>
</html>
