<%@ page import="beans.StudentGradeStatics" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="WebDB.TeacherDAO" %>
<%@ page import="beans.Users" %>
<%@ page import="cn.vove7.mydiv.GradeStaticsTable" %><%--
  Created by IntelliJ IDEA.
  User: Vove
  Date: 2017/6/6
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%
   Users teacher = (Users) session.getAttribute("user");

   TeacherDAO teacherDAO = new TeacherDAO(teacher);
   String condition=request.getParameter("condition");
   ArrayList<StudentGradeStatics> studentGradeStaticsList=teacherDAO.staticsStudentGradeList(condition);
   out.println(GradeStaticsTable.getTableContent(studentGradeStaticsList));
%>
