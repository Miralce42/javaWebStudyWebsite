<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.InteractionTopic" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.TopicComments" %>
<%@ page import="beans.deleteHTMLTag" %><%--
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
        <%
            StudentDAO studentDao = new StudentDAO();
            ArrayList<InteractionTopic> Topics = new ArrayList<InteractionTopic>();
            int number = studentDao.getAllTopic(Topics);
            String gpTopicType = request.getParameter("topicType");
            String showType = gpTopicType;
            if (gpTopicType == null) showType="全部";
            else if("Other".equals(gpTopicType)) showType="其他";
        %>
        <div class="main-content">
            <div class="container-fluid">
                <table width="100%">
                <tr>
                    <td width="60%"><h3 class="page-title"><br><%=showType%>类型话题</h3></td>
                    <td height="20">
                        <form name="form1" class="navbar-form navbar-left" action="showSearchTopic.jsp" method="post">
                            <div class="input-group">
                                <span class="input-group-btn"> <input name="condition" id="condition"  type="text" value="" class="form-control" placeholder="搜索话题">&nbsp;</span>
                                <a onclick="searchTopic()" class="floatButton">GO!</a>
                            </div>
                        </form>
                    </td>
                    <td height="20" align="right">
                         <a href="createTopic.jsp" class="floatButton">创建话题</a>
                    </td>
                    <td>
                        <a href="interaction.jsp" class="floatButton">话题主页面</a>
                    </td>
                </tr>
                </table>
                <hr>

                <div class="panel panel-headline">
                    <%
                        if(gpTopicType==null){
                            for(int i = 0; i < number; i++){
                                InteractionTopic topic = Topics.get(i);
                                String Content = topic.getContent();
                                //去掉内容中的所有标签，只显示内容
                                String shortContent = deleteHTMLTag.delHTMLTag(Content);
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
                        <P><%=shortContent%></p>
                        <hr>
                        <%
                            ArrayList<TopicComments> Comments = studentDao.getAllComment(topic.getTopicId());
                            //CommentContent原评论内容
                            //shortCommentContent去标签的，截取25字的内容
                            String commentUserName,CommentContent,date,shortCommentContent;
                            if(Comments.size()>0) {
                                TopicComments comment = Comments.get(Comments.size()-1);
                                commentUserName = studentDao.getName(comment.getUsername());
                                CommentContent = comment.getContent();
                                shortCommentContent = deleteHTMLTag.delHTMLTag(CommentContent);
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
                        else{
                                for(int i = 0; i < number; i++){
                                    InteractionTopic topic = Topics.get(i);
                                    String topicType = topic.getTopicType();
                                    if(gpTopicType.equals(topicType)) {
                                        //截取话题内容
                                        String Content = topic.getContent();
                                        //去掉内容中的所有标签，只显示内容
                                        String shortContent = deleteHTMLTag.delHTMLTag(Content);
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
                            <p><%=shortContent%></P>
                            <hr>
                            <%
                                ArrayList<TopicComments> Comments = studentDao.getAllComment(topic.getTopicId());
                                //CommentContent原评论内容
                                //shortCommentContent去标签的，截取25字的内容
                                String commentUserName,CommentContent,date,shortCommentContent;
                                if(Comments.size()>0) {
                                    TopicComments comment = Comments.get(Comments.size()-1);
                                    commentUserName = studentDao.getName(comment.getUsername());
                                    CommentContent = comment.getContent();
                                    shortCommentContent = deleteHTMLTag.delHTMLTag(CommentContent);
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
