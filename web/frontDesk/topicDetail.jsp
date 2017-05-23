<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.InteractionTopic" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/23
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>话题详细显示</title>
    <script src="../ckeditor/ckeditor.js">
        CKEDITOR.replace("topic_editor");
    </script>
    <link rel="stylesheet" href="../myCss/buttonStyle.css">
    <style type="text/css">
        #wei {
            margin-left: 10%;!important;
            width: 80%;!important;
        }
    </style>
    <script src="../js/inputCheck.js"></script>
    <!--输入合法性检验-->
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main">
    <%
        String topicId = request.getParameter("topicId");
        StudentDAO studentDAO = new StudentDAO();
        InteractionTopic topic = studentDAO.getOneTopic(topicId);
        String name = studentDAO.getName(topic.getUsername());
    %>
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <div class="panel panel-headline">
                    <div class="panel-body">
                        <h2><%=topic.getTitle()%></h2>
                        <p class="text-primary text-right"> <small>by <%=name%> </small> / <small> <%=topic.getDate()%> </small></p>
                        <hr>
                        <p class="lead"><%=topic.getContent()%></p>
                        <hr>
                        <div class="well">
                            <p class="text-left"><code>.text-left</code> Left aligned text.</p>
                            <hr>
                            <p class="text-center"><code>.text-center</code> Center aligned text.</p>
                            <p class="text-right"><code>.text-right</code> Right aligned text.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT -->
        <!-- INPUTS -->
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title">编写评论</h3>
            </div>
            <hr />
            <form name="form1" action="CrtTopic.action"  method="post">
                <div class="panel-body">
                    <textarea style="width: 80%" title="编辑器" name="topic.content" id="content" class="ckeditor"></textarea>
                    <hr>
                    <div align="right">
                    <a onclick="topicCheck()" class="floatButton">发布</a>
                    </div>
                </div>
            </form>
        </div>
        <!-- END INPUTS -->
    </div>
    <!-- END MAIN -->
</div>
</body>
</html>