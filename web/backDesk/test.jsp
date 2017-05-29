<%--
  Created by Dreamer.
  User: Dreamer
  Date: 2017/5/25
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
String content=request.getParameter("content");
out.println(content);
%>
</body>
</html>
