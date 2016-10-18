<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jsp-tmpl" uri="/jsp-templ.tld" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="author" content="云从科技">
    <title><tmpl:block name="title"/></title>
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <tmpl:block name="page_css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.min.js"></script>
</head>
<body>
<tmpl:block name="body">
    <%@ include file="top.jsp" %>
    <!--主体内容-->
    <div id="content">
        <%@ include file="sidebar.jsp" %>
        <div id="rightbox" class="left">
            <tmpl:block name="rightBox"/>
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.nicescroll.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menubar.js"></script>
    <script>
        $(function () {
            $("#rightBox").niceScroll({cursorborder: "none", horizrailenabled: true});
        });

        function search() {
            $("#form1").submit();
        }

        function reset() {
            var formElements = $("#form1").find("input,select");
            $.each(formElements, function () {
                switch (this.nodeName.toLowerCase()) {
                    case "input":
                        $(this).val("");
                        break;
                    case "select":
                        $(this).find("option:first").attr('selected', 'selected');
                        break;
                }
            });
            search();
        }
    </script>
</tmpl:block>
<tmpl:block name="page_script"/>
</body>
</html>
