<%--
  Created by IntelliJ IDEA.
  User: 韩壮
  Date: 2017/5/21
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理</title>
    <%
        String moduleString="学生信息管理";
    %>
    <style type="text/css">
        #col-md-6{
            width: 100%;
        }
        .td-search{
            width: 30%;
        }
        .td-text{
            width: 8%;
        }
    </style>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
    <div id="col-md-6" class="col-md-6">
        <!-- TABLE HOVER -->
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title">学生管理</h3>
            </div>
            <div class="panel-body">
                <form>
                    <table border="0" width="100%">
                        <tr>
                            <td class="td-text" align="right">
                                班级：
                            </td>
                            <td>
                                <select class="form-control">
                                    <option value="cheese">Cheese</option>
                                    <option value="tomatoes">Tomatoes</option>
                                    <option value="mozarella">Mozzarella</option>
                                    <option value="mushrooms">Mushrooms</option>
                                    <option value="pepperoni">Pepperoni</option>
                                    <option value="onions">Onions</option>
                                </select>
                            </td>
                            <td class="td-search">
                                <div class="input-group">
                                    <input class="form-control" PLACEHOLDER="查找" type="text">
                                    <span class="input-group-btn"><button class="btn btn-primary" type="button">Go!</button></span>
                                </div>
                            </td>
                            <td class="td-search" align="right">
                                <span class="input-group-btn"><button class="btn btn-primary" type="button">添加学生</button></span>
                            </td>
                        </tr>
                    </table>
                </form>
                <hr>
                <table id="table-hover" class="table table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Username</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>Steve</td>
                        <td>Jobs</td>
                        <td>@steve</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Simon</td>
                        <td>Philips</td>
                        <td>@simon</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Jane</td>
                        <td>Doe</td>
                        <td>@jane</td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>
        <!-- END TABLE HOVER -->
    </div>
</div>
</body>
</html>