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
    <%-- css --%>
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/dropkick.css" rel="stylesheet" type="text/css" />
    <tmpl:block name="page_css"/>
    <%-- js --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
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
        <div class="clearfix"></div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layer/layer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.nicescroll.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menubar.js"></script>
</tmpl:block>
<tmpl:block name="page_script"/>
</body>
</html>
