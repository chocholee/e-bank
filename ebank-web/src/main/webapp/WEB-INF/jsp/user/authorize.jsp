<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">用户授权</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <style>
        body { width: 100% !important; }
    </style>
</tmpl:override>

<tmpl:override name="body">
    <%-- 表格数据 --%>
    <div id="table">
        <table class="table" cellspacing="0">
            <tr>
                <th></th>
                <th>角色名称</th>
                <th>角色描述</th>
            </tr>
            <c:if test="${roles ne null}">
                <c:forEach var="role" items="${roles}">
                    <tr>
                        <td>
                            <c:set var="flag" value="false"/>
                            <c:forEach var="userRole" items="${user.roleEntities}">
                                <c:if test="${userRole.id eq role.id}">
                                    <c:set var="flag" value="true"/>
                                </c:if>
                            </c:forEach>
                            <input type="checkbox" value="${role.id}" <c:if test="${flag}">checked</c:if>>
                        </td>
                        <td>${role.name}</td>
                        <td>${role.description}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript">
        var authorize = function () {
            var roleIds = "";
            var index = parent.layer.getFrameIndex(window.name);
            var checkboxes = $("#table").find("input[type=checkbox]").filter(":checked");
            $.each(checkboxes, function (a) {
                roleIds += $(this).val();
                if (a + 1 < checkboxes.length) {
                    roleIds += ",";
                }
            });
            $.post("${pageContext.request.contextPath}/user/authorize/${user.id}", {roleIds: roleIds}, function (result) {
                parent.layer.alert(result.message, function (index) {
                    if (result.type == "SUCCESS") {
                        parent.layer.closeAll();
                    } else {
                        parent.layer.close(index);
                    }
                });
            });
        }
    </script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>