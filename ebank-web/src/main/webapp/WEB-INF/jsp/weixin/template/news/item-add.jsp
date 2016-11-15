<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">添加图文项</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/news-template.css" rel="stylesheet" type="text/css">
    <style>
        body {
            width: 90%;
        }
        .item-preview,
        .block {
            display: inline-block;
        }
        .block {
            width: 420px;
        }
        .layui-input-block{margin-left:60px !important;}
        .layui-form-label {width: 30px !important;}
    </style>
</tmpl:override>

<tmpl:override name="body">
    <div class="item-preview">
        <div class="item-title">
            <p>标题</p>
        </div>
        <div class="item">
            <i>封面图片</i>
            <div class="item-description">
                <p>描述</p>
            </div>
        </div>
    </div>
    <div class="block">
        <form class="layui-form" action="${pageContext.request.contextPath}/weixin/template/news/item/add/${item.newsTemplateId}"
              method="post">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${item.title}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">作者</label>
                <div class="layui-input-block">
                    <input type="text" name="author" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${item.author}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">图片</label>
                <div class="layui-input-block">
                    <div class="layui-box layui-upload-button layui-upload-file1" style="cursor: pointer;">
                        <span class="layui-upload-icon"><i class="layui-icon"></i>请上传图片</span>
                    </div>
                    <input type="text" name="picUrl" lay-verify="required" value="${item.picUrl}"
                           readonly style="border: 0;width: 200px;">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="description" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${item.description}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">url</label>
                <div class="layui-input-block">
                    <input type="text" name="url" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${item.url}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label red-star">顺序</label>
                <div class="layui-input-block">
                    <input type="text" name="order" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${item.order}">
                </div>
            </div>
        </form>
        <input type="file" name="file1" class="layui-upload-file" lay-method="post" style="display: none;">
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
                url: '${pageContext.request.contextPath}/weixin/template/news/item/upload',
                title: '请上传图片',
                unwrap: true,
                success: function (res, input) {
                    if (res[0].status) {
                        $(".layui-upload-file1").siblings("img").remove();
                        $("input[name=picUrl]").val(res[0].filename);
                        $(".item-preview > .item > i").remove();
                        $(".item-preview > .item > img").remove();
                        $(".item-preview > .item").append("<img src='${tempHost}/" + res[0].filename + "'/>")
                    } else {
                        parent.layer.msg(res[0].message);
                    }
                }
            });

            $(".layui-upload-file1").on("click", function () {
                $("input[name=file1]").trigger("click");
            });

            $("input[name=title]").on("change", function () {
                $(".item-preview > .item-title > p").text($(this).val());
            });

            $("input[name=description]").on("change", function () {
                $(".item-preview > .item > .item-description > p").text($(this).val());
            });
        });
    </script>
</tmpl:override>

<%@ include file="../../../shared/decorator.jsp" %>