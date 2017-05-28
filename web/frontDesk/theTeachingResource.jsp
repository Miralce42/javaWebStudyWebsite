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
    <title>教学资源</title>
</head>
<body>
<%@include file="theResource_Template.jsp"%><!--左侧布局-->
<div style="float: right;width: 65%"><!--右侧布局-->
    <h1 style="align-content: center;width: 60%;" align="center">教学资源</h1>
  <%  String fileSection=null;
    try{
    fileSection=request.getParameter("section");//{"大纲","实验资料","链接","其他"};
    }
    catch (Exception E){
    E.printStackTrace();
    }
    if(fileSection==null)
    fileSection="大纲";
      FileDAO fileDAO=new FileDAO();
      String sql="select * from teachingfile where file_type=? and chapter=?";
      String[] param={"教学资源管理",fileSection};
    if(fileSection.equals("链接"))
    {
        try{
            ResultSet resultSet=fileDAO.getResultSet(sql,param);
            if(!resultSet.next())
            {
  %>
    <label>暂无文件！</label>
    <%
    }//if
    else
            {
                resultSet = fileDAO.getResultSet(sql, param);
                String filename=null;
                out.println("<div  style=\"float:left;margin-left:20%;width=45%;margin-top: 10%\">");
                while (resultSet.next())
                {
                    filename = resultSet.getString("file_name");
                        out.println(filename);
                }
                        out.println("</div>");
            }//else
        }//try
        catch (Exception a){
            a.printStackTrace();
            fileDAO.dbClose();
        }
        fileDAO.dbClose();

    }//if链接

        else//else其他，大纲，参考资料显示
      {
      String[] imgurl={"FileImages/doc.jpg","FileImages/doc.jpg","FileImages/zip.jpg","FileImages/jpg.jpg","FileImages/zip.jpg","FileImages/xsl.jpg"};//图标文件路径
      String[] filesuffix={"doc","docx","zip","jpg","rar","xsl"};//文件后缀名数组
  %>
    <div   style="margin-top: 10%"><!---firstdiv-->
        <div class="fh5co-narrow-content"><!---narrow-->
            <div class="row row-bottom-padded-md"><!---row-->
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
                        <a href="/download.servlet?filename=教学资源管理/<%=fileSection%>/<%=filename%>" class="work image-popup" style="background-image: url('../<%=url%>')">
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
    <%
        }//else其他，大纲，参考资料显示
    %>
</div><!---右侧框架-->
</body>
</html>
