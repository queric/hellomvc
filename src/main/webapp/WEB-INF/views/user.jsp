<%--
  Author: queric  Date: 2016/9/8 15:03
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>用户列表</title>
</head>
<body>
    <%=request.getServletPath()%>
    <table>
        <thead><tr><th>编号ID</th><th>角色</th><th>用户名</th><th>密码</th><th>操作</th></tr></thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr><td>${user.userId}</td><td>
                <c:forEach var="role" items="${user.roles}">
                    ${role.roleId}:${role.roleName},
                </c:forEach>
            </td><td>${user.username}</td><td>${user.password}</td><td><a href="/user/delete/${user.userId}">删除</a> </td></tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
