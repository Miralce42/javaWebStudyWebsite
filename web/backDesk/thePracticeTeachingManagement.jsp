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
    <title>实践教学管理</title>
    <%
        //后台管理页面标题,自行修改
        String moduleString="实践教学管理";
    %>

</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right"><!--右侧布局-->
     <div><!--文件上传-->
           <form action="multipracticefile.action" method="post" enctype="multipart/form-data" >
            <label>上传资料</label><br/>
            <input type="text" name="file_type" id="file_type" value="实践教学资料" hidden="hidden"/>
            文件类型：
            <select name="Section" onchange="this.parentNode.nextSibling.value=this.value;">
                <option value="其他">其他</option>
                <option value="实验文件">实验文件</option>
            </select><br>
<%--
            <input name="Section" id="Section" value="实验文件" hidden/>
--%>
            <input type="file" name="file"  multiple/><br>
            <input type="button" value="上传" onclick="mulitSubmitCheck()"/>
            </form>
     </div><!--文件上传-->
    <div><!--工具链接--->
         <hr>
         <label>添加通知</label>
         <form action="practicedbinsert.action" method="get"  >
             <input type="text" name="file_type" value="实践教学资料" hidden="hidden"/>
             <input name="Section"  value="通知" hidden/>
             <textarea style="width: 80%" title="编辑器" name="file_name" id="file_name" class="ckeditor"></textarea>
             <input type="button" value="提交" onclick="linkAddCheck()" >
         </form>
     </div><!--工具链接-->
       <%
           String[] filetype={"实验文件","通知","其他"};

           for(int i=0;i<filetype.length;i++) {
       %>
         <div><!--类别-->
             <label><%=filetype[i]%></label><br/>
       <%
               String [] param={"实践教学资料",filetype[i]};
               FileDAO fileDAO=new FileDAO();
               int temp=0;
               try {
                   ResultSet resultSet = fileDAO.getResultSet("select * from teachingfile where file_type=? and chapter=? order by upload_date", param);
                   out.println("<table border=2><tr>");
                   if(filetype[i].equals("通知")) {
                       while (resultSet.next()) {
                           if (temp % 3 == 0)
                               out.println("</tr><tr>");
                           out.println("<td>" + resultSet.getString("file_name") + "</td>");
                           out.println("<td> <a href='practicedbdelete.action?info=实践教学资料/");
                           out.println(filetype[i] + "/" + resultSet.getString("file_name") + "'>删除" + "</a></td>");
                           temp++;
                       }
                       out.println("</tr></table></form>");
                   }
                   else {
                       while (resultSet.next()) {
                           if (temp % 3 == 0)
                               out.println("</tr><tr>");
                           out.println("<td>" + resultSet.getString("file_name") + "</td>");
                           out.println("<td> <a href='/delete.servlet?filename=实践教学资料/");
                           out.println(filetype[i] + "/" + resultSet.getString("file_name") + "'>删除" + "</a></td>");
                           temp++;
                       }
                       out.println("</tr></table></form>");
                   }
               }//try
               catch(Exception a){
                   a.printStackTrace();
               }
       %>
         </div><!--类别-->
        <%
           }//for
       %>
</div><!--右侧布局-->
</body>
</html>