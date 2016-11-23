<%--
  Author: Queric  Date: 2016/11/23 15:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<table>
    <thead><tr><th>编号</th><th>类别</th><th>名称</th><th>时间</th></tr></thead>
    <tbody>
    <c:if test="${empty productList}"><tr><td align="center" colspan="3">无数据！</td></tr></c:if>
    <c:forEach var="product" items="${productList}">
        <tr><td>${product.productId}</td><td>${product.category.categoryName}</td><td>${product.productName}</td><td>${product.updateTime}</td><td>${news.uploadTime}</td></tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
