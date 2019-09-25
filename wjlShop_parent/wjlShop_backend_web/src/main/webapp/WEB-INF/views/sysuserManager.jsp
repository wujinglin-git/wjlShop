<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/20
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/index.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script>

        //分页
        $(function () {
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage: ${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                pageUrl:function(type,page, current){
                    return '${pageContext.request.contextPath}/backend/sysuser/findAll?pageNum='+page//为每个页码设置url访问请求链接，page为页码数
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

        //添加
        function addSysuser() {
            $.post(
                '${pageContext.request.contextPath}/backend/sysuser/add',
                $('#fmAddSysuser').serialize(),
                function (result) {
                    layer.msg(result.message,
                        {
                            time:1000
                        }),
                        function () {
                        location.href=('${pageContext.request.contextPath}/backend/sysuser/findAll?pageNum='+${pageInfo.pageNum});
                    }
                }
            )
        }

        //启用禁用
        function modifyStatus(id,btn) {
            $.post(
                '${pageContext.request.contextPath}/backend/sysuser/modifyStatus',
                {'id':id},
                function(result){
                    if(result.status==1){
                        let $td=$(btn).parent().parent().children().eq(5);
                        if($td.text().trim()=='有效'){
                            $td.text('无效');
                            $(btn).val('启用').removeClass('btn-success').addClass('btn-danger');
                        }else{
                            $td.text('有效');
                            $(btn).val('禁用').removeClass('btn-danger').addClass('btn-success');
                        }
                    }
                }
            )
        }

        //修改
        function modify(id) {

        }

    </script>

</head>

<body>
<!-- 系统用户管理 -->
<div class="panel panel-default" id="adminSet">
    <div class="panel-heading">
        <h3 class="panel-title">系统用户管理</h3>
    </div>
    <div class="panel-body">
        <div class="showmargersearch">
            <form class="form-inline" action="/backend/sysuser/findByParams" method="post">
                <div class="form-group">
                    <label for="userName">姓名:</label>
                    <input type="text" class="form-control" id="userName" name="name" placeholder="请输入姓名" value="${sysuserParam.name}">
                </div>
                <div class="form-group" >
                    <label for="loginName">帐号:</label>
                    <input type="text" class="form-control" id="loginName" name="loginName" placeholder="请输入帐号" value="${sysuserParam.loginName}">
                </div>
                <div class="form-group">
                    <label for="phone">电话:</label>
                    <input type="text" class="form-control" id="phone"  name="phone" placeholder="请输入电话" value="${sysuserParam.phone}">
                </div>
                <div class="form-group">
                    <label for="role">角色</label>
                    <select class="form-control" name="role" id="role">
                        <option value="${role.id}" <c:if test="${role.id==sysuserParam.role}">selected</c:if>>${role.roleName}</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="status">状态</label>
                    <select class="form-control" name="isValid" id="status">
                        option value="-1">全部</option>
                        <option value="1" <c:if test="${sysuserParam.isValid==1}">selected</c:if>>---有效---</option>
                        <option value="0" <c:if test="${sysuserParam.isValid==0}">selected</c:if>>---无效---</option>
                    </select>
                </div>
                <input type="submit" value="查询" class="btn btn-primary" id="doSearch">
            </form>
        </div>
        <br>
        <input type="button" value="添加系统用户" class="btn btn-primary" id="doAddManger" >
        <div class="show-list text-center" style="position: relative; top: 10px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">邮箱</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">注册时间</th>
                    <th class="text-center">角色</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="sysuser">
                    <tr>
                        <td>${sysuser.id}</td>
                        <td>${sysuser.name}</td>
                        <td>${sysuser.password}</td>
                        <td>${sysuser.phone}</td>
                        <td>${sysuser.email}</td>
                        <td><c:if test="${sysuser.isValid==1}">有效</c:if>
                            <c:if test="${sysuser.isValid==0}">无效</c:if></td>
                        <td><fmt:formatDate type="date" value="${sysuser.createDate}" /></td>
                        <td>${sysuser.role.name}</td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doMangerModify" value="修改" onclick="modify(${sysuser.id})">
                            <c:if test="${sysuser.isValid==1}">
                                <input type="button" class="btn btn-success btn-sm doMangerDisable" value="禁用" onclick="modifyStatus(${sysuser.id},this)">
                            </c:if>
                            <c:if test="${sysuser.isValid==0}">
                                <input type="button" class="btn btn-danger btn-sm doMangerDisable" value="启用" onclick="modifyStatus(${sysuser.id},this)">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 添加系统用户 start -->
<div class="modal fade" tabindex="-1" id="myMangerUser">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <form id="fmAddSysuser">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">添加系统用户</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="marger-username" class="col-sm-4 control-label">用户姓名：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="marger-username" name="name">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="marger-loginName" class="col-sm-4 control-label">登录帐号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="marger-loginName" name="loginName">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="marger-password" class="col-sm-4 control-label">登录密码：</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="marger-password" name="password">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="marger-phone" class="col-sm-4 control-label">联系电话：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="marger-phone" name="phone">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="marger-email" class="col-sm-4 control-label">联系邮箱：</label>
                    <div class="col-sm-4">
                        <input type="email" class="form-control" id="marger-email" name="email">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="role" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                    <div class=" col-sm-4">
                        <select class="form-control" name="roleId">
                            <option>请选择</option>
                            <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary add-Manger" type="button" onclick="addSysuser()">添加</button>
                <button class="btn btn-primary cancel" data-dismiss="modal" type="button">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>
<!-- 添加系统用户 end -->

<!-- 修改系统用户 start -->
<div class="modal fade" tabindex="-1" id="myModal-Manger">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">系统用户修改</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="MargerStaffId" class="col-sm-4 control-label">用户编号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="MargerStaffId" readonly="readonly">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="MargerStaffname" class="col-sm-4 control-label">用户姓名：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="MargerStaffname">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="MargerLoginName" class="col-sm-4 control-label">登录帐号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="MargerLoginName" readonly="readonly">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="MargerPhone" class="col-sm-4 control-label">联系电话：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="MargerPhone">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="MargerAdrees" class="col-sm-4 control-label">联系邮箱：</label>
                    <div class="col-sm-4">
                        <input type="email" class="form-control" id="MargerAdrees">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="MargerRole" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                    <div class=" col-sm-4">
                        <select class="form-control" id="MargerRole">
                            <option>请选择</option>
                            <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary doMargerModal" onclick="addSysuser()">修改</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改系统用户 end -->

</body>

</html>