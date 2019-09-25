<%@page contentType="text/html; charset=utf-8" %>

<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css" />
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
<script>

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

    $('#tes').click(
        function () {
            alert('cnm')

        }
    )

</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
${pageContext.request.contextPath}
<h1>aaaaaa</h1>
<form id="add" method="post" action="" >
    <input type="text" name="name"><br>
    <input type="text" name="pw">
    <input type="submit" value="sub" id="tes"/>
</form>
</body>
</html>
