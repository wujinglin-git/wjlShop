<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/index.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/file.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/zshop.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script>
        $(function(){
            //上传图像预览
            $('#product-image').on('change',function() {
                $('#img').attr('src', window.URL.createObjectURL(this.files[0]));
            });
            $('#pro-image').on('change',function() {
                $('#img2').attr('src', window.URL.createObjectURL(this.files[0]));
            });
        });

        //校验例子
       /* $(function () {
            $("#addFrom").bootstrapValidator({
                live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
                excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
                submitButtons: '#btn-test',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
                message: '通用的验证失败消息',//好像从来没出现过
                feedbackIcons: {//根据验证结果显示的各种图标
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    text: {
                        validators: {
                            notEmpty: {//检测非空,radio也可用
                                message: '文本框必须输入'
                            },
                            stringLength: {//检测长度
                                min: 6,
                                max: 30,
                                message: '长度必须在6-30之间'
                            },
                            regexp: {//正则验证
                                regexp: /^[a-zA-Z0-9_\.]+$/,
                                message: '所输入的字符不符要求'
                            },
                            remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
                                url: '指定页面',
                                message: 'The username is not available'
                            },
                            different: {//与指定文本框比较内容相同
                                field: '指定文本框name',
                                message: '不能与指定文本框内容相同'
                            },
                            emailAddress: {//验证email地址
                                message: '不是正确的email地址'
                            },
                            identical: {//与指定控件内容比较是否相同，比如两次密码不一致
                                field: 'confirmPassword',//指定控件name
                                message: '输入的内容不一致'
                            },
                            date: {//验证指定的日期格式
                                format: 'YYYY/MM/DD',
                                message: '日期格式不正确'
                            },
                            choice: {//check控件选择的数量
                                min: 2,
                                max: 4,
                                message: '必须选择2-4个选项'
                            }
                        }
                    }
                }
            });
            $("#btn-test").click(function () {//非submit按钮点击后进行验证，如果是submit则无需此句直接验证
                $("#form-test").bootstrapValidator('validate');//提交验证
                if ($("#form-test").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
                    alert("yes");//验证成功后的操作，如ajax
                }
            });
        });*/


        //使用bootstrap validator插件进行客户端数据校验
        $(function () {
            $('#frmAddProduct').bootstrapValidator({
                live: 'disabled',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    name:{
                        validators:{
                            notEmpty:{
                                message:'商品名称不能为空'
                            },
                            remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
                                url: '${pageContext.request.contextPath}/backend/product/checkName',
                               // message: 'The username is not available'
                                type:'post'
                            }

                        }
                    },
                        price:{
                            validators:{
                                notEmpty:{
                                    message:'商品名称不能为空'
                                },
                                regexp:{
                                    regexp:/^\d+(\.\d+)?$/,
                                    message:'商品格式不正确'
                                }
                            }

                        },
                        file:{
                            validators:{
                                notEmpty:{
                                    message:'请选择有效图片'
                                }
                            }

                        },
                        productTypeId:{
                            validators:{
                                notEmpty:{
                                    message:'请选择类型'
                                }
                            }

                        }
                    }


            });
        })

        //分页
        $(function () {
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage: ${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                pageUrl:function(type,page, current){
                    return '${pageContext.request.contextPath}/backend/product/findAll?pageNum='+page//为每个页码设置url访问请求链接，page为页码数
                },
                itemTexts:function(type,page, current){
                    //文字翻译
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "尾页";
                        case "page":
                            return page;
                    }
                }

            });
        });


        
        function showProduct(id) {
            $.post(
                '${pageContext.request.contextPath}/backend/product/findById',
                {'id':id},
                function (result) {
                    $('#pro-num').val(result.data.id);
                    $('#pro-name').val(result.data.name);
                    $('#pro-price').val(result.data.price);
                    $('#pro-productType').val(result.data.productType.id);// 设置Select的Value值为result.data.productType.id的项选
                    $('#img2').attr('src', '${pageContext.request.contextPath}/backend/product/findImage?path='+result.data.image);
                }
            )
        }

        function removeProduct(id) {
            $.post(
                '${pageContext.request.contextPath}/backend/product/remove',
                {'id':id},
                function (result) {
                    layer.msg(result.message,
                        {
                            time:1000
                        },
                    function () {
                        location.href=('${pageContext.request.contextPath}/backend/product/findAll?pageNum='+${pageInfo.pageNum});
                    })
                }
            )
        }

    </script>
</head>

<body>
    <div class="panel panel-default" id="userPic">
        <div class="panel-heading">
            <h3 class="panel-title">商品管理</h3>
        </div>
        <div class="panel-body">
            <input type="button" value="添加商品" class="btn btn-primary" id="doAddPro">
            <br>
            <br>
            <div class="show-list text-center">
                <table class="table table-bordered table-hover" style='text-align: center;'>
                    <thead>
                        <tr class="text-danger">
                            <th class="text-center">编号</th>
                            <th class="text-center">商品</th>
                            <th class="text-center">价格</th>
                            <th class="text-center">产品类型</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    <c:forEach items="${pageInfo.list}" var="product">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.price}</td>
                            <td>${product.productType.name}</td>
                            <td><c:if test="${product.productType.status==1}">有效商品</c:if>
                                <c:if test="${product.productType.status==0}">无效商品</c:if></td>
                            <td class="text-center">
                                <input type="button" class="btn btn-warning btn-sm doProModify" value="修改" onclick="showProduct(${product.id})">
                                <input type="button" class="btn btn-warning btn-sm doProDelete" value="删除" onclick="removeProduct(${product.id})">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <ul id="pagination"></ul>
            </div>
        </div>
    </div>

    <!-- 添加商品 start -->
    <div class="modal fade" tabindex="-1" id="Product" >
        <!-- 窗口声明 -->
        <div class="modal-dialog modal-lg">
            <!-- 内容声明 -->
            <form action="${pageContext.request.contextPath}/backend/product/add" class="form-horizontal"
                  method="post" id="frmAddProduct" enctype="multipart/form-data">
            <div class="modal-content">
                <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加商品</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="product-name" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="product-price" name="price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8">
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="file" id="product-image" >
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" name="productTypeId" id="product-type">
                                 <option value="" >请选择</option>
                                    <c:forEach items="${productTypes}" var="productType">
                                    <option value="${productType.id}"> ${productType.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 170px;height: 160px;" id="img" src="${pageContext.request.contextPath}/images/select.png">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">添加</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
            </form>
        </div>
    </div>
    <!-- 添加商品 end -->

    <!-- 修改商品 start -->
    <div class="modal fade" tabindex="-1" id="myProduct">
        <!-- 窗口声明 -->
        <div class="modal-dialog modal-lg">
            <!-- 内容声明 -->
            <form action="${pageContext.request.contextPath}/backend/product/modify" class="form-horizontal"
                  method="post" id="frmModifyProduct" enctype="multipart/form-data">
            <div class="modal-content">
                <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改商品</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <div class="form-group" >
                            <label for="pro-num" class="col-sm-4 control-label">商品编号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pro-num" name="id" readonly name="id">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="name" id="pro-name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="price" id="pro-price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8">
                                <a class="file">
                                    选择文件 <input type="file" name="file"  id="pro-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="pro-productType" name="productTypeId">
                                    <c:forEach items="${productTypes}" var="productType">
                                    <option value="${productType.id}" >${productType.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 160px;height: 180px;" id="img2">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary updatePro" type="submit" >修改</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
            </form>
        </div>
    <!-- 修改商品 end -->
    </div>
</body>

</html>