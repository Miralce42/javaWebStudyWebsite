<%--
  Created by Dreamer.
  User: Dreamer
  Date: 2017/5/22
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        function  mulitSubmitCheck() {
            var i=0;
            for(i;i<4;i++) {
                if(document.forms[1].file[i].value!="") {
                    document.forms[1].submit();
                    return;
                }
            }
            alert("未选择文件，请选择文件！");

        }
    </script>
    <title>实践教学管理</title>
    <%
        //后台管理页面标题,自行修改
        String moduleString="实践教学管理";
    %>

</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right"><!--右侧布局-->
     <div >
     <form action="multifile.action" method="post" enctype="multipart/form-data" >
    <label>文件上传</label><br/>
    <input type="text" name="file_type" id="file_typem" value="实践教学资料" hidden="hidden"/>
    文件类型：
    <select name="Section" onchange="this.parentNode.nextSibling.value=this.value;">
        <option value="其他">其他</option>
        <option value="实验文件">实验文件</option>
    </select><br>
    <input type="file" name="file"  multiple/><br>
    <input type="button" value="上传" onclick="mulitSubmitCheck()"/>
    </form>
</div><!--多文件上传-->
</div><!--右侧布局-->
</body>
</html>