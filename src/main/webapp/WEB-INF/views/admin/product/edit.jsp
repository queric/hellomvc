<%--
  Author: Queric  Date: 2016/11/21 13:53
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
    商品信息-猎鲜订单管理系统
</title><meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="/static/css/bootstrap.min.css" /><link type="text/css" rel="stylesheet" href="/static/css/font-awesome.min.css" /><link type="text/css" rel="stylesheet" href="/static/css/iCheck_square_blue.css" /><link type="text/css" rel="stylesheet" href="/static/css/admin/main.css" /><link type="text/css" rel="stylesheet" href="/static/css/admin/default.css" />
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/admin_common.js"></script>
</head>
<body>

<!--顶部导航begin-->
<div class="top-navbar navbar navbar-default" style="margin-bottom: 0;" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">导航栏</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="/main.aspx"><span class="navbar-brand"><span class="glyphicon glyphicon-home"></span>&nbsp;猎鲜订单管理系统</span></a>
    </div>

    <div class="navbar-collapse collapse" style="height: 1px;">
        <ul id="main-menu" class="nav navbar-nav navbar-right">
            <!--左侧导航预留位置-->
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user" style="position:relative;top: 3px;"></span>
                    admin  <i class="glyphicon glyphicon-triangle-bottom"></i>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">帮助文档</a></li>
                    <li><a href="/repasswd.aspx">修改密码</a></li>
                    <li class="divider"></li>
                    <li><a tabindex="-1" href="/logout.aspx">退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!--顶部导航end-->
<!--左侧边栏begin-->
<div class="sidebar-nav">
    <ul id="nav-items"></ul>
</div>
<!--左侧边栏end-->
<div class="content" style="background: #f9f9f9;">
    <form method="post" action="/admin/product/add" id="form2" class="form-horizontal form-validation" enctype="multipart/form-data">
        <section class="panel panel-default">
            <div class="panel-heading">
                <strong><span class="glyphicon glyphicon-plus"></span><span id="PageName">添加商品</span></strong>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="help-block help-block-error" style="color: #ffffff;">
                            <div class="error-summary" style="display: none">
                                <p>请修复以下错误</p>
                                <ul></ul>
                            </div>
                        </div>
                        <div class="form-group field-base-cert_type">
                            <label class="control-label col-sm-2" for="CatgoryID">品种类别</label>
                            <div class="col-sm-8">
                                <select name="CatgoryID" id="CatgoryID" class="form-control">
                                    <option value="2">本期产品</option>
                                    <option value="4">常规</option>
                                    <option value="8">下架产品</option>
                                    <option value="12">1231232</option>
                                    <option value="14">333355</option>

                                </select>
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="control-label col-sm-2" for="ProductName">商品名称</label>
                            <div class="col-sm-8">
                                <input name="ProductName" type="text" id="ProductName" class="form-control" />
                                <span id="Label1"></span>
                            </div>
                        </div>
                        <div class="form-group field-base-user_allow_chgpass">
                            <label class="control-label col-sm-2" for="Memo">商品备注</label>
                            <div class="col-sm-8">
                                <input name="Memo" type="text" id="Memo" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group field-base-user_real_name">
                            <label class="control-label col-sm-2" for="InStock">库存数量</label>
                            <div class="col-sm-8">
                                <input name="InStock" type="text" id="InStock" class="form-control" />
                                <div class="help-block ">
                                    <span id="RegularExpressionValidator1" style="color:Red;visibility:hidden;">只能输入数字</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group field-base-user_available">
                            <label class="control-label col-sm-2">商品图片</label>
                            <div class="col-sm-8">
                                <div class="col-sm-12"><img src="#" id="ProPic" width="400" style="display:none;" /></div>
                                <div class="col-sm-12" style="padding-left:0 !important;">
                                    <input type="file" name="FileUpload1" id="FileUpload1" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group field-base-user_expire_time">
                            <label class="control-label col-sm-2" for="PriceRetail">零售价</label>
                            <div class="col-sm-8">
                                <input name="PriceRetail" type="text" id="PriceRetail" class="form-control" />
                                <div class="help-block ">
                                    <span id="RegularExpressionValidator2" style="color:Red;visibility:hidden;">只能输入价格</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group field-base-user_expire_time">
                            <label class="control-label col-sm-2" for="PriceRetail">包装规格</label>
                            <div class="col-sm-8">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <input name="size" type="text" value="0" id="size" class="form-control" />
                                    </div>
                                    <div class="col-sm-10">
                                        <span id="sizetype" class="form-control" style="border:0;"><input id="sizetype_0" type="radio" name="sizetype" value="斤/份" checked="checked" /><label for="sizetype_0"> 斤/份       </label><input id="sizetype_1" type="radio" name="sizetype" value="个/份" /><label for="sizetype_1"> 个/份</label></span>
                                    </div>
                                </div>
                                <div class="help-block ">
                                    <span id="RegularExpressionValidator12" style="color:Red;visibility:hidden;">只能输入价格</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group field-base-user_expire_time">
                            <label class="control-label col-sm-2">推荐指数</label>
                            <div class="col-sm-8">
                                <select name="Recommand" id="Recommand" class="form-control">
                                    <option selected="selected" value="1">★</option>
                                    <option value="2">★★</option>
                                    <option value="3">★★★</option>
                                    <option value="4">★★★★</option>
                                    <option value="5">★★★★★</option>

                                </select>
                            </div>
                        </div>

                        <div class="form-group field-base-user_expire_time" style="display:none;">
                            <label class="control-label col-sm-2" for="PriceAgent">代理价</label>
                            <div class="col-sm-8">
                                <input name="PriceAgent" type="text" value="0" id="PriceAgent" class="form-control" />
                                <div class="help-block ">
                                    <span id="RegularExpressionValidator3" style="color:Red;visibility:hidden;">只能输入价格</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group field-base-user_expire_time"  style="display:none;">
                            <label class="control-label col-sm-2" for="PricePartner">合作伙伴价</label>
                            <div class="col-sm-8">
                                <input name="PricePartner" type="text" value="0" id="PricePartner" class="form-control" />
                                <div class="help-block ">
                                    <span id="RegularExpressionValidator4" style="color:Red;visibility:hidden;">只能输入价格</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group field-base-max_online_num">
                            <label class="control-label col-sm-2" for="base-max_online_num">最后更新时间</label>
                            <div class="col-sm-8">

                            </div>
                        </div>
                        <div class="form-group field- required">
                            <div class="col-sm-10 col-sm-offset-2">
                                <input type="submit" name="submit" value="保存" onclick="javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions(&quot;submit&quot;, &quot;&quot;, true, &quot;&quot;, &quot;&quot;, false, false))" id="submit" class="btn btn-success" />
                                <a class="btn btn-default" href="ProductList.aspx">列表</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>
</div>

<footer>
    <hr />
    <p class="pull-right">© 2016 Powered By 猎鲜网 ,All Rights Reserved.</p>
</footer>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
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

</body>
</html>
