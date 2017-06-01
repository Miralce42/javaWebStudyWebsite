<%@ page import="beans.Users" %><%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/20
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>JAVAWEB教学资源网主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
    <meta name="keywords" content="free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />




    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content="" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:url" content="" />
    <meta name="twitter:card" content="" />

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico">

    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">

    <!-- Animate.css -->
    <link rel="stylesheet" href="../frontDesk_assets/css/animate.css">
    <!-- Icomoon Icon Fonts-->
    <link rel="stylesheet" href="../frontDesk_assets/css/icomoon.css">
    <!-- Bootstrap  -->
    <link rel="stylesheet" href="../frontDesk_assets/css/bootstrap.css">
    <!-- Flexslider  -->
    <link rel="stylesheet" href="../frontDesk_assets/css/flexslider.css">
    <!-- Theme style  -->
    <link rel="stylesheet" href="../frontDesk_assets/css/style.css">

    <link rel="stylesheet" href="../myCss/buttonStyle.css">
    <!-- Modernizr JS -->
    <script src="../frontDesk_assets/js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="../frontDesk_assets/js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        #login{
            color: #228896;
        }
    </style>
</head>
<body>
<div id="fh5co-page">
    <aside id="fh5co-aside" role="complementary" class="border js-fullheight">
        <h3 id="fh5co-logo" style="margin-bottom: 0.3em;"><a href="myInfo.jsp">Java Web</a></h3>
        <%
            Users user = (Users) session.getAttribute("user");
            if(user == null){
                out.println("<h6 align=\"center\"><a id=\"login\" href=\"../login.jsp\">登录</a></h6>");
            }
            else{
                out.println("<h6 align=\"center\">欢迎登录，<a id=\"login\" href=\"myInfo.jsp\">");
                out.println(user.getName());
                out.println("<br><br></a><a id=\"login\" href=\"logout.action\">注销</a></h6>");
            }
        %>
        <nav id="fh5co-main-menu" role="navigation">
            <ul>
                <li><a href="index.jsp">主页</a></li>
                <li><a href="theTeachingCourseware.jsp">教学课件</a></li>
                <li><a href="theTeachingResource.jsp">教学资源</a></li>
                <li><a href="interaction.jsp">课程互动</a></li>
                <li><a href="studentHomework.jsp">我的作业</a></li>
                <li><a href="thePracticeTeaching.jsp">实践教学</a></li>
                <li><a href="myInfo.jsp">我的信息</a></li>
                <li><a href="evaluate.jsp">课程评价</a></li>
                <li><a href="contact.html">联系</a></li>
            </ul>
        </nav>

        <div class="fh5co-footer">
            <p><small>&copy; 2016 CS 151 Class. All Rights.</small></p>
        </div>
    </aside>

    <div id="fh5co-main">

        <aside id="fh5co-hero" class="js-fullheight">
            <div class="flexslider js-fullheight">
                <ul class="slides">
                    <li style="background-image: url(../frontDesk_assets/images/5.jpg);">
                        <div class="overlay"></div>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-2 text-center js-fullheight slider-text">
                                    <div class="slider-text-inner">
                                        <h1>JAVA WEB <strong></strong> will teach you how to create your own Website.</h1>
                                        <p><a class="btn btn-primary btn-demo popup-vimeo" href="theTeachingResource.jsp"> <i class="icon-monitor" ></i>下载资源</a> <a href="theTeachingCourseware.jsp" class="btn  btn-primary btn-learn">学习<i class="icon-arrow-right3"></i></a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li style="background-image: url(../frontDesk_assets/images/3.jpg);">
                        <div class="overlay"></div>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-2 text-center js-fullheight slider-text">
                                    <div class="slider-text-inner">
                                        <h1>JAVA WEB <strong></strong> will teach you how to create your own Website.</h1>
                                        <p><a class="btn btn-primary btn-demo popup-vimeo" href="theTeachingResource.jsp"> <i class="icon-monitor" ></i>下载资源</a> <a href="theTeachingCourseware.jsp" class="btn  btn-primary btn-learn">学习<i class="icon-arrow-right3"></i></a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li style="background-image: url(../frontDesk_assets/images/4.jpg);">
                        <div class="overlay"></div>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-2 text-center js-fullheight slider-text">
                                    <div class="slider-text-inner">
                                        <h1>JAVA WEB <strong></strong> will teach you how to create your own Website.</h1>
                                        <p><a class="btn btn-primary btn-demo popup-vimeo" href="theTeachingResource.jsp"> <i class="icon-monitor" ></i>下载资源</a> <a href="theTeachingCourseware.jsp" class="btn  btn-primary btn-learn">学习<i class="icon-arrow-right3"></i></a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </aside>
        <div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>


        <div class="fh5co-narrow-content">
            <div class="row row-bottom-padded-md">
                <div class="col-md-6 animate-box" data-animate-effect="fadeInLeft">
                    <img class="img-responsive" src="../frontDesk_assets/images/java2.jpeg" alt="Free HTML5 Bootstrap Template by FreeHTML5.co">
                </div>
                <div class="col-md-6 animate-box" data-animate-effect="fadeInLeft">
                    <h2 class="fh5co-heading">课程简介</h2>
                    <p>本课程正是以掌握Java Web应用开发为目标，介绍了Web应用开发多个层面上的技术，针对Java Web初学者，按照由浅人深，通俗易懂的原则介绍了Java Web应用开发基础知识、高级技术、编程应用实例，让学员了解、掌握Java Web应用及开发的基本思想，能用它编制面向对象和网络化的程序，并且能够根据实际需求编制出一些实用的程序。</p>
                </div>
            </div>
        </div>

        <div class="fh5co-narrow-content">
            <div class="row row-bottom-padded-md">
                <div class="col-md-6 animate-box" data-animate-effect="fadeInLeft">
                    <img class="img-responsive" src="../frontDesk_assets/images/2.jpg" alt="Free HTML5 Bootstrap Template by FreeHTML5.co">
                </div>
                <div class="col-md-6 animate-box" data-animate-effect="fadeInLeft">
                    <h2 class="fh5co-heading">教学队伍</h2>
                    <p>王志强，男，毕业于信息工程大学，主要研究方向web开发，移动互联网开发，多年来一直从事“《java/.net程序设计》”、“《移动互联网开发技术》”等课程的教学工作，教学效果良好。个人有较强的开发实战经验，为教学提供充足的实践经验，先后指导学生参加“互联网+，挑战杯”竞赛等，均取得不错的成绩。</p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="../frontDesk_assets/js/jquery.min.js"></script>
<!-- jQuery Easing -->
<script src="../frontDesk_assets/js/jquery.easing.1.3.js"></script>
<!-- Bootstrap -->
<script src="../frontDesk_assets/js/bootstrap.min.js"></script>
<!-- Waypoints -->
<script src="../frontDesk_assets/js/jquery.waypoints.min.js"></script>
<!-- Flexslider -->
<script src="../frontDesk_assets/js/jquery.flexslider-min.js"></script>


<!-- MAIN JS -->
<script src="../frontDesk_assets/js/main.js"></script>


</body>
</html>



