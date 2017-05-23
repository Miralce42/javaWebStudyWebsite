<%@ page import="WebDB.Dao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Users" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/23
  Time: 11:15
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
    <%
        String username = (String)request.getParameter("username");
        if(username == null){
            username = (String)session.getAttribute("updatedStuUsername");
        }
        Dao dao = new Dao();
        ArrayList<Users> Students = new ArrayList<Users>();
        dao.selectStudent(Students,3,username);
        Users stu = Students.get(0);
    %>
    <!-- INPUTS -->
    <div class="panel">
        <div class="panel-body">
            <a href="selectAllStudents.action"><button class="btn btn-primary" onclick="selectSearch()" type="button">返回</button></a>
        </div>
        <form name="form1" method="post">
        <div id="panel-body" class="panel-body">
            姓名：<input name="user.name" id="name" type="text" class="form-control" value="<%=stu.getName()%>">
            <br>
            学号：<input name="user.username" id="username" type="text" class="form-control" value="<%=stu.getUsername()%>">
            <br>
            手机号：<input name="user.phone" id="phone" type="text" class="form-control" value="<%=stu.getPhone()%>">
            <br>
            专业：<input name="user.major" id="major" type="text" class="form-control" value="<%=stu.getMajor()%>">
            <br>
            班级：<input name="user.classNum" id="classNum" type="text" class="form-control" value="<%=stu.getClassNum()%>">
            <br>
            性别：<input name="user.sex" id="sex" type="text" class="form-control" value="<%=stu.getSex()%>">
            <br>
            <table width="80%">
                <tr>
                    <td align="left"><button class="btn btn-primary" onclick="deleteCheck()" type="button">删除</button></td>
                    <td align="left"><button class="btn btn-primary" onclick="updateCheck(<%=stu.getUsername()%>)" type="button">更改</button></td>
                </tr>
            </table>
        </div>
        </form>
    </div>
    <!-- END INPUTS -->
</div>
</body>
</html>
