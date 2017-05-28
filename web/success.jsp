<%--
  Created by Dreamer.
  User: Dreamer
  Date: 2017/5/22
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.PrintWriter" %>
<html>
<head>
<script type="text/javascript">
    function link1() {
        alert("上传完成！");
        setTimeout("location='theCoursewareManagement.jsp'",1);
    }
    function link2() {
        alert("上传完成！");
        setTimeout("location='thePracticeTeachingManagement.jsp'",1);
    }
    function link3() {
        alert("删除完成！");
        setTimeout("location='thePracticeTeachingManagement.jsp'",1);
    }
    function link4() {
        alert("删除完成！");
        setTimeout("location='theResourceManagement.jsp'",1);
    }
    function link5() {
        alert("上传完成！");
        setTimeout("location='theResourceManagement.jsp'",1);
    }
</script>
</head>
<body>

<%
  String tag= request.getParameter("tag");
if(tag.equals("uploadsuccess"))
{
%>
<script type="text/javascript">
    window.link1();
</script>
<%
    }
    if (tag.equals("pinsertsuccess"))
    {
%>
<script type="text/javascript">
    window.link2();
</script>
<%
    }
    if(tag.equals("pdeletesuccess"))
    {
%>
<script type="text/javascript">
    window.link3();
</script>
<%
}
    if(tag.equals("sdeletesuccess"))
    {
%>
<script type="text/javascript">
    window.link4();
</script>
<%
    }

    if(tag.equals("sinsertsuccess")||tag.equals("suploadsuccess"))
    {
%>
<script type="text/javascript">
    window.link5();
</script>
<%
    }
%>
</body>
</html>