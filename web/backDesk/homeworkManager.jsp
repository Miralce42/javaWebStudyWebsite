<%@ page import="beans.Users" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.StudentHomework" %>
<%@ page import="cn.vove7.mydiv.HomeworkDiv" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/20
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业管理</title>
    <link type="text/css" rel="stylesheet" href="../myCss/homeworkDivStyle.css">
    <link type="text/css" rel="stylesheet" href="../myCss/buttonStyle.css">
    <%
        String moduleString = "作业管理";
    %>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
    <!--右侧布局-->
    <div class="float-right">
        <a class="floatButton" href="publishHomework.jsp">发布新作业</a>
    </div>
    <div style="margin-top: 50px">
        <%
            Users teacher = (Users) session.getAttribute("user");
            if (teacher == null || "STUDENT".equals(teacher.getUser_type())) {
                return;
            }
            TeacherDAO teacherDAO = new TeacherDAO(teacher);

            //获取所有作业
            ArrayList<StudentHomework> homeworkList = teacherDAO.getHomework();

            StringBuilder unclosedBuilder = new StringBuilder();//未关闭
            StringBuilder closedBuilder = new StringBuilder();//已关闭
            //展示未关闭作业
            int unclosedNum = 1;
            boolean haveClosedHomework=false;
            int closedNum = 1;
            for (StudentHomework homework : homeworkList) {
                if (unclosedNum % 3  == 1) {
                    unclosedBuilder.append("<div class=\"row\">\n");//行div
                }
                if (closedNum % 3 == 1) {
                    closedBuilder.append("<div class=\"row\">\n");
                }
                String closingTime = homework.getClosingTime();
                HomeworkDiv homeworkDiv = new HomeworkDiv(homework.getId(),
                        homework.getTitle(),
                        "截止时间:" + closingTime.substring(0, closingTime.length() - 2),
                        homework.getTeac_HomeworkStatus()
                );

                switch (homework.getHomeworkStatus()) {
                    case CLOSED:
                        closedBuilder.append(homeworkDiv.toTeaString());
                        closedNum++;
                        haveClosedHomework=true;
                        break;
                    case UNCLOSED:
                        unclosedBuilder.append(homeworkDiv.toTeaString());
                        unclosedNum++;
                        break;
                }

                if (unclosedNum % 3 == 1) {
                    unclosedBuilder.append("</div>");
                }
                if (closedNum % 3 == 1) {
                    closedBuilder.append("</div>");
                }
            }
            //结尾
            if(unclosedNum % 3 != 1){
                unclosedBuilder.append("</div>");
            }
            if(haveClosedHomework && closedNum % 3 != 1){
                closedBuilder.append("</div>");
            }
        %>

        <p>未结束</p>
        <hr class="blue">
        <%=unclosedBuilder.toString()%>


        <p>已结束</p>
        <hr class="blue">
        <%=closedBuilder.toString()%>

    </div>

</div>
</body>
</html>