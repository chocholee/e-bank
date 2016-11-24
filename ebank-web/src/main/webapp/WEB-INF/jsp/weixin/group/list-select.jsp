<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<tmpl:override name="title">选择场景</tmpl:override>

<tmpl:override name="page_css">
    <style>
        body {background: inherit;margin: 0 auto;width: 95%;}
    </style>
</tmpl:override>

<tmpl:override name="body">
    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/weixin/group/list/select">
                <div class="form-group">
                    <label>名称</label>
                    <input name="name" type="text" class="form-control" value="${group.name}">
                </div>
                <div class="form-group">
                    <a href="javascript:void(0);" onclick="CURD.search(this)" class="button"><img
                            src="${pageContext.request.contextPath}/resources/images/btn_search_n.png" alt="查询"
                            height="18"
                            width="18">
                        <span>查询</span>
                    </a>
                </div>
                <div class="form-group mr-5">
                    <a href="javascript:void(0);" onclick="CURD.reset(this)" class="button"><img
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
                <th>名称</th>
                <th>备注</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="group" items="${pagination.data}">
                    <tr>
                        <td><input type="checkbox" name="id" value="${group.id}"></td>
                        <td>${group.name}</td>
                        <td>${group.remark}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>

    <%-- 引入分页 --%>
    <jsp:include page="../../shared/pagination.jsp" flush="true">
        <jsp:param name="pagination" value="${pagination}"/>
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/weixin/group/list/select?name=${group.name}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <script type="text/javascript">
        var select = function () {
            var checkedboxes = $("input[type='checkbox']").filter(":checked");
            var ids = [];
            $.each(checkedboxes, function () {
                var id = $(this).val();
                var name = $(this).parents("td").next().text();
                ids.push({id: id, name: name});
            });
            return ids;
        }
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>