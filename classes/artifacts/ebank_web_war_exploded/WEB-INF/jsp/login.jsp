<%--
  Created by IntelliJ IDEA.
  User: liwenhe
  Date: 2016/9/20
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    ${message}
    <form method="post" action="/login">
        <input type="text" name="username">
        <input type="text" name="password">
        <img id="captcha" src="/captcha">
        <input type="text" name="captcha">
        <input type="submit" value="登录">
    </form>
    <script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
    <script>
        $("#captcha").click(function () {
            $(this).attr("src", "/captcha");
        }).mouseover(function () {
            $(this).css("cursor","pointer");
        });
    </script>
</body>
</html>
