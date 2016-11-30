<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看微信用户</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/weixin/user/group/wechat/${user.id}" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-block">
                    <input type="text" name="nickname" autocomplete="off" class="layui-input"
                           value="${user.nickname}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">原微信分组</label>
                <div class="layui-input-block">
                    <c:if test="${user.groupWechat ne null}">
                        <input type="checkbox" name="groupWechat" checked title="${user.groupWechat.name}">
                    </c:if>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">新微信分组</label>
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-group-wechat" type="button" lay-verify="required"
                            style="margin-bottom:4px;">选择微信分组
                    </button>
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
    <script type="text/javascript">
        // 微信分组按钮事件处理
        $(document).on("click", "#select-group-wechat", function () {
            var _this = this;
            parent.layer.open({
                type: 2,
                title: "选择微信分组",
                shadeClose: true,
                shade: [0.5],
                area: ['800px', '500px'],
                content: "${pageContext.request.contextPath}/weixin/group/list/select/wechat",
                maxmin: false,
                btn: ["确定"],
                yes: function (index, cLayer) {
                    var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                    var ids = iframeWin.select();

                    if (ids.length > 1) {
                        parent.layer.alert("只能选择一条记录");
                        return false;
                    } else if (ids.length == 0) {
                        $(_this).prevAll().remove();
                        parent.layer.close(index);
                    } else {
                        var _inputHidden = "<input type='hidden' name='newGroupWechatId' value='" + ids[0].id + "'>";
                        var _inputShow = "<input class='layui-input' lay-verify='required' type='text' disabled"
                                + " name='newGroupWechatName' style='display:inline-block;width:62%;margin-right:4px;'"
                                + " value='" + ids[0].name + "'>";

                        $(_this).prevAll().remove();
                        $(_this).before(_inputHidden);
                        $(_this).before(_inputShow);
                        $(_this).removeAttr("lay-verify");

                        parent.layer.close(index);
                    }
                }
            });
        });
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>