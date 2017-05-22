<%@ page import="beans.Users" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.HomeworkStudentStatus" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="static beans.StudentHomework.HomeworkStatus.FINISHED" %>
<%@ page import="static beans.StudentHomework.HomeworkStatus.SAVED" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/20
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="../myCss/homeworkDivStyle.css">
    <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css">

    <%
        Users teacher=(Users)session.getAttribute("user");
        if(teacher==null||!teacher.getUser_type().equals("TEACHER")){
            return;
        }
        TeacherDAO teacherDAO=new TeacherDAO(teacher);

    %>
    <%
        String homeworkId=request.getParameter("homeworkId");
        String homeworkTitle=request.getParameter("homeworkTitle");
    %>
    <title><%=homeworkTitle%>详情</title>
    <%
        String moduleString=homeworkTitle+"详情";
    %>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->

<div id="fh5co-main" class="right">
    <!--右侧布局-->
    <p>已提交同学</p>
    <hr class="blue">
    <%int i=1;
        ArrayList<HomeworkStudentStatus> homeworkFinishedAndSavedStudentList=teacherDAO.getHomeworkFinishedAndSavedStudentList(homeworkId);
        for(HomeworkStudentStatus homeworkStudentStatus:homeworkFinishedAndSavedStudentList){
            if (homeworkStudentStatus.getHomeworkStatus()==FINISHED){
                if(i%3==1){
                    out.print("<div class=\"row\">\n");//行div
                }
                out.println(homeworkStudentStatus.getStatusDivContent());
                if(i%3==0){
                    out.print("</div>");
                }i++;
            }
        }
    %>

    <p>暂时保存同学</p>
    <hr class="blue">
    <%
        i=1;
        for(HomeworkStudentStatus homeworkStudentStatus:homeworkFinishedAndSavedStudentList){
            if (homeworkStudentStatus.getHomeworkStatus()==SAVED){
                if(i%3==1){
                    out.print("<div class=\"row\">\n");//行div
                }
                out.println(homeworkStudentStatus.getStatusDivContent());
                out.println(homeworkStudentStatus.getStatusDivContent());
                if(i%3==0){
                    out.print("</div>");
                }i++;
            }
        }
    %>

    <p>未完成同学</p>
    <hr class="blue">
    <%
        ArrayList<HomeworkStudentStatus> homeworkUnfinishedStudentList=teacherDAO.getHomeworkUnfinishedStudentList(homeworkId);
        i=1;
        for(HomeworkStudentStatus homeworkStudentStatus:homeworkUnfinishedStudentList){
                if(i%3==1){
                    out.print("<div class=\"row\">\n");//行div
                }
                out.println(homeworkStudentStatus.getStatusDivContent());
                out.println(homeworkStudentStatus.getStatusDivContent());
                if(i%3==0){
                    out.print("</div>");
                }i++;
        }
    %>

</div>
</body>
</html>