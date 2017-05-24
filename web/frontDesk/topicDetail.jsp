<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.InteractionTopic" %>
<%@ page import="beans.TopicComments" %>
<%@ page import="java.util.ArrayList" %><%--
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
        img{
            display:block;
              width: auto;
              height: auto;
              max-width: 100%;
              max-height: 100%;
        }
        #contentDetail{
            padding-left: 20%;
            padding-right: 20%;
        }
        p {
            margin-bottom: 0.1em;
        }
        #shr {
            margin-top: 5px;
            margin-bottom: 5px;
        }
    </style>
    <script src="js/myJs.js"></script>
    <!--输入合法性检验-->
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main">
    <%
        String topicId = request.getParameter("topicId");
        if(topicId != null) {
            session.setAttribute("topicId", topicId);
        }
        else{
            topicId = (String)session.getAttribute("topicId");
        }
        StudentDAO studentDAO = new StudentDAO();
        InteractionTopic topic = studentDAO.getOneTopic(topicId);
        ArrayList<TopicComments> Comments = studentDAO.getAllComment(topicId);
        String topicUserName = studentDAO.getName(topic.getUsername());
      //  String content = topic.getContent();
      //  content =  content.replaceAll("<img.*>.*</img>",  "<img.*>.*</img><br>").replaceAll("<img.*/>", "<img.*/><br>");
    %>
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <div class="panel panel-headline">
                    <div class="panel-body">
                        <div align="right">
                            <a onclick="location.href='javascript:history.go(-1);'" class="floatButton">返回</a>
                        </div>
                        <hr>
                        <h2><i class="text-primary">标题：</i><%=topic.getTitle()%></h2>
                        <p class="text-primary text-right"> by <STRONG><%=topicUserName%></STRONG>  /  <%=topic.getDate()%> </p>
                        <hr>
                        <div id="contentDetail">
                            <p class="lead"><h4><i class="text-primary">内容：</i><hr><%=topic.getContent()%></h4></p>
                        </div>
                        <hr>
                        <%
                            for(int i = 0 ;i < Comments.size() ; i++){
                                TopicComments comment = Comments.get(i);
                        %>
                        <div class="well">
                            <span class="text-left"><strong><%=studentDAO.getName(comment.getUsername())%></strong></span></h4>/
                            <span class="text-right"><%=comment.getDate()%></span>
                            <hr id="shr">
                            <%=comment.getContent()%>
                        </div>
                        <%
                            }
                        %>
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
            <form name="form1" action="createComment.action"  method="post">
                <div class="panel-body">
                    <textarea style="width: 80%" title="编辑器" name="comment.content" id="comment" class="ckeditor"></textarea>
                    <hr>
                    <div align="right">
                    <a onclick="commentCheck()" class="floatButton">发布评论</a>
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