<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">查看微信公众号</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/weixin-account.css" rel="stylesheet" type="text/css"/>
</tmpl:override>

<tmpl:override name="body">
    <div style="overflow:hidden;width:auto;box-sizing:border-box;background:#fff;border-left:1px solid #ccc;
	    border-bottom:1px solid #dedede;border-right:1px solid #dedede;padding:0 33px;">
        <span class="title">微信公众号</span>
        <table class="table" cellspacing="0">
            <tr>
                <td class="first-td">公众号名称</td>
                <td>${account.name}</td>
            </tr>
            <tr>
                <td class="first-td">公众号Token</td>
                <td>${account.token}</td>
            </tr>
            <tr>
                <td class="first-td">公众号微信号</td>
                <td>${account.number}</td>
            </tr>
            <tr>
                <td class="first-td">公众号微信号(原始ID)</td>
                <td>${account.accountId}</td>
            </tr>
            <tr>
                <td class="first-td">公众号appId</td>
                <td>${account.appId}</td>
            </tr>
            <tr>
                <td class="first-td">公众号appSecret</td>
                <td>${account.appSecret}</td>
            </tr>
            <tr>
                <td class="first-td">公众号email</td>
                <td>${account.email}</td>
            </tr>
            <tr>
                <td class="first-td">公众号描述</td>
                <td>${account.description}</td>
            </tr>
            <tr>
                <td class="first-td">公众号类型</td>
                <td>
                    <c:if test="${account.type ne null}">
                        ${account.type.name}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td class="first-td">创建日期</td>
                <td>${account.createdDate}</td>
            </tr>
        </table>
    </div>
    <button type="button" onclick="close1()">button</button>
</tmpl:override>
<tmpl:override name="page_script">
    <script>
        function close1() {
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
        }
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>