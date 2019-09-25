<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/13
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css" />
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
<script>
    $(function () {
        $('#add').bootstrapValidator({
            live: 'enabled',
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
                        }

                    }
                }
            }
        });
    })





</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
${pageContext.request.contextPath}
<h1>aaaaaa</h1>
<form id="add" method="post" action="">
    <input type="text" name="name"><br>
    <input type="text" name="pw">
    <br>
    <input type="submit" value="sub" id="tes">
</form>
</body>
</html>
