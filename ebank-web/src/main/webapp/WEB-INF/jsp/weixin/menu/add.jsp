<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">新增微信菜单</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/weixin/menu/add" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${menu.name}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">标识</label>
                <div class="layui-input-block">
                    <input type="text" name="key" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${menu.key}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">顺序</label>
                <div class="layui-input-block">
                    <input type="text" name="order" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${menu.order}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">菜单类型</label>
                <div class="layui-input-block">
                    <select name="type" lay-verify="required" lay-filter="type">
                        <option value="">请选择</option>
                        <option value="CLICK">消息触发</option>
                        <option value="VIEW">网页链接</option>
                        <option value="SCANCODE_PUSH">弹出扫码功能</option>
                        <option value="SCANCODE_WAITMSG">弹出扫码功能并返回信息</option>
                        <option value="PIC_SYSPHOTO">弹出系统拍照</option>
                        <option value="PIC_PHOTO_OR_ALBUM">弹出拍照或者相册发图</option>
                        <option value="PIC_WEIXIN">弹出微信相册发图器</option>
                        <option value="LOCATION_SELECT">弹出地理位置选择器</option>
                        <option value="MEDIA_ID">下发消息</option>
                        <option value="VIEW_LIMITED">跳转图文消息URL</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" id="select-parent-menu" type="button">选择父菜单</button>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <script type="text/javascript">
        layui.use('form', function(){
            var form = layui.form();
            form.on('select(type)', function(data){
                $(".layui-dynamic-js").remove();
                switch (data.value) {
                    case "CLICK":
                        var _msgTypeDiv = "<div class='layui-form-item layui-dynamic-js'>"
                                + "<label class='layui-form-label'>消息类型</label>"
                                + "<div class='layui-input-block'>"
                                + "<select name='msgType'>"
                                + "<option value=''>请选择</option>"
                                + "<option value='TEXT'>文本</option>"
                                + "<option value='NEWS'>图文</option>"
                                + "<option value='IMAGE'>图片</option>"
                                + "</select>"
                                + "</div>"
                                + "</div>";
                        var _msgDiv = "<div class='layui-form-item layui-dynamic-js'>"
                                + "<label class='layui-form-label'>消息模板</label>"
                                + "<div class='layui-input-block'>"
                                + "<button class='layui-btn' id='select-menu-msg' type='button'>选择消息模板</button>"
                                + "</div>"
                                + "</div>";
                        $(data.elem).parents(".layui-form-item").after(_msgDiv).after(_msgTypeDiv);
                        break;
                    case "VIEW":
                        var _urlDiv = "<div class='layui-form-item layui-dynamic-js'>"
                                + "<label class='layui-form-label red-star'>url</label>"
                                + "<div class='layui-input-block'>"
                                + "<input type='text' name='url' lay-verify='required' autocomplete='off' class='layui-input'>"
                                + "</div>"
                                + "</div>";
                        $(data.elem).parents(".layui-form-item").after(_urlDiv);
                        break;
                    case "MEDIA_ID":
                        break;
                }
                form.render("select");
            });
        });

        $("#select-menu-msg").on("click", function () {
            parent.layer.open({
                type: 2,
                title: "选择消息模板",
                shadeClose: true,
                shade: [0.5],
                area: ['800px', '500px'],
                content: "${pageContext.request.contextPath}/weixin/menu/parent/list",
                maxmin: false,
                btn: ["确定"],
                yes: function (index, cLayer) {
                    var _msg = {id: undefined, name: undefined};
                    var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                    var result = iframeWin.select(_msg);
//                    var inputHidden = "<input type='hidden' name='templateId' value='" + _msg.id + "'>";
//                    var inputShow = "<input type='text' class='layui-input' value='" + _msg.name + "' disabled>";
//                    var div = "<div class='layui-form-item'>"
//                            + "<label class='layui-form-label'>父菜单</label>"
//                            + "<div class='layui-input-block'>"
//                            + inputHidden
//                            + inputShow
//                            + "</div>"
//                            + "</div>";
//                    if ($("input[name='parent.id']").length != 0) {
//                        $("input[name='parent.id']").parents(".layui-form-item").remove();
//                    }
//                    if (result) {
//                        $(_this).parents(".layui-form-item").before(div);
//                    }
                }
            });
        });

        $("#select-parent-menu").on("click", function () {
            var _this = this;
            parent.layer.open({
                type: 2,
                title: "选择父菜单",
                shadeClose: true,
                shade: [0.5],
                area: ['800px', '500px'],
                content: "${pageContext.request.contextPath}/weixin/menu/parent/list",
                maxmin: false,
                btn: ["确定"],
                yes: function (index, cLayer) {
                    var _menu = {id: undefined, name: undefined};
                    var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                    var result = iframeWin.select(_menu);
                    var inputHidden = "<input type='hidden' name='parent' value='" + _menu.id + "'>";
                    var inputShow = "<input type='text' class='layui-input' value='" + _menu.name + "' disabled>";
                    var div = "<div class='layui-form-item'>"
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
                        $(_this).parents(".layui-form-item").before(div);
                    }
                }
            });
        });
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>