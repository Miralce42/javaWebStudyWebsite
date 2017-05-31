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
    <script src="../ckeditor/ckeditor.js">
        CKEDITOR.replace("topic_editor");
    </script>
    <script type="text/javascript">
        function  mulitSubmitCheck() {
            if(document.forms[1].file.value!="") {
                document.forms[1].submit();
                return;
            }
            alert("未选择文件，请选择文件！");
        }
        function  submitCheck() {
            if(document.forms[0].file.value=="")
                alert("未选择文件，请选择文件！");
            else
                document.forms[0].submit();
        }
    </script>
    <title>课件管理</title>
    <%
        //后台管理页面标题,自行修改
        String moduleString="课件管理";
    %>

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
      <div style="margin-top:15%;position:relative;font-size: larger">
          <p align="center" style="font-size: larger;position: relative">课件管理</p>
          <ul style="margin-left: 12%;position: absolute ">
              <li><a href="theCoursewareManagement.jsp?section=第一章">第一章</a>&nbsp;&nbsp;</li>
          </ul>
          <ul style="margin-left: 25%;position: absolute; ">
              <li><a href="theCoursewareManagement.jsp?section=第二章">第二章</a>&nbsp;&nbsp;</li>
           </ul>
          <ul style="margin-left: 35%;position: absolute; ">
              <li><a href="theCoursewareManagement.jsp?section=第二章">第二章</a>&nbsp;&nbsp;</li>
          </ul>
          <ul style="margin-left: 45%;position: absolute; ">
              <li><a href="theCoursewareManagement.jsp?section=第三章">第三章</a>&nbsp;&nbsp;</li>
          </ul>
          <ul style="margin-left: 55%;position: absolute; ">
              <li><a href="theCoursewareManagement.jsp?section=第四章">第四章</a>&nbsp;&nbsp;</li>
          </ul>
          <ul style="margin-left: 65%;position: absolute; ">
              <li><a href="theCoursewareManagement.jsp?section=第五章">第五章</a>&nbsp;&nbsp;</li>
          </ul>
          <ul style="margin-left: 75%;position: absolute; ">
              <li><a href="theCoursewareManagement.jsp?section=第六章">第六章</a>&nbsp;&nbsp;</li>
          </ul>
          <ul  style="margin-left: 85%;position: absolute ">
               <li><a href="theCoursewareManagement.jsp?section=其他">其他</a></li>
          </ul>
      </div>
</div><!--右侧布局-->
</body>
</html>