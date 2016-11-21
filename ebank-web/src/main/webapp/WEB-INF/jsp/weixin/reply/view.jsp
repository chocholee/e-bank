<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看回复</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form">
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
                <div class="layui-form-item">
                    <label class="layui-form-label red-star">关键字</label>
                    <div class="layui-input-block">
                        <input class="layui-input" lay-verify="required" type="text" name="keyword" value="${reply.keyword}">
                    </div>
                </div>
            </c:if>

            <c:if test="${reply.event eq 'SCENE'}">
                <div class="layui-form-item">
                    <label class="layui-form-label red-star">场景</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="sceneId" value="${reply.scene.id}">
                        <input class="layui-input" lay-verify="required" type="text" name="sceneName"
                               value="${reply.scene.name}" disabled style="display:inline-block;">
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

            <div class="layui-form-item">
                <label class="layui-form-label red-star">回复内容</label>
                <div class="layui-input-block">
                    <c:if test="${reply.type eq 'TEXT'}">
                        <button data-type="${reply.type}" class="layui-btn" type="button"
                                onclick="parent.CURD.view('${pageContext.request.contextPath}/weixin/template/text/view/${reply.templateId}', '查看回复内容', '675px', '375px')">查看回复内容
                        </button>
                    </c:if>
                    <c:if test="${reply.type eq 'IMAGE'}">
                        <button data-type="${reply.type}" class="layui-btn" type="button"
                                onclick="parent.CURD.view('${pageContext.request.contextPath}/weixin/template/image/view/${reply.templateId}', '查看回复内容', '360px', '345px')">查看回复内容
                        </button>
                    </c:if>
                    <c:if test="${reply.type eq 'NEWS'}">
                        <button data-type="${reply.type}" class="layui-btn" type="button"
                                onclick="parent.CURD.view('${pageContext.request.contextPath}/weixin/template/news/item/view/${reply.templateId}', '查看回复内容', '360px', '600px')">查看回复内容
                        </button>
                    </c:if>
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
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>
