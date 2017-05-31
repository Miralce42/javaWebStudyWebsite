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
    <%
        //后台管理页面标题,自行修改
        String moduleString="课件管理";
    %>
    <script type="text/javascript">
        function  submitCheck() {
            if(document.forms[0].file.value=="")
                alert("未选择文件，请选择文件！");
            else
                document.forms[0].submit();
        }
        function  mulitSubmitCheck() {
               if(document.forms[1].file.value!="") {
                   document.forms[1].submit();
                   return;
               }
            alert("未选择文件，请选择文件！");

        }
    </script>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right"><!--右侧布局-->
    <div style="border: double;background-color: #0c1312;">
        <h3 align="center">课件上传</h3>
    <div style="float: left; border:dashed;border-color: #00aa00;margin-left:15%;width: 30%;height: 20%">
    <form name="fileupload" action="uploadaction.action" method="post" enctype="multipart/form-data">
        <input type="text" name="file_type" id="file_type" value="教学课件资料" hidden="hidden"/>
        <label>单文件上传</label> <br/>
        文件类型：
        <select name="Section" onchange="this.parentNode.nextSibling.value=this.value;">
            <option value="其他">其他</option>
            <option value="第一章">第一章</option>
            <option value="第二章">第二章</option>
            <option value="第三章">第三章</option>
            <option value="第四章">第四章</option>
            <option value="第五章">第五章</option>
            <option value="第六章">第六章</option>
        </select><br/>
        <input type="file" name="file"/><br/>
        <input type="button" value="上传" onclick="submitCheck()"/>
    </form>
    </div><!--单文件上传-->

    <div style="float: right;border: double;border-color: #00a0f0;margin-right:20%;width: 30%;height: 20%">
    <form action="multifile.action" method="post" enctype="multipart/form-data">
        <label>多文件上传</label><br/>
        <input type="text" name="file_type" id="file_typem" value="教学课件资料" hidden="hidden"/>
        文件类型：
        <select name="Section" onchange="this.parentNode.nextSibling.value=this.value;">
            <option value="其他">其他</option>
            <option value="第一章">第一章</option>
            <option value="第二章">第二章</option>
            <option value="第三章">第三章</option>
            <option value="第四章">第四章</option>
            <option value="第五章">第五章</option>
            <option value="第六章">第六章</option>
        </select><br>
        <input type="file" name="file"  multiple/><br>
        <input type="button" value="上传" onclick="mulitSubmitCheck()"/>
    </form>
    </div><!--多文件上传-->
    </div><!--文件上传-->
    <hr>
              <!--编辑-->
    <div  style=" margin-top: 20%">
    <h3 align="center" style="background-color: #0f0f0f"> 课件编辑 </h3>
<%
  String[] fileSection={"第一章","第二章","第三章","第四章","第五章","第六章","其他"};
    FileDAO fileDAO=new FileDAO();
    String sql="select * from teachingfile where file_type=? and chapter=?";
    for(int i=0;i<fileSection.length;i++){
    String[] param={"教学课件资料",fileSection[i]};
    String[] style={"left","right"};
   %>
        <div  style="float: <%=style[i%2]%>;width: 50%; ">
        <form  action="delete.servlet" method="get">
            <h2 style="align-content: center" align="center"><%=fileSection[i]%></h2><br/>

            <%
                try{
                ResultSet resultSet=fileDAO.getResultSet(sql,param);
                resultSet=fileDAO.getResultSet(sql,param);
                if(!resultSet.next())
                {
            %>
               <label>暂无文件！</label>
            <%
                }
                  else
                {
                 resultSet=fileDAO.getResultSet(sql,param);
                while (resultSet.next())
                {
                    String filename=resultSet.getString("file_name").trim();
            %>
               <label><%=filename%></label><a href="/delete.servlet?filename=教学课件资料/<%=fileSection[i]%>/<%=filename%>">删除</a><br/>
            <%
                }//while
                }//else
                }//try
                catch (Exception e){e.printStackTrace();
                    fileDAO.dbClose();
                }

            %>
        </form>
        </div><!-- 章节-->
        <%
      }//for
       fileDAO.dbClose();
        %>
    </div><!--编辑-->
</div><!--右侧布局-->
</body>
</html>
