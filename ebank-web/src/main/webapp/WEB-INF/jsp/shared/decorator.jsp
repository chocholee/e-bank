<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jsp-tmpl" uri="/jsp-templ.tld" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="author" content="云从科技">
    <title><tmpl:block name="title"/></title>
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <tmpl:block name="page_css"/>
</head>
<body>
    <%@ include file="top.jsp" %>
    <!--主体内容-->
    <div id="content">
        <%@ include file="sidebar.jsp" %>
        <div id="rightbox" class="left">
            <tmpl:block name="rightBox"/>
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menubar.js"></script>
    <tmpl:block name="page_script"/>
</body>
</html>
