<%@ page import="beans.Users" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/19
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的信息</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<%@include file="aside.jsp"%><!--左侧布局-->
<%
    Users student = (Users)session.getAttribute("user");
%>
<div id="fh5co-main">
    <div class="fh5co-narrow-content">
        <h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">我的信息</h2>
        <div class="row">
            <div class="col-md-6">
                <div class="fh5co-feature animate-box" data-animate-effect="fadeInLeft">
                    <div class="fh5co-icon">
                        <i class="icon-settings"></i>
                    </div>
                    <div class="fh5co-text">
                        <h3>基本信息</h3>
                        <p>姓名：<%=student.getName()%></p>
                        <p>学号：<%=student.getUsername()%></p>
                        <p>性别：<%=student.getSex()%></p>
                        <p>专业：<%=student.getMajor()%></p>
                        <p>班级：<%=student.getClassNum()%></p>
                        <a href="../changePassword.jsp"> <span class="input-group-btn"><button type="button" class="btn btn-pri">修改密码</button></span></a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="fh5co-feature animate-box" data-animate-effect="fadeInLeft">
                    <div class="fh5co-icon">
                        <i class="icon-paperplane"></i>
                    </div>
                    <div class="fh5co-text">
                        <h3>手机号</h3>
                        <p><%=student.getPhone()%></p>
                        <a href="changePhoneNum.jsp"> <span class="input-group-btn"><button type="button" class="btn btn-pri">修改手机号</button></span></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
