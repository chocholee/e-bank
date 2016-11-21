<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">回复列表</tmpl:override>

<tmpl:override name="page_css">
    <style>
        .button-wrapper .dk_container { display: inline-block; line-height: 1; float: none; }
        .button-wrapper .dk_container ul { height: inherit; padding: 0px; }
    </style>
</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">回复列表</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <sec:authorize url="/weixin/reply/add">
                <div class="button-group mr-20">
                    <a href="javascript:CURD.add('${pageContext.request.contextPath}/weixin/reply/add', '新增', '600px', '400px');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                             alt="添加">
                        <span>添加</span>
                    </a>
                </div>
            </sec:authorize>
        </div>
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/weixin/reply/list">
                <div class="form-group">
                    <label>事件类型</label>
                    <select name="event">
                        <option value="ALL">请选择</option>
                        <option value="DEFAULT" <c:if test="${reply.event eq 'DEFAULT'}">selected</c:if>>默认回复</option>
                        <option value="SUBSCRIBE" <c:if test="${reply.event eq 'SUBSCRIBE'}">selected</c:if>>关注时回复</option>
                        <option value="KEYWORD" <c:if test="${reply.event eq 'KEYWORD'}">selected</c:if>>关键词回复</option>
                        <option value="SCENE" <c:if test="${reply.event eq 'SCENE'}">selected</c:if>>场景回复</option>
                    </select>
                </div>
                <div class="form-group">
                    <a href="javascript:void(0);" onclick="CURD.search(this)" class="button"><img
                            src="${pageContext.request.contextPath}/resources/images/btn_search_n.png" alt="查询"
                            height="18"
                            width="18">
                        <span>查询</span>
                    </a>
                </div>
                <div class="form-group">
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
                <th>事件类型</th>
                <th>回复类型</th>
                <th>关键字</th>
                <th>场景</th>
                <th>创建日期</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="reply" items="${pagination.data}">
                    <tr>
                        <td>${reply.event.name}</td>
                        <td>${reply.type.name}</td>
                        <td>${reply.keyword}</td>
                        <td><c:if test="${reply.scene ne null}">${reply.scene.name}</c:if></td>
                        <td>${reply.createdDate}</td>
                        <td class="last-td">
                            <sec:authorize url="/weixin/reply/view/">
                                <a href="javascript:CURD.view('${pageContext.request.contextPath}/weixin/reply/view/${reply.id}', '查看', '650px', '355px')" title="查看">
                                    <img src="${pageContext.request.contextPath}/resources/images/eye.png">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/reply/edit/">
                                <a href="javascript:CURD.edit('${pageContext.request.contextPath}/weixin/reply/edit/${reply.id}', '编辑', '600px', '400px')" title="编辑">
                                    <img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="编辑">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/reply/delete/">
                                <a href="javascript:CURD.delete('${pageContext.request.contextPath}/weixin/reply/delete/${reply.id}')" title="删除">
                                    <img src="${pageContext.request.contextPath}/resources/images/btn_delete_n.png" alt="删除">
                                </a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>

    <%-- 引入分页 --%>
    <jsp:include page="../../shared/pagination.jsp" flush="true">
        <jsp:param name="pagination" value="${pagination}"/>
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/weixin/reply/list?event=${reply.event}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <script type="text/javascript">
        // 初始化CURD
        CURD.init(window, window);
        $('.button-wrapper select').dropkick();
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>