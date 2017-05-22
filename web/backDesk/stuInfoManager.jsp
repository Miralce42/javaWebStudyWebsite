<%@ page import="WebDB.Dao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Users" %><%--
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
            width: 10%;
        }
    </style>
</head>
<body>
<%@include file="sidebar.jsp"%><!--左侧布局-->
<div id="fh5co-main" class="right">
    <div id="col-md-6" class="col-md-6">
        <%
            Dao dao = new Dao();
            ArrayList<Users> Students = new ArrayList<Users>();
            ArrayList<String> classNames = new ArrayList<String>();
            int classNamesCount = dao.selectClassName(classNames);
            int count = dao.selectStudent(Students,0,null);
        %>
        <!-- TABLE HOVER -->
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title">学生管理</h3>
            </div>
            <div class="panel-body">
                <form name="form1" action="">
                    <table border="0" width="100%">
                        <tr>
                            <td class="td-text" align="right">
                                班级：
                            </td>
                            <td>
                                <select onchange="selectChange()" class="form-control">
                                    <option></option>
                                    <%
                                        for(int i = 0 ; i < classNamesCount ;i++){
                                            String className = classNames.get(i);
                                    %>
                                    <option value="<%=className%>"> <%=className%> </option>
                                    <%
                                        }
                                    %>
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
                            <td  class="td-text" align="right">
                                <button type="button" class="btn btn-primary"><i class="fa fa-refresh"></i>刷新</button>
                            </td>
                        </tr>
                    </table>
                </form>
                <hr>
                <table id="table-hover" class="table table-hover">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>专业</th>
                        <th>班级</th>
                        <th>手机号</th>
                        <th>性别</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%

                        System.out.println("进来了4");
                        for(int i = 0 ; i < count ;i++){
                            Users allStu = Students.get(i);

                    %>
                        <tr>
                            <td><%=allStu.getUsername()%></td>
                            <td><%=allStu.getName()%></td>
                            <td><%=allStu.getMajor()%></td>
                            <td><%=allStu.getClassNum()%></td>
                            <td><%=allStu.getPhone()%></td>
                            <td><%=allStu.getSex()%></td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- END TABLE HOVER -->
    </div>
</div>
</body>
</html>