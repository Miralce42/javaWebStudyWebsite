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
                if(document.forms[0].file.value!="") {
                    document.forms[0].submit();
                    return;
                }
            alert("未选择文件，请选择文件！");
        }
        function   linkAddCheck() {
           var content = CKEDITOR.instances.file_name.getData();//CKEDITOR.instances["file_name"].getData()
          if(content!="") {
              document.forms[1].submit();
              return;
          }
          alert("未添加信息！");
        }
    </script>
    <title>教学资源管理</title>
    <%
        //后台管理页面标题,自行修改
        String moduleString="教学资源管理";
    %>

</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right"><!--右侧布局-->
     <div><!--文件上传-->
           <form action="multiresourcefile.action" method="post" enctype="multipart/form-data" >
            <label>上传资料</label><br/>
            <input type="text" name="file_type" id="file_type" value="教学资源管理" hidden="hidden"/>
            文件类型：
            <select name="Section" onchange="this.parentNode.nextSibling.value=this.value;">
                <option value="其他">其他</option>
                <option value="参考资料">参考资料</option>
                <option value="大纲">大纲</option>
                <option value="链接">链接</option>
            </select><br>
            <input type="file" name="file"  multiple/><br>
            <input type="button" value="上传" onclick="mulitSubmitCheck()"/>
            </form>
     </div><!--文件上传-->
    <div style="height: 40%"><!--工具链接--->
         <hr>
         <label>添加工具链接</label>
         <form action="resourcedbinsert.action" method="get"  >
             <input type="text" name="file_type" value="教学资源管理" hidden="hidden"/>
             <input name="Section"  value="链接" hidden/>
             <textarea style="width: 80%" title="编辑器" name="file_name" id="file_name" class="ckeditor"></textarea>
             <input type="button" value="提交" onclick="linkAddCheck()" >
         </form>
     </div><!--工具链接-->
      <div style="margin-top:10%;position:relative;font-size: larger">
          <p align="center" style="font-size: larger;position: relative">资源管理</p>
          <ul style="margin-left: 15%;position: absolute ">
           <li><a href="theResourceManagement.jsp?section=大纲">大纲</a>&nbsp;&nbsp;</li>
          </ul>
          <ul style="margin-left: 35%;position: absolute; ">
              <li><a href="theResourceManagement.jsp?section=参考资料">参考资料</a>&nbsp;&nbsp;</li>
           </ul>
          <ul  style="margin-left: 75%;position: absolute ">
              <li><a href="theResourceManagement.jsp?section=链接">链接</a></li>
          </ul>
          <ul  style="margin-left: 55%;position: absolute ">
               <li><a href="theResourceManagement.jsp?section=其他">其他</a></li>
          </ul>
      </div>
</div><!--右侧布局-->
</body>
</html>