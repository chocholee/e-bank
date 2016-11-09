<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">编辑图标</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/icon/edit/${icon.id}" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${icon.name}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">变化前图标</label>
                <div class="layui-input-block">
                    <div class="layui-box layui-upload-button layui-upload-file1" style="cursor: pointer;">
                        <span class="layui-upload-icon"><i class="layui-icon"></i>请上传图片</span>
                    </div>
                    <img height="34" src="${host}/${icon.beforeHoverPath}">
                    <input type="text" name="beforeHoverPath" lay-verify="required" value="${icon.beforeHoverPath}"
                           readonly style="border: 0;">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">变化后图标</label>
                <div class="layui-input-block">
                    <div class="layui-box layui-upload-button layui-upload-file2" style="cursor: pointer;">
                        <span class="layui-upload-icon"><i class="layui-icon"></i>请上传图片</span>
                    </div>
                    <img height="34" src="${host}/${icon.afterHoverPath}">
                    <input type="text" name="afterHoverPath" lay-verify="required" value="${icon.afterHoverPath}"
                           readonly style="border: 0;">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="description" autocomplete="off" class="layui-input"
                           value="${icon.description}">
                </div>
            </div>
        </form>
        <input type="file" name="file1" class="layui-upload-file" lay-method="post" style="display: none;">
        <input type="file" name="file2" class="layui-upload-file" lay-method="post" style="display: none;">
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <script>
        // 文件上传
        layui.use('upload', function () {
            layui.upload({
                elem: 'input[name=file1]',
                url: '${pageContext.request.contextPath}/icon/upload',
                title: '请上传图片',
                unwrap: true,
                success: function (res, input) {
                    if (res[0].status) {
                        $(".layui-upload-file1").siblings("img").remove();
                        $(".layui-upload-file1")
                                .after("<img height='34' src='${tempHost}/" + res[0].filename + "'>");
                        $("input[name=beforeHoverPath]").val(res[0].filename);
                    } else {
                        parent.layer.msg(res[0].message);
                    }
                }
            });
            layui.upload({
                elem: 'input[name=file2]',
                url: '${pageContext.request.contextPath}/icon/upload',
                title: '请上传图片',
                unwrap: true,
                success: function (res, input) {
                    if (res[0].status) {
                        $(".layui-upload-file2").siblings("img").remove();
                        $(".layui-upload-file2")
                                .after("<img height='34' src='${tempHost}/" + res[0].filename + "'>");
                        $("input[name=afterHoverPath]").val(res[0].filename);
                    } else {
                        parent.layer.msg(res[0].message);
                    }
                }
            });
           $(".layui-upload-file1").on("click", function () {
               $("input[name=file1]").trigger("click");
           });
            $(".layui-upload-file2").on("click", function () {
                $("input[name=file2]").trigger("click");
            });
        });
    </script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>