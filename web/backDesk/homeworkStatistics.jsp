<%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/6/1
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <link type="text/css" rel="stylesheet" href="../myCss/container-field.css"/>
   <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css"/>
   <link type="text/css" rel="stylesheet" href="css/homeworkStaticsStyle.css"/>
   <%
      String moduleString = "作业统计";
   %>
   <title><%=moduleString%>
   </title>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
   <!--右侧布局-->
   <div class="container-field">
      <form class="navbar-form">
         <div class="input-group">
            <input type="text" value="" class="form-control" name="searchValue" placeholder="查找学生">
            <span class="input-group-btn"><button type="button" class="btn btn-primary">Go</button></span>
         </div>
      </form>
      <div class="panel">
         <div class="panel-heading">
            <h3 class="panel-title">Basic Table</h3>
         </div>
         <div class="panel-body">
            <table class="table">
               <thead>
               <tr>
                  <th>#</th>
                  <th>学号</th>
                  <th>姓名</th>
                  <th>专业</th>
                  <th>平均分</th>
               </tr>
               </thead>
               <tbody>
               <tr>
                  <td>1</td>
                  <td>Steve</td>
                  <td>Steve</td>
                  <td>Jobs</td>
                  <td>@steve</td>
               </tr>
               <tr>
                  <td>2</td>
                  <td>Simon</td>
                  <td>Philips</td>
                  <td>Philips</td>
                  <td>@simon</td>
               </tr>
               <tr>
                  <td>3</td>
                  <td>Jane</td>
                  <td>Jane</td>
                  <td>Doe</td>
                  <td>@jane</td>
               </tr>
               </tbody>
            </table>
         </div>
      </div>
   </div>
</div>
</body>
</html>