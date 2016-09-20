<%--
  Author: queric  Date: 2016/9/19 14:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>添加用户</title>
</head>
<body>
<!-- 加载编辑器的容器 -->
<script id="container" name="content" type="text/plain">
        这里写你的初始化内容
    </script>
<!-- 配置文件 -->
<script type="text/javascript" src="/static/js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/static/js/ueditor/ueditor.all.min.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('container');
</script>
    <form action="/user/add" method="post">
        <label>用户名：<input type="text" name="username" /> </label>
        <label>密码：<input type="text" name="password" /> </label>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
