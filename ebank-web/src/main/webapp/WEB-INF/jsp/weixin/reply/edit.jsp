<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">编辑回复</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/weixin/reply/edit/${reply.id}" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">事件类型</label>
                <div class="layui-input-block">
                    <select name="event" lay-verify="required" lay-filter="event">
                        <option value="">请选择</option>
                        <option value="DEFAULT" <c:if test="${reply.event eq 'DEFAULT'}">selected</c:if>>默认回复</option>
                        <option value="SUBSCRIBE" <c:if test="${reply.event eq 'SUBSCRIBE'}">selected</c:if>>关注时回复</option>
                        <option value="KEYWORD" <c:if test="${reply.event eq 'KEYWORD'}">selected</c:if>>关键词回复</option>
                        <option value="SCENE" <c:if test="${reply.event eq 'SCENE'}">selected</c:if>>场景回复</option>
                    </select>
                </div>
            </div>

            <c:if test="${reply.event eq 'KEYWORD'}">
                <div class="layui-form-item layui-dynamic-event-js">
                    <label class="layui-form-label red-star">关键字</label>
                    <div class="layui-input-block">
                        <input class="layui-input" lay-verify="required" type="text" name="keyword" value="${reply.keyword}">
                    </div>
                </div>
            </c:if>

            <c:if test="${reply.event eq 'SCENE'}">
                <div class="layui-form-item layui-dynamic-event-js">
                    <label class="layui-form-label red-star">场景</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="sceneId" value="${reply.scene.id}">
                        <input class="layui-input" lay-verify="required" type="text" name="sceneName"
                               value="${reply.scene.name}" disabled style="display:inline-block;width:70%;">
                        <button class="layui-btn" id="select-scene" type="button"
                                style="margin-bottom:4px;">选择场景
                        </button>
                    </div>
                </div>
            </c:if>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">回复类型</label>
                <div class="layui-input-block">
                    <select name="type" lay-verify="required" lay-filter="type">
                        <option value="">请选择</option>
                        <option value="TEXT" <c:if test="${reply.type eq 'TEXT'}">selected</c:if>>文本回复</option>
                        <option value="IMAGE" <c:if test="${reply.type eq 'IMAGE'}">selected</c:if>>图片回复</option>
                        <option value="NEWS" <c:if test="${reply.type eq 'NEWS'}">selected</c:if>>图文回复</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item layui-dynamic-type-js">
                <label class="layui-form-label red-star">回复内容</label>
                <div class="layui-input-block">
                    <c:if test="${template ne null}">
                        <input type="hidden" name="templateId" value="${template.id}">
                        <input class="layui-input" lay-verify="required" type="text" name="templateName"
                               value="${template.name}" disabled style="display:inline-block;width:68%;">
                    </c:if>
                    <button data-type="${reply.type}" class="layui-btn" id="select-message" type="button"
                            style="margin-bottom:4px;">选择回复内容
                    </button>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <c:if test="${reply eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
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
