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
    function linkCoursewareUpload() {
        alert("上传完成！");
        setTimeout("location='theCoursewareManagement.jsp'",1);
    }
    function linkCoursewareDelete() {
        alert("删除完成！");
        setTimeout("location='theCoursewareManagement.jsp'",1);
    }
    function linkPracticeInsert() {
        alert("上传完成！");
        setTimeout("location='thePracticeTeachingManagement.jsp'",1);
    }
    function linkPracticeDelete() {
        alert("删除完成！");
        setTimeout("location='thePracticeTeachingManagement.jsp'",1);
    }
    function linkResourceDelete() {
        alert("删除完成！");
        setTimeout("location='theResourceManagement.jsp'",1);
    }
    function linkResourceUpload() {
        alert("上传完成！");
        setTimeout("location='theResourceManagement.jsp'",1);
    }
</script>
</head>
<body>

<%
  String tag= request.getParameter("tag");
  System.out.println(tag);
if(tag.equals("uploadsuccess"))
{
%>
<script type="text/javascript">
    window.linkCoursewareUpload();
</script>
<%
    }
    if (tag.equals("pinsertsuccess")||tag.equals("puploadsuccess"))
    {
%>
<script type="text/javascript">
    window.linkPracticeInsert();
</script>
<%
    }
    if(tag.equals("pdeletesuccess"))
    {
%>
<script type="text/javascript">
    window.linkPracticeDelete();
</script>
<%
}
    if(tag.equals("sdeletesuccess"))
    {
%>
<script type="text/javascript">
    window.linkResourceDelete();
</script>
<%
    }

    if(tag.equals("sinsertsuccess")||tag.equals("suploadsuccess"))
    {
%>
<script type="text/javascript">
    window.linkResourceUpload();
</script>
<%
    }
%>
</body>
</html>