<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">接收消息列表</tmpl:override>

<tmpl:override name="page_css">
</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">接收消息列表</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/weixin/receive/list">
                <div class="form-group">
                    <label>用户名称</label>
                    <input name="nickname" type="text" class="form-control" value="${receive.nickname}">
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
                <th>用户名称</th>
                <th>消息类型</th>
                <th>消息内容</th>
                <th>回复内容</th>
                <th>创建日期</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="receive" items="${pagination.data}">
                    <tr>
                        <td>${receive.nickname}</td>
                        <td><c:if test="${receive.msgType eq 'text'}">文本</c:if></td>
                        <td>${receive.content}</td>
                        <td>${receive.response}</td>
                        <td>${receive.createdDate}</td>
                        <td class="last-td">
                            <c:if test="${!receive.isResponse}">
                                <sec:authorize url="/weixin/receive/reply/">
                                    <a href="javascript:CURD.edit('${pageContext.request.contextPath}/weixin/receive/reply/${receive.id}', '回复消息', '600px', '295px')"
                                       title="回复消息">
                                        <img src="${pageContext.request.contextPath}/resources/images/edit.png"
                                             alt="回复消息">
                                    </a>
                                </sec:authorize>
                            </c:if>
                            <sec:authorize url="/weixin/receive/delete/">
                                <a href="javascript:CURD.delete('${pageContext.request.contextPath}/weixin/receive/delete/${receive.id}')"
                                   title="删除">
                                    <img src="${pageContext.request.contextPath}/resources/images/btn_delete_n.png"
                                         alt="删除">
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
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/weixin/receive/list?nickname=${receive.nickname}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <script type="text/javascript">
        // 初始化CURD
        CURD.init(window, window);
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>
