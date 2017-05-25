<%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/5/24
  Time: 16:35
      统一执行信息页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        //统一执行信息页面
        //session传入参数：
        String title = (String) session.getAttribute("title");//页面标题
        String path = (String) session.getAttribute("path");//指定自动跳转路径（注意目录）
        String time = (String) session.getAttribute("time");//若有时间，定时跳转到path
        String pageContent = (String) session.getAttribute("pageContent");
        session.removeAttribute("time");
        session.removeAttribute("path");
        session.removeAttribute("pageContent");
    %>
    <title><%=title%>
    </title>
    <style type="text/css">
        a, a:active {
            color: #06a92f;
            font-weight: bold;
            cursor: auto;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
            cursor: auto;
        }

        body {
            margin: 30px;
            font-family: 'Microsoft Yahei';
        }
    </style>
</head>
<body>
<div class="pageContent" align="center">
    <%=pageContent%>
</div>
<%  //跳转
    if (time != null && path != null) {
        response.setHeader("refresh", time + ";URL=" + path);
    }
%>
</body>
</html>
