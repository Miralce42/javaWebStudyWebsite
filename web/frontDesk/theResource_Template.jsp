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
    <title>教学资源模板</title>
    <style type="text/css">
        a:visited {color: #c0c0c0} /* 已访问的链接 */
        a:link {color: black} /* 未访问的链接 */

        a:hover {color: blue;} /* 鼠标移动到链接上 */
        a:active {color: #7a43b6} /* 选定的链接 */
    </style>
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div style="align-content: center;margin-top: 10%;margin-left: 22%;width: 10%;float:left;" >
    <nav >
        <ul style="font-size: 20px;">
            <li><a href="theTeachingResource.jsp?section=大纲">大纲</a></li><br/>
            <li><a href="theTeachingResource.jsp?section=参考资料">参考资料</a></li><br/>
            <li><a href="theTeachingResource.jsp?section=链接">工具链接</a></li><br/>
            <li><a href="theTeachingResource.jsp?section=其他">其他</a></li><br/>
        </ul>
    </nav>
</div>
</body>
</html>
