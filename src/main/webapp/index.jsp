<%@ page import="java.util.*" %><%--07/14 by lwp--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <c:out value="43424" />
    <%
        List<String> list=new ArrayList<>();
        list.add("string A");
        list.add("string B");
        list.add("string C");
        list.add("string D");
        for (String str : list){
            System.out.println("str = " + str);
        }
        Set<String> set=new LinkedHashSet<String>() {
        };
        set.add("A string");
        set.add("B string");
        set.add("C string");
        set.add("D string");
        for (String str : set){
            System.out.println("str = " + str);
        }
    %>
</body>
</html>