<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2017/5/22
  Time: 上午9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head lang="zh-CN">
    <title>教学评分</title>
    <style>
      .stars{ overflow: hidden; clear: both; margin: 10px; padding: 10px; border: 1px saddlebrown solid;}
      .stars span{ float: left; height: 30px; line-height: 30px; cursor:default;}
      .stars i{width: 30px; height: 30px; line-height: 30px; float: left; margin-right: 5px; background:#f0f0f0; color:white; text-align: center; cursor:default; font-style: normal;}
      .stars .on{ color:red;}
    </style>
    <script src="js/jquery.star.js"></script>
    <script src="../ckeditor/ckeditor.js">
      CKEDITOR.replace("sss");
    </script>
    <script src="js/star.js"></script>
    <script src="js/ceshi.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
  </head>

  <%@include file="aside.jsp"%><!--左侧布局-->
  <div id="fh5co-main">
    <!--右侧布局-->
    <form name="evalute" action="uploadEvalute.servlet" method="post">
  <h2>课程评价</h2>
    <div style="margin-left:90%"><input type="submit" onclick="CESHI()" value="提交" class="btn-primary "></div>
  <div name="div1" id="div1" class="stars">
    <span style="font-family:'Heiti TC';color:black " >教学内容：</span>
    <i>★</i>
    <i>★</i>
    <i>★</i>
    <i>★</i>
    <i>★</i>
    <input name="text1" id="text1" hidden type="text"/>
  </div>
  <div name="div2" id="div2" class="stars">
    <span style="font-family: 'Heiti TC';color: black">教学方式：</span>
    <i class="" score="1">★</i>
    <i class="" score="2">★</i>
    <i class="" score="3">★</i>
    <i class="" score="4">★</i>
    <i class="" score="5">★</i>
    <input name="text2" id="text2" hidden type="text"/>
  </div>
  <div name="div3"  id="div3" class="stars">
    <span style="font-family: 'Heiti TC';color: black">教学风格：</span>
    <i>★</i>
    <i>★</i>
    <i>★</i>
    <i>★</i>
    <i>★</i>
    <input name="text3" id="text3" hidden type="text"/>
  </div>
  <div name="div4" id="div4" class="stars">
    <span style="font-family: 'Heiti TC'; color: black">教学趣味：</span>
    <i>★</i>
    <i>★</i>
    <i>★</i>
    <i>★</i>
    <i>★</i>
    <input name="text4" id="text4" type hidden="text"/>
  </div>
  <textarea name="content" id="content" class="ckeditor"></textarea>
    </form>
  <div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    <p>&nbsp;</p>
  </div>
  </div>


  </body>
</html>
