<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看二维码</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label red-star">名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${qrcode.name}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">场景</label>
                <div class="layui-input-block">
                    <input type="hidden" name="sceneId" value="${qrcode.scene.id}">
                    <input class="layui-input" lay-verify="required" type="text" name="sceneName"
                           value="${qrcode.scene.name}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">渠道</label>
                <div class="layui-input-block">
                    <input type="hidden" name="channelId" value="${qrcode.channel.id}">
                    <input class="layui-input" lay-verify="required" type="text" name="channelName"
                           value="${qrcode.channel.name}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">微信分组</label>
                <div class="layui-input-block">
                    <input type="hidden" name="groupWechatId" value="${qrcode.groupWechat.id}">
                    <input class="layui-input" lay-verify="required" type="text" name="groupWechatName"
                           value="${qrcode.groupWechat.name}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">虚拟分组</label>
                <div class="layui-input-block">
                    <c:choose>
                        <c:when test="${qrcode.groupVirtual ne null}">
                            <input type="hidden" name="groupVirtualId" value="${qrcode.groupVirtual.id}">
                            <input class="layui-input" lay-verify="required" type="text" name="groupVirtualName"
                                   value="${qrcode.groupVirtual.name}" disabled>
                        </c:when>
                        <c:otherwise>
                            <input class="layui-input" type="text" name="groupVirtualName"disabled>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">类型</label>
                <div class="layui-input-block">
                    <input type="hidden" name="type" value="${qrcode.type}">
                    <input type="text" class="layui-input" value="${qrcode.type.name}" disabled>
                </div>
            </div>

            <c:if test="${qrcode.type eq 'QR_SCENE'}">
                <div class="layui-form-item">
                    <label class="layui-form-label">到期时间</label>
                    <div class="layui-input-block">
                        <input class="layui-input" type="text" value="${qrcode.expireSeconds}" disabled>
                    </div>
                </div>
            </c:if>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <c:if test="${qrcode eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>
