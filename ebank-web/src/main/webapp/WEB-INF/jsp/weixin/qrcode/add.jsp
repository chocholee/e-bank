<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">新增二维码</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/weixin/qrcode/add" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${qrcode.name}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">场景</label>
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-scene" type="button" lay-verify="required"
                            style="margin-bottom:4px;margin-left:4px;">选择场景
                    </button>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">渠道</label>
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-channel" type="button" lay-verify="required"
                            style="margin-bottom:4px;margin-left:4px;">选择渠道
                    </button>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">微信分组</label>
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-group-wechat" type="button" lay-verify="required"
                            style="margin-bottom:4px;margin-left:4px;">选择微信分组
                    </button>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">虚拟分组</label>
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-group-virtual" type="button"
                            style="margin-bottom:4px;margin-left:4px;">选择虚拟分组
                    </button>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">类型</label>
                <div class="layui-input-block">
                    <select name="type" lay-verify="required" lay-filter="type">
                        <option value="">请选择</option>
                        <option value="QR_SCENE" <c:if test="${qrcode.type eq 'QR_SCENE'}">selected</c:if>>临时二维码
                        </option>
                        <option value="QR_LIMIT_STR_SCENE"
                                <c:if test="${qrcode.type eq 'QR_LIMIT_STR_SCENE'}">selected</c:if>>永久的字符串
                        </option>
                    </select>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <script type="text/javascript">
        layui.use('form', function() {
            var form = layui.form();
            // 类型处理
            form.on('select(type)', function (data) {
                $(".layui-dynamic-event-js").remove();
                switch (data.value) {
                    case "QR_SCENE":
                        var _eSDiv = "<div class='layui-form-item layui-dynamic-event-js'>"
                                + "<label class='layui-form-label red-star'>有效期(天)</label>"
                                + "<div class='layui-input-block'>"
                                + "<input class='layui-input' lay-verify='required' type='text' name='expireSeconds' value='1'>"
                                + "</div>"
                                + "</div>";
                        $(data.elem).parents(".layui-form-item").after(_eSDiv);
                        break;
                }
            });

            // 场景按钮事件处理
            $(document).on("click", "#select-scene", function () {
                var _this = this;
                parent.layer.open({
                    type: 2,
                    title: "选择场景",
                    shadeClose: true,
                    shade: [0.5],
                    area: ['800px', '500px'],
                    content: "${pageContext.request.contextPath}/weixin/scene/list/select",
                    maxmin: false,
                    btn: ["确定"],
                    yes: function (index, cLayer) {
                        var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                        var ids = iframeWin.select();

                        if (ids.length != 1) {
                            parent.layer.alert("只能选择一条记录");
                            return false;
                        } else {
                            var _inputHidden = "<input type='hidden' name='sceneId' value='" + ids[0].id + "'>";
                            var _inputShow = "<input class='layui-input' lay-verify='required' type='text' disabled"
                                    + " name='sceneName' style='display:inline-block;width:70%;'"
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

            // 渠道按钮事件处理
            $(document).on("click", "#select-channel", function () {
                var _this = this;
                parent.layer.open({
                    type: 2,
                    title: "选择渠道",
                    shadeClose: true,
                    shade: [0.5],
                    area: ['800px', '500px'],
                    content: "${pageContext.request.contextPath}/weixin/channel/list/select",
                    maxmin: false,
                    btn: ["确定"],
                    yes: function (index, cLayer) {
                        var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                        var ids = iframeWin.select();

                        if (ids.length != 1) {
                            parent.layer.alert("只能选择一条记录");
                            return false;
                        } else {
                            var _inputHidden = "<input type='hidden' name='channelId' value='" + ids[0].id + "'>";
                            var _inputShow = "<input class='layui-input' lay-verify='required' type='text' disabled"
                                    + " name='channelName' style='display:inline-block;width:70%;'"
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

                        if (ids.length != 1) {
                            parent.layer.alert("只能选择一条记录");
                            return false;
                        } else {
                            var _inputHidden = "<input type='hidden' name='groupWechatId' value='" + ids[0].id + "'>";
                            var _inputShow = "<input class='layui-input' lay-verify='required' type='text' disabled"
                                    + " name='groupWechatName' style='display:inline-block;width:62%;'"
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

            // 虚拟分组按钮事件处理
            $(document).on("click", "#select-group-virtual", function () {
                var _this = this;
                parent.layer.open({
                    type: 2,
                    title: "选择虚拟分组",
                    shadeClose: true,
                    shade: [0.5],
                    area: ['800px', '500px'],
                    content: "${pageContext.request.contextPath}/weixin/group/list/select/virtual",
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
                            var _inputHidden = "<input type='hidden' name='groupVirtualId' value='" + ids[0].id + "'>";
                            var _inputShow = "<input class='layui-input' lay-verify='required' type='text' disabled"
                                    + " name='groupVirtualName' style='display:inline-block;width:62%;'"
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
        });
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>
