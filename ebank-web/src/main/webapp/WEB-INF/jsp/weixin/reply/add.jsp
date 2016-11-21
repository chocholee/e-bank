<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">新增回复</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/weixin/reply/add" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">事件类型</label>
                <div class="layui-input-block">
                    <select name="event" lay-verify="required" lay-filter="event">
                        <option value="">请选择</option>
                        <option value="DEFAULT">默认回复</option>
                        <option value="SUBSCRIBE">关注时回复</option>
                        <option value="KEYWORD">关键词回复</option>
                        <option value="SCENE">场景回复</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">回复类型</label>
                <div class="layui-input-block">
                    <select name="type" lay-verify="required" lay-filter="type">
                        <option value="">请选择</option>
                        <option value="TEXT">文本回复</option>
                        <option value="IMAGE">图片回复</option>
                        <option value="NEWS">图文回复</option>
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
            // 事件类型处理
            form.on('select(event)', function(data){
                $(".layui-dynamic-event-js").remove();
                switch (data.value) {
                    case "KEYWORD":
                        var _kwDiv = "<div class='layui-form-item layui-dynamic-event-js'>"
                                + "<label class='layui-form-label red-star'>关键字</label>"
                                + "<div class='layui-input-block'>"
                                + "<input class='layui-input' lay-verify='required' type='text' name='keyword'>"
                                + "</div>"
                                + "</div>";
                        $(data.elem).parents(".layui-form-item").after(_kwDiv);
                        break;
                    case "SCENE":
                        var _sceneBtn = "<div class='layui-form-item layui-dynamic-event-js'>"
                                + "<label class='layui-form-label red-star'>场景</label>"
                                + "<div class='layui-input-block'>"
                                + "<button class='layui-btn' lay-verify='required' id='select-scene' type='button'"
                                + " style='margin-bottom:4px;'>选择场景</button>"
                                + "</div>"
                                + "</div>";
                        $(data.elem).parents(".layui-form-item").after(_sceneBtn);
                        break;
                };
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

            // 回复类型处理
            form.on('select(type)', function(data){
                $(".layui-dynamic-type-js").remove();
                var _messageBtn = "<div class='layui-form-item layui-dynamic-type-js'>"
                        + "<label class='layui-form-label red-star'>回复内容</label>"
                        + "<div class='layui-input-block'>"
                        + "<button data-type='" + data.value + "' class='layui-btn' lay-verify='required'"
                        + " id='select-message' type='button' style='margin-bottom:4px;'>选择回复内容</button>"
                        + "</div>"
                        + "</div>";
                $(data.elem).parents(".layui-form-item").after(_messageBtn);
            });

            // 回复内容事件处理
            $(document).on("click", "#select-message", function () {
                var _this = this;
                var type = $(_this).attr("data-type");
                var url;

                if (type == "TEXT")  url = "${pageContext.request.contextPath}/weixin/template/text/list/select";
                if (type == "NEWS")  url = "${pageContext.request.contextPath}/weixin/template/news/list/select";
                if (type == "IMAGE") url = "${pageContext.request.contextPath}/weixin/template/image/list/select";

                parent.layer.open({
                    type: 2,
                    title: "选择回复内容",
                    shadeClose: true,
                    shade: [0.5],
                    area: ['800px', '500px'],
                    content: url,
                    maxmin: false,
                    btn: ["确定"],
                    yes: function (index, cLayer) {
                        var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                        var ids = iframeWin.select();

                        if (ids.length != 1) {
                            parent.layer.alert("只能选择一条记录");
                            return false;
                        } else {
                            var _inputHidden = "<input type='hidden' name='templateId' value='" + ids[0].id + "'>";
                            var _inputShow = "<input class='layui-input' lay-verify='required' type='text' disabled"
                                    + " name='templateName' style='display:inline-block;width:68%;'"
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
