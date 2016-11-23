<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">编辑分组</tmpl:override>

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
                           value="${group.name}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">类型</label>
                <div class="layui-input-block">
                    <input type="radio" name="type" title="微信分组" value="WECHAT" lay-verify="required" disabled
                           <c:if test="${group.type eq 'WECHAT'}">checked</c:if>>
                    <input type="radio" name="type" title="虚拟分组" value="VIRTUAL" lay-verify="required" disabled
                           <c:if test="${group.type eq 'VIRTUAL'}">checked</c:if>>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea name="remark" placeholder="请输入备注" class="layui-textarea"
                              disabled>${group.remark}</textarea>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <c:if test="${group eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>
