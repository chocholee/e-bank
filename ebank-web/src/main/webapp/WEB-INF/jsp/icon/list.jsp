<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<tmpl:override name="title">图标列表</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">图标列表</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <div class="button-group mr-20">
                <a href="javascript:_add('${pageContext.request.contextPath}/icon/add', '新增', '600px', '390px');" class="button">
                    <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                         alt="添加">
                    <span>添加</span>
                </a>
            </div>
        </div>
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/icon/list">
                <div class="form-group">
                    <label>名称</label>
                    <input name="name" type="text" class="form-control" value="${icon.name}">
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
                <th>名称</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="icon" items="${pagination.data}">
                    <tr>
                        <td>${icon.name}</td>
                        <td>${icon.description}</td>
                        <td class="last-td">
                            <a href="javascript:_view('${pageContext.request.contextPath}/icon/view/${icon.id}', '查看', '600px', '340px')" title="查看">
                                <img src="${pageContext.request.contextPath}/resources/images/eye.png">
                            </a>
                            <a href="javascript:_edit('${pageContext.request.contextPath}/icon/edit/${icon.id}', '编辑', '600px', '390px')" title="编辑">
                                <img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="编辑">
                            </a>
                            <a href="javascript:_delete('${pageContext.request.contextPath}/icon/delete/${icon.id}')" title="删除">
                                <img src="${pageContext.request.contextPath}/resources/images/btn_delete_n.png" alt="删除">
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
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/icon/list?name=${icon.name}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>