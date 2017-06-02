<%@ page import="com.dream.FileClasses.FileDAO" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by Dreamer.
  User: Dreamer
  Date: 2017/5/24
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课件下载</title>
</head>
<body>
<%@include file="theCourseware_Template.jsp"%>
<div style="float: right;width: 65%"><!---右侧框架-->
    <h1 style="align-content: center;width: 60%;" align="center">教学课件</h1>
    <%
        String fileSection=null;
        try{
            fileSection=request.getParameter("section");//{"第一章","第二章","第三章","第四章","其他"};
        }
        catch (Exception E){
            E.printStackTrace();
        }
        if(fileSection==null)
            fileSection="第一章";
        FileDAO fileDAO=new FileDAO();
        String sql="select * from teachingfile where file_type=? and chapter=?";
        String[] param={"教学课件资料",fileSection};
        String[] imgurl={"FileImages/doc.jpg","FileImages/doc.jpg","FileImages/zip.jpg","FileImages/jpg.jpg","FileImages/zip.jpg","FileImages/xsl.jpg"};//图标文件路径
        String[] filesuffix={"doc","docx","zip","jpg","rar","xsl"};//文件后缀名数组
    %>
    <div name="firstdiv" style="margin-top: 10%"><!---firstdiv-->
        <div class="fh5co-narrow-content"><!---narrow-->
            <div class="row row-bottom-padded-md"><!---row-->

            <input name="file_type" hidden="hidden" value="教学课件资料"/>
            <input name="section" value="<%=fileSection%>" hidden="hidden"/>
            <%
                try{
                ResultSet resultSet=fileDAO.getResultSet(sql,param);
                if(!resultSet.next())
                { %>
                <label>暂无文件！</label>
                <%
                }//if
                else
                {
                resultSet=fileDAO.getResultSet(sql,param);
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
       <%--     <label><%=filename%></label><a href="/download.servlet?filename=教学课件资料/<%=fileSection%>/<%=filename%>"><img src="../<%=url%>" title="<%=filename%>" alt="<%=filename%>"/>下载</a><br/>--%>
                <div class="col-md-3 col-sm-6 col-padding text-center animate-box" >
                    <a href="/javaWebStudyWebsite/download.servlet?filename=教学课件资料/<%=fileSection%>/<%=filename%>" class="work image-popup" style="background-image: url('../<%=url%>')">
                        <div class="desc">
                            <h3 style="width: 90%"><%=filename%></h3>
                            <span>上传时间：<%=resultSet.getString("upload_date")%></span>
                            <span>点击下载</span>
                        </div>
                    </a>
                </div>
            <%
                }//遍历数据库
                }//else
                }//try
                catch (Exception e){e.printStackTrace();}
                fileDAO.dbClose();
            %>

    </div><!---row-->
    </div><!---narrow-->
    </div><!---firstdiv-->
</div><!---右侧框架-->
</body>
</html>
