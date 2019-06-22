<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/21 0021
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <h1>TheWeeknd的网站</h1>
  <hr/>
  <c:if test="${user==null}">
    <a href="${pageContext.request.contextPath}/servlet/RegisterUIServlet" target="_blank">注册</a>
    <a href="${pageContext.request.contextPath}/servlet/LoginUIServlet">登陆</a>
  </c:if>
  <c:if test="${user!=null}">
    欢迎您：${user.userName}
    <input type="button" value="退出登陆" onclick="doLogout()">
  </c:if>
  <hr/>
  $END$
  </body>
</html>
