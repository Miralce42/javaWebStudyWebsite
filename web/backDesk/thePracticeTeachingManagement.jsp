<%@ page import="com.dream.FileClasses.FileClasses" %>
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
    <title>实践教学管理</title>
</head>
<body>
<%@include file="thePracticeTeachingManagement_template.jsp"%>
<div style="margin-top: 5%;float: right;width: 80%; overflow-y: scroll"><!----右侧框架-->
            <%
           String section=null;
           try {
                section= request.getParameter("section");
           }
           catch (Exception a){}
           if(section==null)
               section="实验文件";
           String [] param={"实践教学资料",section};
               FileDAO fileDAO=new FileDAO();
               try {
                   ResultSet resultSet = fileDAO.getResultSet("select * from teachingfile where file_type=? and chapter=? order by upload_date", param);
                   if(section.equals("通知")) {
                       out.println("<table border=2 align='center'><tr><td>通知上传时间</td><td>通知内容</td><td>功能</td></tr>");
                       while (resultSet.next()) {
                               out.println("<tr>");
                           out.println("<td>"+resultSet.getString("upload_date")+"</td><td>" + resultSet.getString("file_name") + "</td>");
                           out.println("<td> <a href='practicedbdelete.action?info=实践教学资料/");
                           out.println(section + "/" + resultSet.getString("file_name") + "'>删除" + "</a></td>");
                           out.println("</tr>");
                       }
                       out.println("</table></form>");
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
  <%--                    <td><a href='practicedbdelete.action?info=实践教学资料/<%=section%>/<%=filename%>'> 删除</a></td>
    --%>
                      <td><a href='/javaWebStudyWebsite/delete.servlet?filename=实践教学资料/<%=section%>/<%=filename%>'> 删除</a></td>
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
</div>
</body>
</html>