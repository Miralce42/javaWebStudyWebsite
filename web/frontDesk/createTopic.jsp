<%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/21
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建话题</title>
</head>
<body>
<%@include file="aside.jsp"%><!--左侧布局-->
<div id="fh5co-main">
    <aside id="fh5co-hero" class="js-fullheight">
        <div class="flexslider js-fullheight">
            <ul class="slides">
                <li style="background-image: url(../frontDesk_assets/images/EyeOfGod.jpg);">
                    <div class="overlay"></div>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2 text-center js-fullheight slider-text">
                                <div class="slider-text-inner">
                                    <!-- INPUTS -->
                                    <div class="panel">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">创建话题</h3>
                                        </div>
                                        <hr />
                                        <form>
                                            <div class="panel-body">
                                                话题种类：（必填，请准确选择！）
                                                <label class="fancy-radio">
                                                    <input name="gender" checked="checked" value="Other" type="radio">
                                                    <span><i></i>Other</span>
                                                </label>
                                                <label class="fancy-radio">
                                                    <input name="gender" value="JSP" type="radio">
                                                    <span><i></i>JSP</span>
                                                </label>
                                                <label class="fancy-radio">
                                                    <input name="gender" value="HTML" type="radio">
                                                    <span><i></i>HTML</span>
                                                </label><label class="fancy-radio">
                                                <input name="gender" value="HTML" type="radio">
                                                <span><i></i>JAVA</span>
                                            </label>
                                                <hr />
                                                <input type="text" class="form-control" placeholder="标题">
                                                <hr />
                                                <textarea class="form-control" placeholder="内容" rows="4"></textarea>
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
</div>
</body>
</html>
