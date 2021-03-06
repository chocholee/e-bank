<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">编辑微信公众号</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/weixin/account/edit/${account.id}" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${account.name}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">Token</label>
                <div class="layui-input-block">
                    <input type="text" name="token" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${account.token}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">微信号</label>
                <div class="layui-input-block">
                    <input type="text" name="number" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${account.number}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">原始ID</label>
                <div class="layui-input-block">
                    <input type="text" name="accountId" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${account.accountId}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">AppId</label>
                <div class="layui-input-block">
                    <input type="text" name="appId" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${account.appId}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">AppSecret</label>
                <div class="layui-input-block">
                    <input type="text" name="appSecret" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${account.appSecret}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">Email</label>
                <div class="layui-input-block">
                    <input type="text" name="email" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${account.email}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="description" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${account.description}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">类型</label>
                <div class="layui-input-block">
                    <c:if test="${account.type ne null}">
                        <select lay-verify="required" name="type" id="type">
                            <option value="">请选择</option>
                            <option value="SERVICE" <c:if test="${account.type eq 'SERVICE'}">selected</c:if>>服务号</option>
                            <option value="SUBSCRIPTION" <c:if test="${account.type eq 'SUBSCRIPTION'}">selected</c:if>>订阅号</option>
                            <option value="ENTERPRISE" <c:if test="${account.type eq 'ENTERPRISE'}">selected</c:if>>企业号</option>
                        </select>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>
<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <%-- 判断页面是否存在记录 --%>
    <c:if test="${account eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>