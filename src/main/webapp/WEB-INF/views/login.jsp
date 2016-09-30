<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Created by lwp on 2016/7/14--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录 - 猎鲜企业管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="/static/css/bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/font-awesome.min.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/awesome-bootstrap-checkbox.css" />
    <link type="text/css" rel="stylesheet" href="/static/css/admin/default.css" />
    <style type="text/css">
        .login-panel { margin-top: 25%;}
        .panel-body{ padding:35px;}
        .form-control{ height:40px;}
        .btn-md{ padding:9px 12px;}
        .txt-tips{ padding:10px 20px; border-radius:4px;}
        .bg-danger{ color:#b84442;}
    </style>
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
    <!--[if lte IE 9]>
    <script type="text/javascript" src="/static/js/html5shiv.min.js"></script>
    <script type="text/javascript" src="/static/js/respond.min.js"></script>
    <script type="text/javascript" src="/static/js/jquery.placeholder.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('input[type="text"]').placeholder();
        })
    </script>
    <![endif]-->
    <script type="text/javascript">
        var LS = {
            /**
             * 获取/设置存储字段
             * @param {String} name 字段名称
             * @param {String} value 值
             * @return {String}
             */
            init_sys: function () {
                if (LS.isSupportLocalStorage()) {
                    return true;
                }
                else {
                    return false;
                }
            },
            item: function (name, value) {
                var val = null;
                if (LS.isSupportLocalStorage()) {
                    if (value) {
                        localStorage.setItem(name, value);
                        val = value;
                    } else {
                        val = localStorage.getItem(name);
                    }
                } else {
                    console.log('浏览器不支持html5的本地存储！');
                    return false;
                }
                return val;
            },
            /**
             * 移除指定name的存储
             * @param {String} name 字段名称
             * @return {Boolean}
             */
            removeItem: function (name) {
                if (LS.isSupportLocalStorage()) {
                    localStorage.removeItem(name);
                } else {
                    console.log('浏览器不支持html5的本地存储！');
                    return false;
                }
                return true;
            },
            /**
             * 判断浏览器是否直接html5本地存储
             */
            isSupportLocalStorage: function () {
                var ls = LS, is = ls.IS_HAS_LOCAL_STORAGE;
                if (is == null) {
                    if (window.localStorage) {
                        is = ls.IS_HAS_LOCAL_STORAGE = true;
                    }
                }
                return is;
            },
            IS_HAS_LOCAL_STORAGE: null
        };
        window.onload = function () {
            var username, password, doRemember;
            if (LS.isSupportLocalStorage) {
                doRemember = LS.item('doRemember');
                if (doRemember == "1") {
                    $("#remember").prop("checked", true);
                    username = LS.item('txtVal1');
                    if ((username != null) && (username != "")) $("#txtUsername").val(username);
                    password = LS.item('txtVal2');
                    if ((password != null) && (password != "")) $("#txtPassword").val(password);
                }
            }
        };
        function rememberMe() {
            var username = $("#txtUsername").val();
            var password = $("#txtPassword").val();
            if (username != "" && password != "") {
                if ($("#remember").prop("checked")) {
                    if (LS.isSupportLocalStorage) {
                        LS.item('txtVal1', $.trim(username));
                        LS.item('txtVal2', $.trim(password));
                        LS.item('doRemember', "1");
                    }
                }
                else {
                    LS.removeItem('txtVal1');
                    LS.removeItem('txtVal2');
                    LS.removeItem('doRemember');
                }
                return true;
            }
            else {
                alert('请填写用户名和密码！');
                return false;
            }

        }
    </script>
</head>
<body style="background:#f8f8f8;">
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><span class="glyphicon glyphicon-home"></span>&nbsp;猎鲜企业管理系统</h3>
                </div>
                <div class="panel-body">
                    <form method="post" action="/login"  onsubmit="return rememberMe();">
                        <fieldset>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input name="username" id="username" class="form-control" placeholder="用户名" type="text" autofocus="autofocus" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                    <input name="password" id="password" class="form-control" placeholder="密    码" type="password" />
                                </div>
                            </div>
                            <div class="checkbox checkbox-success"><input id="remember" type="checkbox" /><label for="remember">记住密码</label></div>
                            <input type="submit" name="btnLogin" value="登  录" id="btnLogin" class="btn btn-md btn-success btn-block" />
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:out value="${script}" escapeXml="false" />
</body>
</html>