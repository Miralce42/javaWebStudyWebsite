<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.Users" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.StudentHomework" %>
<%@ page import="cn.vove7.mydiv.ManagerHomeworkDiv" %>
<%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/16
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生作业</title>
    <link type="text/css" rel="stylesheet" href="../myCss/homeworkDivStyle.css">
    <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css">

</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main">
    <!--右侧布局-->
    <h3>我的作业</h3>
        <%
        Users student =(Users)session.getAttribute("user");

        StudentDAO studentDAO=new StudentDAO(student);

        ArrayList<StudentHomework> homeworkList=studentDAO.getHomeworkList();
        int i=1;
        for(StudentHomework homework:homeworkList){
           if(i%3==1){
              out.print("<div class=\"row\">\n");//行div
           }
           String closingTime=homework.getClosingTime();

           ManagerHomeworkDiv managerHomeworkDiv=new ManagerHomeworkDiv(homework.getId(),
                   homework.getTitle(),
                   "截止时间:"+closingTime.substring(0,closingTime.length()-2),
                   homework.statusToValue_stu()
           );

           out.println(managerHomeworkDiv.toStuString());//divContent

           if(i%3==0){
              out.print("</div>");
           }i++;
        }
        if(i%3!=0){
            out.print("</div>");
        }
    %>

</body>
</html>
