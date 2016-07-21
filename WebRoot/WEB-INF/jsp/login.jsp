<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="common/include.jsp" %>

    <title>吉点点平台</title>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css"
          href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/login/assets/css/form-elements.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/login/assets/css/style.css">


</head>

<body>
<!-- <img src="/img/QQ.jpg" alt="凯凯"/> -->
<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">

            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h1>吉点点平台</h1>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-lock"></i>
                        </div>
                    </div>

                    <div class="form-bottom">
                        <form role="form" action="" method="post" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="form-username" placeholder="用户ID"
                                       class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="form-password" placeholder="密码"
                                       class="form-password form-control" id="form-password">
                            </div>
                        </form>
                    </div>
                    <input type="hidden" id="loginStatus" value="0">
                    <a id="login" href="javascript:void(0);" class="btn">登录</a>
                </div>

            </div>
        </div>
    </div>

</div>

</body>

</html>


<script>
    $(function () {
        if (top.location != self.location) {
            top.location = self.location;
        }
        $("#login").click(login);

        document.onkeydown = function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 13) { // enter 键
                login();
            }
        };

        function login() {
            var userId = $("#form-username").val();
            if (userId == null || userId == "") {
                layer.tips('用户名不能为空', '#form-username');
                return;
            }
            var password = $("#form-password").val();
            if (password == null || password == "") {
                layer.tips('密码不能为空', '#form-password');
                return;
            }
            var index = layer.load();
            $.post("/login", {
                userId: userId,
                password: password
            }, function (data) {
                layer.close(index);
                if (data == "success") {
                    //$("#loginStatus").val("1");
                    //$(".login-form").attr("action", "login.do").submit();
                    //window.location.href="/admin/default";
                    window.location.href = "/home/default/home.do";
                } else {
                    layer.msg("登录失败，请重试", {
                        icon: 5
                    });
                }

            });
        }
        ;

    });
</script>

