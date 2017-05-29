<%@ page import="com.dream.FileClasses.FileDAO" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by Dreamer.
  User: Dreamer
  Date: 2017/5/22
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教学资源管理</title>
</head>
<body>
<%@include file="theResourceManagement_template.jsp"%>
<div style="margin-top: 5%;float: right;width: 80%; "><!---右侧布局-->
    <%
        String section=null;
        try {
            section= request.getParameter("section");
        }
        catch (Exception a){}
        if(section==null)
            section="大纲";
        String [] param={"教学资源管理",section};
        FileDAO fileDAO=new FileDAO();
        try {
            ResultSet resultSet = fileDAO.getResultSet("select * from teachingfile where file_type=? and chapter=? order by upload_date", param);
            if(section.equals("链接")) {
                out.println("<table border=2><tr>");
                while (resultSet.next()) {
                    out.println("</tr><tr>");
                    out.println("<td>" + resultSet.getString("file_name") + "</td>");
                    out.println("<td> <a href='resourcedbdelete.action?info=教学资源管理/");
                    out.println(section + "/" + resultSet.getString("file_name") + "'>删除" + "</a></td>");
                }
                out.println("</tr></table></form>");
            }
            else {
                out.println("<table border=2 align='center'><tr><td>文件名</td><td>上传时间</td><td>功能</td>");
                while (resultSet.next()) {
                    String filename=resultSet.getString("file_name");
                    String time=resultSet.getString("upload_date");
    %>
    <tr>
        <td><span><%=filename%></span></td>
        <td><span>上传时间：<%=time%></span></td>
        <td><a href="/delete.servlet?filename=教学资源管理/<%=section%>/<%=filename%>"> 删除</a></td>
    </tr>
    <%
                }//while
                out.println("</table>");
            }//else
        }//try
        catch(Exception a){
            a.printStackTrace();
        }
    %>
</div><!--右侧布局-->
</body>
</html>