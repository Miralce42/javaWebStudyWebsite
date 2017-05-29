<%@ page import="WebDB.StudentDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.InteractionTopic" %>
<%@ page import="beans.TopicComments" %>
<%@ page import="beans.deleteHTMLTag" %><%--
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
      #btn-pri3{
         padding: 2px 10px !important;
         font-size: small;
         width: 120px;
         height: 40px;
         text-align: center;
      }
      .titlea{
         color: #0b5b97;
      }
   </style>
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main">
   <table width="100%">
      <tr>
         <td width="20%" align="center">
            <span class="input-group-btn"><a href="showAllTopic.jsp" class="floatButton"><button type="button" id="btn-pri3" class="btn btn-pri">查看全部话题</button></a></span>
         </td>
         <td height="20" align="right">
            <span class="input-group-btn"><a href="createTopic.jsp" class="floatButton"><button type="button" id="btn-pri2" class="btn btn-pri">创建留言</button></a></span>
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
      <a  href="showAllTopic.jsp?topicType=Other"><h2 class="fh5co-heading animate-box titlea" data-animate-effect="fadeInLeft">其他最新话题</h2></a>
      <hr>
      <div class="row row-bottom-padded-md">
         <%
            count =0;
            for(int i = 0; i < number; i++){
               if(count > 3) break;
               InteractionTopic topic = Topics.get(i);
               String topicType = topic.getTopicType();
               if("Other".equals(topicType)) {
                  count++;
                  //截取话题内容
                  String Content = topic.getContent();
                  //去掉内容中的所有标签，只显示内容
                  String shortContent = deleteHTMLTag.delHTMLTag(Content);
                  if(shortContent.length() > 20) {
                     shortContent = shortContent.substring(0,20);
                  }
                  String name = studentDao.getName(topic.getUsername());
         %>
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
                     //CommentContent原评论内容
                     //shortCommentContent去标签的，截取25字的内容
                     String commentUserName,CommentContent,date,shortCommentContent;
                     if(Comments.size()>0) {
                        TopicComments comment = Comments.get(Comments.size()-1);
                        commentUserName = studentDao.getName(comment.getUsername());
                        CommentContent = comment.getContent();
                        shortCommentContent = deleteHTMLTag.delHTMLTag(CommentContent);
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
                  <a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>" class="lead">了解更多<i class="icon-arrow-right3"></i></a>
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
      <a href="showAllTopic.jsp?topicType=JSP"><h2 class="fh5co-heading animate-box titlea" data-animate-effect="fadeInLeft">JSP相关最新话题</h2></a>
      <hr>
      <div class="row row-bottom-padded-md">
         <%
            count = 0;
            for(int i = 0; i < number; i++){
               if(i > 3) break;
               InteractionTopic topic = Topics.get(i);
               String topicType = topic.getTopicType();
               if("JSP".equals(topicType)) {
                  count++;
                  //截取话题内容
                  String Content = topic.getContent();
                  //去掉内容中的所有标签，只显示内容
                  String shortContent = deleteHTMLTag.delHTMLTag(Content);
                  if(shortContent.length() > 20) {
                     shortContent = shortContent.substring(0,20);
                  }
                  String name = studentDao.getName(topic.getUsername());
         %>
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
                     //CommentContent原评论内容
                     //shortCommentContent去标签的，截取25字的内容
                     String commentUserName,CommentContent,date,shortCommentContent;
                     if(Comments.size()>0) {
                        TopicComments comment = Comments.get(Comments.size()-1);
                        commentUserName = studentDao.getName(comment.getUsername());
                        CommentContent = comment.getContent();
                        shortCommentContent = deleteHTMLTag.delHTMLTag(CommentContent);
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
      <a href="showAllTopic.jsp?topicType=JAVA"><h2 class="fh5co-heading animate-box titlea" data-animate-effect="fadeInLeft">JAVA相关最新话题</h2></a>
      <hr>
      <div class="row row-bottom-padded-md">
         <%
            count = 0;
            for(int i = 0; i < number; i++){
               if(i > 3) break;
               InteractionTopic topic = Topics.get(i);
               String topicType = topic.getTopicType();
               if("JAVA".equals(topicType)) {
                  count++;
                  //截取话题内容
                  String Content = topic.getContent();
                  //去掉内容中的所有标签，只显示内容
                  String shortContent = deleteHTMLTag.delHTMLTag(Content);
                  if(shortContent.length() > 20) {
                     shortContent = shortContent.substring(0,20);
                  }
                  String name = studentDao.getName(topic.getUsername());
         %>
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
                     //CommentContent原评论内容
                     //shortCommentContent去标签的，截取25字的内容
                     String commentUserName,CommentContent,date,shortCommentContent;
                     if(Comments.size()>0) {
                        TopicComments comment = Comments.get(Comments.size()-1);
                        commentUserName = studentDao.getName(comment.getUsername());
                        CommentContent = comment.getContent();
                        shortCommentContent = deleteHTMLTag.delHTMLTag(CommentContent);
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
      <a href="showAllTopic.jsp?topicType=HTML"><h2 class="fh5co-heading animate-box titlea" data-animate-effect="fadeInLeft">HTML相关最新话题</h2></a>
      <hr>
      <div class="row row-bottom-padded-md">
         <%
            count = 0;
            for(int i = 0; i < number; i++){
               if(i > 3) break;
               InteractionTopic topic = Topics.get(i);
               String topicType = topic.getTopicType();
               if("HTML".equals(topicType)) {
                  count++;
                  //截取话题内容
                  String Content = topic.getContent();
                  //去掉内容中的所有标签，只显示内容
                  String shortContent = deleteHTMLTag.delHTMLTag(Content);
                  if(shortContent.length() > 20) {
                     shortContent = shortContent.substring(0,20);
                  }
                  String name = studentDao.getName(topic.getUsername());
         %>
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
                     //CommentContent原评论内容
                     //shortCommentContent去标签的，截取25字的内容
                     String commentUserName,CommentContent,date,shortCommentContent;
                     if(Comments.size()>0) {
                        TopicComments comment = Comments.get(Comments.size()-1);
                        commentUserName = studentDao.getName(comment.getUsername());
                        CommentContent = comment.getContent();
                        shortCommentContent = deleteHTMLTag.delHTMLTag(CommentContent);
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
         </div
         <%
               }
            }
         %>
      </div>
   </div>
   <!---->
</div>
</body>
</html>