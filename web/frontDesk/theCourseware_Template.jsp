<%--
  Created by Dreamer.
  User: Dreamer
  Date: 2017/5/24
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课件模板</title>
    <style type="text/css">
        a:visited {color: #c0c0c0} /* 已访问的链接 */
        a:link {color: black} /* 未访问的链接 */

        a:hover {color: blue;text-underline: !important;} /* 鼠标移动到链接上 */
        a:active {color: #7a43b6} /* 选定的链接 */
    </style>
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div style="align-content: center;margin-top: 10%;margin-left: 22%;width: 10%;float:left;" >
    <nav >
        <ul style="font-size: 20px;">
            <li><a href="theTeachingCourseware.jsp?section=第一章">第一章</a></li><br/>
            <li><a href="theTeachingCourseware.jsp?section=第二章">第二章</a></li><br/>
            <li><a href="theTeachingCourseware.jsp?section=第三章">第三章</a></li><br/>
            <li><a href="theTeachingCourseware.jsp?section=第四章">第四章</a></li><br/>
            <li><a href="theTeachingCourseware.jsp?section=第五章">第五章</a></li><br/>
            <li><a href="theTeachingCourseware.jsp?section=第六章">第六章</a></li><br/>
            <li><a href="theTeachingCourseware.jsp?section=其他">其他</a></li>
        </ul>
    </nav>
</div>
</body>
</html>
