<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看图片消息</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/news-template.css" rel="stylesheet" type="text/css">
    <style>
        body {
            width: 90%;
        }
        .item-preview,
        .block {
            display: inline-block;
        }
        .block {
            width: 420px;
        }
        .layui-input-block{margin-left:60px !important;}
        .layui-form-label {width: 30px !important;}
    </style>
</tmpl:override>

<tmpl:override name="body">
    <div class="item-preview">
        <div class="item">
            <img src="${templateHost}/${image.path}"/>
        </div>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <c:if test="${image eq null}">
        <script>
            parent.layer.alert('记录不存在!', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../../../shared/decorator.jsp" %>