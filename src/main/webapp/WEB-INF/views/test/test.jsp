<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <jsp:include page="/WEB-INF/views/admin/headset.jsp"></jsp:include>
</head>
<body>
    <form action="/test/test" method="post">
        <input type="text" name="username" />
        <input type="password" name="password" />
        <input type="submit" value="submit" />
    </form>
    <jsp:include page="/WEB-INF/views/admin/footer.jsp"></jsp:include>
</body>
</html>
