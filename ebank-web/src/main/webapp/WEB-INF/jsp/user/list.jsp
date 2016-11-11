<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">用户列表</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .layui-form-switch { margin-top: 0; }
        .layui-form-onswitch:before { padding-right: 16px; }
    </style>
</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">用户列表</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <sec:authorize url="/user/add">
                <div class="button-group mr-20">
                    <a href="javascript:_add('${pageContext.request.contextPath}/user/add', '新增', '600px', '550px');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                             alt="添加">
                        <span>添加</span>
                    </a>
                </div>
            </sec:authorize>
        </div>
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/user/list">
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
                <th>用户名</th>
                <th>真实姓名</th>
                <th>父用户</th>
                <th>创建日期</th>
                <th>更新日期</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="user" items="${pagination.data}">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.realname}</td>
                        <td><c:if test="${user.parent ne null}">${user.parent.username}</c:if></td>
                        <td>${user.createdDate}</td>
                        <td>${user.updatedDate}</td>
                        <td>
                            <div class="layui-unselect layui-form-switch <c:if test="${user.status eq 'ENABLE'}">layui-form-onswitch</c:if>"
                                 onclick="changeStatus(this, '${user.id}')">
                                <i></i>
                            </div>
                        </td>
                        <td class="last-td">
                            <sec:authorize url="/user/view/">
                                <a href="javascript:_view('${pageContext.request.contextPath}/user/view/${user.id}', '查看', '650px', '600px')" title="查看">
                                    <img src="${pageContext.request.contextPath}/resources/images/eye.png">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/user/edit/">
                                <a href="javascript:_edit('${pageContext.request.contextPath}/user/edit/${user.id}', '编辑', '600px', '500px')" title="编辑">
                                    <img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="编辑">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/user/delete/">
                                <a href="javascript:_delete('${pageContext.request.contextPath}/user/delete/${user.id}')">
                                    <img src="${pageContext.request.contextPath}/resources/images/btn_delete_n.png" alt="删除" title="删除">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/user/authorize/">
                                <a href="javascript:authorize('${pageContext.request.contextPath}/user/authorize/${user.id}')">
                                    <img src="${pageContext.request.contextPath}/resources/images/btn_user_n.png" alt="授权" title="授权">
                                </a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>

    <%-- 引入分页 --%>
    <jsp:include page="../shared/pagination.jsp" flush="true">
        <jsp:param name="pagination" value="${pagination}"/>
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/user/list?username=${user.username}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <script type="text/javascript">
        var changeStatus = function (_this, id) {
            $.get("${pageContext.request.contextPath}/user/lock/" + id, function (result) {
                layer.alert(result.message, function (index) {
                    if (result.type == "SUCCESS") {
                        $(_this).toggleClass("layui-form-onswitch");
                    }
                    layer.close(index);
                });
            });
        };
        var authorize = function (url) {
            parent.layer.open({
                type: 2,
                title: "用户授权",
                shadeClose: true,
                shade: [0.5],
                area: ['800px', '500px'],
                content: url,
                maxmin: false,
                btn: ["确定"],
                yes: function (index, cLayer) {
                    var iframeWin = parent.window[cLayer.find('iframe')[0]['name']];
                    iframeWin.authorize();
                }
            });
        }
    </script>
</tmpl:override>

<%@ include file="../shared/decorator.jsp" %>
