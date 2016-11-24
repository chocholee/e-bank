<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">二维码列表</tmpl:override>

<tmpl:override name="page_css">
</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">二维码列表</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <sec:authorize url="/weixin/qrcode/add">
                <div class="button-group mr-20">
                    <a href="javascript:CURD.add('${pageContext.request.contextPath}/weixin/qrcode/add', '新增', '600px', '550px');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                             alt="添加">
                        <span>添加</span>
                    </a>
                </div>
            </sec:authorize>
        </div>
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/weixin/qrcode/list">
                <div class="form-group">
                    <label>名称</label>
                    <input name="name" type="text" class="form-control" value="${qrcode.name}">
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
                <th>名称</th>
                <th>类型</th>
                <th>场景</th>
                <th>渠道</th>
                <th>二维码</th>
                <th>唯一key</th>
                <th>到期时间</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="qrcode" items="${pagination.data}">
                    <tr>
                        <td>${qrcode.name}</td>
                        <td>${qrcode.type.name}</td>
                        <td>${qrcode.scene.name}</td>
                        <td><c:if test="${qrcode.channel ne null}">${qrcode.channel.name}</c:if></td>
                        <td>
                            <sec:authorize url="/weixin/qrcode/show/">
                                <a href="javascript:CURD.view('${pageContext.request.contextPath}/weixin/qrcode/show/${qrcode.id}', '`${qrcode.name}`的二维码', '360px', '410px');"
                                   title="`${qrcode.name}`的二维码">
                                    <img src="${pageContext.request.contextPath}/resources/images/eye.png">
                                </a>
                            </sec:authorize>
                        </td>
                        <td>${qrcode.key}</td>
                        <td>
                            <c:choose>
                                <c:when test="${qrcode.type eq 'QR_SCENE'}">
                                    ${qrcode.expireSeconds}
                                </c:when>
                                <c:when test="${qrcode.type eq 'QR_LIMIT_STR_SCENE'}">
                                    永久
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="last-td">
                            <sec:authorize url="/weixin/qrcode/view/">
                                <a href="javascript:CURD.view('${pageContext.request.contextPath}/weixin/qrcode/view/${qrcode.id}', '查看', '650px', '550px')" title="查看">
                                    <img src="${pageContext.request.contextPath}/resources/images/eye.png">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/qrcode/edit/">
                                <a href="javascript:CURD.edit('${pageContext.request.contextPath}/weixin/qrcode/edit/${qrcode.id}', '编辑', '600px', '550px')" title="编辑">
                                    <img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="编辑">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/qrcode/delete/">
                                <a href="javascript:CURD.delete('${pageContext.request.contextPath}/weixin/qrcode/delete/${qrcode.id}')" title="删除">
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
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/weixin/qrcode/list?name=${qrcode.name}"/>
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