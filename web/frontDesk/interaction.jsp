<%@ page import="WebDB.StudentDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.InteractionTopic" %>
<%@ page import="beans.TopicComments" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/20
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课堂互动</title>
    <link rel="stylesheet" href="../css/style.css">
    <style type="text/css">
        #btn-pri1{
             padding: 2px 10px !important;
             font-size: small;
             width: 40px;
             height: 40px;
             text-align: center;
         }
        #btn-pri2{
            padding: 2px 10px !important;
            font-size: small;
            width: 80px;
            height: 40px;
            text-align: center;
        }
        #title{
            width: 99%;
        }
    </style>
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main">
    <table width="100%" border="0" bgcolor="black">
        <tr>
            <td height="20" align="left">
                <form class="navbar-form navbar-left">
                    <div class="input-group">
                        <br>
                        <input id="title"  type="text" value="" class="form-control" placeholder="搜索标题">&nbsp;
                        <span class="input-group-btn"><button id="btn-pri1"  type="button" class="btn btn-pri">Go</button></span>
                    </div>
                </form>
            </td>
            <td height="20" align="right">
                <span class="input-group-btn"><a href="createTopic.jsp"><button type="button" id="btn-pri2" class="btn btn-pri">创建留言</button></a></span>
            </td>
        </tr>
    </table>

    <%
        StudentDAO studentDao = new StudentDAO();
        ArrayList<InteractionTopic> Topics = new ArrayList<InteractionTopic>();
        int number = studentDao.getAllTopic(Topics);
        int count = 0;
    %>
    <!--Other-->
        <div  class="fh5co-narrow-content">
            <h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">其他最新话题</h2>
            <hr>
            <%
                count =0;
                for(int i = 0; i < number; i++){
                    if(count > 3) break;
                    InteractionTopic topic = Topics.get(i);
                    String topicType = topic.getTopicType();
                    if("Other".equals(topicType)) {
                        count++;
                        //截取话题内容
                        String shortContent = topic.getContent();
                        //去掉内容中的图片，用【图片】代替  ！important
                        shortContent =  shortContent.replaceAll("<img.*>.*</img>",  "<i>【图片】</i>").replaceAll("<img.*/>", "<i>【图片】</i>");
                        if(shortContent.length() > 25) {
                            shortContent = shortContent.substring(0,25);
                        }
                        String name = studentDao.getName(topic.getUsername());
            %>
            <div class="row row-bottom-padded-md">
                <div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft">
                    <div class="blog-entry">
                        <div class="desc">
                            <h2><a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>"><%=topic.getTitle()%></a></h2>
                            <h4>
                            <span><small>by <%=name%> </small> / <small> <%=topic.getDate()%> </small></span>
                                <div class="content">
                                    <p><%=shortContent%></p>
                                </div>
                            </h4>
                            <%
                                ArrayList<TopicComments> Comments = studentDao.getAllComment(topic.getTopicId());
                                String commentUserName,shortCommentContent,date;
                                if(Comments.size()>0) {
                                    TopicComments comment = Comments.get(Comments.size()-1);
                                    commentUserName = studentDao.getName(comment.getUsername());
                                    shortCommentContent = comment.getContent();
                                    date = comment.getDate();
                                    if (shortCommentContent.length() > 25) {
                                        shortCommentContent = shortCommentContent.substring(0, 25);
                                    }
                                }
                                else {
                                    commentUserName=date="";
                                    shortCommentContent="暂时还没有评论";
                                 }
                            %>
                            <h7>最近评论：</h7>
                            <span><small>by <%=commentUserName%> </small>/ <small> <%=date%></small></span>
                            <p><%=shortCommentContent%></p>
                            <a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>" class="lead">Read More <i class="icon-arrow-right3"></i></a>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    <!--JSP-->
        <div  class="fh5co-narrow-content">
            <h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">JSP相关最新话题</h2>
            <hr>
            <%
                count = 0;
                for(int i = 0; i < number; i++){
                    if(i > 3) break;
                    InteractionTopic topic = Topics.get(i);
                    String topicType = topic.getTopicType();
                    if("JSP".equals(topicType)) {
                        count++;
                        //截取话题内容
                        String shortContent = topic.getContent();
                        //去掉内容中的图片，用【图片】代替  ！important
                        shortContent =  shortContent.replaceAll("<img.*>.*</img>",  "<i>【图片】</i>").replaceAll("<img.*/>", "<i>【图片】</i>");
                        if(shortContent.length() > 25) {
                            shortContent = shortContent.substring(0,25);
                        }
                        String name = studentDao.getName(topic.getUsername());
            %>
            <div class="row row-bottom-padded-md">
                <div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft">
                    <div class="blog-entry">
                        <div class="desc">
                            <h2><a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>"><%=topic.getTitle()%></a></h2>
                            <h4>
                                <span><small>by <%=name%> </small> / <small> <%=topic.getDate()%> </small></span>
                                <div class="content">
                                    <p><%=shortContent%></p>
                                </div>
                            </h4>
                            <%
                                ArrayList<TopicComments> Comments = studentDao.getAllComment(topic.getTopicId());
                                String commentUserName,shortCommentContent,date;
                                if(Comments.size()>0) {
                                    TopicComments comment = Comments.get(Comments.size()-1);
                                    commentUserName = studentDao.getName(comment.getUsername());
                                    shortCommentContent = comment.getContent();
                                    date = comment.getDate();
                                    if (shortCommentContent.length() > 25) {
                                        shortCommentContent = shortCommentContent.substring(0, 25);
                                    }
                                }
                                else {
                                    commentUserName=date="";
                                    shortCommentContent="暂时还没有评论";
                                }
                            %>
                            <h7>最近评论：</h7>
                            <span><small>by <%=commentUserName%> </small>/ <small> <%=date%></small></span>
                            <p><%=shortCommentContent%></p>
                            <a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>" class="lead">Read More <i class="icon-arrow-right3"></i></a>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    <!--JAVA-->
    <div  class="fh5co-narrow-content">
        <h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">JAVA相关最新话题</h2>
        <hr>
        <%
            count = 0;
            for(int i = 0; i < number; i++){
                if(i > 3) break;
                InteractionTopic topic = Topics.get(i);
                String topicType = topic.getTopicType();
                if("JAVA".equals(topicType)) {
                    count++;
                    //截取话题内容
                    String shortContent = topic.getContent();
                    //去掉内容中的图片，用【图片】代替  ！important
                    shortContent =  shortContent.replaceAll("<img.*>.*</img>",  "<i>【图片】</i>").replaceAll("<img.*/>", "<i>【图片】</i>");
                    if(shortContent.length() > 25) {
                        shortContent = shortContent.substring(0,25);
                    }
                    String name = studentDao.getName(topic.getUsername());
        %>
        <div class="row row-bottom-padded-md">
            <div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft">
                <div class="blog-entry">
                    <div class="desc">
                        <h2><a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>"><%=topic.getTitle()%></a></h2>
                        <h4>
                            <span><small>by <%=name%> </small> / <small> <%=topic.getDate()%> </small></span>
                            <div class="content">
                                <p><%=shortContent%></p>
                            </div>
                        </h4>
                        <%
                            ArrayList<TopicComments> Comments = studentDao.getAllComment(topic.getTopicId());
                            String commentUserName,shortCommentContent,date;
                            if(Comments.size()>0) {
                                TopicComments comment = Comments.get(Comments.size()-1);
                                commentUserName = studentDao.getName(comment.getUsername());
                                shortCommentContent = comment.getContent();
                                date = comment.getDate();
                                if (shortCommentContent.length() > 25) {
                                    shortCommentContent = shortCommentContent.substring(0, 25);
                                }
                            }
                            else {
                                commentUserName=date="";
                                shortCommentContent="暂时还没有评论";
                            }
                        %>
                        <h7>最近评论：</h7>
                        <span><small>by <%=commentUserName%> </small>/ <small> <%=date%></small></span>
                        <p><%=shortCommentContent%></p>
                        <a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>" class="lead">Read More <i class="icon-arrow-right3"></i></a>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
    <!--HTML-->
    <div  class="fh5co-narrow-content">
        <h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">HTML相关最新话题</h2>
        <hr>
        <%
            count = 0;
            for(int i = 0; i < number; i++){
                if(i > 3) break;
                InteractionTopic topic = Topics.get(i);
                String topicType = topic.getTopicType();
                if("HTML".equals(topicType)) {
                    count++;
                    //截取话题内容
                    String shortContent = topic.getContent();
                    //去掉内容中的图片，用【图片】代替  ！important
                    shortContent =  shortContent.replaceAll("<img.*>.*</img>",  "<i>【图片】</i>").replaceAll("<img.*/>", "<i>【图片】</i>");
                    if(shortContent.length() > 25) {
                        shortContent = shortContent.substring(0,25);
                    }
                    String name = studentDao.getName(topic.getUsername());
        %>
        <div class="row row-bottom-padded-md">
            <div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft">
                <div class="blog-entry">
                    <div class="desc">
                        <h2><a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>"><%=topic.getTitle()%></a></h2>
                        <h4>
                            <span><small>by <%=name%> </small> / <small> <%=topic.getDate()%> </small></span>
                            <div class="content">
                                <p><%=shortContent%></p>
                            </div>
                        </h4>
                        <%
                            ArrayList<TopicComments> Comments = studentDao.getAllComment(topic.getTopicId());
                            String commentUserName,shortCommentContent,date;
                            if(Comments.size()>0) {
                                TopicComments comment = Comments.get(Comments.size()-1);
                                commentUserName = studentDao.getName(comment.getUsername());
                                shortCommentContent = comment.getContent();
                                date = comment.getDate();
                                if (shortCommentContent.length() > 25) {
                                    shortCommentContent = shortCommentContent.substring(0, 25);
                                }
                            }
                            else {
                                commentUserName=date="";
                                shortCommentContent="暂时还没有评论";
                            }
                        %>
                        <h7>最近评论：</h7>
                        <span><small>by <%=commentUserName%> </small>/ <small> <%=date%></small></span>
                        <p><%=shortCommentContent%></p>
                        <a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>" class="lead">Read More <i class="icon-arrow-right3"></i></a>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
