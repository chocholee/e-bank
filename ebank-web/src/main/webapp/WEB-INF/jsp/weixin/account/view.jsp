<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看微信公众号</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" readonly class="layui-input" value="${account.name}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">Token</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" readonly class="layui-input" value="${account.token}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">微信号</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" readonly class="layui-input" value="${account.number}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">原始ID</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" readonly class="layui-input" value="${account.accountId}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">AppId</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" readonly class="layui-input" value="${account.appId}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">AppSecret</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" readonly class="layui-input" value="${account.appSecret}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">Email</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" readonly class="layui-input" value="${account.email}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" readonly class="layui-input" value="${account.description}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" readonly
                           autocomplete="off" class="layui-input" value="<c:if test="${account.type ne null}">${account.type.name}</c:if>">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">授权状态</label>
                <div class="layui-input-block">
                    <div class="layui-unselect layui-form-switch <c:if test="${account.status eq 'AUTHORIZED'}">layui-form-onswitch</c:if>">
                        <i></i>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">创建日期</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" readonly class="layui-input" value="${account.createdDate}">
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <c:if test="${account eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>