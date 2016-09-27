<%--
  Author: queric  Date: 2016/9/21 14:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
    <select>
        <option value="-1">请选择</option>
        <c:forEach var="catgory" items="${catgories}">
            <option value="${catgory.catgoryId}">${catgory.catgoryName}</option>
        </c:forEach>
    </select>
    <table>
        <thead><tr><th>编号</th><th>标题</th><th>正文</th><th>时间</th><th>类别</th></tr></thead>
        <tbody>
            <c:if test="${empty newsList}"><tr><td align="center" colspan="5">无数据！</td></tr></c:if>
            <c:forEach var="news" items="${newsList}">
                <tr><td>${news.newsId}</td><td>${news.newsTitle}</td><td>${news.newsContent}</td><td>${news.uploadTime}</td><td>${news.newsCatgory.catgoryName}</td></tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
