<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/jsp-templ.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tmpl:override name="title">微信公众号</tmpl:override>

<tmpl:override name="page_css">
    <link href="${pageContext.request.contextPath}/resources/js/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .layui-form-switch { margin-top: 0; }
        .layui-form-onswitch:before { padding-right: 16px; }
    </style>
</tmpl:override>

<tmpl:override name="rightBox">
    <%-- 标题 --%>
    <span class="title">微信公众号</span>
    <div class="greyLine"></div>

    <%-- 条件查询及按钮操作区域 --%>
    <div class="button-wrapper">
        <div class="left button-group-wrapper">
            <sec:authorize url="/weixin/account/add">
                <div class="button-group">
                    <a href="javascript:_add('${pageContext.request.contextPath}/weixin/account/add', '新增', '600px', '600px');" class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_add_n.png" height="18" width="18"
                             alt="添加">
                        <span>添加</span>
                    </a>
                </div>
            </sec:authorize>
            <sec:authorize url="/weixin/account/token/">
                <div class="button-group">
                    <a href="javascript:token('${pageContext.request.contextPath}/weixin/account/token');"
                       class="button">
                        <img src="${pageContext.request.contextPath}/resources/images/btn_Reset_n.png" height="18"
                             width="18"
                             alt="重置微信Token">
                        <span>重置微信Token</span>
                    </a>
                </div>
            </sec:authorize>
        </div>
        <div class="right">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/weixin/account/list">
                <div class="form-group">
                    <label>名称</label>
                    <input name="name" type="text" class="form-control" value="${account.name}">
                </div>
                <div class="form-group">
                    <label>AppId</label>
                    <input name="appId" type="text" class="form-control" value="${account.appId}">
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
                <th>名称</th>
                    <%--<th>token</th>--%>
                <th>微信号</th>
                <th>原始ID</th>
                <%--<th>AppId</th>--%>
                <%--<th>AppSecret</th>--%>
                <th>Email</th>
                <th>描述</th>
                <th>类型</th>
                <th>授权状态</th>
                <th>关联用户</th>
                <th>创建日期</th>
                <th>操作</th>
            </tr>
            <c:if test="${pagination ne null && pagination.data ne null}">
                <c:forEach var="account" items="${pagination.data}">
                    <tr>
                        <td><input type="checkbox" value="${account.id}"></td>
                        <td>${account.name}</td>
                            <%--<td>${account.token}</td>--%>
                        <td>${account.number}</td>
                        <td>${account.accountId}</td>
                        <%--<td>${account.appId}</td>--%>
                        <%--<td>${account.appSecret}</td>--%>
                        <td>${account.email}</td>
                        <td>${account.description}</td>
                        <td>${account.type.name}</td>
                        <td>
                            <div class="layui-unselect layui-form-switch <c:if test="${account.status eq 'AUTHORIZED'}">layui-form-onswitch</c:if>">
                                <i></i>
                            </div>
                        </td>
                        <td>
                            <c:if test="${account.user ne null}">
                                ${account.user.username}
                            </c:if>
                        </td>
                        <td>${account.createdDate}</td>
                        <td class="last-td">
                            <sec:authorize url="/weixin/account/view/">
                                <a href="javascript:_view('${pageContext.request.contextPath}/weixin/account/view/${account.id}', '查看', '600px', '600px')" title="查看">
                                    <img src="${pageContext.request.contextPath}/resources/images/eye.png">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/account/edit/">
                                <a href="javascript:_edit('${pageContext.request.contextPath}/weixin/account/edit/${account.id}', '编辑', '600px', '600px')" title="编辑">
                                    <img src="${pageContext.request.contextPath}/resources/images/edit.png">
                                </a>
                            </sec:authorize>
                            <sec:authorize url="/weixin/account/delete/">
                                <a href="javascript:_delete('${pageContext.request.contextPath}/weixin/account/delete/${account.id}')" title="删除">
                                    <img src="${pageContext.request.contextPath}/resources/images/btn_delete_n.png">
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
        <jsp:param name="paginationURL" value="${pageContext.request.contextPath}/weixin/account/list?name=${account.name}&appId=${account.appId}"/>
    </jsp:include>
</tmpl:override>

<tmpl:override name="page_script">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dropkick-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
    <script>
        function token(url) {
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
                layer.alert("请择一条记录进行操作!")
                return false;
            } else {
                url = url + "/" + $(checkbox).val();
                $.get(url, function (result) {
                    layer.alert(result.message, function () {
                        window.location.reload();
                    });
                });
            }
        }
    </script>
</tmpl:override>

<%@ include file="../../shared/decorator.jsp" %>