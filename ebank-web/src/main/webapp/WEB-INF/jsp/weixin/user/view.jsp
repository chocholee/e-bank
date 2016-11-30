<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看微信用户</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
</tmpl:override>

<tmpl:override name="body">
    <div class="block">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-block">
                    <input type="text" name="nickname" autocomplete="off" class="layui-input"
                           value="${user.nickname}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" name="mobileNumber" autocomplete="off" class="layui-input"
                           value="${user.mobileNumber}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">OpenID</label>
                <div class="layui-input-block">
                    <input type="text" name="openId" autocomplete="off" class="layui-input"
                           value="${user.openId}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="type" title="未知" value="UNKNOWN" readonly
                           <c:if test="${user.sex eq 'UNKNOWN'}">checked</c:if>>
                    <input type="radio" name="type" title="男" value="MALE" readonly
                           <c:if test="${user.sex eq 'MALE'}">checked</c:if>>
                    <input type="radio" name="type" title="女" value="FEMALE" readonly
                           <c:if test="${user.sex eq 'FEMALE'}">checked</c:if>>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">国家</label>
                <div class="layui-input-block">
                    <input type="text" name="country" autocomplete="off" class="layui-input"
                           value="${user.country}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">省份</label>
                <div class="layui-input-block">
                    <input type="text" name="province" autocomplete="off" class="layui-input"
                           value="${user.province}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">城市</label>
                <div class="layui-input-block">
                    <input type="text" name="city" autocomplete="off" class="layui-input"
                           value="${user.city}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">语言</label>
                <div class="layui-input-block">
                    <input type="text" name="language" autocomplete="off" class="layui-input"
                           value="${user.language}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">头像</label>
                <div class="layui-input-block">
                    <img src="${user.headImgUrl}" alt="头像" width="70" height="70">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="remark" autocomplete="off" class="layui-input"
                           value="${user.remark}" disabled>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">微信分组</label>
                <div class="layui-input-block">
                    <c:if test="${user.groupWechat ne null}">
                        <input type="checkbox" name="groupWechat" checked title="${user.groupWechat.name}">
                    </c:if>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">虚拟分组</label>
                <div class="layui-input-block">
                    <c:if test="${user.groupVirtualEntities ne null}">
                        <c:forEach var="groupVirtual" items="${user.groupVirtualEntities}">
                            <input type="checkbox" name="groupVirtual" checked title="${groupVirtual.name}">
                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">所属渠道</label>
                <div class="layui-input-block">
                    <c:if test="${user.channel ne null}">
                        <input type="checkbox" name="channel" checked title="${user.channel.name}">
                    </c:if>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否关注</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="subscribeType" lay-skin="switch"
                           <c:if test="${user.subscribeType eq 'SUBSCRIBED'}">checked</c:if>>
                </div>
            </div>
        </form>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submit.js"></script>
    <%-- 判断页面是否存在记录 --%>
    <c:if test="${user eq null}">
        <script>
            parent.layer.alert('记录不存在', {title: "警告"}, function () {
                parent.layer.closeAll(); //再执行关闭所有层
            });
        </script>
    </c:if>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>