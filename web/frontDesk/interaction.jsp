<%--
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
        <div class="fh5co-narrow-content">
            <h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">关于jsp</h2>
            <div class="row row-bottom-padded-md">
                <div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft">
                    <div class="blog-entry">
                        <div class="desc">
                            <h3><a href="#">我是一个长长的标题啦啦啦</a></h3>
                            <h4>
                            <span><small>by Admin </small> / <small> Web Design </small> / <small> <i class="icon-comment"></i> 14</small></span>
                            Design must be functional and functionality must be translated into visual aesthetics
                            </h4>
                            <h7>最近评论：</h7>
                            <span><small>by Admin </small> / <small> Web Design </small> / <small> <i class="icon-comment"></i> 14</small></span>
                            <p>Design must be functional and functionality must be translated into visual aesthetics</p>
                            <a href="#" class="lead">Read More <i class="icon-arrow-right3"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
