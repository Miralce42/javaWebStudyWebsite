<%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/23
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        //后台管理页面标题,自行修改
        String moduleString="学生信息编辑";
    %>
    <title><%=moduleString%></title>
    <style type="text/css">
        #panel-body{
            padding-left: 200px;
        }
    </style>
    <script src="js/checkStudentInfo.js"></script>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
    <!-- INPUTS -->
    <div class="panel">
        <div class="panel-body">
            <a href="selectAllStudents.action"><button class="btn btn-primary" onclick="selectSearch()" type="button">返回</button></a>
        </div>
        <form name="form1" method="post">
            <div id="panel-body" class="panel-body">
                姓名：<input name="user.name" id="name" type="text" class="form-control">
                <br>
                学号：<input name="user.username" id="username" type="text" class="form-control">
                <br>
                手机号：<input name="user.phone" id="phone" type="text" class="form-control">
                <br>
                专业：<input name="user.major" id="major" type="text" class="form-control">
                <br>
                班级：<input name="user.classNum" id="classNum" type="text" class="form-control">
                <br>
                性别：<input name="user.sex" id="sex" type="text" class="form-control">
                <br>
                <table width="50%">
                    <tr>
                        <td align="right"><button class="btn btn-primary" onclick="addCheck()" type="button">添加</button></td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <!-- END INPUTS -->
</div>
</body>
</html>
