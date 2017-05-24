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
    <title>教学课件</title>
</head>
<body>

<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main" align="center"><!--右侧布局-->
   <h1 style="align-content: center">教学课件</h1>
        <%
  String[] fileSection={"第一章","第二章","第三章","第四章","其他"};
    FileDAO fileDAO=new FileDAO();
    String sql="select * from teachingfile where file_type=? and chapter=?";
    for(int i=0;i<fileSection.length;i++){
    String[] param={"教学课件资料",fileSection[i]};
    String[] imgurl={"FileImages/doc.jpg","FileImages/zip.jpg","FileImages/jpg.jpg"};//图标文件路径
    String[] filesuffix={"doc","zip","jpg"};//文件后缀名数组
%>
    <div name="firstdiv">
        <label style="align-content: center"><%=fileSection[i]%></label>
        <form name="firstsec" action="delete.servlet" method="get">
            <input name="file_type" hidden="hidden" value="教学课件资料"/>
            <input name="section" value="<%=fileSection[i]%>" hidden="hidden"/>
            <%
                try{
                    ResultSet resultSet=fileDAO.getResultSet(sql,param);
                    while (resultSet.next())
                    {
                        String filename=resultSet.getString("file_name").trim();
                        String filetype=filename.substring(filename.indexOf(".")+1);
                        String url=null;
                        for (int a=0;a<filesuffix.length;a++) {
                            if(filetype.equals(filesuffix[a]))
                                url=imgurl[a];
                        }//根据文件类型动态获取图标
            %>
            <label><%=filename%></label><a href="/download.servlet?filename=教学课件资料/<%=fileSection[i]%>/<%=filename%>"><img src="../<%=url%>" title="<%=filename%>" alt="<%=filename%>"/>下载</a><br/>
            <%
                    }
                }
                catch (Exception e){e.printStackTrace();}
                fileDAO.dbClose();
            %>
        </form>
    </div>
    <%
     }
    %>

</div>
</body>
</html>
