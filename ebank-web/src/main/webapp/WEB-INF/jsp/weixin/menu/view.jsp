<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看微信菜单</tmpl:override>

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
                           value="${menu.name}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">顺序</label>
                <div class="layui-input-block">
                    <input type="text" name="order" lay-verify="required" autocomplete="off" class="layui-input"
                           value="${menu.order}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label red-star">菜单类型</label>
                <div class="layui-input-block">
                    <select name="type" lay-verify="required" lay-filter="type" disabled>
                        <option value="">请选择</option>
                        <option value="CLICK" <c:if test="${menu.type eq 'CLICK'}">selected</c:if>>关键字</option>
                        <option value="VIEW" <c:if test="${menu.type eq 'VIEW'}">selected</c:if>>网页链接</option>
                        <option value="SCANCODE_PUSH" <c:if test="${menu.type eq 'SCANCODE_PUSH'}">selected</c:if>>
                            弹出扫码功能
                        </option>
                        <option value="SCANCODE_WAITMSG" <c:if test="${menu.type eq 'SCANCODE_WAITMSG'}">selected</c:if>>
                            弹出扫码功能并返回信息
                        </option>
                        <option value="PIC_SYSPHOTO" <c:if test="${menu.type eq 'PIC_SYSPHOTO'}">selected</c:if>>
                            弹出系统拍照
                        </option>
                        <option value="PIC_PHOTO_OR_ALBUM"
                                <c:if test="${menu.type eq 'PIC_PHOTO_OR_ALBUM'}">selected</c:if>>
                            弹出拍照或者相册发图
                        </option>
                        <option value="PIC_WEIXIN" <c:if test="${menu.type eq 'PIC_WEIXIN'}">selected</c:if>>弹出微信相册发图器
                        </option>
                        <option value="LOCATION_SELECT" <c:if test="${menu.type eq 'LOCATION_SELECT'}">selected</c:if>>
                            弹出地理位置选择器
                        </option>
                        <option value="MEDIA_ID" <c:if test="${menu.type eq 'MEDIA_ID'}">selected</c:if>>下发消息</option>
                        <option value="VIEW_LIMITED" <c:if test="${menu.type eq 'VIEW_LIMITED'}">selected</c:if>>
                            跳转图文消息URL
                        </option>
                    </select>
                </div>
            </div>

            <c:if test="${menu.type eq 'CLICK'}">
                <div class="layui-form-item">
                    <label class="layui-form-label">关键字</label>
                    <div class="layui-input-block">
                        <input type="text" name="key" autocomplete="true" class="layui-input"
                               value="${menu.key}" disabled>
                    </div>
                </div>
            </c:if>

            <c:if test="${menu.type eq 'VIEW'}">
                <div class="layui-form-item">
                    <label class="layui-form-label">url</label>
                    <div class="layui-input-block">
                        <input type="text" name="url" autocomplete="true" class="layui-input"
                               value="${menu.url}" disabled>
                    </div>
                </div>
            </c:if>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <c:if test="${menu eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>