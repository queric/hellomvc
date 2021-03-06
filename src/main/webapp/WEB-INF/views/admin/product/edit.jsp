<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/WEB-INF/views/admin/headset.jsp"></jsp:include>
</head>

<body>
<jsp:include page="/WEB-INF/views/admin/header.jsp"></jsp:include>
<div class="content" style="background: #f9f9f9;">
    <form method="post" action="/admin/product/add" id="form2" class="form-horizontal form-validation" enctype="multipart/form-data">
        <section class="panel panel-default">
            <div class="panel-heading">
                <strong><span class="glyphicon glyphicon-plus"></span><span id="PageName">添加商品</span></strong>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group field-base-cert_type">
                            <label class="control-label col-sm-2" for="CatgoryID">品种类别</label>
                            <div class="col-sm-8">
                                <select name="categoryId" id="categoryId" class="form-control">
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.categoryId}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="control-label col-sm-2" for="productName">商品名称</label>
                            <div class="col-sm-8">
                                <input name="productName" value="${product.productName}" type="text" id="productName" class="form-control" />
                                <span id="Label1"></span>
                            </div>
                        </div>
                        <div class="form-group field-base-user_allow_chgpass">
                            <label class="control-label col-sm-2" for="Memo">商品备注</label>
                            <div class="col-sm-8">
                                <input name="remark" type="text" id="Memo" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group field-base-user_real_name">
                            <label class="control-label col-sm-2" for="InStock">库存数量</label>
                            <div class="col-sm-8">
                                <input name="stock" type="text" id="InStock" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group field-base-user_available">
                            <label class="control-label col-sm-2">商品图片</label>
                            <div class="col-sm-8">
                                <div class="col-sm-12"><img src="" id="ProPic" width="400" style="display:none;" /></div>
                                <div class="col-sm-12" style="padding-left:0 !important;">
                                    <input type="file" name="file" id="FileUpload1" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group field-base-user_expire_time">
                            <label class="control-label col-sm-2" for="PriceRetail">零售价</label>
                            <div class="col-sm-8">
                                <input name="priceCents" type="text" id="PriceRetail" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group field-base-user_expire_time">
                            <label class="control-label col-sm-2" for="PriceRetail">包装规格</label>
                            <div class="col-sm-8">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <input name="special" type="text" value="0" id="size" class="form-control" />
                                    </div>
                                    <div class="col-sm-10">
                                        <span id="sizetype" class="form-control" style="border:0;"><input id="sizetype_0" type="radio" name="specialUnit" value="斤/份" checked="checked" /><label for="sizetype_0"> 斤/份       </label><input id="sizetype_1" type="radio" name="specialUnit" value="个/份" /><label for="sizetype_1"> 个/份</label></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group field-base-user_expire_time">
                            <label class="control-label col-sm-2">推荐指数</label>
                            <div class="col-sm-8">
                                <select name="recommendLevel" id="Recommand" class="form-control">
                                    <option value="1">★</option>
                                    <option value="2">★★</option>
                                    <option value="3">★★★</option>
                                    <option value="4">★★★★</option>
                                    <option value="5">★★★★★</option>

                                </select>
                            </div>
                        </div>
                        <div class="form-group field-base-max_online_num">
                            <label class="control-label col-sm-2" for="base-max_online_num">最后更新时间</label>
                            <div class="col-sm-8">

                            </div>
                        </div>
                        <div class="form-group field-required">
                            <div class="col-sm-10 col-sm-offset-2">
                                <input type="submit" name="submit" value="保存" id="submit" class="btn btn-success" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>
</div>
<c:out value="${hello}" escapeXml="false" />
<jsp:include page="/WEB-INF/views/admin/footer.jsp"></jsp:include>
</body>
</html>
