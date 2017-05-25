<%@ page import="beans.InteractionTopic" %>
<%@ page import="WebDB.StudentDAO" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/25
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改话题</title>
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
    <script src="js/myJs.js"></script>
    <!--输入合法性检验-->
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<%
    String topicId = request.getParameter("topicId");
    StudentDAO studentDAO = new StudentDAO();
    if(topicId != null) {
        InteractionTopic topic = studentDAO.getOneTopic(topicId);
%>
<div id="fh5co-main">
    <aside id="fh5co-hero" class="js-fullheight">
        <div class="flexslider js-fullheight">
            <ul class="slides">
                <li style="background-image: url(../frontDesk_assets/images/EyeOfGod.jpg);">
                    <div class="overlay"></div>
                    <div class="container-fluid">
                        <div class="row">
                            <div id="wei" class="col-md-8 col-md-offset-2 text-center js-fullheight slider-text">
                                <div class="slider-text-inner">
                                    <!-- INPUTS -->
                                    <div class="panel">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">修改话题</h3>
                                        </div>
                                        <hr />
                                        <form name="form1" action="updateTopic.action?topicId=<%=topic.getTopicId()%>"  method="post">
                                            <div class="panel-body">
                                                话题种类：
                                                <label class="fancy-radio">
                                                 <%=topic.getTopicType()%>
                                                </label>
                                                <hr />
                                                <input type="text" disabled="disabled" class="form-control" value="<%=topic.getTitle()%>">
                                                <hr />
                                                <textarea style="width: 80%" title="编辑器" name="topic.content" id="content" class="ckeditor"><%=topic.getContent()%></textarea>
                                                <hr>
                                                <a onclick="UpdateTopic()" class="floatButton">修改</a>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- END INPUTS -->
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </aside>
    <%
        }
    %>
</div>
</body>
</html>

