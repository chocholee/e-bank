<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看微信场景</tmpl:override>

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
                           value="${scene.name}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block">
                    <textarea name="remark" lay-verify="required" placeholder="请输入内容" class="layui-textarea"
                              disabled>${scene.remark}</textarea>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <c:if test="${scene eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>
