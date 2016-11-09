<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tmpl:override name="title">选择父用户</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <style>
        body { width: 95% !important; }
    </style>
</tmpl:override>

<tmpl:override name="body">
    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left">
            <form id="form1" class="form-inline" role="form" action="${pageContext.request.contextPath}/user/parent/list">
                <div class="form-group">
                    <label>用户名称</label>
                    <input name="username" type="text" class="form-control" value="${user.username}">
                </div>
                <div class="form-group">
                    <a href="javascript:void(0);" onclick="_search(this)" class="button"><img
                            src="${pageContext.request.contextPath}/resources/images/btn_search_n.png" alt="查询"
                            height="18"
                            width="18">
                        <span>查询</span>
                    </a>
                </div>
                <div class="form-group">
                    <a href="javascript:void(0);" onclick="_reset(this)" class="button"><img
                            src="${pageContext.request.contextPath}/resources/images/btn_Reset_n.png" alt="重置"
                            height="18"
                            width="18">
                        <span>重置</span>
                    </a>
                </div>
            </form>
        </div>
    </div>
    <div class="clearfix"></div>

    <%-- 表格数据 --%>
    <div id="table">
        <table class="table" cellspacing="0">
            <tr>
                <th></th>
                <th>用户名</th>
                <th>真实姓名</th>
                <th>父用户</th>
                <th>状态</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="user" items="${pagination.data}">
                    <tr>
                        <td><input type="checkbox" value="${user.id}"></td>
                        <td>${user.username}</td>
                        <td>${user.realname}</td>
                        <td><c:if test="${user.parent ne null}">${user.parent.username}</c:if></td>
                        <td>${user.status.name}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>

    <%-- 引入分页 --%>
    <jsp:include page="../shared/pagination.jsp" flush="true">
        <jsp:param name="pagination" value="${pagination}"/>
        <jsp:param name="paginationURL"
                   value="${pageContext.request.contextPath}/user/parent/list?username=${user.username}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.nicescroll.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <script type="text/javascript">
        var select = function (user) {
            var index = parent.layer.getFrameIndex(window.name);
            var checkboxes = $("#table").find("input[type=checkbox]");
            var checkCount = 0;
            var checkbox = undefined;
            $.each(checkboxes, function () {
                var checked = $(this).is(":checked");
                if (checked == true) {
                    checkCount++;
                    checkbox = this;
                }
            });
            if (checkCount != 1) {
                parent.layer.alert("请择一条记录进行操作!")
                return false;
            } else {
                user.id = $(checkbox).val();
                user.username = $(checkbox).parents("td").next().text();
                parent.layer.close(index);
                return true;
            }
        }
    </script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>