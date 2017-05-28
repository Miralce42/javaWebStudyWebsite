<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.TeachingEvaluation" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/20
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        //后台管理页面标题,自行修改
        String moduleString = "课程评价";
    %>
    <title><%=moduleString%>
    </title>
    <style>
        .evaluate-field {
            min-height: 20px;
            padding: 19px;
            margin-bottom: 20px;
            margin: 25px;
            background-color: #f5f5f5;
            border: 1px solid #00aaff;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
        }

        .name {
            font-family: -webkit-pictograph;
            margin-bottom: 15px;
            font-size: larger;
        }

        .evaluate-result {
            height: 32px;
            padding-left: 20%;
            margin-top: 1%;
            padding-top: 3%;
            font-family: "Source Sans Pro", sans-serif;
            font-size: large;

        }

        .content{
            font-family: "Source Sans Pro", sans-serif;
            font-size: large;
        }

        .time{
            margin-left: 90%;
        }
    </style>
    <%
        TeacherDAO teacherDAO=new TeacherDAO();
        double [] star=teacherDAO.getAvgStar();
        ArrayList<TeachingEvaluation> StudentEvaluationSaved=teacherDAO.getStudentEvaluation();


    %>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">

    <!--右侧布局-->
    <div class="evaluate-result">
        教学内容：<%=star[0]%> 分（平均）
    </div>
    <div class="evaluate-result">
        教学方式：<%=star[1]%>分（平均）
    </div>
    <div class="evaluate-result">
        教学风格：<%=star[2]%>分（平均）
    </div>
    <div class="evaluate-result">
        教学趣味：<%=star[3]%>分（平均）
    </div>

        <%
            for(TeachingEvaluation teachingEvaluation:StudentEvaluationSaved){
                out.print("<div class=\"evaluate-field\">");
                String date=teachingEvaluation.getEvaluate_date();
                if(date!=null){
                    date=date.substring(0,10);
                }
                else {
                    date="";
                }
                out.print("<div class=\"name\">"+teachingEvaluation.getMajor()+"&nbsp"+teachingEvaluation.getUsername()+"</div>");
                out.print("<div class=\"content\">"+teachingEvaluation.getContent()+"</div>");
                out.print("<div class=\"time\">" +date+"</div>");
                out.print("</div>");
            }
        %>


</div>
</body>
</html>