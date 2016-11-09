<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看用户</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${user.username}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="realname" autocomplete="off" class="layui-input"
                           value="${user.realname}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" autocomplete="off" class="layui-input"
                           value="${user.phone}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" name="email" autocomplete="off" class="layui-input"
                           value="${user.email}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="remark" autocomplete="off" class="layui-input"
                           value="${user.remark}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="status" lay-skin="switch" <c:if test="${user.status eq 'ENABLE'}">checked</c:if> disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">登录日期</label>
                <div class="layui-input-block">
                    <input type="text" name="loginDate" autocomplete="off" class="layui-input"
                           value="${user.loginDate}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上次登录日期</label>
                <div class="layui-input-block">
                    <input type="text" name="lastLoginDate" autocomplete="off" class="layui-input"
                           value="${user.lastLoginDate}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">更新日期</label>
                <div class="layui-input-block">
                    <input type="text" name="updatedDate" autocomplete="off" class="layui-input"
                           value="${user.updatedDate}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">创建日期</label>
                <div class="layui-input-block">
                    <input type="text" name="createdDate" autocomplete="off" class="layui-input"
                           value="${user.createdDate}" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">父用户</label>
                <div class="layui-input-block">
                    <input type="text" autocomplete="off" class="layui-input" value="<c:if test="${user.parent ne null}">${user.parent.username}</c:if>" disabled>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block">
                    <c:forEach var="role" items="${user.roleEntities}">
                        <div class="layui-unselect layui-form-checkbox layui-form-checked">
                            <span>${role.name}</span>
                            <i class="layui-icon"></i>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <%-- 判断页面是否存在记录 --%>
    <c:if test="${user eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>