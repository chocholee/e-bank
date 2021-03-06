<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">新增用户</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/user/add" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${user.username}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${user.password}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="realname" autocomplete="off" class="layui-input"
                           value="${user.realname}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" autocomplete="off" class="layui-input"
                           value="${user.phone}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" name="email" autocomplete="off" class="layui-input"
                           value="${user.email}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="remark" autocomplete="off" class="layui-input"
                           value="${user.remark}">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-parent-user" type="button">选择父用户</button>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <script type="text/javascript">
        $("#select-parent-user").on("click", function () {
            var _this = this;
            parent.layer.open({
                type: 2,
                title: "选择父用户",
                shadeClose: true,
                shade: [0.5],
                area: ['800px', '500px'],
                content: "${pageContext.request.contextPath}/user/parent/list",
                maxmin: false,
                btn: ["确定"],
                yes: function (index, cLayer) {
                    var user = { id: undefined, username: undefined };
                    var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                    var result = iframeWin.select(user);
                    var inputHidden = "<input type='hidden' name='parent.id' value='" + user.id + "'>";
                    var inputShow   = "<input type='text' class='layui-input' value='" + user.username + "' disabled>";
                    var div         = "<div class='layui-form-item'>"
                                    + "<label class='layui-form-label'>父用户</label>"
                                    + "<div class='layui-input-block'>"
                                    + inputHidden
                                    + inputShow
                                    + "</div>"
                                    + "</div>";
                    if ($("input[name='parent.id']").length != 0) {
                        $("input[name='parent.id']").parents(".layui-form-item").remove();
                    }
                    if (result) $(_this).parents(".layui-form-item").before(div);
                }
            });
        });
    </script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>