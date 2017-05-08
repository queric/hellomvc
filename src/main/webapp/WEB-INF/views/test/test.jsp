<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <jsp:include page="/WEB-INF/views/admin/headset.jsp"></jsp:include>
    <script type="text/javascript" src="/static/js/react/react.js"></script>
    <script type="text/javascript" src="/static/js/react/react-dom.js"></script>
    <script type="text/javascript" src="/static/js/react/browser.min.js"></script>
</head>
<body>
    <form action="/test/test" method="post">
        <input type="text" name="username" />
        <input type="password" name="password" />
        <input type="submit" value="submit" />
    </form>
    <div id="example"></div>
    <script type="text/babel">
        ReactDOM.render(
                <h1>Hello world!</h1>,
                document.getElementById('example')
        )
    </script>
    <c:out value="${script}" escapeXml="false" />
    <jsp:include page="/WEB-INF/views/admin/footer.jsp"></jsp:include>
</body>
</html>
