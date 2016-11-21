<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">新增菜单</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/function/add" method="post">
            <input type="hidden" name="type" value="FIRST">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${function.name}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">路径</label>
                <div class="layui-input-block">
                    <input type="text" name="uri" autocomplete="off" class="layui-input"
                           value="${function.uri}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">图标</label>
                <div class="layui-input-block">
                    <select name="iconId">
                        <option value="">请选择</option>
                        <option value="">请选择</option>
                        <c:forEach var="icon" items="${icons}">
                            <option value="${icon.id}">${icon.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-block">
                    <input type="text" name="order" autocomplete="off" class="layui-input"
                           value="${function.order}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="description" autocomplete="off" class="layui-input"
                           value="${function.description}">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-parent-function" type="button">选择父菜单</button>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <script type="text/javascript">
        $("#select-parent-function").on("click", function () {
            var _this = this;
            parent.layer.open({
                type: 2,
                title: "选择父菜单",
                shadeClose: true,
                shade: [0.5],
                area: ['800px', '500px'],
                content: "${pageContext.request.contextPath}/function/parent/list",
                maxmin: false,
                btn: ["确定"],
                yes: function (index, cLayer) {
                    var _function = { id: undefined, name: undefined };
                    var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                    var result = iframeWin.select(_function);
                    var inputHidden = "<input type='hidden' name='parent.id' value='" + _function.id + "'>";
                    var inputShow   = "<input type='text' class='layui-input' value='" + _function.name + "' disabled>";
                    var div         = "<div class='layui-form-item'>"
                            + "<label class='layui-form-label'>父菜单</label>"
                            + "<div class='layui-input-block'>"
                            + inputHidden
                            + inputShow
                            + "</div>"
                            + "</div>";
                    if ($("input[name='parent.id']").length != 0) {
                        $("input[name='parent.id']").parents(".layui-form-item").remove();
                    }
                    if (result) {
                        switch (_function.type) {
                            case 'FIRST':
                                $("input[name='type']").val("SECOND");
                                break;
                            case 'SECOND':
                                $("input[name='type']").val("THIRD");
                                break;

                        }
                        $(_this).parents(".layui-form-item").before(div);
                    }
                }
            });
        });
    </script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>