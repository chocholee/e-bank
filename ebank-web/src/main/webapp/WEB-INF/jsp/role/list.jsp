<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<tmpl:override name="title">角色列表</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">角色列表</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <div class="button-group mr-20">
                <a href="javascript:_add('${pageContext.request.contextPath}/role/add', '新增', '600px', '340px');" class="button">
                    <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                         alt="添加">
                    <span>添加</span>
                </a>
            </div>
        </div>
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/role/list">
                <div class="form-group">
                    <label>角色名称</label>
                    <input name="name" type="text" class="form-control" value="${role.name}">
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
                <th>角色名称</th>
                <th>角色描述</th>
                <th>角色排序</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="role" items="${pagination.data}">
                    <tr>
                        <td>${role.name}</td>
                        <td>${role.description}</td>
                        <td>${role.order}</td>
                        <td class="last-td">
                            <a href="javascript:_edit('${pageContext.request.contextPath}/role/edit/${role.id}', '编辑', '600px', '340px')" title="编辑">
                                <img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="编辑">
                            </a>
                            <a href="javascript:_delete('${pageContext.request.contextPath}/role/delete/${role.id}')">
                                <img src="${pageContext.request.contextPath}/resources/images/btn_delete_n.png" alt="删除" title="删除">
                            </a>
                            <a href="javascript:authorize('${pageContext.request.contextPath}/role/authorize/${role.id}')">
                                <img src="${pageContext.request.contextPath}/resources/images/btn_user_n.png" alt="授权" title="授权">
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>

    <%-- 引入分页 --%>
    <jsp:include page="../shared/pagination.jsp" flush="true">
        <jsp:param name="pagination" value="${pagination}"/>
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/role/list?name=${role.name}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>