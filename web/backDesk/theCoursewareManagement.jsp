<%@ page import="com.dream.FileClasses.FileDAO" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by Dreamer.
  User: Dreamer
  Date: 2017/5/22
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课件管理</title>
</head>
<body>
<%@include file="theCoursewareManagement_template.jsp"%><!--左侧布局-->
    <div style="margin-top: 5%;float: right;width: 70%;">
<%
    String fileSection=null;
    try{
       fileSection=request.getParameter("section");
      // System.out.println(fileSection);
    }
    catch (Exception A){}
    if(fileSection==null)
        fileSection="第一章";
    FileDAO fileDAO=new FileDAO();
    String sql="select * from teachingfile where file_type=? and chapter=?";
    String[] param={"教学课件资料",fileSection};
                try{
                ResultSet resultSet=fileDAO.getResultSet(sql,param);
               /* System.out.println(12);*/
                if(!resultSet.next())
                {
                 //  System.out.println(00);
            %>
               <label style="margin-left: 40%">暂无文件！</label>
            <%
                }
            else
                {// System.out.println(13);
                 resultSet=fileDAO.getResultSet(sql,param);
                 out.println("<table border=2 align='center'><tr><td>文件名</td><td>上传时间</td><td>功能</td>");
                    while (resultSet.next())
                {
                    String filename=resultSet.getString("file_name").trim();
                    String time=resultSet.getString("upload_date");
            %>
             <%--  <label><%=filename%></label><a href="/delete.servlet?filename=教学课件资料/<%=fileSection%>/<%=filename%>">删除</a><br/>
        --%>
        <tr>
            <td><span><%=filename%></span></td>
            <td><span>上传时间：<%=time%></span></td>
            <td><a href="/javaWebStudyWebsite/delete.servlet?filename=教学课件资料/<%=fileSection%>/<%=filename%>"> 删除</a></td>
        </tr>
      <%--  <a href="/backDesk/delete.servlet?filename=教学课件资料/<%=fileSection%>/<%=filename%>">123123</a>
--%>
        <%
                }//while
                out.println("</table>");
                }//else
                }//try
                catch (Exception e){e.printStackTrace();
                    fileDAO.dbClose();
                }
            %>
        <%
       fileDAO.dbClose();
        %>
    </div><!--编辑-->
</body>
</html>
