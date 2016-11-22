<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">新增微信主菜单</tmpl:override>

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
                        <option value="CLICK">关键字</option>
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
                        var _kwDiv = "<div class='layui-form-item layui-dynamic-js'>"
                                + "<label class='layui-form-label'>关键字</label>"
                                + "<div class='layui-input-block'>"
                                + "<input type='text' name='key' autocomplete='off' class='layui-input'>"
                                + "</div>"
                                + "</div>";
                        $(data.elem).parents(".layui-form-item").after(_kwDiv);
                        break;
                    case "VIEW":
                        var _urlDiv = "<div class='layui-form-item layui-dynamic-js'>"
                                + "<label class='layui-form-label'>url</label>"
                                + "<div class='layui-input-block'>"
                                + "<input type='text' name='url' autocomplete='off' class='layui-input'>"
                                + "</div>"
                                + "</div>";
                        $(data.elem).parents(".layui-form-item").after(_urlDiv);
                        break;
                    case "MEDIA_ID":
                        break;
                }
            });
        });
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>