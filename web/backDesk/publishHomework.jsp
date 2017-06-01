<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
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

    <script type="text/javascript" src="../laydate/laydate.js"></script>
    <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="js/checkHomeworkInfo.js"></script>
    <script type="text/javascript" src="js/publishHomework.js"></script>
    <link type="text/css" rel="stylesheet" href="../myCss/publishHomework.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
    <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css"/>
    <%
        String moduleString = "发布新作业";
    %>
    <%
        //获取当前时间
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String nowTime = formatter.format(new Date());
    %>
    <title><%=moduleString%>
    </title>
</head>
<body onload="addChoice()">
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
    <!--右侧布局-->
    <div class="container-field">
        <form name="form" action="publishHomework.servlet" method="post">
            <input type="hidden" name="action" value="FIRST_PUBLISH">
            <div class="float-right">
                <input class="homework-title" name="homeworkTitle" type="text" placeholder="作业标题">
                <a class="floatButton" onclick="checkHomework()">发布</a>
            </div>
            <div align="right" style="padding-right: 12%">
                开始时间:<input CLASS="score" type="text" name="beginTime" id="beginTime" value="<%=nowTime%>" title="选择开始时间"
                       onclick="laydate({istime:true,format: 'YYYY-MM-DD hh:mm:ss'})">
                结束时间:<input CLASS="score" type="text" name="endTime" id="endTime" placeholder="选择结束时间" onclick="laydate({istime:true,format: 'YYYY-MM-DD hh:mm:ss'})">
            </div>

            <h3>选择题</h3>
            <div id="choices_field">
            </div>
            <a class="floatButton" onclick="addChoice()">添加选择题</a>
            <br>
            <br>
            <h3>填空题</h3>
            <div class="completions_field" id="completions_field">
            </div>
            <a class="floatButton" onclick="addCompletion()">添加填空题</a>
            <h3>操作题</h3>
            <div class="operations_field" id="operations_field">
            </div>
            <a class="floatButton" onclick="addOperation()">添加操作题</a>
        </form>
    </div>
</div>
</body>
</html>