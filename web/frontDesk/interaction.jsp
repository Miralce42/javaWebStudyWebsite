<%@ page import="WebDB.StudentDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.InteractionTopic" %><%--
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
        StudentDAO stuDao = new StudentDAO();
        ArrayList<InteractionTopic> Topics = new ArrayList<InteractionTopic>();
        int number = stuDao.getAllTopic(Topics);
    %>

        <div  class="fh5co-narrow-content">
            <h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">其他最新话题</h2>
            <hr>
            <%
                for(int i = 0; i < number; i++){
                    if(i > 4) break;
                    InteractionTopic topic = Topics.get(i);
                    String topicType = topic.getTopicType();
                    if("Other".equals(topicType)) {
                        //截取话题内容
                        String shortContent = topic.getContent();
                        //去掉内容中的图片，用【图片】代替  ！important
                        shortContent =  shortContent.replaceAll("<img.*>.*</img>",  "<i>【图片】</i>").replaceAll("<img.*/>", "<i>【图片】</i>");
                        if(shortContent.length() > 25) {
                            shortContent = shortContent.substring(0,25);
                        }
                        String name = stuDao.getName(topic.getUsername());
            %>
            <div class="row row-bottom-padded-md">
                <div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft">
                    <div class="blog-entry">
                        <div class="desc">
                            <h2><a href="topicDetail.jsp?topicId=<%=topic.getTopicId()%>"><%=topic.getTitle()%></a></h2>
                            <h4>
                            <span><small>by <%=name%> </small> / <small> <%=topic.getDate()%> </small></span>
                                <div id="content">
                                    <p><%=shortContent%></p>
                                </div>
                            </h4>
                            <h7>最近评论：</h7>
                            <span><small>by Admin </small> / <small> Web Design </small> / <small> <i class="icon-comment"></i> 14</small></span>
                            <p>Design must be functional and functionality must be translated into visual aesthetics</p>
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
