<%@ page import="java.util.*" %>
<%@ page import="com.demo.test.TestWork" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <%
        TestWork testWork=new TestWork();
        testWork.hello();
        testWork.suckit();
    %>
</body>
</html>