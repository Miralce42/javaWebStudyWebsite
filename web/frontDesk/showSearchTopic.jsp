<%@ page import="WebDB.StudentDAO" %>
<%@ page import="beans.InteractionTopic" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.TopicComments" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/25
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>话题搜索结果显示</title>
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
            String condition = request.getParameter("condition");
            System.out.println("condition="+condition);
            ArrayList<InteractionTopic> Topics = studentDao.selectSearchTopic(condition);
        %>
        <div class="main-content">
            <div class="container-fluid">
                <table width="100%">
                    <tr>
                        <td width="60%"><h3 class="page-title"><br>搜索话题结果</h3></td>
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
                            <a href="showAllTopic.jsp" class="floatButton">刷新</a>
                        </td>
                    </tr>
                </table>
                <hr>
                <div class="panel panel-headline">
                    <%
                        for (InteractionTopic topic : Topics) {
                            //截取话题内容
                            String shortContent = topic.getContent();
                            //去掉内容中的图片，用【图片】代替  ！important
                            shortContent = shortContent.replaceAll("<img.*>.*</img>", "<i>【图片】</i>").replaceAll("<img.*/>", "<i>【图片】</i>");
                            if (shortContent.length() > 50) {
                                shortContent = shortContent.substring(0, 50);
                            }
                            String name = studentDao.getName(topic.getUsername());
                    %>
                    <a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>">
                        <div class="well">
                            <h4><%=topic.getTitle()%>
                            </h4>
                            <p class="text-right text-primary">
                                <small>by <%=name%>
                                </small>
                                /
                                <small><%=topic.getDate()%>
                                </small>
                            </p>
                            <hr>
                            <%=shortContent%>
                            <hr>
                            <%
                                ArrayList<TopicComments> Comments = studentDao.getAllComment(topic.getTopicId());
                                String commentUserName, shortCommentContent, date;
                                if (Comments.size() > 0) {
                                    TopicComments comment = Comments.get(Comments.size() - 1);
                                    commentUserName = studentDao.getName(comment.getUsername());
                                    shortCommentContent = comment.getContent();
                                    date = comment.getDate();
                                    if (shortCommentContent.length() > 40) {
                                        shortCommentContent = shortCommentContent.substring(0, 40);
                                    }
                                } else {
                                    commentUserName = date = "";
                                    shortCommentContent = "暂时还没有评论";
                                }
                            %>
                            <div id="comment">
                                <p class="text-danger">最近评论：</p>
                                <p class="all"><%=shortCommentContent%>
                                </p>
                                <p class="text-right text-primary">
                                    <small>by <%=commentUserName%>
                                    </small>
                                    /
                                    <small><%=date%>
                                    </small>
                                </p>
                            </div>
                        </div>
                    </a>
                    <%
                        }
                    %>
                </div>
        <!-- END MAIN CONTENT -->
    </div>
    <!-- END MAIN -->
</div>
</body>
</html>
