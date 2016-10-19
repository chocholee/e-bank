<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">编辑微信公众号</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/weixin-account.css" rel="stylesheet" type="text/css"/>
</tmpl:override>

<tmpl:override name="body">
    <div style="overflow:hidden;width:auto;box-sizing:border-box;background:#fff;border-left:1px solid #ccc;
	    border-bottom:1px solid #dedede;border-right:1px solid #dedede;padding:0 33px;">
        <span class="title">微信公众号</span>
        <form id="form1" action="/weixin/account/edit/${account.id}" method="post">
            <table class="table" cellspacing="0">
                <tr>
                    <td class="first-td">公众号名称</td>
                    <td>
                        <input class="form-control" type="text" name="name" value="${account.name}">
                    </td>
                </tr>
                <tr>
                    <td class="first-td">公众号Token</td>
                    <td>
                        <input class="form-control" type="text" name="token" value="${account.token}">
                    </td>
                </tr>
                <tr>
                    <td class="first-td">公众号微信号</td>
                    <td>
                        <input class="form-control" type="text" name="number" value="${account.number}">
                    </td>
                </tr>
                <tr>
                    <td class="first-td">公众号微信号(原始ID)</td>
                    <td>
                        <input class="form-control" type="text" name="accountId" value="${account.accountId}">
                    </td>
                </tr>
                <tr>
                    <td class="first-td">公众号appId</td>
                    <td>
                        <input class="form-control" type="text" name="appId" value="${account.appId}">
                    </td>
                </tr>
                <tr>
                    <td class="first-td">公众号appSecret</td>
                    <td>
                        <input class="form-control" type="text" name="appSecret" value="${account.appSecret}">
                    </td>
                </tr>
                <tr>
                    <td class="first-td">公众号email</td>
                    <td>
                        <input class="form-control" type="text" name="email" value="${account.email}">
                    </td>
                </tr>
                <tr>
                    <td class="first-td">公众号描述</td>
                    <td>
                        <input class="form-control" type="text" name="description" value="${account.description}">
                    </td>
                </tr>
                <tr>
                    <td class="first-td">公众号类型</td>
                    <td>
                        <c:if test="${account.type ne null}">
                            <select class="form-control" name="type" id="type">
                                <option value="">请选择</option>
                                <c:choose>
                                    <c:when test="${account.type eq 'SERVICE'}">
                                        <option value="SERVICE" selected>服务号</option>
                                        <option value="SUBSCRIPTION">订阅号</option>
                                        <option value="ENTERPRISE">企业号</option>
                                    </c:when>
                                    <c:when test="${account.type eq 'SUBSCRIPTION'}">
                                        <option value="SERVICE">服务号</option>
                                        <option value="SUBSCRIPTION" selected>订阅号</option>
                                        <option value="ENTERPRISE">企业号</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="SERVICE">服务号</option>
                                        <option value="SUBSCRIPTION">订阅号</option>
                                        <option value="ENTERPRISE" selected>企业号</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</tmpl:override>
<tmpl:override name="page_script">
    <c:choose>
        <c:when test="${account eq null}">
            <script>
                var parentIndex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.alert('记录不存在', {title: "警告"}, function () {
                    parent.layer.closeAll(); //再执行关闭所有层
                });
            </script>
        </c:when>
        <c:otherwise>
            <script>
                function tips(message) {
                    var parentIndex = parent.layer.getFrameIndex(window.name);
                    var parentWindow = parent.window;
                    parent.layer.msg(message, {
                        time: 800
                    }, function(){
                        parentWindow.location.reload();
                    });
                    parent.layer.close(parentIndex);
                }

                function edit() {
                    var form = $("#form1");
                    $.ajax({
                        url: form.attr("action"),
                        type: form.attr("method"),
                        data: form.serialize(),
                        success: function (result) {
                            if (result.type === "SUCCESS") {
                                tips(result.message);
                            } else {
                                if (result.data !== null && result.data !== undefined) {
                                    // TODO 显示错误信息
                                } else {
                                    tips(result.message);
                                }
                            }
                        }
                    });
                }
            </script>
        </c:otherwise>
    </c:choose>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>