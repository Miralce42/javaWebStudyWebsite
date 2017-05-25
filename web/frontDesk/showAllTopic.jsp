<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.InteractionTopic" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.TopicComments" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/24
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>全部话题显示</title>
    <link rel="stylesheet" href="../myCss/buttonStyle.css">
    <style type="text/css">
        #comment{
             padding-left: 10%;
             padding-right: 10%;
         }
        #content{
            padding-left: 5%;
            padding-right: 5%;
        }
        .all{
            margin-bottom: 0.1em;
        }
        h4{
            margin: 0 0 3px 0;
        }
        p {
            text-indent:2em;
            color: black;
        }
    </style>
    <script src="js/myJs.js"></script>
    <!--输入合法性检验-->
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main">
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <h3 class="page-title">Typography</h3>

                <div class="panel panel-headline">
                    <%
                        StudentDAO studentDao = new StudentDAO();
                        ArrayList<InteractionTopic> Topics = new ArrayList<InteractionTopic>();
                        int number = studentDao.getAllTopic(Topics);
                        String gpTopicType = request.getParameter("topicType");
                        if(gpTopicType==null){
                            for(int i = 0; i < number; i++){
                                InteractionTopic topic = Topics.get(i);
                                    //截取话题内容
                                    String shortContent = topic.getContent();
                                    //去掉内容中的图片，用【图片】代替  ！important
                                    shortContent =  shortContent.replaceAll("<img.*>.*</img>",  "<i>【图片】</i>").replaceAll("<img.*/>", "<i>【图片】</i>");
                                    if(shortContent.length() > 50) {
                                        shortContent = shortContent.substring(0,50);
                                    }
                                    String name = studentDao.getName(topic.getUsername());
                    %>
                    <a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>">
                    <div class="well">
                        <h4><%=topic.getTitle()%></h4>
                        <p  class="text-right text-primary"> <small>by <%=name%> </small> / <small> <%=topic.getDate()%> </small></p>
                        <hr>
                        <%=shortContent%>
                        <hr>
                        <%
                            ArrayList<TopicComments> Comments = studentDao.getAllComment(topic.getTopicId());
                            String commentUserName,shortCommentContent,date;
                            if(Comments.size()>0) {
                                TopicComments comment = Comments.get(Comments.size()-1);
                                commentUserName = studentDao.getName(comment.getUsername());
                                shortCommentContent = comment.getContent();
                                date = comment.getDate();
                                if (shortCommentContent.length() > 40) {
                                    shortCommentContent = shortCommentContent.substring(0, 40);
                                }
                            }
                            else {
                                commentUserName=date="";
                                shortCommentContent="暂时还没有评论";
                            }
                        %>
                        <div id="comment">
                            <p class="text-danger">最近评论：</p>
                            <p class="all"><%=shortCommentContent%></p>
                            <p  class="text-right text-primary"> <small>by <%=commentUserName%> </small>/ <small> <%=date%></small></p>
                        </div>
                    </div>
                    </a>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT -->
    </div>
    <!-- END MAIN -->
</div>
</body>
</html>
