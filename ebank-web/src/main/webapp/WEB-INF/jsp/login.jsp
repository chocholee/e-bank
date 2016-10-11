<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="author" content="云从科技">
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="content">
    <%-- logo --%>
    <div id="logo">
        <img src="${pageContext.request.contextPath}/resources/images/logo.png">
    </div>

    <%-- 登录表单 --%>
    <div id="login">
        <p>用户登录</p>
        <form action="${pageContext.request.contextPath}/login" method="post" id="loginForm">
            <div class="tip">
                <img src="${pageContext.request.contextPath}/resources/images/ico_user.png">
                <input id="username" name="username" type="text" placeholder="用户名" nullmsg="请输入用户名!"
                       style="margin-left:2px;">
            </div>
            <div class="tip">
                <img src="${pageContext.request.contextPath}/resources/images/ico_locked.png">
                <input id="password" name="password" type="password" placeholder="密码" nullmsg="请输入密码!"
                       style="margin-left:6px;">
            </div>
            <div id="wrapper">
                <div class="tip left" id="randCode">
                    <img src="${pageContext.request.contextPath}/resources/images/ico_pen.png">
                    <input id="captcha" name="captcha" type="text" placeholder="验证码" nullmsg="请输入验证码!">

                </div>
                <div id="rand" class="right">
                    <img id="randCodeIamge" src="${pageContext.request.contextPath}/captcha" class="right">
                    <p>看不清？换一张</p>
                </div>
            </div>
            <div class="clearfix"></div>
                <span id="rempwd">
                    <img src="${pageContext.request.contextPath}/resources/images/btn_check_h.png" class="left">
                    <input type="checkbox" name="remember-me" checked="checked" style="z-index:-1;position:relative;left:-83px;">
                    <span class="left">记住密码</span>
                    <span class="notice right" id="alertMessage">${message}</span>
                </span>
            <button id="loginBtn" type="submit"><span>登&ensp;录</span></button>
        </form>
    </div>

    <%-- 版权 --%>
    <div id="copyright">
        <span>Copyright&copy;&ensp;2016&ensp;云从科技.&ensp;All&ensp;Rights&ensp;Reserved.&ensp;CloudWalk&ensp;Technology&ensp;Co.,&ensp;Ltd..v1.0.025</span>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.jrumble.min.js"></script>
<script>
    $(function () {
        //each遍历文本框
        $("input").each(function () {
            //保存当前文本框的值
            $(this).focus(function () {
                //获得焦点时，如果值为默认值，则设置为空
                $(this).parent().addClass("tip-focus");
            });
            $(this).blur(function () {
                //失去焦点时，如果值为空，则设置为默认值
                $(this).parent().removeClass("tip-focus");
            });
        });

        $("#rempwd").find("img").click(function () {
            if ($(this).attr("src").indexOf("btn_check_h") != -1) {
                $(this).attr("src", "${pageContext.request.contextPath}/resources/images/btn_check_n.png");
                $("#rempwd").find("input[type=checkbox]").attr("checked", false);    // 复选框不选中
            } else {
                $(this).attr("src", "${pageContext.request.contextPath}/resources/images/btn_check_h.png");
                $("#rempwd").find("input[type=checkbox]").attr("checked", true);     // 复选框选中
            }
        });

        // 更新验证操作
        $("#rand p").click(function () {
            $("#randCodeIamge").attr("src", "${pageContext.request.contextPath}/captcha");
        });

        // 表单提交操作
        $("#loginForm").submit(function () {
            var flag = true;
            $("input[nullmsg]").each(function () {
                if ($(this).val() == "") {
                    showError($(this).attr("nullmsg"));
                    jrumble();
                    hideError();
                    flag = false;
                    return false;
                }
            });
            return flag;
        });

        // 显示错误提示
        function showError(str) {
            $('#alertMessage').html(str).stop(true, true).show().animate({
                opacity: 1,
                right: '0'
            }, 500);
        }

        // 隐藏错误提示
        function hideError() {
            setTimeout(function () {
                $('#alertMessage').animate({
                    opacity: 0,
                    right: '-20'
                }, 500, function () {
                    $(this).hide();
                });
            }, 1000);
        }

        // 表单晃动
        function jrumble() {
            $('#login').jrumble({
                x: 4,
                y: 0,
                rotation: 0
            });
            $('#login').trigger('startRumble');
            setTimeout('$("#login").trigger("stopRumble")', 500);
        }
    });
</script>
</body>
</html>