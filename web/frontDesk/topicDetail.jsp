<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.InteractionTopic" %>
<%@ page import="beans.TopicComments" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Users" %><%--
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
        #topicDeleteBtn {
            display: none;
        }
        .commentDeleteBtn{
            display: none;
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
        String topicUserId = topic.getUsername();
        String topicUserName = studentDAO.getName(topicUserId);
        Users thisUser = (Users)session.getAttribute("user");
        String thisUserId = thisUser.getUsername();
        //  String content = topic.getContent();
        //  content =  content.replaceAll("<img.*>.*</img>",  "<img.*>.*</img><br>").replaceAll("<img.*/>", "<img.*/><br>");
    %>
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <div class="panel panel-headline">
                    <div class="panel-body"  onmousemove="showTopicDiv(<%=topicUserId%>,<%=thisUserId%>)">
                        <div align="right">
                            <a onclick="location.href='javascript:history.go(-1);'" class="floatButton">返回</a>
                        </div>
                        <hr>
                        <h3><i class="text-primary">标题：</i><%=topic.getTitle()%></h3>
                        <table width="100%">
                            <tr>
                                <td align="left">
                                    <div id="topicDeleteBtn">
                                        <a id="topicDelete" onclick="deleteTopic()" href="topicDelete.action?topicId=<%=topic.getTopicId()%>">删除</a>|
                                        <a id="topicUpdate" href="changeTopic.jsp?topicId=<%=topic.getTopicId()%>">修改</a>
                                    </div>
                                </td>
                                <td align="right">
                                <p class="text-primary text-right"> by <STRONG><%=topicUserName%></STRONG>  /  <%=topic.getDate()%> </p>
                                </td>
                            </tr>
                        </table>
                         <hr>
                        <div id="contentDetail">
                            <p class="lead"><h4><i class="text-primary">内容：</i><hr><%=topic.getContent()%></h4></p>
                        </div>
                        <hr>
                        <%
                            for (int i = 0;i < Comments.size();i++) {
                                TopicComments comment = Comments.get(i);
                                String commentUserId = comment.getUsername();
                        %>
                        <!--下面那句话废了我一半的脑细胞，所以不要乱动-->
                        <div class="well" onmousemove="showCommentDiv(<%=commentUserId%>,<%=thisUserId%>,<%=i%>)">
                            <span class="text-left"><strong><%=studentDAO.getName(comment.getUsername())%></strong></span>
                            <span class="text-right"><%=comment.getDate()%></span>
                            <div name="commentDeleteBtn" class="commentDeleteBtn">
                                <a id="commentDelete" onclick="deleteComment()" href="commentDelete.action?commentId=<%=comment.getCommentId()%>">删除</a>
                            </div>
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