<%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/20
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../ckeditor/ckeditor.js">
        CKEDITOR.replace("question_content");
    </script>
    <script type="text/javascript" src="js/publishHomework.js"></script>
    <link type="text/css" rel="stylesheet" href="../myCss/publishHomework.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css"/>
    <%
        String moduleString = "发布新作业";
    %>
    <title><%=moduleString%>
    </title>
</head>
<body onload="addChoice()">
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
    <div class="container-field">
        <div class="float-right">
            <a class="floatButton" onclick="document.form.submit()">发布</a>
        </div>
        <form name="form" action="publishHomework.servlet" method="post">
            <!--右侧布局-->
            <input class="homework-title" name="homeworkTitle" type="text" placeholder="作业标题">
            <h2>选择题</h2>
            <div id="choices_field">
            </div>
            <a class="floatButton" onclick="addChoice()">添加选择题</a>
            <br>
            <br>
            <h2>填空题</h2>
            <div class="completions_field" id="completions_field">
                <div class="completion_field">
                </div>
            </div>
            <a class="floatButton" onclick="addCompletion()">添加填空题</a>
        </form>
    </div>
</div>
</body>
</html>