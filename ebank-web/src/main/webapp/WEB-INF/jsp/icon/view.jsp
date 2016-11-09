<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看图标</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${icon.name}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">变化前图标</label>
                <div class="layui-input-block">
                    <img height="34" src="${host}/${icon.beforeHoverPath}">
                    <input type="text" name="beforeHoverPath" lay-verify="required" value="${icon.beforeHoverPath}"
                           readonly style="border: 0;">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">变化后图标</label>
                <div class="layui-input-block">
                    <img height="34" src="${host}/${icon.afterHoverPath}">
                    <input type="text" name="afterHoverPath" lay-verify="required" value="${icon.afterHoverPath}"
                           readonly style="border: 0;">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="description" autocomplete="off" class="layui-input"
                           value="${icon.description}">
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <%-- 判断页面是否存在记录 --%>
    <c:if test="${icon eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>